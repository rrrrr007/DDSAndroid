package com.diucity.newsoulrecycler.biz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.diucity.newsoulrecycler.R;
import com.diucity.newsoulrecycler.base.BaseAdapter;
import com.diucity.newsoulrecycler.base.ViewHolder;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;

/**
 * By：CH  on 2017/7/13 0013
 * <p>
 * email：576748006@qq.com
 */
public class CountAdapter extends BaseAdapter<String> {
    ViewHolder enable;
    private int index = 0;

    public CountAdapter(Context context, List<String> model) {
        super(context, model);
    }

    @Override
    public int getId() {
        return R.layout.adapter_count;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.e("ch","bind"+position);
        TextView tv = holder.getView(R.id.adapter_tv_count);
        if (position == 0) {
            tv.setEnabled(false);
            if (enable==null) enable = holder;
        }
        tv.setText(getModel().get(position));
        RxView.clicks(tv).subscribe(o -> {
            index = position;
            tv.setEnabled(false);
            enable.getView(R.id.adapter_tv_count).setEnabled(true);
            enable = holder;
        });

    }

    public int getIndex() {
        return index+1;
    }
}
