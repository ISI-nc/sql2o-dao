package nc.is.sql2o_dao.services;

import nc.is.sql2o_dao.mock.BaseEntityForTest;
import nc.isi.slq2o_dao.daos.DaoFactory;
import nc.isi.slq2o_dao.daos.DaoFactoryImpl;
import nc.isi.slq2o_dao.db.CamelCaseToUpperSnakeCaseClassToTNameConverter;
import nc.isi.slq2o_dao.db.CamelCaseToUpperSnakeCasePropertyToFieldNameConverter;
import nc.isi.slq2o_dao.db.ClassToTNameConverter;
import nc.isi.slq2o_dao.db.DatabaseDefProvider;
import nc.isi.slq2o_dao.db.DatabaseDefProviderImpl;
import nc.isi.slq2o_dao.db.EntityToTableConverter;
import nc.isi.slq2o_dao.db.EntityToTableConverterProvider;
import nc.isi.slq2o_dao.db.EntityToTableConverterProviderImpl;
import nc.isi.slq2o_dao.db.PropertyToFieldNameConverter;
import nc.isi.slq2o_dao.db.SqlDialectProvider;
import nc.isi.slq2o_dao.db.SqlDialectProviderImpl;
import nc.isi.slq2o_dao.entities.EntityDefProvider;
import nc.isi.slq2o_dao.entities.EntityDefProviderImpl;
import nc.isi.slq2o_dao.services.EntityToDataSource;
import nc.isi.slq2o_dao.services.EntityToDataSourceImpl;
import nc.isi.slq2o_dao.services.ServiceDefaultProvider;
import nc.isi.slq2o_dao.services.ServiceDefaultProviderImpl;
import nc.isi.slq2o_dao.services.Sql2oDbProvider;
import nc.isi.slq2o_dao.services.Sql2oDbProviderImpl;
import nc.isi.slq2o_dao.services.SqlDSLProvider;
import nc.isi.slq2o_dao.services.SqlDSLProviderImpl;

import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.jooq.SQLDialect;

public class QaModule {
	public static final String DS_KEY = "jdbc/smti";

	public static final String SCD_DS_KEY = "blurp";

	public static void bind(ServiceBinder binder) {
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

	public static void contributeEntityToTableConverterProvider(
			MappedConfiguration<String, EntityToTableConverter> configuration,
			final CamelCaseToUpperSnakeCaseClassToTNameConverter camelCaseToUpperSnakeCaseClassToTNameConverter,
			final CamelCaseToUpperSnakeCasePropertyToFieldNameConverter camelCaseToUpperSnakeCasePropertyToFieldNameConverter) {
		configuration.add(DS_KEY, new EntityToTableConverter() {

			@Override
			public PropertyToFieldNameConverter getPropertyToFieldNameConverter() {
				return camelCaseToUpperSnakeCasePropertyToFieldNameConverter;
			}

			@Override
			public ClassToTNameConverter getClassToTNameConverter() {
				return camelCaseToUpperSnakeCaseClassToTNameConverter;
			}
		});
		configuration.add(SCD_DS_KEY, new EntityToTableConverter() {

			@Override
			public PropertyToFieldNameConverter getPropertyToFieldNameConverter() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public ClassToTNameConverter getClassToTNameConverter() {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}

	public static void contributeSqlDialectProvider(
			MappedConfiguration<String, SQLDialect> configuration) {
		configuration.add(DS_KEY, SQLDialect.POSTGRES);
	}

	public static void contributeServiceDefaultProvider(
			MappedConfiguration<Class, Object> configuration) {
		configuration.add(EntityToDataSource.class, SCD_DS_KEY);
	}

	public static void contributeEntityToDataSource(
			MappedConfiguration<Class, String> configuration) {
		configuration.add(BaseEntityForTest.class, DS_KEY);
	}
}
