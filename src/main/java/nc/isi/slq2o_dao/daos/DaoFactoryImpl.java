package nc.isi.slq2o_dao.daos;

import java.util.Map;

import nc.isi.slq2o_dao.entities.Entity;
import nc.isi.slq2o_dao.entities.EntityDefProvider;

import org.slf4j.Logger;

@SuppressWarnings("rawtypes")
public class DaoFactoryImpl implements DaoFactory {
	private final EntityDefProvider entityDefProvider;
	private final Logger logger;

	private final Map<Class, Dao> daos;

	public DaoFactoryImpl(EntityDefProvider entityDefProvider, Logger logger,
			Map<Class, Dao> daos) {
		this.entityDefProvider = entityDefProvider;
		this.logger = logger;
		this.daos = daos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Entity> Dao<T> get(Class<T> tClass) {
		Dao<T> dao = (Dao<T>) daos.get(tClass);
		if (dao == null) {
			dao = new DefaultDao<>(entityDefProvider, logger, tClass);
			daos.put(tClass, dao);
		}
		return dao;
	}

}
