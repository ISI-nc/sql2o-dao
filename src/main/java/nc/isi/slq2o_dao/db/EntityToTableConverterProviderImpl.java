package nc.isi.slq2o_dao.db;

import java.util.Map;

public class EntityToTableConverterProviderImpl implements
		EntityToTableConverterProvider {
	private final Map<String, EntityToTableConverter> keyToEntityToTableConverter;

	public EntityToTableConverterProviderImpl(
			Map<String, EntityToTableConverter> keyToEntityToTableConverter) {
		this.keyToEntityToTableConverter = keyToEntityToTableConverter;
	}

	@Override
	public EntityToTableConverter provide(String key) {
		return keyToEntityToTableConverter.get(key);
	}

}
