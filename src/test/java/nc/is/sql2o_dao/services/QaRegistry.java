package nc.is.sql2o_dao.services;

import org.apache.tapestry5.ioc.Registry;
import org.apache.tapestry5.ioc.RegistryBuilder;

public enum QaRegistry {
	INSTANCE;

	private final Registry registry = RegistryBuilder
			.buildAndStartupRegistry(QaModule.class);

	public Registry getRegistry() {
		return registry;
	}

	public <T> T getService(Class<T> tClass) {
		return registry.getService(tClass);
	}

}
