package nc.isi.slq2o_dao.services;

import javax.sql.DataSource;

import org.sql2o.Sql2o;

/**
 * Fournisseur de connection à la db via {@link Sql2o}
 * 
 * @author jmaltat
 * 
 */
public interface Sql2oDbProvider {

	/**
	 * @param dataSource
	 *            : une dataSource valide
	 * @return une instance valide de {@link Sql2o} pour la {@link DataSource}
	 *         spécifiée
	 */
	Sql2o provide(DataSource dataSource);

}