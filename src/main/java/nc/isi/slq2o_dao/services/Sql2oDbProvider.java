package nc.isi.slq2o_dao.services;


import org.sql2o.Sql2o;

/**
 * Fournisseur de connection à la db via {@link Sql2o}
 * 
 * @author jmaltat
 * 
 */
public interface Sql2oDbProvider {

	/**
	 * @return une instance valide de {@link Sql2o}
	 */
	Sql2o provide();

}