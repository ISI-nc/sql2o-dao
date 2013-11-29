package nc.isi.slq2o_dao.db;

import java.util.Collection;

import javax.sql.DataSource;

import org.jooq.DSLContext;
import org.sql2o.Sql2o;

public interface DatabaseDef {

	/**
	 * 
	 * @return une liste de propriété à exclure lors de la recherche des bean
	 *         properties
	 */
	Collection<String> getExcludedProperties();

	Collection<Class<?>> getExcludedPropertyTypes();

	EntityToTableConverter getEntityToTableConverter();

	DataSource getDataSource();

	String dataSourceKey();

	Sql2o getSql2o();

	DSLContext getSqlDSL();

}
