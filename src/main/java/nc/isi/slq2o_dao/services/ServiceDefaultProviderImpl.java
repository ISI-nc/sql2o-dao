package nc.isi.slq2o_dao.services;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import nc.isi.fragaria_reflection.utils.FluentHashMap;

@SuppressWarnings("rawtypes")
public class ServiceDefaultProviderImpl implements ServiceDefaultProvider {
	private final Map<Class, Object> serviceDefaults = new FluentHashMap<>();
	private final Map<Class, Object> userServiceDefaults;

	public ServiceDefaultProviderImpl(Map<Class, Object> userServiceDefaults) {
		this.userServiceDefaults = userServiceDefaults;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T provide(Class<?> serviceName) {
		Object o = userServiceDefaults.get(serviceName);
		if (o == null) {
			o = serviceDefaults.get(serviceName);
		}
		return (T) checkNotNull(o, "pas de défaut définit pour ce service");
	}

}
