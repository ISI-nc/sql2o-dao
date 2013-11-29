package nc.is.sql2o_dao.services;

import static junit.framework.Assert.assertEquals;
import nc.isi.slq2o_dao.services.EntityToDataSource;
import nc.isi.slq2o_dao.services.ServiceDefaultProvider;

import org.junit.Test;

public class TestServiceDefaultProvider {
	private final ServiceDefaultProvider serviceDefaultProvider = QaRegistry.INSTANCE
			.getService(ServiceDefaultProvider.class);

	@Test
	public void shouldProvideSCD_DSasDefaultForEntityDataSource() {
		assertEquals(QaModule.SCD_DS_KEY,
				serviceDefaultProvider.provide(EntityToDataSource.class));
	}

}
