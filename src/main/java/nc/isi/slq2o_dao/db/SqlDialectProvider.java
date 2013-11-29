package nc.isi.slq2o_dao.db;

import org.jooq.SQLDialect;

public interface SqlDialectProvider {

	SQLDialect provide(String key);

}
