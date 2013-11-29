package nc.isi.slq2o_dao.db;


public interface EntityToTableConverter {
	ClassToTNameConverter getClassToTNameConverter();

	PropertyToFieldNameConverter getPropertyToFieldNameConverter();

}
