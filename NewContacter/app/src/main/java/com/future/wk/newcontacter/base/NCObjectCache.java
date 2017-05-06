package com.future.wk.newcontacter.base;

import android.app.Activity;
import android.content.Context;

import com.future.wk.newcontacter.base.store.db.BaseDB;
import com.future.wk.newcontacter.base.store.db.ORMManager;
import com.future.wk.newcontacter.base.store.preference.NCPreference;
import com.future.wk.newcontacter.util.CoreCommonUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 缓存对象
 **/

public class NCObjectCache {
	
	private static NCObjectCache cache;
	
	private Map<String,Activity> activityTree = new HashMap<String,Activity>();
	
	public synchronized AppDBHelper getDBFromUserAccunt(String account){
		BaseDB xtionDB = ORMManager.getInstance().getSqliteHelper(account);
		if(xtionDB==null){
			xtionDB = new AppDBHelper(NCAppContext.getContext().getApplicationContext(),
					NCAppContext.getInstance().getDBname(account),
					CoreCommonUtil.getDbVersion(NCAppContext.getContext().getApplicationContext()));
			ORMManager.getInstance().setCurrentDBName(account);
			ORMManager.getInstance().addSqliteOpenHelper(account,xtionDB);
		}else{
			ORMManager.getInstance().setCurrentDBName(account);
		}
		return (AppDBHelper) xtionDB;
	}

	public AppDBHelper getDBFromUserAccunt(Context context){
		String account = NCPreference.getInstance(context).getLastOriginalAccount();
		return getDBFromUserAccunt(account);
	}
	
	public void closeAllDB(){
		ORMManager.getInstance().closeDB();
	}
	
	public static synchronized NCObjectCache getInstance(){
		if(cache==null){
			cache = new NCObjectCache();
		}
		return cache;
	}
	
	public void clearCache(){
		if(activityTree!=null)activityTree.clear();
		System.gc();
	}
	
	public void putActivityTree(Activity activity){
		String className = activity.getClass().getName();
		activityTree.put(className, activity);
	}
	
	public void removeActivityInTree(String className){
		if(activityTree.containsKey(className)){
			activityTree.remove(className);
		}
	}
	
	public Activity getActivityInTree(String className){
		return activityTree.get(className);
	}

	public Activity getTopActivityInTree() {
        Activity result = null;
		for (Entry<String, Activity> entry : activityTree.entrySet()) {
			result = entry.getValue();
		}
        return result;
	}
	
	public synchronized void finishAllActivity(){
		for (Entry<String, Activity> e : activityTree.entrySet()) {
			e.getValue().finish();
		}
	}
}