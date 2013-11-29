package nc.isi.slq2o_dao.db;

import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.google.common.base.Throwables;
import com.google.common.collect.Maps;

public class DataSourceBuilderImpl implements DataSourceBuilder {

	public static final String JAVA_COMP_ENV = "java:comp/env";
	private final Context envCtx;

	public DataSourceBuilderImpl() {
		try {
			Context initCtx = new InitialContext();
			this.envCtx = (Context) initCtx.lookup(JAVA_COMP_ENV);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

	private final Map<String, DataSource> lookupToDataSource = Maps
			.newConcurrentMap();

	@Override
	public DataSource buildJNDI(String lookup) {
		DataSource dataSource = lookupToDataSource.get(lookup);
		if (dataSource == null) {
			// Look up our data source
			try {
				dataSource = (DataSource) envCtx.lookup(lookup);
				lookupToDataSource.put(lookup, dataSource);
			} catch (NamingException e) {
				throw Throwables.propagate(e);
			}
		}
		return dataSource;
	}

}
