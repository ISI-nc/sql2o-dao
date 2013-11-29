package nc.is.sql2o_dao.mock;

import nc.isi.slq2o_dao.entities.BaseEntity;

public class MyTestClass extends BaseEntity {

	private MySubTestClass mySubTestClass;

	public void setToto(String toto) {

	}

	public String getToto() {
		return null;
	}

	public MySubTestClass getMySubTestClass() {
		return mySubTestClass;
	}

	public void setMySubTestClass(MySubTestClass mySubTestClass) {
		this.mySubTestClass = mySubTestClass;
	}

}
