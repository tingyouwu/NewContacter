package com.future.wk.newcontacter.base;

import android.app.Application;
import android.text.TextUtils;

import com.future.wk.newcontacter.base.store.preference.NCPreference;
import com.orhanobut.logger.Logger;

/**
 * 应用 Application
 **/
public class NCApplication extends Application {

	public static final String LocalTag = "通讯录";
	public static final String NetworkTag = "微服务";
	public static final String MyselfTag = "设置";

	@Override
	public void onCreate() {
		super.onCreate();
		NCAppContext.setContext(getApplicationContext());
		//创建一下公共数据库
		NCObjectCache.getInstance().getDBFromUserAccunt(NCAppContext.DB_COMMON);

		//检查数据库升级
		String lastOriginalAccount = NCPreference.getInstance(this).getLastOriginalAccount();
		if(!TextUtils.isEmpty(lastOriginalAccount)){
			NCObjectCache.getInstance().getDBFromUserAccunt(lastOriginalAccount);
		}

		Logger.init("NewContacter")               // default tag : PRETTYLOGGER or use just init()
                .methodCount(10)
				.hideThreadInfo();  // default : LogLevel.FULL
	}
}
