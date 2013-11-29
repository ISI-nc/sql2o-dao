package nc.isi.slq2o_dao.db;

import javax.sql.DataSource;

public interface DataSourceBuilder {

	DataSource buildJNDI(String lookup);

}
