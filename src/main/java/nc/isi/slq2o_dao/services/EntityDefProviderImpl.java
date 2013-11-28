package nc.isi.slq2o_dao.services;

import static org.jooq.impl.DSL.fieldByName;
import static org.jooq.impl.DSL.tableByName;

import java.util.Map;

import nc.isi.fragaria_reflection.services.ObjectMetadataProvider;
import nc.isi.fragaria_reflection.utils.ObjectMetadata;
import nc.isi.slq2o_dao.entities.DefaultEntityDef;
import nc.isi.slq2o_dao.entities.Entity;
import nc.isi.slq2o_dao.entities.EntityDef;
import nc.isi.slq2o_dao.utils.DBUtils;

import org.jooq.Field;

import com.google.common.collect.Maps;

public class EntityDefProviderImpl implements EntityDefProvider {

	@SuppressWarnings("rawtypes")
	private final Map<Class, EntityDef> entityDefByClass;
	private final ObjectMetadataProvider objectMetadataProvider;
	private final DBUtils dbUtils;

	@SuppressWarnings("rawtypes")
	public EntityDefProviderImpl(Map<Class, EntityDef> entityDefByClass,
			ObjectMetadataProvider objectMetadataProvider, DBUtils dbUtils) {
		this.entityDefByClass = entityDefByClass;
		this.objectMetadataProvider = objectMetadataProvider;
		this.dbUtils = dbUtils;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Entity> EntityDef<T> provide(Class<T> entityClass) {
		EntityDef<T> entityDef = entityDefByClass.get(entityClass);
		if (entityDef == null) {
			String tName = dbUtils.convertClassToTableName(entityClass);
			entityDef = new DefaultEntityDef<>(entityClass, tableByName(tName),
					buildPropertyToFields(entityClass,
							objectMetadataProvider.provide(entityClass), tName));
			entityDefByClass.put(entityClass, entityDef);
		}
		return entityDef;
	}

	protected Map<String, Field<Object>> buildPropertyToFields(
			Class<? extends Entity> entityClass, ObjectMetadata objectMetadata,
			String tName) {
		Map<String, Field<Object>> results = Maps.newHashMap();
		for (String s : dbUtils.getInsertablePropertyNames(entityClass,
				objectMetadata)) {
			results.put(s, fieldByName(tName, s.toUpperCase()));
		}
		return results;
	}

}
