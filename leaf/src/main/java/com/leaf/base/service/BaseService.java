package com.leaf.base.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.leaf.base.dao.BaseDao;
import com.leaf.base.domain.BaseEntity;
import com.leaf.common.vo.DataVO;

public abstract class BaseService<E extends BaseEntity> {
	protected BaseDao<E> baseDao;

	public BaseDao<E> getBaseDao() {
		return this.baseDao;
	}

	public void setBaseDao(BaseDao<E> baseDao) {
		this.baseDao = baseDao;
	}

	public void delete(E entity) {
		this.baseDao.delete(entity);
	}

	public void deleteById(Serializable pid) {
		this.baseDao.delete(pid);
	}

	public void deleteByIds(Long... pids) {
		this.baseDao.deleteByids(pids);
	}

	public E get(Serializable pid) {
		E e = this.baseDao.findById(pid);
		return e;
	}

	public void save(E entity) {
		this.baseDao.save(entity);
	}

	public void saveAll(Collection<E> entities) {
		this.baseDao.saveOrUpdateBatch(entities);
	}

	public void update(E entity) {
		this.baseDao.update(entity);
	}

	public void updateAll(Collection<E> entities) {
		this.baseDao.saveOrUpdateBatch(entities);
	}

	public DataVO<E> getPageData(DetachedCriteria condition, int start,
			int pageSize) {
		DataVO<E> data = new DataVO<E>();
		int total = this.getBaseDao().getRowCountByDetachedCriteria(condition);
		condition.setProjection(null);
		List<E> rows = this.getBaseDao().findByDetachedCriteria(condition,
				start, pageSize);
		data.setRows(rows);
		data.setTotal(total);
		return data;
	}
}
