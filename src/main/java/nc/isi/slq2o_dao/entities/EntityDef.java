package nc.isi.slq2o_dao.entities;

import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Table;

public interface EntityDef<E extends Entity> {

	<T> Field<T> getField(String property);

	Table<Record> getTable();

	Class<E> getEntityClass();

	Field<Object> getIdField();

}
