package com.dragon.navigation.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dragon.navigation.Model.SearchpoiEntity;
import com.dragon.navigation.Model.TravelingEntity;
import com.dragon.navigation.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sunfusheng on 16/4/20.
 */
public class SearchpoiAdapter extends BaseListAdapter<SearchpoiEntity> {

    private boolean isNoData;
    private int mHeight;
    public static final int ONE_SCREEN_COUNT = 7; // 一屏能显示的个数，这个根据屏幕高度和各自的需求定
    public static final int ONE_REQUEST_COUNT = 10; // 一次请求的个数

    public SearchpoiAdapter(Context context) {
        super(context);
    }

    public SearchpoiAdapter(Context context, List<SearchpoiEntity> list) {
        super(context, list);
    }

    // 设置数据
    public void setData(List<SearchpoiEntity> list) {
        clearAll();
        addALL(list);

        isNoData = false;
        if (list.size() == 1 && list.get(0).isNoData()) {
            // 暂无数据布局
            isNoData = list.get(0).isNoData();
            mHeight = list.get(0).getHeight();
        } else {
            // 添加空数据
            if (list.size() < ONE_SCREEN_COUNT) {
                addALL(createEmptyList(ONE_SCREEN_COUNT - list.size()));
            }
        }
        notifyDataSetChanged();
    }

    // 创建不满一屏的空数据
    public List<SearchpoiEntity> createEmptyList(int size) {
        List<SearchpoiEntity> emptyList = new ArrayList<>();
        if (size <= 0) return emptyList;
        for (int i=0; i<size; i++) {
            emptyList.add(new SearchpoiEntity());
        }
        return emptyList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 暂无数据
        if (isNoData) {
            convertView = mInflater.inflate(R.layout.item_no_data_layout, null);
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeight);
            RelativeLayout rootView = ButterKnife.findById(convertView, R.id.rl_root_view);
            rootView.setLayoutParams(params);
            return convertView;
        }

        // 正常数据
        final ViewHolder holder;
        if (convertView != null && convertView instanceof LinearLayout) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.item_searchpoi, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        SearchpoiEntity entity = getItem(position);
        holder.searchpoi_root_view.setVisibility(View.VISIBLE);
        holder.poiname.setText(entity.getPoiName());
        holder.poides.setText(entity.getPoiDes());
        holder.poidis.setText(String.valueOf(entity.getDistance()));
        String Maintype = entity.getPoiType().substring(0,4);
        //前四个字，（0，3），不包括3，故应为（0，4）
        Log.i("ds",Maintype);
        switch (Maintype){
           case "餐饮服务":
                holder.poiImage.setImageResource(R.drawable.ar_meishi);
               break;
            case "商务住宅":
                holder.poiImage.setImageResource(R.drawable.zhuzhai);
                break;
            case "科教文化":
                holder.poiImage.setImageResource(R.drawable.jiaoyu);
                break;
            case "公共设施":
                holder.poiImage.setImageResource(R.drawable.ar_cesuo);
                break;
            case "购物服务":
                holder.poiImage.setImageResource(R.drawable.ar_chaoshi);
                break;
            case "风景名胜":
                holder.poiImage.setImageResource(R.drawable.ar_fengjing);
                break;
            case  "交通设施":
                holder.poiImage.setImageResource(R.drawable.ar_gongjiao);
                break;
            case  "医疗保健":
                holder.poiImage.setImageResource(R.drawable.yiliao);
                break;
            case  "金融保险":
                holder.poiImage.setImageResource(R.drawable.yinhang);
                break;
            default:
                holder.poiImage.setImageResource(R.drawable.ar_qita);
                break;

        }

        //mImageManager.loadUrlImage(entity.getImage_url(), holder.poiImage);

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.searchpoi_root_view)
        LinearLayout searchpoi_root_view;
        @BindView(R.id.poi_image)
        ImageView poiImage;
        @BindView(R.id.poi_name)
        TextView poiname;
        @BindView(R.id.poi_description)
        TextView poides;
        @BindView(R.id.poi_dis)
        TextView poidis;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
