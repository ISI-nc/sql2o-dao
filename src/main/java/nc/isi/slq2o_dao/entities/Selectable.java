package nc.isi.slq2o_dao.entities;



public abstract class Selectable extends BaseEntity {

	public abstract String getLabel();

	@Override
	public String toString() {
		return getLabel();
	}

}
