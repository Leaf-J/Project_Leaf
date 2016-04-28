package com.leaf.common.vo;

import java.util.List;

import com.leaf.base.domain.BaseEntity;
import com.leaf.base.vo.ValueObject;

/**
 * 表格数据VO
 * 
 * @author admin
 * 
 */
public class DataVO<E extends BaseEntity> extends ValueObject {
	private long total;
	private List<E> rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<E> getRows() {
		return rows;
	}

	public void setRows(List<E> rows) {
		this.rows = rows;
	}

}
