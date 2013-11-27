package nc.isi.slq2o_dao.entities;

import static org.jooq.impl.DSL.fieldByName;
import static org.jooq.impl.DSL.tableByName;

import java.util.Map;

import nc.isi.fragaria_reflection.utils.ObjectMetadata;
import nc.isi.slq2o_dao.utils.DBUtils;

import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Table;

import com.google.common.collect.Maps;

public class DefaultEntityDef<E extends Entity> implements EntityDef<E> {

	protected static Map<String, Field<Object>> buildPropertyToFields(
			Class<? extends Entity> entityClass, ObjectMetadata objectMetadata,
			String tName) {
		Map<String, Field<Object>> results = Maps.newHashMap();
		for (String s : DBUtils.getInsertablePropertyNames(entityClass,
				objectMetadata)) {
			results.put(s, fieldByName(tName, s.toUpperCase()));
		}
		return results;
	}

	private final Map<String, Field<Object>> propertyToFields;
	private final Class<E> entityClass;
	private final Table<Record> table;

	public static <E extends Entity> DefaultEntityDef<E> build(
			Class<E> entityClass) {
		return new DefaultEntityDef<>(entityClass, buildPropertyToFields(
				entityClass, new ObjectMetadata(entityClass),
				DBUtils.convertClassToTableName(entityClass)));
	}

	private DefaultEntityDef(Class<E> entityClass,
			Map<String, Field<Object>> propertyToFields) {
		this.propertyToFields = propertyToFields;
		this.entityClass = entityClass;
		this.table = tableByName(DBUtils.convertClassToTableName(entityClass));
	}

	@Override
	public Field<Object> getField(String property) {
		return propertyToFields.get(property);
	}

	@Override
	public Table<Record> getTable() {
		return table;
	}

	@Override
	public Class<E> getEntityClass() {
		return entityClass;
	}

}
