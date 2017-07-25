package com.diucity.newsoulrecycler.biz.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diucity.newsoulrecycler.R;
import com.diucity.newsoulrecycler.base.BaseAdapter;
import com.diucity.newsoulrecycler.base.ViewHolder;
import com.diucity.newsoulrecycler.entity.UpLoadBean;
import com.diucity.newsoulrecycler.utils.SpUtils;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;


/**
 * By：CH  on 2017/7/12 0012
 * <p>
 * email：576748006@qq.com
 */
public class DataAdapter extends BaseAdapter<UpLoadBean.ItemsBean> {
    private AlertDialog dialog;
    private int longPress;
    private LongClick listener;

    public DataAdapter(Context context, List<UpLoadBean.ItemsBean> model) {
        super(context, model);
        initDialog();
    }

    private void initDialog() {
        dialog = new AlertDialog.Builder(getContext())
                .setMessage("删除该条数据?")
                .setPositiveButton(android.R.string.cancel, (anInterface, i) -> anInterface.dismiss())
                .setNegativeButton(android.R.string.ok, ((dialog1, which) -> {
                    remove(longPress);
                    dialog1.dismiss();
                })).create();
    }

    @Override
    public int getId() {
        return R.layout.adapter_data;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RxView.longClicks(holder.itemView).subscribe(o -> {
            dialog.show();
            longPress = position;
        });
        TextView type = holder.getView(R.id.adapter_tv_data_type);
        String str;
        switch (getModel().get(position).getType()){
            case 1:
                str="未分类";
                break;
            case 2:
                str="其他";
                break;
            case 3:
                str="纸张";
                break;
            case 4:
                str="易拉罐";
                break;
            case 5:
                str="玻璃瓶";
                break;
            case 6:
                str="塑料瓶";
                break;
            case 7:
                str="纸箱";
                break;
            default:
                str = "未分类";
                break;
        }
        type.setText(str);
        TextView id = holder.getView(R.id.adapter_tv_data_id);
        id.setText(getModel().get(position).getCode()+"");
        TextView score = holder.getView(R.id.adapter_tv_data_score);
        score.setText(getModel().get(position).getIntegral()+"");
    }

    public void setListener(LongClick listener) {
        this.listener = listener;
    }

    public void remove(int postion){
        if (listener!=null) listener.click(postion);
    }

    public interface LongClick{
        void click(int position);
    }
}
