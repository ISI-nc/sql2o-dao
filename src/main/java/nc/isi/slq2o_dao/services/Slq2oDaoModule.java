package nc.isi.slq2o_dao.services;

import nc.isi.slq2o_dao.daos.DaoFactory;
import nc.isi.slq2o_dao.daos.DaoFactoryImpl;
import nc.isi.slq2o_dao.utils.DBUtils;
import nc.isi.slq2o_dao.utils.DBUtilsImpl;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.ServiceBinder;

/**
 * Module en charge des tâches liées au management des données:
 * 
 * - récupération depuis la base
 * 
 * @author jmaltat
 * 
 */
public class Slq2oDaoModule {

	public static void bind(ServiceBinder binder) {
		binder.bind(Sql2oDbProvider.class, Sql2oDbProviderImpl.class);
		binder.bind(SqlDSLProvider.class, SqlDSLProviderImpl.class);
		binder.bind(DaoFactory.class, DaoFactoryImpl.class);
		binder.bind(DBUtils.class, DBUtilsImpl.class);
		binder.bind(EntityDefProvider.class, EntityDefProviderImpl.class);
	}

	public static void contributeDBUtils(Configuration<String> configuration) {
		configuration.add("class");
		configuration.add("objectMetadata");
		configuration.add("field");
	}

}
