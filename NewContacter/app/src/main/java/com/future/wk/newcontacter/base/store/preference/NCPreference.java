package com.future.wk.newcontacter.base.store.preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author wty
 * ShareP 工具类  请在Application oncreate初始化
 **/
public class NCPreference {

	private static String PREFERENCES_NAME = "Preferences_NC";//preference名字
	public static String LastPassword = "lastPassword";//登陆密码
	public static String LastName = "lastname";//登陆名字
	public static String LastAccount = "laseAccount";//登录账户
	public static String LastOriginalAccount = "laseOriginalAccount";//由服务器分配的表示账号唯一性
	public static String IsAutoLogin = "IsAutoLogin";//是否自动登陆
	public static String LogoUrl = "logourl";//头像
	public static String IsFirstLogin = "isFirstLogin";//是否是第一次安装应用程序

	private SharedPreferences mSharedPreferences;
	private static volatile NCPreference sInstance = null;

	/**
	 * 单例模式，获取instance实例
	 */
	public static NCPreference getInstance(Context mcontext) {
		if (sInstance == null) {
			synchronized (NCPreference.class) {
				if (sInstance == null) {
					sInstance = new NCPreference(mcontext.getApplicationContext());
				}
			}
		}
		return sInstance;
	}

	private NCPreference(Context context) {
		mSharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
	}

	public void writePreferences(String key, String value){
		mSharedPreferences.edit().putString(key, value).commit();// 提交修改;
	}

	public void writePreferences(String key, Boolean value){
		mSharedPreferences.edit().putBoolean(key, value).commit();// 提交修改;
	}

	public String getLogoUrl(){
		return mSharedPreferences.getString(LogoUrl,null);
	}

	public String getLastPassword(){
        return mSharedPreferences.getString(LastPassword, null);
	}

	public String getLastAccount(){return mSharedPreferences.getString(LastAccount,null);}

	public String getLastOriginalAccount(){return mSharedPreferences.getString(LastOriginalAccount,null);}

	public boolean isAutoLogin(){
	    return mSharedPreferences.getBoolean(IsAutoLogin, false);
	}

	public String getLastName(){
		return mSharedPreferences.getString(LastName, null);
	}

	public boolean isFirstLogin(){
		return mSharedPreferences.getBoolean(IsFirstLogin,true);
	}
}
