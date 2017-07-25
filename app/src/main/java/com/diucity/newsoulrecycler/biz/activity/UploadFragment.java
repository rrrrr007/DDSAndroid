package com.diucity.newsoulrecycler.biz.activity;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.diucity.newsoulrecycler.MyApplication;
import com.diucity.newsoulrecycler.R;
import com.diucity.newsoulrecycler.api.ApiManager;
import com.diucity.newsoulrecycler.api.Callback;
import com.diucity.newsoulrecycler.base.BaseFragment;
import com.diucity.newsoulrecycler.entity.NormalBack;
import com.diucity.newsoulrecycler.entity.UpLoadBack;
import com.diucity.newsoulrecycler.entity.UpLoadBean;
import com.diucity.newsoulrecycler.utils.GsonUtils;
import com.diucity.newsoulrecycler.utils.SpUtils;
import com.diucity.newsoulrecycler.utils.ToastUtils;
import com.diucity.newsoulrecycler.widget.CirclePercentView;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;


import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * By：CH  on 2017/7/12 0012
 * <p>
 * email：576748006@qq.com
 */
public class UploadFragment extends BaseFragment {

    private ApiManager manager;

    @BindView(R.id.cpv_upload)
    CirclePercentView cpv;

    @BindView(R.id.tv_upload_number)
    TextView tv;

    private CompositeDisposable mCompositeDisposable;

    private boolean isLoading =false;

    @Override
    protected void loadData() {
        RxView.clicks(cpv).throttleFirst(2, TimeUnit.SECONDS).subscribe(aVoid -> {
            if (MyApplication.list.size()==0){
                ToastUtils.showShort("没有数据可上传");
                return;
            }
            cpv.setEnabled(false);
            isLoading = true;
            cpv.setPercent(88);
            String str = GsonUtils.serialize(new UpLoadBean(getActivity(), MyApplication.list));
            HashMap<String, String> map = new HashMap<>();
            map.put(str, "");
            manager.getApi().upload(map).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Callback<UpLoadBack>() {
                        @Override
                        protected void subscribe(Disposable d) {
                            mCompositeDisposable.add(d);
                        }

                        @Override
                        protected void onFailure() {
                            cpv.reSet();
                            cpv.setEnabled(true);
                            isLoading = false;
                        }

                        @Override
                        protected void onSuccess(UpLoadBack back) {
                            Log.e("ch", GsonUtils.serialize(back));
                            ToastUtils.showShort(back.getResult().getMessage());
                            if (back.getResult().getCode() == 0) {
                                cpv.setPercent(100);
                                MyApplication.list.clear();
                                SpUtils.save(getActivity());
                                setNumber();
                            }else {
                                cpv.reSet();
                                cpv.setEnabled(true);
                            }
                            isLoading = false;
                        }
                    });
        });
    }

    @Override
    protected void init() {
        mCompositeDisposable = new CompositeDisposable();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.frag_upload;
    }

    @Override
    protected void initInject() {
        manager = MyApplication.getInstance().getAppComponent().apiManager();
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

    public void setNumber() {
        if (!isLoading){
            cpv.reSet();
            cpv.setEnabled(true);
        }
        tv.setText("待上传数据" + MyApplication.list.size() + "条");
    }

    @Override
    public void onDestroy() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();//保证activity结束时取消所有正在执行的订阅
        }
        super.onDestroy();
    }
}
