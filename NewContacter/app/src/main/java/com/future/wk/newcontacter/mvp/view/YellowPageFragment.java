package com.future.wk.newcontacter.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.future.wk.newcontacter.R;
import com.future.wk.newcontacter.activity.YellowPageDetailActivity;
import com.future.wk.newcontacter.base.BaseFragment;
import com.future.wk.newcontacter.mvp.contract.IYellowPageContract;
import com.future.wk.newcontacter.mvp.presenter.YellowPagePresenter;
import com.future.wk.newcontacter.util.YellowPageUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

import static com.future.wk.newcontacter.util.YellowPageUtil.YPItemNum;

/**
 * Created by kuangminan on 2017/5/14.
 */
public class YellowPageFragment extends BaseFragment<YellowPagePresenter> implements IYellowPageContract.IYellowPageView {

    @Bind(R.id.yellowpage_gridview)
    GridView mGridView;

    private String TAG = "YellowPageFragment";
    private String IMAGE_ITEM = "imgage_item";
    private String TEXT_ITEM = "text_item";
    private String ID_ITEM = "ID_item";
    private List<Map<String,Object>> data_list;
    private SimpleAdapter simpleAdapter;


    @Override
    public YellowPagePresenter getPresenter(){
        return new YellowPagePresenter();
    }

    @Override
    public void onInitView(Bundle savedInstanceState){

        SimpleAdapter mImageItems = new SimpleAdapter(getActivity(),
                getGridViewData(),
                R.layout.grideview_item,
                new String[] { IMAGE_ITEM, TEXT_ITEM },
                new int[] { R.id.image, R.id.text });


        mGridView.setAdapter(mImageItems);
        mGridView.setOnItemClickListener(onGrideItemClick);
    }

    /**
     * 获取GridView的数据
     */
    private List<HashMap<String, Object>> getGridViewData() {
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

        for (int i = 0; i < YPItemNum; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(IMAGE_ITEM, YellowPageUtil.YPIcon[i]);
            map.put(TEXT_ITEM, YellowPageUtil.YPIconName[i]);
            map.put(ID_ITEM, YellowPageUtil.YPIconID[i]);
            list.add(map);
        }
        return list;
    }

    public final AdapterView.OnItemClickListener onGrideItemClick = new  AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long rowid){
            // 根据元素位置获取对应的值
            HashMap<String, Object> item = (HashMap<String, Object>) adapter.getItemAtPosition(position);


            String itemText=(String)item.get(TEXT_ITEM);
            String itemID = (String)item.get(ID_ITEM);
            Toast.makeText(getActivity(),"You Select "+itemText,Toast.LENGTH_SHORT).show();
            /*start the detail activity */
            Intent YPDetailIntent = new Intent(getActivity(), YellowPageDetailActivity.class);
            YPDetailIntent.putExtra("itemID",itemID);
            Log.d(TAG,"start activity itemID:"+itemID);
            startActivity(YPDetailIntent);
        }};


    @Override
    public int getLayoutResource(){
        return R.layout.fragment_yellowpage;
    }

    @Override
    public void showYellowPageList(){
        return;
    }
}
