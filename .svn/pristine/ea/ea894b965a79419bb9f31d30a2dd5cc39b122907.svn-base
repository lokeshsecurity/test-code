package bh.gov.cio.gbs.dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import bh.gov.cio.gbs.exception.DatabaseException;
import bh.gov.cio.gbs.model.FillFromResultSet;


/**
 * The Class BaseDAO.
 */

public abstract class BaseDAO implements QueryStore {
	
	
	private static final Logger logger = LoggerFactory.getLogger(BaseDAO.class);
	
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;

	protected JdbcTemplate jdbcTemplate = null;
	
	protected PasswordEncoder passwordEncoder;

	/** The queries. */
	private Map<String, String> queries = null;
	
	
	
	
	

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * Gets the queries.
	 * 
	 * @return the queries
	 */
	public Map<String, String> getQueries() {
		return queries;
	}

	/**
	 * Sets the queries.
	 * 
	 * @param queries
	 *            the queries to set
	 */
	public void setQueries(Map<String, String> queries) {
		this.queries = queries;
		for (Iterator<String> iterator = this.queries.keySet().iterator(); iterator
				.hasNext();) {
			String query = (String) iterator.next();
			logger.debug("Query<{}> loaded", query);
		}
	}

	/**
	 * @since Feb 24, 2014 queryById
	 * @param queryId
	 * @return String
	 * @throws DatabaseException also you can change with your exception
	 *             
	 */
	protected String queryById(String queryId) throws DatabaseException {
		if (StringUtils.isBlank(queryId)) {
			throw new DatabaseException(QueryStore.EMPTY_QUERY_ID);
		}
		String result = null;
		if (queries == null) {
			throw new DatabaseException(
					QueryStore.QUERY_MAP_NOT_INITIALIZED);
		}
		result = queries.get(queryId);
		if (StringUtils.isBlank(result)) {
			throw new DatabaseException(QueryStore.QUERY_NOT_FOUND + "<"
					+ queryId + ">");
		}
		return result;
	}


	/**
	 * Gets the long.
	 * 
	 * @param queryId
	 *            the query id
	 * @param params
	 *            the params
	 * @return the long
	 * @throws DataAccessException
	 *             the data access exception
	 * @throws DatabaseException
	 *             the application exception
	 */
	protected Long getLong(String queryId, Map<String, Object> params)
			throws DatabaseException {
		logger.debug("getLong({})", queryId, params);
		Long value = namedParameterJdbcTemplate.query(queryById(queryId), params,
				new ResultSetExtractor<Long>() {
					public Long extractData(ResultSet rs) throws SQLException,
							DataAccessException {
						try {
							return rs.next() ? rs.getLong(1) : null;
						} catch (Throwable e) {
							throw new DatabaseException(e);
						}

					}

				});
		return value;
	}
	
	protected Integer getInt(String queryId, Map<String, Object> params)
			throws DatabaseException {
		logger.debug("getLong({})", queryId, params);
		Integer value = namedParameterJdbcTemplate.query(queryById(queryId), params,
				new ResultSetExtractor<Integer>() {
					public Integer extractData(ResultSet rs) throws SQLException,
							DataAccessException {
						try {
							return rs.next() ? rs.getInt(1) : null;
						} catch (Throwable e) {
							throw new DatabaseException(e);
						}

					}

				});
		return value;
	}

