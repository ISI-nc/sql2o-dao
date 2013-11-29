package nc.is.sql2o_dao.services;

import static junit.framework.Assert.assertEquals;
import nc.is.sql2o_dao.mock.MyTestClass;
import nc.is.sql2o_dao.mock.Point;
import nc.isi.slq2o_dao.services.EntityToDataSource;

import org.junit.Test;

public class TestEntityToDataSource {
	private final EntityToDataSource entityToDataSource = QaRegistry.INSTANCE
			.getService(EntityToDataSource.class);

	@Test
	public void shouldProvideDefaultForNotABaseEntitityForTest() {
		assertEquals(QaModule.SCD_DS_KEY,
				entityToDataSource.provide(MyTestClass.class));
	}

	@Test
	public void souldProvideDSForBaseEntityForTest() {
		assertEquals(QaModule.DS_KEY, entityToDataSource.provide(Point.class));
	}

}
