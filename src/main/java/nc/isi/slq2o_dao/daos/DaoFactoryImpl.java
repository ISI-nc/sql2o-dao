package nc.isi.slq2o_dao.daos;

import java.util.HashMap;
import java.util.Map;

import nc.isi.fragaria_reflection.services.ObjectMetadataProvider;
import nc.isi.slq2o_dao.entities.Entity;
import nc.isi.slq2o_dao.services.EntityDefProvider;
import nc.isi.slq2o_dao.services.Sql2oDbProvider;
import nc.isi.slq2o_dao.services.SqlDSLProvider;
import nc.isi.slq2o_dao.utils.DBUtils;

import org.slf4j.Logger;

public class DaoFactoryImpl implements DaoFactory {
	private final DBUtils dbUtils;
	private final Sql2oDbProvider sql2oDbProvider;
	private final SqlDSLProvider sqlDSLProvider;
	private final EntityDefProvider entityDefProvider;
	private final ObjectMetadataProvider objectMetadataProvider;
	private final Logger logger;
	private final Map<Class<?>, Dao<?>> daos = new HashMap<>();

	public DaoFactoryImpl(DBUtils dbUtils, Sql2oDbProvider sql2oDbProvider,
			SqlDSLProvider sqlDSLProvider, EntityDefProvider entityDefProvider,
			ObjectMetadataProvider objectMetadataProvider, Logger logger) {
		this.dbUtils = dbUtils;
		this.sql2oDbProvider = sql2oDbProvider;
		this.sqlDSLProvider = sqlDSLProvider;
		this.entityDefProvider = entityDefProvider;
		this.objectMetadataProvider = objectMetadataProvider;
		this.logger = logger;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Entity> Dao<T> get(Class<T> tClass) {
		Dao<T> dao = (Dao<T>) daos.get(tClass);
		if (dao == null) {
			dao = new DefaultDao<>(dbUtils, sql2oDbProvider, sqlDSLProvider,
					entityDefProvider, objectMetadataProvider, logger, tClass);
			daos.put(tClass, dao);
		}
		return dao;
	}

}
