package nc.is.sql2o_dao.services;

import nc.isi.slq2o_dao.db.CamelCaseToUpperSnakeCaseClassToTNameConverter;
import nc.isi.slq2o_dao.db.CamelCaseToUpperSnakeCasePropertyToFieldNameConverter;
import nc.isi.slq2o_dao.db.ClassToTNameConverter;
import nc.isi.slq2o_dao.db.EntityToTableConverter;
import nc.isi.slq2o_dao.db.PropertyToFieldNameConverter;
import nc.isi.slq2o_dao.services.Sql2oDaoModule;

import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.annotations.SubModule;
import org.jooq.SQLDialect;

@SubModule(Sql2oDaoModule.class)
public class QaModule {
	public static final String DS_KEY = "jdbc/smti";

	public static final String SCD_DS_KEY = "blurp";

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
}