	/**
	 * Gets the string.
	 * 
	 * @param queryId
	 *            the query id
	 * @param params
	 *            the params
	 * @return the string
	 * @throws DataAccessException
	 *             the data access exception
	 * @throws DatabaseException
	 *             the application exception
	 */
	protected String getString(String queryId, Map<String, Object> params)
			throws DatabaseException {
		logger.debug("getString({})", queryId, params);
		String value = namedParameterJdbcTemplate.query(queryById(queryId), params,
				new ResultSetExtractor<String>() {
					public String extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						try {
							return rs.next() ? rs.getString(1) : null;
						} catch (Throwable e) {
							throw new DatabaseException(e);
						}

					}
				});
		return value;
	}

	/**
	 * Execute query.
	 * 
	 * @param queryId
	 *            the query id
	 * @param params
	 *            the params
	 * @return the int
	 * @throws DataAccessException
	 *             the data access exception
	 * @throws DatabaseException
	 *             the application exception
	 */
	protected int executeQuery(String queryId, Map<String, Object> params)
			throws DatabaseException {
		try {
			logger.debug("executeQuery({})", queryId, params);
			int noOfAffectedRows = namedParameterJdbcTemplate.update(queryById(queryId), params);
			return noOfAffectedRows;
		} catch (Throwable e) {
			throw new DatabaseException(e);
		}
		
	}

	/**
	 * Gets the object.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param queryId
	 *            the query id
	 * @param params
	 *            the params
	 * @param objType
	 *            the obj type
	 * @return the object
	 * @throws DataAccessException
	 *             the data access exception
	 * @throws DatabaseException
	 *             the application exception
	 */
	@SuppressWarnings("unchecked")
	protected <T> T getObject(String queryId, Map<String, Object> params,
			final Class<T> objType) throws DatabaseException {
		logger.debug("getObject({})", queryId, params, objType.getName());
		T entity = namedParameterJdbcTemplate.query(queryById(queryId), params,
				new ResultSetExtractor<T>() {
					public T extractData(ResultSet rs) throws SQLException,
							DataAccessException {
						if (rs.next()) {
							FillFromResultSet obj = null;
							try {
								obj = (FillFromResultSet) objType.newInstance();
								obj.fillFromResultSet(rs);
							} catch (Throwable e) {
								logger.error("Failed to getObject (" + objType
										+ ")", e);
								throw new DatabaseException(e);
							}
							return (T) obj;
						}
						return null;
					}
				});
		return entity;
	}

	/**
	 * Gets the list.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param queryId
	 *            the query id
	 * @param objType
	 *            the obj type
	 * @param fetchSize
	 *            the fetch size
	 * @return the list
	 * @throws DataAccessException
	 *             the data access exception
	 * @throws DatabaseException
	 *             the application exception
	 */
	@SuppressWarnings("unchecked")
	protected <T> List<T> getList(String queryId, final Class<T> objType,
			int fetchSize) throws DatabaseException {
		logger.debug("getList({})", queryId, objType.getName(), fetchSize);
		jdbcTemplate.setFetchSize(fetchSize);
		List<T> list = jdbcTemplate.query(queryById(queryId),
				new RowMapper<T>() {
					public T mapRow(ResultSet resultSet, int index)
							throws SQLException {
						FillFromResultSet obj = null;
						try {
							obj = (FillFromResultSet) objType.newInstance();
							obj.fillFromResultSet(resultSet);
						} catch (Throwable e) {
							logger.error("Failed to getList of (" + objType
									+ ")", e);
							throw new DatabaseException(e);
						}
						return (T) obj;
					}
				});
		return list;
	}

	/**
	 * Gets the list.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param queryId
	 *            the query id
	 * @param objType
	 *            the obj type
	 * @return the list
	 * @throws DataAccessException
	 *             the data access exception
	 * @throws DatabaseException
	 *             the application exception
	 */
	@SuppressWarnings("unchecked")
	protected <T> List<T> getList(String queryId, final Class<T> objType)
			throws DatabaseException {
		logger.debug("getList({})", queryId, objType.getName());
		List<T> list = jdbcTemplate.query(queryById(queryId),
				new RowMapper<T>() {
					public T mapRow(ResultSet resultSet, int index)
							throws SQLException {
						FillFromResultSet obj = null;
						try {
							obj = (FillFromResultSet) objType.newInstance();
							obj.fillFromResultSet(resultSet);
						} catch (Throwable e) {
							logger.error("Failed to getList of (" + objType
									+ ")", e);
							throw new DatabaseException(e);
						}
						return (T) obj;
					}
				});
		return list;
	}
	
	@SuppressWarnings("unchecked")
	protected <T> List<T> getPrimitiveList(String queryId, final Class<T> objType, Object[] params)
			throws DatabaseException {
		logger.debug("getList({})", queryId, objType.getName());
		List<T> list = jdbcTemplate.query(queryById(queryId), params,
				new RowMapper<T>() {
					public T mapRow(ResultSet resultSet, int index)
							throws SQLException {
						T obj = null;
						try {
							obj = (T) resultSet.getObject(1);
						} catch (Throwable e) {
							logger.error("Failed to getList of (" + objType
									+ ")", e);
							throw new DatabaseException(e);
						}
						return (T) obj;
					}
				});
		return list;
	}

	/**
	 * @author Ahmed Emad Gets the list.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param queryId
	 *            the query id
	 * @param objType
	 *            the obj type
	 * @param params
	 *            the params
	 * @param fetchSize
	 *            the fetch size
	 * @return the list
	 * @throws DataAccessException
	 *             the data access exception
	 * @throws DatabaseException
	 *             the application exception
	 */
	@SuppressWarnings("unchecked")
	protected <T> List<T> getList(String queryId, final Class<T> objType,
			Map<String, Object> params, int fetchSize)
			throws DatabaseException {
		logger.debug("getList({})", queryId, objType.getName(), params,
				fetchSize);
		jdbcTemplate.setFetchSize(fetchSize);
		List<T> list = namedParameterJdbcTemplate.query(queryById(queryId), params,
				new RowMapper<T>() {
					public T mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						FillFromResultSet obj = null;
						try {
							obj = (FillFromResultSet) objType.newInstance();
							obj.fillFromResultSet(rs);
						} catch (Throwable e) {
							logger.error("Failed to getList of (" + objType
									+ ")", e);
							throw new DatabaseException(e);
						}
						return (T) obj;
					}
				});
		return list;
	}

	/**
	 * @author csdvedd Gets the list.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param queryId
	 *            the query id
	 * @param objType
	 *            the obj type
	 * @param params
	 *            the params
	 * @return the list
	 * @throws DataAccessException
	 *             the data access exception
	 * @throws DatabaseException
	 *             the application exception
	 */
	@SuppressWarnings("unchecked")
	protected <T> List<T> getList(String queryId, final Class<T> objType,
			Map<String, Object> params) throws DatabaseException {
		logger.debug("getList({})", queryId, objType.getName(), params);
		List<T> list = namedParameterJdbcTemplate.query(queryById(queryId), params,
				new RowMapper<T>() {
					public T mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						FillFromResultSet obj = null;
						try {
							obj = (FillFromResultSet) objType.newInstance();
							obj.fillFromResultSet(rs);
						} catch (Throwable e) {
							logger.error(
									"Failed to getList of ("
											+ objType.getName() + ")", e);
							throw new DatabaseException(e);
						}
						return (T) obj;
					}
				});
		return list;
	}
	
	@SuppressWarnings("unchecked")
	protected <T> List<T> getList(String queryId, final Class<T> objType,
			Map<String, Object> params , boolean usingXmlFile) throws DatabaseException {
		logger.debug("getList({})", queryId, objType.getName(), params);
		List<T> list = namedParameterJdbcTemplate.query(usingXmlFile ? queryById(queryId) : queryId, params,
				new RowMapper<T>() {
					public T mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						FillFromResultSet obj = null;
						try {
							obj = (FillFromResultSet) objType.newInstance();
							obj.fillFromResultSet(rs);
						} catch (Throwable e) {
							logger.error(
									"Failed to getList of ("
											+ objType.getName() + ")", e);
							throw new DatabaseException(e);
						}
						return (T) obj;
					}
				});
		return list;
	}

	/**
	 * 
	 * @since Feb 24, 2014 isExist
	 * @param queryId
	 * @param params
	 * @param useQueryById
	 * @return
	 * @throws DatabaseException
	 *             boolean
	 */
	protected boolean isExist(final String queryId, Map<String, Object> params,
			boolean useQueryById) throws DatabaseException {
		logger.debug("isExist({})", queryId, useQueryById, params);
		Long value = namedParameterJdbcTemplate.query(
				useQueryById ? queryById(queryId) : queryId, params,
				new ResultSetExtractor<Long>() {
					public Long extractData(ResultSet rs) throws SQLException,
							DataAccessException {
						try {
							return rs.next() ? rs.getLong(1) : null;
						} catch (Throwable e) {
							logger.error("Failed to check is exist (" + queryId
									+ ")", e);
							throw new DatabaseException(e);
						}
					}
				});
		if (value == null)
			return false;
		return true;
	}

	/**
	 * @author csdvedd Fill params.
	 * @param values
	 *            the values
	 * @param params
	 *            the params
	 * @return the hash map
	 */
	protected Map<String, Object> fillParams(List<Object> values,
			String... params) {
		logger.debug("fillParams({})", values, params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < params.length; i++) {
			map.put(params[i], values.get(i));
		}
		return map;
	}

	protected SqlParameterSource fillSqlParams(List<Object> values,
			String... params) {
		logger.debug("fillSqlParams({})", values, params);
		MapSqlParameterSource map = new MapSqlParameterSource();
		for (int i = 0; i < params.length; i++) {
			map.addValue(params[i], values.get(i));
		}
		return map;
	}

	/**
	 * @author
	 * @since Feb 24, 2014 executeQueryReturnLong
	 * @param queryId
	 * @param paramSource
	 * @return
	 * @throws DatabaseException
	 *             Long
	 */
	protected Long executeQueryReturnLong(String queryId,
			SqlParameterSource paramSource) throws DatabaseException {
		try {
			logger.debug("executeQueryReturnLong({})", queryId, paramSource);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			namedParameterJdbcTemplate.update(queryById(queryId), paramSource, keyHolder);
			return keyHolder.getKey().longValue();
		} catch (Throwable e) {
			throw new DatabaseException(e);
		}
		
	}
	
	protected Integer executeQueryReturnInt(String queryId,
			SqlParameterSource paramSource) throws DatabaseException {
		try {
			
			logger.debug("executeQueryReturnInt({})", queryId, paramSource);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			namedParameterJdbcTemplate.update(queryById(queryId), paramSource, keyHolder);
			return keyHolder.getKey().intValue();
		} catch (Throwable e) {
			throw new DatabaseException(e);
		}
		
	}
	
	protected Integer executeQueryReturnInteger(String queryId,
			SqlParameterSource paramSource , String [] columnIds) throws DatabaseException {
		try {
			
			logger.debug("executeQueryReturnLong({})", queryId, paramSource);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			namedParameterJdbcTemplate.update(queryById(queryId), paramSource, keyHolder , columnIds);
			return keyHolder.getKey().intValue();
		} catch (Throwable e) {
			throw new DatabaseException(e);
		}
		
	}

	/**
	 * @author
	 * @since Feb 24, 2014 executeBatchUpdate
	 * @param queryId
	 * @param batchs
	 * @return
	 * @throws DatabaseException
	 *             int[]
	 */
	protected int[] executeBatchUpdate(String queryId, List<Object[]> batchArgs)
			throws DatabaseException {
		try {
			logger.info("executeBatchUpdate({})", queryId, batchArgs);
			return jdbcTemplate.batchUpdate(queryById(queryId), batchArgs);
		} catch (Throwable e) {
			throw new DatabaseException(e);
		}
		
	}

	/**
	 * Gets the list.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param queryId
	 *            the query id
	 * @param objType
	 *            the obj type
	 * @return the list
	 * @throws DataAccessException
	 *             the data access exception
	 * @throws DatabaseException
	 *             the application exception
	 */
	@SuppressWarnings("unchecked")
	protected <T> List<T> getList(String queryId, final Class<T> objType , boolean usingXmlFile)
			throws DatabaseException {
		logger.debug("getList({})", queryId, objType.getName());
		List<T> list = jdbcTemplate.query(usingXmlFile?queryById(queryId):queryId,
				new RowMapper<T>() {
					public T mapRow(ResultSet resultSet, int index)
							throws SQLException {
						FillFromResultSet obj = null;
						try {
							obj = (FillFromResultSet) objType.newInstance();
							obj.fillFromResultSet(resultSet);
						} catch (Throwable e) {
							logger.error("Failed to getList of (" + objType
									+ ")", e);
							throw new DatabaseException(e);
						}
						return (T) obj;
					}
				});
		return list;
	}
	
	/**
	 * 
	 * @param queryId
	 * @param params
	 * @return count
	 */
	
	protected int getCount(String queryId, Object[] params) {
		int count = jdbcTemplate.queryForInt(queryById(queryId), params);
		return count;
	}
	
	
	@SuppressWarnings("unchecked")
	protected <T> List<T> getList(final String queryId, final Class<T> objType,
			Object[] params, int fetchSize)
			throws DatabaseException {
		logger.debug("getList({})", queryId, objType.getName(), params,
				fetchSize);
		jdbcTemplate.setFetchSize(fetchSize);
		List<T> list = jdbcTemplate.query(queryId, params, new RowMapper<T>() {
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {
				FillFromResultSet obj = null;
				try {
					obj = (FillFromResultSet) objType.newInstance();
					obj.fillFromResultSet(rs);
				} catch (Throwable e) {
					logger.error("Failed to getList of (" + objType + ")", e);
					throw new DatabaseException(e);
				}
				return (T) obj;
			}
		});
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	protected <T> List<T> getListByInClause(String queryId, final Class<T> objType , MapSqlParameterSource paramSource)
			throws DatabaseException {
		logger.debug("getListByInClause({})", queryId, objType.getName());
		List<T> list = namedParameterJdbcTemplate.query(queryById(queryId), paramSource, new RowMapper<T>() {
			public T mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				FillFromResultSet obj = null;
				try {
					obj = (FillFromResultSet) objType.newInstance();
					obj.fillFromResultSet(resultSet);
				} catch (Throwable e) {
					logger.error("Failed to getList of (" + objType
							+ ")", e);
					throw new DatabaseException(e);
				}
				return (T) obj;
			}
		});
		return list;
	}
	
	protected List<Integer> getListInteger(String queryId, final Class<Integer> objType , Map<String, Object> params){
		List<Integer> list = namedParameterJdbcTemplate.query(queryById(queryId), params, new RowMapper<Integer>() {
			public Integer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				return Integer.valueOf(resultSet.getInt(1));
			}
		});
		return list;
	}
	
	
	
}
