package nc.isi.slq2o_dao.entities;

import static org.jooq.impl.DSL.fieldByName;

import java.util.Collection;
import java.util.Map;

import nc.isi.fragaria_reflection.services.ObjectMetadataProvider;
import nc.isi.fragaria_reflection.utils.ObjectMetadata;
import nc.isi.slq2o_dao.db.DatabaseDef;
import nc.isi.slq2o_dao.db.DatabaseDefProvider;

import org.jooq.Field;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class EntityDefProviderImpl implements EntityDefProvider {

	private final DatabaseDefProvider databaseDefProvider;
	private final ObjectMetadataProvider objectMetadataProvider;
	@SuppressWarnings("rawtypes")
	private final Map<Class, EntityDef> entityDefByClass;

	@SuppressWarnings("rawtypes")
	public EntityDefProviderImpl(DatabaseDefProvider databaseDefProvider,
			ObjectMetadataProvider objectMetadataProvider,
			Map<Class, EntityDef> entityDefByClass) {
		this.entityDefByClass = entityDefByClass;
		this.databaseDefProvider = databaseDefProvider;
		this.objectMetadataProvider = objectMetadataProvider;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Entity> EntityDef<T> provide(Class<T> entityClass) {
		EntityDef<T> entityDef = entityDefByClass.get(entityClass);
		if (entityDef == null) {
			entityDef = buildEntityDef(
					databaseDefProvider.getDatabaseDef(entityClass),
					entityClass);
			entityDefByClass.put(entityClass, entityDef);
		}
		return entityDef;
	}

	private Map<String, Field<Object>> buildPropertyToFields(
			DatabaseDef databaseDef, String tName, ObjectMetadata objectMetadata) {
		Map<String, Field<Object>> results = Maps.newHashMap();
		for (String s : getFieldNames(databaseDef, objectMetadata)) {
			results.put(
					s,
					fieldByName(tName, databaseDef.getEntityToTableConverter()
							.getPropertyToFieldNameConverter().convert(s)));
		}
		return results;

	}

	private Collection<String> getFieldNames(DatabaseDef databaseDef,
			ObjectMetadata objectMetadata) {
		Collection<String> fieldNames = Lists.newArrayList();
		for (String property : objectMetadata.propertyNames()) {
			if (databaseDef.getExcludedProperties().contains(property)
					|| excludedPropertyTypesContains(databaseDef,
							objectMetadata, property)
					|| !objectMetadata.canWrite(property)) {
				continue;
			}
			fieldNames.add(property);
		}
		return fieldNames;
	}

	private Boolean excludedPropertyTypesContains(DatabaseDef databaseDef,
			ObjectMetadata objectMetadata, String propertyName) {
		Class<?> propertyClass = objectMetadata.propertyType(propertyName);
		for (Class<?> cl : databaseDef.getExcludedPropertyTypes()) {
			if (cl.isAssignableFrom(propertyClass)) {
				return true;
			}
		}
		return false;
	}

	private <E extends Entity> EntityDef<E> buildEntityDef(
			DatabaseDef databaseDef, Class<E> entityClass) {
		ObjectMetadata objectMetadata = objectMetadataProvider
				.provide(entityClass);
		String tableName = databaseDef.getEntityToTableConverter()
				.getClassToTNameConverter().convert(entityClass);
		EntityDef<E> entityDef = new DefaultEntityDef<>(objectMetadata,
				tableName, entityClass, buildPropertyToFields(databaseDef,
						tableName, objectMetadata), databaseDef);
		return entityDef;
	}
}
