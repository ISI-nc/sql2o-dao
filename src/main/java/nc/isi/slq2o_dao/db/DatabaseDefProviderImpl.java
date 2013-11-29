package nc.isi.slq2o_dao.db;

import java.util.Map;

import javax.sql.DataSource;

import nc.isi.slq2o_dao.entities.Entity;
import nc.isi.slq2o_dao.services.EntityToDataSource;
import nc.isi.slq2o_dao.services.ServiceDefaultProvider;
import nc.isi.slq2o_dao.services.Sql2oDbProvider;
import nc.isi.slq2o_dao.services.SqlDSLProvider;

import com.google.common.collect.Maps;

public class DatabaseDefProviderImpl implements DatabaseDefProvider {
	private final DataSourceBuilder dataSourceBuilder;
	private final EntityToDataSource entityToDataSource;
	private final Sql2oDbProvider sql2oDbProvider;
	private final SqlDSLProvider sqlDSLProvider;
	private final EntityToTableConverterProvider entityToTableConverterProvider;
	private final Map<Class<? extends Entity>, DatabaseDef> entityClassToDatabaseDef = Maps
			.newConcurrentMap();

	public DatabaseDefProviderImpl(DataSourceBuilder dataSourceBuilder,
			Sql2oDbProvider sql2oDbProvider, SqlDSLProvider sqlDSLProvider,
			EntityToTableConverterProvider entityToTableConverterProvider,
			ServiceDefaultProvider serviceDefaultProvider,
			EntityToDataSource entityToDataSource) {
		this.dataSourceBuilder = dataSourceBuilder;
		this.sql2oDbProvider = sql2oDbProvider;
		this.sqlDSLProvider = sqlDSLProvider;
		this.entityToTableConverterProvider = entityToTableConverterProvider;
		this.entityToDataSource = entityToDataSource;
	}

	@Override
	public DatabaseDef getDatabaseDef(Class<? extends Entity> entityClass) {
		DatabaseDef databaseDef = entityClassToDatabaseDef.get(entityClass);
		if (databaseDef == null) {
			String key = entityToDataSource.provide(entityClass);
			DataSource dataSource = dataSourceBuilder.buildJNDI(key);
			databaseDef = new DefaultDataBaseDef(
					entityToTableConverterProvider.provide(key),
					sql2oDbProvider.provide(dataSource),
					sqlDSLProvider.provide(key), key);
			entityClassToDatabaseDef.put(entityClass, databaseDef);
		}
		return databaseDef;
	}
}
