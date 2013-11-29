package nc.isi.slq2o_dao.entities;

import static org.jooq.impl.DSL.fieldByName;
import static org.jooq.impl.DSL.tableByName;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;

import nc.isi.fragaria_reflection.utils.ObjectMetadata;
import nc.isi.slq2o_dao.db.DatabaseDef;

import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Table;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

public class DefaultEntityDef<E extends Entity> implements EntityDef<E> {

	private final ObjectMetadata objectMetadata;
	private final Map<String, Field<Object>> propertyToFields;
	private final Class<E> entityClass;
	private final String tableName;
	private final Table<Record> table;
	private final Field<Object> idField;
	private final DatabaseDef databaseDef;

	public DefaultEntityDef(ObjectMetadata objectMetadata, String tableName,
			Class<E> entityClass, Map<String, Field<Object>> propertyToFields,
			DatabaseDef databaseDef) {
		this.objectMetadata = objectMetadata;
		this.tableName = tableName;
		this.propertyToFields = propertyToFields;
		this.entityClass = entityClass;
		this.table = tableByName(tableName);
		this.idField = propertyToFields.get("id");
		this.databaseDef = databaseDef;
	}

	protected Map<String, Field<Object>> buildPropertyToFields(String tName) {
		Map<String, Field<Object>> results = Maps.newHashMap();
		for (String s : getFieldNames()) {
			results.put(s, fieldByName(tName, s.toUpperCase()));
		}
		return results;
	}

	@Override
	public Collection<String> getFieldNames() {
		return propertyToFields.keySet();
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
	public Field<Object> getIdField() {
		return idField;
	}

	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public DatabaseDef getDatabaseDef() {
		return databaseDef;
	}

	@Override
	public ImmutableSet<String> propertyNames() {
		return objectMetadata.propertyNames();
	}

	@Override
	public <T extends Annotation> T getPropertyAnnotation(String propertyName,
			Class<T> annotation) {
		return objectMetadata.getPropertyAnnotation(propertyName, annotation);
	}

	@Override
	public Class<E> getTypeClass() {
		return entityClass;
	}

	@Override
	public Class<?> propertyType(String propertyName) {
		return objectMetadata.propertyType(propertyName);
	}

	@Override
	public PropertyDescriptor getPropertyDescriptor(String propertyName) {
		return objectMetadata.getPropertyDescriptor(propertyName);
	}

	@Override
	public Class<?>[] propertyParameterClasses(String propertyName) {
		return objectMetadata.propertyParameterClasses(propertyName);
	}

	@Override
	public Object read(Object object, String propertyName) {
		return objectMetadata.read(object, propertyName);
	}

	@Override
	public Boolean canWrite(String propertyName) {
		return objectMetadata.canWrite(propertyName);
	}

	@Override
	public void write(Object object, String propertyName, Object value) {
		objectMetadata.write(object, propertyName, value);
	}

}
