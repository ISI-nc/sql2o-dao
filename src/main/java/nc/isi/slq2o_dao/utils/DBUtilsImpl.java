package nc.isi.slq2o_dao.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.isi.fragaria_reflection.utils.ObjectMetadata;
import nc.isi.slq2o_dao.entities.Entity;
import nc.isi.slq2o_dao.entities.Selectable;

import com.google.common.collect.Lists;

public class DBUtilsImpl implements DBUtils {

	private final List<String> excludedProperties;
	private final Map<Class<? extends Entity>, String> classTableNames = new HashMap<>();
	private final Map<Class<? extends Entity>, Collection<String>> classPropertyNames = new HashMap<>();

	public DBUtilsImpl(List<String> excludedProperties) {
		this.excludedProperties = excludedProperties;
	}

	@Override
	public Collection<String> getInsertablePropertyNames(
			Class<? extends Entity> tClass, ObjectMetadata objectMetadata) {
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

	@Override
	public String removeArrayMarkerFromCollectionToString(Collection<String> col) {
		return col.toString().replaceAll("\\[", "").replaceAll("\\]", "");
	}

	@Override
	public String convertClassToTableName(Class<? extends Entity> cl) {
		String tName = classTableNames.get(cl);
		if (tName == null) {
			tName = splitCamelCase(cl.getSimpleName(), "_").toUpperCase();
			classTableNames.put(cl, tName);
		}
		return tName;
	}

	@Override
	public String splitCamelCase(String s, String split) {
		return s.replaceAll(String.format("%s|%s|%s",
				"(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])",
				"(?<=[A-Za-z])(?=[^A-Za-z])"), split);
	}
}
