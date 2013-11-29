package nc.isi.slq2o_dao.entities;


public interface EntityDefProvider {

	<T extends Entity> EntityDef<T> provide(Class<T> entityClass);

}
