package nc.isi.slq2o_dao.db;

import java.util.Arrays;
import java.util.Collection;

import javax.sql.DataSource;

import nc.isi.slq2o_dao.entities.Entity;

import org.jooq.DSLContext;
import org.sql2o.Sql2o;

public class DefaultDataBaseDef implements DatabaseDef {
	private class Bidon {

	}

	private static final Collection<String> EXCLUDED_PROPERTIES = Arrays
			.asList("class");
	private static final Collection<Class<?>> EXCLUDED_PROPERTYTYPES = Arrays
			.asList(Entity.class, Bidon.class);
	private final EntityToTableConverter entityToTableConverter;
	private final Sql2o sql2o;
	private final DSLContext sqlDsl;
	private final String key;

	public DefaultDataBaseDef(EntityToTableConverter entityToTableConverter,
			Sql2o sql2o, DSLContext sqlDsl, String key) {
		this.entityToTableConverter = entityToTableConverter;
		this.sql2o = sql2o;
		this.sqlDsl = sqlDsl;
		this.key = key;
	}

	@Override
	public Collection<String> getExcludedProperties() {
		return EXCLUDED_PROPERTIES;
	}

	@Override
	public Collection<Class<?>> getExcludedPropertyTypes() {
		return EXCLUDED_PROPERTYTYPES;
	}

	@Override
	public DataSource getDataSource() {
		return sql2o.getDataSource();
	}

	@Override
	public Sql2o getSql2o() {
		return sql2o;
	}

	@Override
	public DSLContext getSqlDSL() {
		return sqlDsl;
	}

	@Override
	public EntityToTableConverter getEntityToTableConverter() {
		return entityToTableConverter;
	}

	@Override
	public String dataSourceKey() {
		return key;
	}

}
