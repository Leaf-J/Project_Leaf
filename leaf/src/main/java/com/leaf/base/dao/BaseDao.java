package com.leaf.base.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.leaf.base.domain.BaseEntity;
import com.leaf.base.util.GenericUtil;
import com.leaf.base.util.SecurityUtil;
import com.leaf.common.domain.AdminUser;

/**
 * 
 * @author admin
 * 
 */
public abstract class BaseDao<E extends BaseEntity> extends HibernateDaoSupport {
	private Logger log = Logger.getLogger(this.getClass());
	protected Class<E> clazz;

	public BaseDao() {
		try {
			clazz = GenericUtil.getActualClass(this.getClass(), 0);
		} catch (Exception e) {
			log.error("base dao can not get clazz!", e);
		}
	}

	@PostConstruct
	protected void init() {
		initHQL();
		initSQL();
	}


	private void beforeSave(E entity) {
		AdminUser user = SecurityUtil.getCurrentUser(AdminUser.class);
		if (user != null) {
			entity.setCreatedBy(user.getPid());
			entity.setModifiedBy(user.getPid());
		}
		Date date = new Date();
		entity.setCreatedOn(date);
		entity.setModifiedOn(date);
	}


	private void beforeUpdate(E entity) {
		AdminUser user = SecurityUtil.getCurrentUser(AdminUser.class);
		if (user != null) {
			entity.setModifiedBy(user.getPid());
		}
		entity.setModifiedOn(new Date());
	}
	
	protected void initHQL() {
	}

	protected void initSQL() {
	}

	protected String getEntityClassName() {
		return clazz.getName();
	}

	@Resource(name = "hibernateTemplate")
	public void setBasehibernateTemplate(HibernateTemplate basehibernateTemplate) {
		this.setHibernateTemplate(basehibernateTemplate);
	}

	public void save(E entity) {
		if(entity.getCreatedBy()==null){
			this.beforeSave(entity);
		}else{
			this.beforeUpdate(entity);
		}
		this.getHibernateTemplate().save(entity);
	}

	public void update(E entity) {
		if(entity.getCreatedBy()==null){
			this.beforeSave(entity);
		}else{
			this.beforeUpdate(entity);
		}
		this.getHibernateTemplate().update(entity);
	}

	public void delete(E entity) {
		this.getHibernateTemplate().delete(entity);
	}

	public void delete(Serializable pid) {
		E entity = this.findById(pid);
		this.delete(entity);
	}

	public void deleteByids(final Long... pids) {
		String hql = "delete from " + clazz.getName() + " where pid in (:pids)";
		this.getSession().createQuery(hql).setParameterList("pids", pids)
				.executeUpdate();
	}

	public void saveOrUpdate(E entity) {
		if(entity.getCreatedBy()==null){
			this.beforeSave(entity);
		}else{
			this.beforeUpdate(entity);
		}
		this.getHibernateTemplate().saveOrUpdate(entity);
	}

	public void saveOrUpdateBatch(Collection<E> entities) {
		Session session = this.getSessionFactory().getCurrentSession();
		int count = 1;
		for (@SuppressWarnings("rawtypes")
		Iterator iterator = entities.iterator(); iterator.hasNext();) {
			@SuppressWarnings("unchecked")
			E e = (E) iterator.next();
			this.saveOrUpdate(e);

			if (count % 20 == 0) {
				session.flush();
				session.clear();
			}
		}
	}

	public E findById(Serializable pid) {
		return this.getHibernateTemplate().get(clazz, pid);
	}

	/**
	 * @description hql基础查询
	 * @param hql
	 *            hql查询语句
	 * @param params
	 *            条件的值,对应hql中的"?"
	 * @param currPage
	 *            当前页
	 * @param pageSIze
	 *            每页显示条目数量
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<E> findByHql(final String hql, final Object[] params,
			final int currPage, final int pageSize) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if (params != null && params.length > 0) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i, params[i]);
					}
				}
				if (currPage >= 1 && pageSize >= 1) {
					query.setFirstResult((currPage - 1) * pageSize);
					query.setMaxResults(pageSize);
				}
				return query.list();
			}

		});
	}

	/**
	 * @description 无参数的hql全部记录查询
	 * @param hql
	 *            hql语句
	 * @return
	 */
	public List<E> findByHql(String hql) {
		return this.findByHql(hql, null, 0, 0);
	}

