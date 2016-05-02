package com.leaf.base.util;

import java.io.IOException;

import com.google.common.base.Preconditions;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;

public class FreeMarkerUtil {
	
	private static Configuration defaultConfig;
	
	private static Configuration stringLoaderConfig;
	
	public static Configuration getDefaultConfiguration(){
		if(defaultConfig==null){
			defaultConfig = new Configuration();
		}
		return defaultConfig;
	}

	private static StringTemplateLoader templateLoader = new StringTemplateLoader();
	
	public static Configuration getStringLoaderConfiguration(){
		if(stringLoaderConfig==null){
			stringLoaderConfig = new Configuration();
			stringLoaderConfig.setTemplateLoader(templateLoader);
		}
		return stringLoaderConfig;
	}
	
	public static void putStringTemplate(String templateName,String templateContent){
		Preconditions.checkNotNull(templateName);
		Preconditions.checkNotNull(templateContent);
		StringTemplateLoader loader = (StringTemplateLoader)getStringLoaderConfiguration().getTemplateLoader();
		loader.putTemplate(templateName, templateContent);
	}

}
