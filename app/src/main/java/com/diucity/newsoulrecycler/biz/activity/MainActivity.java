package com.diucity.newsoulrecycler.biz.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.Toast;

import com.diucity.newsoulrecycler.R;
import com.diucity.newsoulrecycler.base.BaseActivity;
import com.smartdevicesdk.scanner.ScannerHelper3501;

public class MainActivity extends BaseActivity {

    private FragmentManager manager;
    private MainFragment mFrag;
    private DataFragment dFrag;
    private UploadFragment uFrag;


    public static String choosed_serial = "/dev/ttyMT0";// 串口名称
    public static int choosed_buad = 9600;// 波特率
    //String device= DeviceManage.getDevInfo("PDA3501").getScannerSerialport();
    //int baudrate=DeviceManage.getDevInfo("PDA3501").getScannerBaudrate();
    private static Handler handler = null;
    private ScannerHelper3501 helper;
    private MediaPlayer player;
    public static byte[] defaultSetting2D = new byte[]{0x16, 0x4D, 0x0D,
            0x25, 0x25, 0x25, 0x44, 0x45, 0x46, 0x2E};
    public static byte[] dataTypeFor2D = new byte[]{0x16, 0x4D, 0x0D, 0x38,
            0x32, 0x30, 0x32, 0x44, 0x30, 0x31, 0x2E};


    @Override
    protected void init() {
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (mFrag == null) {
            mFrag = new MainFragment();
            transaction.add(R.id.activity_main, mFrag);
        }
        if (dFrag == null) {
            dFrag = new DataFragment();
            transaction.add(R.id.activity_main, dFrag);
            transaction.hide(dFrag);
        }
        if (uFrag == null) {
            uFrag = new UploadFragment();
            transaction.add(R.id.activity_main, uFrag);
            transaction.hide(uFrag);
        }
        transaction.commit();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String barCodeStr = msg.obj.toString();
                player.start();
                if (barCodeStr.contains("http://qrcode.diucity.com/activesticker")) {
                    int i = 0;
                    try {
                        String[] split = barCodeStr.split("id=");
                        i = Integer.parseInt(split[split.length - 1]);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "无效二维码", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(MainActivity.this, "id" + i, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, CountActivity.class);
                    intent.putExtra("id", i);
                    startActivity(intent);

                } else {
                    Toast.makeText(MainActivity.this, "无效二维码", Toast.LENGTH_SHORT).show();
                }
            }
        };

        helper = new ScannerHelper3501(choosed_serial, choosed_buad, handler);
        helper.Write(defaultSetting2D);

        player = MediaPlayer.create(getApplicationContext(), R.raw.beep);
    }



    @Override
    protected void initInject() {
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }


    public void showFrag(int i) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(uFrag);
        transaction.hide(mFrag);
        transaction.hide(dFrag);
        switch (i) {
            case 131:
                transaction.show(dFrag);
                setTitle("F1-本机数据");
                break;
            case 132:
                transaction.show(uFrag);
                setTitle("F2-上传本机数据");
                break;
            default:
                transaction.show(mFrag);
                setTitle("废品回收");
                break;
        }
        transaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 131 || keyCode == 132) showFrag(keyCode);
        if (keyCode == 135) scan();
        return super.onKeyDown(keyCode, event);
    }

    public void scan() {
        helper.scan();
    }

    @Override
    public void onBackPressed() {
        if (mFrag.isHidden()) {
            showFrag(0);
        } else {
            helper.Close();
            super.onBackPressed();
        }
    }
}
