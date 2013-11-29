package nc.isi.slq2o_dao.db;

import javax.sql.DataSource;

public interface EntityToTableConverterProvider {

	EntityToTableConverter provide(DataSource dataSource);

}