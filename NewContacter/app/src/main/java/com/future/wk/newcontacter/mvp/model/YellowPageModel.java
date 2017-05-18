package com.future.wk.newcontacter.mvp.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.future.wk.newcontacter.data.dalex.ContactDALEx;
import com.future.wk.newcontacter.mvp.contract.IYellowPageContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kuangminan on 2017/5/14.
 */
public class YellowPageModel implements IYellowPageContract.IYellowPageModel {

    private String TAG = "YellowPageModel";

    @Override
    public void geYellowPageList(){
        return;
    }

    @Override
    public List<ContactDALEx> getYellowPageDetail(Context context, String category ){
        // 从Asset文件中找到文件，获取json字符串
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;
        List<ContactDALEx> contactList = new ArrayList<ContactDALEx>();
        try{
            inputStream = assetManager.open("YP.json");
            String jsonStr = readInPutStream(inputStream);

            JSONArray jsonArray = new JSONArray(jsonStr);
            int length = jsonArray.length();
            Log.d(TAG,"length:"+length);
            for(int i = 0; i < length; i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //JSONObject jsonkuaidi = jsonObject.getJSONObject("kuaidi");
                String item = jsonObject.getString("item");
                String name = jsonObject.getString("name");
                String number = jsonObject.getString("number");

                Log.d(TAG,"Item:"+item);
                Log.d(TAG,"name:"+name);
                Log.d(TAG,"number:"+number);

                ContactDALEx contactDALEx = new ContactDALEx();
                contactDALEx.setUsername(name);
                contactDALEx.setUserphone(number);
                contactList.add(contactDALEx);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return contactList;
    }

    // 从字节流中读取数据转换为String字符串
    public String readInPutStream(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        StringBuilder stringBuilder = new StringBuilder();
        try{
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(reader != null){
                try{
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }
}
