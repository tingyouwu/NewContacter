package com.future.wk.newcontacter.base.store.db.annotation;

import com.future.wk.newcontacter.base.store.db.SqliteBaseDALEx;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于缓存对象模型
 **/
public class SqliteDao {
	private static Map<Class<? extends SqliteBaseDALEx>, SqliteBaseDALEx> daos = new HashMap<Class<? extends SqliteBaseDALEx>,SqliteBaseDALEx>();
	
	public static <T extends SqliteBaseDALEx> T getDao(Class<? extends SqliteBaseDALEx> clazz){
		SqliteBaseDALEx dao = daos.get(clazz);
		if(dao==null){
			try {
				dao = clazz.newInstance();
				daos.put(clazz, dao);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		dao.createTable();
		return (T)dao;
	}
	
	public static void clear(){
		daos.clear();
	}
}
