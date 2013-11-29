package nc.is.sql2o_dao.utils;

import static junit.framework.Assert.assertEquals;

import java.util.Arrays;

import nc.isi.slq2o_dao.utils.StringUtils;

import org.junit.Test;

public class TestStringUtils {
	private static final String[] TESTS_STRINGS = { "test", "youypi", "tralala" };

	@Test
	public void splitCamelCaseWithUnderscoreShouldReturnSnakeCase() {
		assertEquals("je_Suis_En_Camel_Case",
				StringUtils.splitCamelCase("jeSuisEnCamelCase", "_"));
	}

	@Test
	public void removeArrayMarkerFromCollectionToStringShouldReturnAComaSeparatedString() {
		assertEquals("test, youypi, tralala",
				StringUtils.removeArrayMarkerFromCollectionToString(Arrays
						.asList(TESTS_STRINGS)));
	}

}
