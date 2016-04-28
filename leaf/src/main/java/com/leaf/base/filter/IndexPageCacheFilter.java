package com.leaf.base.filter;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;

public class IndexPageCacheFilter extends SimplePageCachingFilter{

	@Override
	protected boolean acceptsGzipEncoding(HttpServletRequest request) {
		//TODO 若为true，浏览器端不能解压缩，待研究
		return false;
	}
}
