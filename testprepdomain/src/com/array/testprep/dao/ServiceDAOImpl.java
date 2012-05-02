package com.array.testprep.dao;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.Type;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ServiceDAOImpl<T> extends HibernateDaoSupport implements
		ServiceDAO<T> {

	protected HibernateTemplate hibernateTemplate;

	private Class<T> type;

	/**
	 * Constructor
	 * 
	 * @param type
	 */
	public ServiceDAOImpl(Class<T> type) {
		this.type = type;
	}

	/**
	 * Returns single instance per DAO object
	 */
	protected void init() {
		if (hibernateTemplate == null) {
			this.hibernateTemplate = getHibernateTemplate();
		}
	}

	/**
	 * Inserts a row in the table
	 * 
	 * @param obj
	 */
	public void insert(T obj) {
		init();
		hibernateTemplate.save(obj);
	}
	/**
	 * Inserts a row in the table
	 * 
	 * @param obj
	 */
	public Serializable insert1(T obj) {
		init();
		return hibernateTemplate.save(obj);
		
	}

	/**
	 * Deletes row from the table based on primary key
	 * 
	 * @param obj
	 */
	public void delete(T obj) {
		init();
		hibernateTemplate.delete(obj);
	}

	/**
	 * Updates row in the table based on primary key
	 * 
	 * @param obj
	 */
	public void update(T obj) {
		init();
		hibernateTemplate.update(obj);
	}

	/**
	 * Gets row from the table when primary key is specified.
	 * 
	 * @return the persistent instance, or null if not found
	 */
	@SuppressWarnings("unchecked")
	public T selectBasedOnPrimaryKey(T obj) {
		// primary key should be in <composite-id> tag; even though it is
		// single.
		init();
		obj = (T) this.hibernateTemplate.get(type, (Serializable) obj);

		return obj;
	}

	/**
	 * Returns list, when value of the fields based on whom selection is to be
	 * done, are specified. </br> values can not be part of composite primary
	 * key.</br> Rest fields are to be essentially kept null.
	 * 
	 * @param obj
	 * @return a List containing 0 or more persistent instances
	 */
	@SuppressWarnings("unchecked")
	public List<T> select(final T obj) {
		init();
		List<T> result = (List<T>) hibernateTemplate.findByExample(obj);
		return result;
	}

	/**
	 * Saves if not exists ; or Updates if exists.
	 * 
	 * @param obj
	 */
	public void saveOrUpdate(T obj) {
		init();
		hibernateTemplate.saveOrUpdate(obj);
		
	}
	@SuppressWarnings("unchecked")
	public List<T> selectByMultiplaColumns(final String queryString,final Object value )
	{
		init();
		List<T> result = (List<T>) hibernateTemplate.find(queryString, value);
		System.out.println("Redult size ="+result.size());
		return result;
	}
	/**
	 * Selects all rows from a table.
	 * 
	 * @param tablename
	 */
	@SuppressWarnings("unchecked")
	public List<T> selectAll(String tablename) {
		init();
		StringBuffer buffer = new StringBuffer();
		buffer.append("from ");
		buffer.append(tablename);
		buffer.append(" ");
		buffer.append(tablename.toLowerCase());
		List<T> result = (List<T>) hibernateTemplate.find(buffer.toString());
		return result;
	}

	/**
	 * Executes native sql query to select; only one column is specified in
	 * native SQL. Returns a list of objects.
	 * 
	 * @param query
	 * @return list
	 */
/*	@SuppressWarnings("unchecked")
	public List<Object> executeNativeSQLSingleColumn(final String query) {
		init();
		return (List<Object>) this.hibernateTemplate
				.execute(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery query1 = session.createSQLQuery(query);
						return query1.list();
					}
				});
	}*/

	/**
	 * Executes native sql query to select; multiple columns are specified in
	 * native SQL. Returns a list of object array.
	 * 
	 * @param query
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> executeNativeSQLMultipleColumns(final String query) {
		init();
		return (List<Object[]>) this.hibernateTemplate
				.execute(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery query1 = session.createSQLQuery(query);
						return query1.list();
					}
				});
	}

	/**
	 * Executes update, insert or delete SQL queries.
	 * 
	 * @param query
	 */
	/*public void executeNativeSQLUpdate(final String query) {
		init();
		// return (Integer) (this.hibernateTemplate.execute(new
		// HibernateCallback() {
		// public Object doInHibernate(Session session) throws
		// HibernateException {
		// SQLQuery query1 = session.createSQLQuery(query);
		// return query1.executeUpdate();
		// }
		// }));
	}*/

	/**
	 * Executes HQL query to update the table.
	 * 
	 * @param hqlQuery
	 */
	public void executeHQLUpdate(final String hqlQuery) {
		init();
		this.hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query1 = session.createQuery(hqlQuery);
				return query1.executeUpdate();
			}
		});
	}

	/**
	 * Executes HQL query to select data.
	 * 
	 * @param hqlQuery
	 */
	@SuppressWarnings("unchecked")
	public List<T> executeHQL(final String hqlQuery) {
		init();
		List<T> result = (List<T>) this.hibernateTemplate.find(hqlQuery);
		return result;
	}

	/**
	 * Executes HQL statement to select selected properties of a DO.
	 * 
	 * @param hqlQuery
	 */
