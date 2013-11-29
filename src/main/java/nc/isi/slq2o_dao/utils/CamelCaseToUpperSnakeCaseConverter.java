package nc.isi.slq2o_dao.utils;

import java.util.Map;

import com.google.common.collect.Maps;

public class CamelCaseToUpperSnakeCaseConverter {

	private final Map<String, String> camelCaseToUpperSnakeCase = Maps
			.newConcurrentMap();

	public String convert(final String propertyName) {
		String fieldName = camelCaseToUpperSnakeCase.get(propertyName);
		if (fieldName == null) {
			fieldName = StringUtils.splitCamelCase(propertyName, "_")
					.toUpperCase();
			camelCaseToUpperSnakeCase.put(propertyName, fieldName);
		}
		return fieldName;
	}

}