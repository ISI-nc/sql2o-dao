package nc.isi.slq2o_dao.utils;

import java.util.Collection;

import nc.isi.fragaria_reflection.utils.ObjectMetadata;
import nc.isi.slq2o_dao.entities.Entity;

public interface DBUtils {

	Collection<String> getInsertablePropertyNames(
			Class<? extends Entity> tClass, ObjectMetadata objectMetadata);

	String removeArrayMarkerFromCollectionToString(Collection<String> col);

	String convertClassToTableName(Class<? extends Entity> cl);

	String splitCamelCase(String s, String split);

}