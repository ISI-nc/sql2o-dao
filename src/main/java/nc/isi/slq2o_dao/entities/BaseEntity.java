package nc.isi.slq2o_dao.entities;

import java.util.UUID;

import com.google.common.base.Objects;

public abstract class BaseEntity implements Entity {

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

}
