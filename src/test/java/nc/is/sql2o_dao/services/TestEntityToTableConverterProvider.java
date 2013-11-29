package nc.is.sql2o_dao.services;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import nc.isi.slq2o_dao.db.CamelCaseToUpperSnakeCaseClassToTNameConverter;
import nc.isi.slq2o_dao.db.CamelCaseToUpperSnakeCasePropertyToFieldNameConverter;
import nc.isi.slq2o_dao.db.EntityToTableConverter;
import nc.isi.slq2o_dao.db.EntityToTableConverterProvider;

import org.junit.Test;

public class TestEntityToTableConverterProvider {
	private final EntityToTableConverterProvider entityToTableConverterProvider = QaRegistry.INSTANCE
			.getService(EntityToTableConverterProvider.class);

	@Test
	public void testGetSCD_DSreturnAnInstanceWithNullProperties() {
		EntityToTableConverter entityToTableConverter = entityToTableConverterProvider
				.provide(QaModule.SCD_DS_KEY);
		assertNotNull(entityToTableConverter);
		assertNull(entityToTableConverter.getClassToTNameConverter());
		assertNull(entityToTableConverter.getPropertyToFieldNameConverter());
	}

	@Test
	public void testGetDSreturnACamelCaseToUpperSnakeCaseInstance() {
		EntityToTableConverter entityToTableConverter = entityToTableConverterProvider
				.provide(QaModule.DS_KEY);
		assertNotNull(entityToTableConverter);
		assertNotNull(entityToTableConverter.getClassToTNameConverter());
		assertTrue(CamelCaseToUpperSnakeCaseClassToTNameConverter.class
				.equals(entityToTableConverter.getClassToTNameConverter()));
		assertNotNull(entityToTableConverter.getPropertyToFieldNameConverter());
		assertTrue(CamelCaseToUpperSnakeCasePropertyToFieldNameConverter.class
				.equals(entityToTableConverter
						.getPropertyToFieldNameConverter()));
	}

}
