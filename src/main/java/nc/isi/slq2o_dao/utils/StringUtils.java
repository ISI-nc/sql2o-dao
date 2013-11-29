package nc.isi.slq2o_dao.utils;

import java.util.Collection;

public class StringUtils {
	private StringUtils() {

	}

	/**
	 * <sample> splitCamelCase("jeSuisEnCamelCase", "_") =>
	 * je_Suis_En_Camel_Case </sample>
	 * 
	 * @param s
	 *            : {@link String} en camelCase à séparer
	 * @param split
	 *            : le séparateur à appliquer
	 * @return {@link String} avec split entre chaque partie
	 * 
	 */
	public static final String splitCamelCase(String s, String split) {
		return s.replaceAll(String.format("%s|%s|%s",
				"(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])",
				"(?<=[A-Za-z])(?=[^A-Za-z])"), split);
	}

	/**
	 * 
	 * @param col
	 *            : Une collection de {@link String}
	 * @return une chaîne contenant les éléments de la collection séparés par
	 *         une ','
	 */
	public static final String removeArrayMarkerFromCollectionToString(
			Collection<String> col) {
		return col.toString().replaceAll("\\[", "").replaceAll("\\]", "");
	}

}
