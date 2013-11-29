package nc.isi.slq2o_dao.db;


public interface EntityToTableConverterProvider {

	EntityToTableConverter provide(String key);

}