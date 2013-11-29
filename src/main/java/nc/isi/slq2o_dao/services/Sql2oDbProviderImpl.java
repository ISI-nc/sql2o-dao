package nc.isi.slq2o_dao.services;

import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.sql2o.Sql2o;
import org.sql2o.converters.Convert;
import org.sql2o.converters.Converter;

import com.google.common.collect.Maps;

/**
 * Implémentation de {@link Sql2oDbProvider} se connectant à une base Oracle
 * 
 * @author jmaltat
 * 
 */
public class Sql2oDbProviderImpl implements Sql2oDbProvider {

	private final Map<DataSource, Sql2o> dataSourceToSql2o = Maps
			.newConcurrentMap();

	public Sql2oDbProviderImpl(Map<Class, Converter> converters)
			throws SQLException {
		for (Entry<Class, Converter> converter : converters.entrySet()) {
			Convert.registerConverter(converter.getKey(), converter.getValue());
		}
	}

	@Override
	public Sql2o provide(DataSource dataSource) {
		return initSql2o(dataSource);
	}

	private synchronized Sql2o initSql2o(DataSource dataSource) {
		Sql2o sql2o = dataSourceToSql2o.get(dataSource);
		if (sql2o != null) {
			return sql2o;
		}
		sql2o = new Sql2o(dataSource);
		dataSourceToSql2o.put(dataSource, sql2o);
		return sql2o;
	}

}
