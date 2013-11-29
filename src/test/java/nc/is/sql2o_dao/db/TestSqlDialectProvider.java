package nc.is.sql2o_dao.db;

import static junit.framework.Assert.assertTrue;
import nc.is.sql2o_dao.services.QaModule;
import nc.is.sql2o_dao.services.QaRegistry;
import nc.isi.slq2o_dao.db.SqlDialectProvider;

import org.jooq.SQLDialect;
import org.junit.Test;

public class TestSqlDialectProvider {
	private final SqlDialectProvider sqlDialectProvider = QaRegistry.INSTANCE
			.getService(SqlDialectProvider.class);

	@Test
	public void shouldReturnPOSTGRESforDS() {
		assertTrue(SQLDialect.POSTGRES == sqlDialectProvider
				.provide(QaModule.DS_KEY));
	}

}
