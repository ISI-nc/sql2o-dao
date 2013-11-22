package nc.isi.slq2o_dao.services;


import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

public class SqlDSLProviderImpl implements SqlDSLProvider {
	private final DSLContext defaultDslContext = DSL.using(SQLDialect.POSTGRES);

	@Override
	public DSLContext provide() {
		return defaultDslContext;
	}

}
