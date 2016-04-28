package com.leaf.common.service;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leaf.base.constant.Constant;
import com.leaf.base.dao.BaseDao;
import com.leaf.base.service.BaseService;
import com.leaf.common.dao.AdminResourceDao;
import com.leaf.common.domain.AdminResource;

@Service
public class AdminResourceService extends BaseService<AdminResource> {

	private AdminResourceDao adminResDao;

	private AdminResource rootResource;

	private static List<AdminResource> allRes;// 将集合存在内存中，防止重复查找。以后需要设置过期时间，解决数据同步问题

	@Resource(name = "adminResourceDao")
	public void setBaseDao(BaseDao<AdminResource> baseDao) {
		this.baseDao = baseDao;
		adminResDao = (AdminResourceDao) baseDao;
	}

	public AdminResource getRootResource() {
		if (rootResource == null) {
			rootResource = adminResDao.findRootResource();
		}
		return rootResource;
	}

	public AdminResource getByResourceName(String resName) {
		List<AdminResource> list = this.adminResDao.findByHql(
				"from AdminResource where name=?", new Object[] { resName });
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public List<AdminResource> getAll() {
/*		if (allRes == null) {
			allRes = this.adminResDao.findByHql("from AdminResource");
		}
		return allRes;*/
		return this.adminResDao.findByHql("from AdminResource");
	}

	/**
	 * 维护表的叶子字段的状态 , 由于涉及记录的保存操作不在同一个事务中，当过程出错时有可能会造成数据不同步
	 */
	@SuppressWarnings("unchecked")
	public void updateLeafStatus(Long resId, Long newParentId) {
		List<Object> list = this.getBaseDao().findBySql(
				"select parent_id from t_admin_resource t where t.pid=?",
				new Object[] { resId });
		Long oldParentId = ((BigInteger) list.get(0)).longValue();
		if (oldParentId != newParentId) { // parent change , check parent leaf
											// status
			// check old
			Integer cnto = ((BigInteger) this
					.getBaseDao()
					.findBySql(
							"select count(*) from t_admin_resource where parent_id = ?",
							new Object[] { oldParentId }).get(0)).intValue();
			if (cnto - 1 == 0) {
				this.getBaseDao().executeSql(
						"update t_admin_resource set leaf_flag=1 where pid=?",
						new Object[] { oldParentId });
			}
			// check new
			if (newParentId != null) {
				Integer leafFlagn = (Integer) this
						.getBaseDao()
						.findBySql(
								"select leaf_flag from t_admin_resource where pid = ?",
								new Object[] { newParentId }).get(0);
				if (leafFlagn == Constant.ADMIN_RESOURCE_IS_LEAF_YES) {
					this.getBaseDao()
							.executeSql(
									"update t_admin_resource set leaf_flag=0 where pid=?",
									new Object[] { newParentId });
				}
			}

		}
	}
}
