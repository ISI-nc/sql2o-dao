package nc.isi.slq2o_dao.services;


import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.sql2o.Sql2o;
import org.sql2o.converters.Convert;
import org.sql2o.converters.Converter;

/**
 * Implémentation de {@link Sql2oDbProvider} se connectant à une base Oracle
 * 
 * @author jmaltat
 * 
 */
public class Sql2oDbProviderImpl implements Sql2oDbProvider {

	private final DataSource dataSource;

	private Sql2o sql2o;

	public Sql2oDbProviderImpl(DataSource dataSource,
			Map<Class, Converter> converters) throws SQLException {
		this.dataSource = dataSource;
		for (Entry<Class, Converter> converter : converters.entrySet()) {
			Convert.registerConverter(converter.getKey(), converter.getValue());
		}
	}

	public Sql2o provide() {
		if (sql2o == null) {
			initSql2o();
		}
		return sql2o;
	}

	private synchronized void initSql2o() {
		if (sql2o != null) {
			return;
		}
		sql2o = new Sql2o(dataSource);
	}

}
