package nc.isi.slq2o_dao.entities;

import java.util.Collection;

import nc.isi.fragaria_reflection.utils.ObjectMetadata;
import nc.isi.slq2o_dao.db.DatabaseDef;

import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Table;

public interface EntityDef<E extends Entity> extends ObjectMetadata {

	<T> Field<T> getField(String property);

	Table<Record> getTable();

	Class<E> getTypeClass();

	Field<Object> getIdField();

	/**
	 * 
	 * @param tClass
	 *            : une classe étendant {@link Entity}
	 * @return les propriétés écrivables en base
	 */
	Collection<String> getFieldNames();

	String getTableName();

	DatabaseDef getDatabaseDef();

}
