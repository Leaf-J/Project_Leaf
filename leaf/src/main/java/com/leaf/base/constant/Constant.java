package com.leaf.base.constant;

public final class Constant {
	private Constant() {
	}

	/**
	 * 视图父路径KEY
	 */
	public static final String PARENT_VIEW_PATH = "parent_view_path";

	/**
	 * 后台管理系统资源根路径
	 */
	public static final String ADMIN_RES_CONTEXT = "/leaf/adminRes";

	/**
	 * 后台管理根路径
	 */
	public static final String ADMIN_CONTEXT_PATH = "/videoadmin";
	
	/**
	 * 前台网站根路径
	 */
	public static final String FRONT_CONTEXT_PATH = "/front";
	
	/**
	 * 后台管理系统用户对象属性名
	 */
	public static final String SESSION_KEY_USER = "admin_user_obj";

	/**
	 * 后台管理系统用户访问资源属性名
	 */
	public static final String SESSION_KEY_RESOURCES = "admin_res";

	/**
	 * 后台管理系统用户访问子资源属性名
	 */
	public static final String SESSION_KEY_SUB_MENUS = "admin_sub_menus";
	
	public static final String SESSION_KEY_SUB_PAGE_NAME = "admin_sub_page_name";
	
	/**
	 * 后台管理系统导航栏当前资源标记属性
	 */
	public static final String SESSION_KEY_ACTIVE_RES_ID = "activeResId";
	
	/**
	 * 后台管理系统子菜单当前资源标记属性
	 */
	public static final String SESSION_KEY_ACTIVE_SUB_MENU_ID = "activeSubMenuId";
	
	/**
	 * 后台管理资源为叶子节点
	 */
	public static final int ADMIN_RESOURCE_IS_LEAF_YES = 1;

	/**
	 * 后台管理资源不是叶子节点
	 */
	public static final int ADMIN_RESOURCE_IS_LEAF_NO = 0;
	
	/**
	 * 响应失败
	 */
	public static final int RESPONSE_FAILED = 0;
	
	/**
	 * 响应成功
	 */
	public static final int RESPONSE_SUCCESS = 1;
	
	public static final String RESPONSE_MSG_SUCCESS = "请求成功";
	
	public static final String RESPONSE_MSG_FAILED = "请求失败";
	
	/**
	 * 后台getData url
	 */
	public static final String ACTION_GET_DATA = "getData";
	/**
	 * 后台doDelete url
	 */	
	public static final String ACTION_DO_DELETE = "doDelete";
	/**
	 * 后台doSave url
	 */	
	public static final String ACTION_DO_SAVE = "doSave";
	/**
	 * 后台doUpdate url
	 */	
	public static final String ACTION_DO_UPDATE = "doUpdate";
	
	
	/**
	 * 后台模块操作权限:add modify delete
	 */
	public static final String OPR_LIST = "opr_list";
	
	/**
	 * 前台会员session key name
	 */
	public static final String SES_KEY_MBM_USR = "memberUser";
	
	/**
	 * 前台会员session time alive
	 */
	public static final int SES_TIME_ALIVE = 600; //600秒
	
	/**
	 * 前台网站会员路径
	 */
	public static final String FRONT_MEMBER_PATH = "/front/member";
	
	/**
	 * 前台登录页面url
	 */
	public static final String FRONT_LOGIN_URL = "/front/login";
}
