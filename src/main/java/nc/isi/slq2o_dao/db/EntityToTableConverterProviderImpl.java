package nc.isi.slq2o_dao.db;

import java.util.Map;

import javax.sql.DataSource;

public class EntityToTableConverterProviderImpl implements
		EntityToTableConverterProvider {
	private final Map<DataSource, EntityToTableConverter> dataSourceToEntityToTableConverter;

	public EntityToTableConverterProviderImpl(
			Map<DataSource, EntityToTableConverter> dataSourceToEntityToTableConverter) {
		this.dataSourceToEntityToTableConverter = dataSourceToEntityToTableConverter;
	}

	@Override
	public EntityToTableConverter provide(DataSource dataSource) {
		return dataSourceToEntityToTableConverter.get(dataSource);
	}

}
