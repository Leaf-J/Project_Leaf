package com.leaf.common.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.leaf.base.dao.BaseDao;
import com.leaf.common.domain.AdminRoleResource;

@Repository
public class AdminRoleResourceDao extends BaseDao<AdminRoleResource> {
	
	private String HQL_GET_BY_RES_NAME;
	private String SQL_GET_USER_RES;
	private String SQL_GET_OPR;
	private String SQL_DEL_OPR_BY_ROLE_RES_ID;
	
	@Override
	protected void initHQL(){
		HQL_GET_BY_RES_NAME="from "+this.getEntityClassName()+" where resource.name=?";
	}
	
	@Override
	protected void initSQL(){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("res.pid res_id, ");
		sb.append("res.name res_name, ");
		sb.append("res.url res_url, ");
		sb.append("res.leaf_flag, ");
		sb.append("res.label, ");
		sb.append("group_concat( ");
		sb.append("o.NAME ");
		sb.append("ORDER BY ");	
		sb.append("o.NAME SEPARATOR \",\" ");	
		sb.append(") AS operations ");		
		sb.append("FROM ");
		sb.append("t_admin_role r ");
		sb.append("JOIN t_admin_role_res rr ON r.pid = rr.role_id ");
		sb.append("JOIN t_admin_resource res ON rr.resource_id = res.pid ");
		sb.append("LEFT JOIN t_admin_role_res_opr rro ON rr.resource_id = rro.role_res_id ");
		sb.append("LEFT JOIN t_admin_operation o ON rro.opration_id = o.pid ");
		sb.append("WHERE ");
		sb.append("r.pid IN (:roleIds)" );
		sb.append("AND res.parent_id = :parentId ");
		sb.append("GROUP BY ");
		sb.append("res_id, ");
		sb.append("res_name, ");
		sb.append("res_url, ");
		sb.append("leaf_flag, ");
		sb.append("label ");
		SQL_GET_USER_RES = sb.toString();
		
		sb.setLength(0);

		sb.append("SELECT ");
		sb.append("opr.name ");
		sb.append("FROM ");
		sb.append("t_admin_resource res ");
		sb.append("JOIN t_admin_role_res rr ON res.pid = rr.resource_id ");
		sb.append("JOIN t_admin_role role ON rr.role_id = role.pid ");
		sb.append("JOIN t_admin_role_res_opr rro ON rr.pid = rro.role_res_id ");
		sb.append("JOIN t_admin_operation opr ON opr.pid = rro.opration_id ");
		sb.append("WHERE ");
		sb.append("res.url = :resUrl ");
		sb.append("AND role.pid IN (:roleIds) ");
		sb.append("GROUP BY opr.name ");
		
		SQL_GET_OPR = sb.toString();
		
		sb.setLength(0);
		
		sb.append("DELETE ");
		sb.append("FROM ");
		sb.append("t_admin_role_res_opr ");
		sb.append("WHERE ");
		sb.append("role_res_id = :roleResId ");
		
		SQL_DEL_OPR_BY_ROLE_RES_ID = sb.toString();
		
		sb.setLength(0);
	}
	
	public List<AdminRoleResource> findByResName(final String resName){
		return this.findByHql(HQL_GET_BY_RES_NAME, new Object[]{resName});
	}
	
	/**
	 * 查找授权用户的资源
	 */
	public List<Map<String,Object>> findUserResources(final Long[] roleIds,final Long parentId){
		SQLQuery sqlQuery = this.getSession().createSQLQuery(SQL_GET_USER_RES);
		sqlQuery.setParameterList("roleIds", Arrays.asList(roleIds));
		sqlQuery.setLong("parentId", parentId);
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return sqlQuery.list();
	}
	
	/**
	 * 查找用户对某个资源的权限
	 */
	public List<String> findResourceOperations(final Long[] roleIds,final String resUrl){
		SQLQuery sqlQuery = this.getSession().createSQLQuery(SQL_GET_OPR);
		sqlQuery.setParameterList("roleIds", Arrays.asList(roleIds));
		sqlQuery.setString("resUrl",resUrl);
		return sqlQuery.list();		
	}
	
	/**
	 * 根据role_res_id删除权限配置记录
	 * @param roleResId
	 */
	public void deleteOperationsByRoleResId(Long roleResId){
		SQLQuery sqlQuery = this.getSession().createSQLQuery(SQL_DEL_OPR_BY_ROLE_RES_ID);
		sqlQuery.setLong("roleResId", roleResId);
		sqlQuery.executeUpdate();				
	}
}
