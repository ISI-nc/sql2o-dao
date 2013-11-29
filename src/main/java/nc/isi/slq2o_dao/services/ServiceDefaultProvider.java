package nc.isi.slq2o_dao.services;

/**
 * Un service pour fournir une valeur par défaut aux autres services
 * 
 * @author jmaltat
 * 
 */
public interface ServiceDefaultProvider {

	<T> T provide(Class<?> serviceName);

}
