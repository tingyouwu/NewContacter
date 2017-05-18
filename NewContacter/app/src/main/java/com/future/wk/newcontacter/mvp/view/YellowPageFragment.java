package com.future.wk.newcontacter.mvp.view;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by kuangminan on 2017/5/14.
 */
public class YellowPageFragment extends BaseFragment<YellowPagePresenter> implements IYellowPageContract.IYellowPageView {

    @Bind(R.id.yellowpage_gridview)
    GridView mGridView;

    private String IMAGE_ITEM = "imgage_item";
    private String TEXT_ITEM = "text_item";
    private List<Map<String,Object>> data_list;
    private SimpleAdapter simpleAdapter;

    private int[] icon={R.mipmap.icon_min_head, R.mipmap.icon_min_head, R.mipmap.icon_min_head,R.mipmap.icon_min_head, R.mipmap.icon_min_head, R.mipmap.icon_min_head,
            R.mipmap.icon_min_head, R.mipmap.icon_min_head, R.mipmap.icon_min_head,R.mipmap.icon_min_head, R.mipmap.icon_min_head, R.mipmap.icon_min_head,
            R.mipmap.icon_min_head, R.mipmap.icon_min_head, R.mipmap.icon_min_head,R.mipmap.icon_min_head, R.mipmap.icon_min_head};
    private String[] iconName={"快递","出租车","银行","美食","娱乐","酒店","列车","旅行","租车","汽车","运营商","网购","健康","市政","教育","游戏","保险",};

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

        for (int i=0; i<16; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(IMAGE_ITEM, icon[i]);
            map.put(TEXT_ITEM, iconName[i]);
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
            Object object=item.get(IMAGE_ITEM);
            Toast.makeText(getActivity(),"You Select "+itemText,Toast.LENGTH_SHORT).show();
                /*start the detail activity */
            Intent YPDetailIntent = new Intent(getActivity(), YellowPageDetailActivity.class);
            YPDetailIntent.putExtra("detailName",itemText);
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
