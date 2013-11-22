package nc.isi.slq2o_dao.entities;

import static org.jooq.impl.DSL.fieldByName;

import java.util.Map;
import java.util.UUID;

import nc.isi.fragaria_reflection.utils.ObjectMetadata;
import nc.isi.slq2o_dao.daos.DaoFactory;
import nc.isi.slq2o_dao.utils.DBUtils;
import nc.isi.slq2o_dao.utils.DefaultRegistry;

import org.jooq.Field;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;

public abstract class BaseEntity {

	protected static Map<String, Field<Object>> buildPropertyToFields(
			Class<? extends BaseEntity> entityClass,
			ObjectMetadata objectMetadata, String tName) {
		Map<String, Field<Object>> results = Maps.newHashMap();
		for (String s : DBUtils.getInsertablePropertyNames(entityClass,
				objectMetadata)) {
			results.put(s, fieldByName(tName, s.toUpperCase()));
		}
		return results;
	}

	protected final DaoFactory daoFactory = DefaultRegistry
			.getService(DaoFactory.class);

	private String id;

	protected BaseEntity() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public <T extends Selectable> T get(T cache, String id, Class<T> tClass) {
		if (cache == null) {
			cache = daoFactory.get(tClass).get(id);
		}
		return cache;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BaseEntity)) {
			return false;
		}
		return Objects.equal(this.getId(), ((BaseEntity) obj).getId());
	}

	public abstract Field<Object> getField(String property);

}
