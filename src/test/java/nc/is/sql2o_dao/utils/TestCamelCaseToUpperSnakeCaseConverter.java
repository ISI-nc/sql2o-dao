package nc.is.sql2o_dao.utils;

import static junit.framework.Assert.assertEquals;
import nc.isi.slq2o_dao.utils.CamelCaseToUpperSnakeCaseConverter;

import org.junit.Test;

public class TestCamelCaseToUpperSnakeCaseConverter {

	@Test
	public void shouldReturnAUpperSnakeCaseVersionOfCamelCaseString() {
		CamelCaseToUpperSnakeCaseConverter converter = new CamelCaseToUpperSnakeCaseConverter();
		assertEquals("CAMEL_CASE", converter.convert("camelCase"));
	}

}
