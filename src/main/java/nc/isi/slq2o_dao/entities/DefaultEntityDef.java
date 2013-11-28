package nc.isi.slq2o_dao.entities;

import java.util.Map;

import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Table;

public class DefaultEntityDef<E extends Entity> implements EntityDef<E> {

	private final Map<String, Field<Object>> propertyToFields;
	private final Class<E> entityClass;
	private final Table<Record> table;
	private final Field<Object> idField;

	public DefaultEntityDef(Class<E> entityClass, Table<Record> table,
			Map<String, Field<Object>> propertyToFields) {
		this.propertyToFields = propertyToFields;
		this.entityClass = entityClass;
		this.table = table;
		this.idField = propertyToFields.get("id");
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

	@Override
	public Field<Object> getIdField() {
		return idField;
	}

}
