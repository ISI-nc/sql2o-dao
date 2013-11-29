package nc.isi.slq2o_dao.services;

import java.util.Map;

import nc.isi.slq2o_dao.db.SqlDialectProvider;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import com.google.common.collect.Maps;

public class SqlDSLProviderImpl implements SqlDSLProvider {
	private final Map<String, DSLContext> dataSourceToDSL = Maps.newHashMap();
	private final SqlDialectProvider sqlDialectProvider;

	public SqlDSLProviderImpl(SqlDialectProvider sqlDialectProvider) {
		this.sqlDialectProvider = sqlDialectProvider;
	}

	@Override
	public DSLContext provide(String key) {
		DSLContext dslContext = dataSourceToDSL.get(key);
		if (dslContext == null) {
			dslContext = DSL.using(sqlDialectProvider.provide(key));
			dataSourceToDSL.put(key, dslContext);
		}
		return dslContext;
	}

}
