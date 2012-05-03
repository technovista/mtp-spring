package com.array.testprep.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.type.Type;


public interface ServiceDAO<T> {

    /**
     * Inserts row in the table
     * 
     * @param obj
     */
    public void insert(T obj);
    public Serializable insert1(T obj);

    /**
     * Deletes row from the table based on primary key
     * 
     * @param obj
     */
    public void delete(T obj);

    /**
     * Updates row in the table based on primary key
     * 
     * @param obj
     */
    public void update(T obj);

    /**
     * Gets row from the table when primary key is specified.
     * 
     * @return the persistent instance, or null if not found
     */
    public T selectBasedOnPrimaryKey(T obj);

    /**
     * Returns list, when value of the fields based on whom selection is to be
     * done, are specified. </br> values can not be part of composite primary
     * key.</br> Rest fields are to be essentially kept null.
     * 
     * @param obj
     * @return a List containing 0 or more persistent instances
     */
    public List<T> select(final T obj);

    /**
     * Saves if not exists ; or Updates if exists.
     * 
     * @param obj
     */
    public void saveOrUpdate(T obj);

    /**
     * Selects all rows from a table.
     * 
     * @param tablename
     */
    public List<T> selectAll(String tablename);
    public List<T> selectByMultiplaColumns(String queryString,Object values);
    /**
     * Executes native sql query to select; only one column is specified in
     * native SQL. Returns a list of objects.
     * 
     * @param query
     * @return list
     */
 //   public List<Object> executeNativeSQLSingleColumn(final String query);

    /**
     * Executes native sql query to select; multiple columns are specified in
     * native SQL. Returns a list of object array.
     * 
     * @param query
     * @return list
     */
  //  public List<Object[]> executeNativeSQLMultipleColumns(final String query);

    /**
     * Executes update, insert or delete SQL queries.
     * 
     * @param query
     */
  //  public void executeNativeSQLUpdate(final String query);

    /**
     * Executes HQL query to update the table.
     * 
     * @param hqlQuery
     */
  //  public void executeHQLUpdate(final String hqlQuery);

    /**
     * Executes HQL query to select data.
     * 
     * @param hqlQuery
     */
    public List<T> executeHQL(final String hqlQuery);

    /**
     * Executes HQL statement to select selected properties of a DO.
     * 
     * @param hqlQuery
     */
 //   public List<Object[]> executeHQLForSelectedColumns(final String hqlQuery);

    /**
     * Executes named Query to select.
     * 
     * @param query
     */
 //   public List findByNamedQuery(String query);

    /**
     * Executes named Query to select. In the query place holders are replaced
     * by parameters.
     * 
     * @param query
     * @param obj
     */
//    public List findByNamedQuery(String query, Object[] obj);

    /**
     * Executes named Query. In the query place holders are replaced by
     * parameters.
     * 
     * @param query
     * @param param
     * @param obj
     */
  //  public List findByNamedQueryAndNamedParam(String query, String param,
	//    Object value);

    /**
     * Executes named Query. In the query place holders are replaced by
     * parameters.
     * 
     * @param query
     * @param param
     * @param obj
     */
 //   public List findByNamedQueryAndNamedParam(String query, String[] params,
	//    Object[] values);

   
   
    /**
     * Executes native sql query to select; only one column is specified in
     * native SQL. Returns a list of objects.
     * 
     * @param query
     * @param scalarMap this map has column name as key and Hibernate Type to be added as scalar as value.
     * @return list
     */
 //   @SuppressWarnings("unchecked")
 //   public List<T> executeNativeSQLSingleColumn(final String query,final Map<String,Type> scalarMap);
    public List<T> findByCriteria(DetachedCriteria criteria);
}
