package nc.isi.slq2o_dao.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.isi.fragaria_reflection.utils.ObjectMetadata;
import nc.isi.slq2o_dao.entities.BaseEntity;
import nc.isi.slq2o_dao.entities.Selectable;

import com.google.common.collect.Lists;

public class DBUtils {
	private DBUtils() {

	}

	private static final List<String> excludedProperties = Arrays.asList(
			"class", "objectMetadata", "field");
	private static final Map<Class<? extends BaseEntity>, String> classTableNames = new HashMap<>();
	private static final Map<Class<? extends BaseEntity>, Collection<String>> classPropertyNames = new HashMap<>();

	public static Collection<String> getInsertablePropertyNames(
			Class<? extends BaseEntity> tClass, ObjectMetadata objectMetadata) {
		Collection<String> results = classPropertyNames.get(tClass);
		if (results == null) {
			results = Lists.newArrayList();
			for (String propertyName : objectMetadata.propertyNames()) {
				if (Selectable.class.isAssignableFrom(objectMetadata
						.propertyType(propertyName))) {
					continue;
				}
				if (excludedProperties.contains(propertyName)) {
					continue;
				}
				results.add(propertyName);
			}
			classPropertyNames.put(tClass, results);
		}
		return results;
	}

	public static String removeArrayMarkerFromCollectionToString(
			Collection<String> col) {
		return col.toString().replaceAll("\\[", "").replaceAll("\\]", "");
	}

	public static String convertClassToTableName(Class<? extends BaseEntity> cl) {
		String tName = classTableNames.get(cl);
		if (tName == null) {
			tName = splitCamelCase(cl.getSimpleName(), "_").toUpperCase();
			classTableNames.put(cl, tName);
		}
		return tName;
	}

	public static String splitCamelCase(String s, String split) {
		return s.replaceAll(String.format("%s|%s|%s",
				"(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])",
				"(?<=[A-Za-z])(?=[^A-Za-z])"), split);
	}
}
