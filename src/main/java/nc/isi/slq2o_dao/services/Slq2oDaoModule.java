package nc.isi.slq2o_dao.services;

import nc.isi.slq2o_dao.daos.DaoFactory;
import nc.isi.slq2o_dao.daos.DaoFactoryImpl;

import org.apache.tapestry5.ioc.ServiceBinder;

/**
 * Module en charge des tâches liées au management des données:
 * 
 * - récupération depuis la base
 * 
 * @author jmaltat
 * 
 */
public class ModelModule {

	public static void bind(ServiceBinder binder) {
		binder.bind(Sql2oDbProvider.class, Sql2oDbProviderImpl.class);
		binder.bind(SqlDSLProvider.class, SqlDSLProviderImpl.class);
		binder.bind(DaoFactory.class, DaoFactoryImpl.class);
	}

}