/*	@SuppressWarnings("unchecked")
	public List<Object[]> executeHQLForSelectedColumns(final String hqlQuery) {
		init();
		List<Object[]> result = (List<Object[]>) this.hibernateTemplate
				.find(hqlQuery);
		return result;
	}*/

	/**
	 * Executes named Query to select.
	 * 
	 * @param query
	 */
/*	public List findByNamedQuery(final String query) {
		init();
		return (List) hibernateTemplate.findByNamedQuery(query);
	}*/

	/**
	 * Executes named Query to select. In the query place holders are replaced
	 * by parameters.
	 * 
	 * @param query
	 * @param obj
	 */
/*	public List findByNamedQuery(final String query, Object[] obj) {
		init();
		return (List) hibernateTemplate.findByNamedQuery(query, obj);
	}*/

	/**
	 * Executes named Query. In the query place holders are replaced by
	 * parameters.
	 * 
	 * @param query
	 * @param param
	 * @param obj
	 */
	/*public List findByNamedQueryAndNamedParam(final String query, String param,
			Object value) {
		init();
		return (List) hibernateTemplate.findByNamedQueryAndNamedParam(query,
				param, value);
	}*/

	/**
	 * Executes named Query. In the query place holders are replaced by
	 * parameters.
	 * 
	 * @param query
	 * @param param
	 * @param obj
	 */
/*	public List findByNamedQueryAndNamedParam(final String query,
			String[] params, Object[] values) {
		init();
		return (List) hibernateTemplate.findByNamedQueryAndNamedParam(query,
				params, values);
	}*/

	/**
	 * Executes Stored Procedure without any Result Parameters
	 * 
	 * @param procedureName
	 * @param params
	 *            An Object array containing the values of input parameters to
	 *            be used by the procedure
	 * @param sqlTypes
	 *            An Integer array containing SQL types for all the input
	 *            parameters of the procedures in params array
	 * @throws SQLException
	 *             if parameters passed to the method are not proper or size of
	 *             parmas and sqlTypes arrays are unequal
	 */
	/*public void executeProcedureWithoutResultParameter(
			final String procedureName, final Object[] params,
			final Integer[] sqlTypes) throws SQLException {
		final int paramValueLen = params.length;
		if (procedureName == null || params == null || sqlTypes == null
				|| paramValueLen != sqlTypes.length) {
			throw new SQLException("Invalid parameters for procedure call");
		}
		init();

		hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Connection con = session.connection();
				boolean b = false;
				StringBuffer procCallString = new StringBuffer("{call "
						+ procedureName);

				if (paramValueLen > 0) {
					procCallString.append("(?");

					for (int i = 1; i < paramValueLen; i++) {

						procCallString.append(",?");

					}
					procCallString.append(")");
				}
				procCallString.append("}");

				CallableStatement cb = con.prepareCall(procCallString
						.toString());
				System.out.println(procCallString.toString());
				Object param = null;
				int sqlType = -1;
				for (int i = 0; i < paramValueLen; i++) {

					param = params[i];
					sqlType = sqlTypes[i];
				//	logger.info("Binding parameter " + param + "to " + i + 1);
					cb.setObject(i + 1, param, sqlType);
				}

				b = cb.execute();
				con.close();
				return null;
			}
		});
	}*/

	/**
	 * Executes native sql query to select; only one column is specified in
	 * native SQL. Returns a list of objects.
	 * 
	 * @param query
	 * @param scalarMap
	 *            this map has column name as key and Hibernate Type to be added
	 *            as scalar as value.
	 * @return list
	 */
/*	@SuppressWarnings("unchecked")
	public List<T> executeNativeSQLSingleColumn(final String query,
			final Map<String, Type> scalarMap) {
		init();
		return (List<T>) this.hibernateTemplate
				.execute(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						SQLQuery query1 = session.createSQLQuery(query);
						Set<String> keySet = scalarMap.keySet();
						Iterator<String> i = keySet.iterator();
						String columnName = null;
						while (i.hasNext()) {
							columnName = i.next();
							Type scalarType = scalarMap.get(columnName);
							query1.addScalar(columnName, scalarType);
						}

						return query1.list();
					}
				});
	}*/
/**
 * Hibernate Criteria API
 */
	public List<T> findByCriteria(DetachedCriteria criteria) {
	    init();	   
	    return (List<T>)hibernateTemplate.findByCriteria(criteria);    
	    }    
}
