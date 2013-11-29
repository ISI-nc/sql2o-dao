package nc.isi.slq2o_dao.db;

import java.util.Map;

import org.jooq.SQLDialect;

public class SqlDialectProviderImpl implements SqlDialectProvider {
	private final Map<String, SQLDialect> keyToSqlDialect;

	public SqlDialectProviderImpl(Map<String, SQLDialect> keyToSqlDialect) {
		this.keyToSqlDialect = keyToSqlDialect;
	}

	@Override
	public SQLDialect provide(String key) {
		return keyToSqlDialect.get(key);
	}

}