	/**
	 * @description 带有参数的hql全部记录查询
	 * @param hql
	 *            hql语句
	 * @param params
	 *            条件的值,对应hql中的"?"
	 * @return
	 */
	public List<E> findByHql(final String hql, final Object[] params) {
		return this.findByHql(hql, params, 0, 0);
	}

	/**
	 * @description 不带参数的hql分页查询
	 * @param hql
	 *            hql语句
	 * @param currPage
	 *            当前页
	 * @param pageSize
	 *            每页显示条目数量
	 * @return
	 */
	public List<E> findByHql(String hql, int currPage, int pageSize) {
		return this.findByHql(hql, null, currPage, pageSize);
	}

	/**
	 * @description sql基础查询
	 * @param sql
	 *            sql语句
	 * @param params
	 *            条件的值,对应sql语句中的"?"
	 * @param currPage
	 *            当前页
	 * @param pageSize
	 *            每页显示条目数量
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findBySql(final String sql, final Object[] params,
			final int currPage, final int pageSize) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session s) throws HibernateException,
					SQLException {
				Query query = s.createSQLQuery(sql);
				if (params != null && params.length > 0) {
					for (int i = 0, j = params.length; i < j; i++) {
						query.setParameter(i, params[i]);
					}
				}
				if (currPage >= 1 && pageSize >= 1) {
					query.setFirstResult((currPage - 1) * pageSize);
					query.setMaxResults(pageSize);
				}
				List list = query.list();
				return list;
			}
		});
	}

	/**
	 * @description 不带参数的sql全部记录查询
	 * @param sql
	 *            sql语句
	 * @return
	 */
	public List<Object> findBySql(final String sql) {
		return this.findBySql(sql, null, 0, 0);
	}

	/**
	 * @description 带参数的sql全部记录查询
	 * @param sql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> findBySql(final String sql, final Object[] params) {
		return this.findBySql(sql, params, 0, 0);
	}

	/**
	 * 不带参数的sql分页查询
	 * 
	 * @param sql
	 *            sql语句
	 * @param currPage
	 *            当前页
	 * @param pageSize
	 *            每页显示条目数量
	 */
	public List<Object> findBySql(final String sql, int currPage, int pageSize) {
		return this.findBySql(sql, null, currPage, pageSize);
	}

	/**
	 * @description 执行sql语句
	 * @param sql
	 */
	public void executeSql(final String sql) {
		this.getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql);
				query.executeUpdate();
				return null;
			}

		});
	}

	/**
	 * @description 执行带"?"的sql语句
	 * @param sql
	 *            sql语句
	 * @param params
	 *            条件的值,对应sql语句中的"?"
	 */
	@SuppressWarnings("unchecked")
	public void executeSql(final String sql, final Object[] params) {
		this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createSQLQuery(sql);
				if (params != null && params.length > 0) {
					for (int i = 0, j = params.length; i < j; i++) {
						query.setParameter(i, params[i]);
					}
				}
				query.executeUpdate();
				return null;
			}
		});
	}

	public int getRowCountByDetachedCriteria(DetachedCriteria condition) {
		Criteria criteria = condition.getExecutableCriteria(this.getSession());
		Long totalCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		return totalCount == null ? 0 : totalCount.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<E> findByCriteria(Criteria criteria){
		if(criteria == null){
			throw new IllegalArgumentException("The criteria must not be null! ");
		}
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<E> findByCriteria(Criteria criteria,final int start,final int pageSize){
		if(criteria == null){
			throw new IllegalArgumentException("The criteria must not be null! ");
		}
		criteria.setFirstResult((start-1)*pageSize).setMaxResults(pageSize);
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		return this.findByCriteria(criteria);
	}
	
	@SuppressWarnings("unchecked")
	public List<E> findByDetachedCriteria(DetachedCriteria detachedCriteria){
		if(detachedCriteria == null){
			throw new IllegalArgumentException("The detachedCriteria must not be null! ");
		}
		Criteria criteria = detachedCriteria.getExecutableCriteria(this.getSession());
		return this.findByCriteria(criteria);
	}
	
	public List<E> findByDetachedCriteria(final DetachedCriteria detachedCriteria,final int start,final int pageSize){
		if(detachedCriteria == null){
			throw new IllegalArgumentException("The detachedCriteria must not be null! ");
		}
		Criteria criteria = detachedCriteria.getExecutableCriteria(this.getSession());
		return this.findByCriteria(criteria, start, pageSize);
	}
	
	public List<E> findAll(){
		return this.findByDetachedCriteria(DetachedCriteria.forClass(clazz));
	}
}
