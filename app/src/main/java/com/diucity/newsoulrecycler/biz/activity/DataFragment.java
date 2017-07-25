package com.diucity.newsoulrecycler.biz.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.diucity.newsoulrecycler.MyApplication;
import com.diucity.newsoulrecycler.R;
import com.diucity.newsoulrecycler.base.BaseFragment;
import com.diucity.newsoulrecycler.biz.adapter.DataAdapter;
import com.diucity.newsoulrecycler.utils.SpUtils;

import butterknife.BindView;

/**
 * By：CH  on 2017/7/12 0012
 * <p>
 * email：576748006@qq.com
 */
public class DataFragment extends BaseFragment {

    @BindView(R.id.tv_data_number)
    TextView tv;

    @BindView(R.id.rv_data)
    RecyclerView rv;

    private DataAdapter adapter;

    @Override
    protected void loadData() {

    }

    @Override
    public void onResume() {
        setNumber();
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        setNumber();
    }

    @Override
    protected void init() {
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        adapter = new DataAdapter(getActivity(), null);
        rv.setAdapter(adapter);
        adapter.setListener(position -> {
            MyApplication.list.remove(position);
            SpUtils.save(getActivity());
            setNumber();
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_data;
    }

    @Override
    protected void initInject() {

    }

    public void setNumber() {
        tv.setText("待上传数据" + MyApplication.list.size() + "条");
        adapter.updateBySet(MyApplication.list);
    }
}
