package nc.isi.slq2o_dao.daos;

import java.util.HashMap;
import java.util.Map;

import nc.isi.fragaria_reflection.services.ObjectMetadataProvider;
import nc.isi.slq2o_dao.entities.Entity;
import nc.isi.slq2o_dao.services.Sql2oDbProvider;
import nc.isi.slq2o_dao.services.SqlDSLProvider;

import org.slf4j.Logger;

public class DaoFactoryImpl implements DaoFactory {
	private final Sql2oDbProvider sql2oDbProvider;
	private final SqlDSLProvider sqlDSLProvider;
	private final ObjectMetadataProvider objectMetadataProvider;
	private final Logger logger;
	private final Map<Class<?>, Dao<?>> daos = new HashMap<>();

	public DaoFactoryImpl(Sql2oDbProvider sql2oDbProvider,
			SqlDSLProvider sqlDSLProvider,
			ObjectMetadataProvider objectMetadataProvider, Logger logger) {
		this.sql2oDbProvider = sql2oDbProvider;
		this.sqlDSLProvider = sqlDSLProvider;
		this.objectMetadataProvider = objectMetadataProvider;
		this.logger = logger;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Entity> Dao<T> get(Class<T> tClass) {
		Dao<T> dao = (Dao<T>) daos.get(tClass);
		if (dao == null) {
			dao = new DefaultDao<>(sql2oDbProvider, sqlDSLProvider,
					objectMetadataProvider, logger, tClass);
			daos.put(tClass, dao);
		}
		return dao;
	}

}
