package com.diucity.newsoulrecycler.biz.activity;


import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.diucity.newsoulrecycler.MyApplication;
import com.diucity.newsoulrecycler.R;
import com.diucity.newsoulrecycler.base.BaseActivity;
import com.diucity.newsoulrecycler.biz.adapter.CountAdapter;
import com.diucity.newsoulrecycler.entity.UpLoadBean;
import com.diucity.newsoulrecycler.utils.SpUtils;
import com.diucity.newsoulrecycler.widget.WrapContentGridLayoutManager;
import com.jakewharton.rxbinding2.view.RxView;


import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;

public class CountActivity extends BaseActivity {

    @BindView(R.id.et_count)
    EditText et;

    @BindView(R.id.tv_count_clear)
    TextView clear;

    @BindView(R.id.rv_count_kinds)
    RecyclerView rv;

    private CountAdapter adapter;
    @Override
    protected void init() {
        setTitle("SCAN-采集数据");
        et.requestFocus();
        et.setInputType(InputType.TYPE_NULL);
        et.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction()==KeyEvent.ACTION_UP){
                if (keyCode==66){
                    UpLoadBean.ItemsBean bean = new UpLoadBean.ItemsBean(getIntent().getIntExtra("id", 0), adapter.getIndex(), Integer.parseInt(et.getText().toString()));
                    MyApplication.list.add(bean);
                    SpUtils.save(this);
                    finish();
                    return true;
                }
            }
            return false;
        });
        RxView.clicks(clear).subscribe(aVoid -> et.setText(""));
        ArrayList<String> list = new ArrayList<>(Arrays.asList("未分类", "其他", "纸张","易拉罐","玻璃瓶","塑料瓶","纸箱"));
        rv.setLayoutManager(new WrapContentGridLayoutManager(this, 4));
        adapter = new CountAdapter(this, list);
        rv.setAdapter(adapter);
    }

    @Override
    protected void initInject() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_count;
    }

}
