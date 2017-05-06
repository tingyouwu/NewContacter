package com.future.wk.newcontacter.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.future.wk.newcontacter.base.store.db.BaseDB;


/**
 * @Desc 应用数据库
 **/
public class AppDBHelper extends BaseDB {

	private Context context;

	public AppDBHelper(Context context, String dbName, int dbVersion) {
		super(context, dbName,dbVersion);
		System.out.println("---------------dbname = "+dbName+"--------------");

		try {
			getWritableDatabase(); // 读写方式打开数据库
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int i, int i1) {
	}
}
