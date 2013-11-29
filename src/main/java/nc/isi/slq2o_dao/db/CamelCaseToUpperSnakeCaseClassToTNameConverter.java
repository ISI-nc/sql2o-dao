package nc.isi.slq2o_dao.db;

import nc.isi.slq2o_dao.entities.Entity;
import nc.isi.slq2o_dao.utils.CamelCaseToUpperSnakeCaseConverter;

public class CamelCaseToUpperSnakeCaseClassToTNameConverter extends
		CamelCaseToUpperSnakeCaseConverter implements ClassToTNameConverter {

	@Override
	public String convert(Class<? extends Entity> entityClass) {
		return convert(entityClass.getSimpleName());
	}

}
