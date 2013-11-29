package nc.isi.slq2o_dao.services;

import java.util.Map;

import nc.isi.slq2o_dao.entities.Entity;

@SuppressWarnings("rawtypes")
public class EntityToDataSourceImpl implements EntityToDataSource {

	private final Map<Class, String> entityClassToDataSource;
	private final String defaultDataSource;

	public EntityToDataSourceImpl(
			ServiceDefaultProvider serviceDefaultProvider,
			Map<Class, String> entityClassToDataSource) {
		this.entityClassToDataSource = entityClassToDataSource;
		this.defaultDataSource = serviceDefaultProvider
				.provide(EntityToDataSource.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String provide(Class<? extends Entity> entityClass) {
		for (Class cl : entityClassToDataSource.keySet()) {
			if (cl.isAssignableFrom(entityClass)) {
				return entityClassToDataSource.get(cl);
			}
		}
		return defaultDataSource;
	}

}
