package com.ljt.cjb.xlp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;


/**
 * @author FulianLi
 * 加载配置文件，返回DataSource数据源
 */
public class PropertiesUtils {

	private static Properties properties = new Properties();
	
	static {
		try {
			InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream("db.properties");
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static DataSource getDataSource() {
		try {
			return BasicDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
