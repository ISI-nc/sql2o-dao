package nc.isi.slq2o_dao.daos;

import static org.jooq.impl.DSL.param;
import static org.jooq.impl.DSL.selectFrom;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLDataException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import nc.isi.fragaria_reflection.services.ObjectMetadataProvider;
import nc.isi.fragaria_reflection.utils.ObjectMetadata;
import nc.isi.slq2o_dao.entities.Entity;
import nc.isi.slq2o_dao.entities.EntityDef;
import nc.isi.slq2o_dao.services.EntityDefProvider;
import nc.isi.slq2o_dao.services.Sql2oDbProvider;
import nc.isi.slq2o_dao.services.SqlDSLProvider;
import nc.isi.slq2o_dao.utils.DBUtils;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Param;
import org.jooq.Record;
import org.jooq.UpdateSetFirstStep;
import org.jooq.UpdateSetMoreStep;
import org.jooq.conf.ParamType;
import org.slf4j.Logger;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class DefaultDao<T extends Entity> implements Dao<T> {

	public class Parameters {
		private Parameters() {

		}

		public static final String ID = "id";
	}

	protected final DBUtils dbUtils;
	protected final Sql2o sql2o;
	protected final Logger logger;
	protected final Class<T> tClass;
	protected final EntityDef<T> entityDef;
	protected final DSLContext sqlDsl;
	protected final ObjectMetadata objectMetadata;

	public DefaultDao(DBUtils dbUtils, Sql2oDbProvider sql2oDbProvider,
			SqlDSLProvider sqlDSLProvider, EntityDefProvider entityDefProvider,
			ObjectMetadataProvider objectMetadataProvider, Logger logger,
			Class<T> tClass) {
		this.entityDef = entityDefProvider.provide(tClass);
		this.dbUtils = dbUtils;
		this.sql2o = sql2oDbProvider.provide();
		this.sqlDsl = sqlDSLProvider.provide();

		this.logger = logger;
		this.tClass = tClass;
		this.objectMetadata = objectMetadataProvider.provide(tClass);
	}

	@Override
	public Collection<T> getAll() {
		return sql2o.createQuery(selectFrom(entityDef.getTable()).getSQL())
				.executeAndFetch(tClass);
	}

	@Override
	public T get(String id) {
		return getUnique(sql2o.createQuery(
				sqlDsl.renderNamedParams(selectFrom(entityDef.getTable())
						.where(entityDef.getIdField().eq(
								param(Parameters.ID, String.class)))))
				.addParameter(Parameters.ID, id));
	}

	protected T getUnique(Query query) {
		List<T> elements = query.executeAndFetch(tClass);
		if (elements.size() > 1) {
			throw new RuntimeException(new SQLDataException(
					String.format("Plusieurs %s correspondent à l'id",
							tClass.getSimpleName())));
		}
		if (elements.size() == 0) {
			return null;
		}
		return elements.get(0);
	}

	@Override
	public void insert(T entity) {
		List<Field<?>> fields = Lists.newArrayList();
		List<Param<?>> params = Lists.newArrayList();
		for (String property : dbUtils.getInsertablePropertyNames(tClass,
				objectMetadata)) {
			Object value = objectMetadata.read(entity, property);
			if (value == null) {
				continue;
			}
			fields.add(entityDef.getField(property));
			params.add(param(property, String.class));
		}
		Query query = sql2o.createQuery(
				sqlDsl.insertInto(entityDef.getTable(), fields).values(params)
						.getSQL(ParamType.NAMED), false);
		for (Param<?> param : params) {
			try {
				query.addParameter(param.getName(), objectMetadata
						.getPropertyDescriptor(param.getName()).getReadMethod()
						.invoke(entity));
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				throw new RuntimeException(e);
			}

		}
		query.executeUpdate();
	}

	public void update(T entity) {
		T oldEntity = get(entity.getId());
		if (oldEntity == null) {
			throw new RuntimeException(
					"impossible d'updater un objet qui n'exite pas : "
							+ entity.getId());
		}

		UpdateSetFirstStep<Record> update = sqlDsl.update(entityDef.getTable());
		Map<String, Object> parameters = Maps.newHashMap();
		UpdateSetMoreStep<Record> updateSet = null;
		for (String s : dbUtils.getInsertablePropertyNames(tClass,
				objectMetadata)) {
			Object value = objectMetadata.read(entity, s);
			Object oldValue = objectMetadata.read(oldEntity, s);
			if (!Objects.equal(value, oldValue)) {
				if (updateSet == null) {
					updateSet = update.set(entityDef.getField(s),
							param(s.toUpperCase(), String.class));
				} else {
					updateSet.set(entityDef.getField(s),
							param(s.toUpperCase(), String.class));
				}
				parameters.put(s.toUpperCase(), value);
			}
		}
		updateSet.where(entityDef.getIdField().eq(
				param(Parameters.ID, String.class)));
		Query query = sql2o.createQuery(updateSet.getSQL(ParamType.NAMED),
				false);
		for (String parameter : parameters.keySet()) {
			query.addParameter(parameter, parameters.get(parameter));
		}
		query.addParameter(Parameters.ID, entity.getId()).executeUpdate();
	}

	protected boolean exist(T entity) {
		return get(entity.getId()) != null;
	}
}
