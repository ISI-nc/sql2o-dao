package nc.isi.slq2o_dao.services;

import nc.isi.slq2o_dao.daos.DaoFactory;
import nc.isi.slq2o_dao.daos.DaoFactoryImpl;
import nc.isi.slq2o_dao.db.CamelCaseToUpperSnakeCaseClassToTNameConverter;
import nc.isi.slq2o_dao.db.CamelCaseToUpperSnakeCasePropertyToFieldNameConverter;
import nc.isi.slq2o_dao.db.DataSourceBuilder;
import nc.isi.slq2o_dao.db.DataSourceBuilderImpl;
import nc.isi.slq2o_dao.db.DatabaseDefProvider;
import nc.isi.slq2o_dao.db.DatabaseDefProviderImpl;
import nc.isi.slq2o_dao.db.EntityToTableConverterProvider;
import nc.isi.slq2o_dao.db.EntityToTableConverterProviderImpl;
import nc.isi.slq2o_dao.db.SqlDialectProvider;
import nc.isi.slq2o_dao.db.SqlDialectProviderImpl;
import nc.isi.slq2o_dao.entities.EntityDefProvider;
import nc.isi.slq2o_dao.entities.EntityDefProviderImpl;

import org.apache.tapestry5.ioc.ServiceBinder;

/**
 * Module en charge des tâches liées au management des données:
 * 
 * - récupération depuis la base
 * 
 * @author jmaltat
 * 
 */

public class Sql2oDaoModule {

	public static void bind(ServiceBinder binder) {
		binder.bind(DataSourceBuilder.class, DataSourceBuilderImpl.class);
		binder.bind(Sql2oDbProvider.class, Sql2oDbProviderImpl.class);
		binder.bind(SqlDSLProvider.class, SqlDSLProviderImpl.class);
		binder.bind(DaoFactory.class, DaoFactoryImpl.class);
		binder.bind(EntityDefProvider.class, EntityDefProviderImpl.class);
		binder.bind(DatabaseDefProvider.class, DatabaseDefProviderImpl.class);
		binder.bind(EntityToTableConverterProvider.class,
				EntityToTableConverterProviderImpl.class);
		binder.bind(EntityToDataSource.class, EntityToDataSourceImpl.class);
		binder.bind(ServiceDefaultProvider.class,
				ServiceDefaultProviderImpl.class);
		binder.bind(SqlDialectProvider.class, SqlDialectProviderImpl.class);
		binder.bind(CamelCaseToUpperSnakeCaseClassToTNameConverter.class);
		binder.bind(CamelCaseToUpperSnakeCasePropertyToFieldNameConverter.class);
	}

}
