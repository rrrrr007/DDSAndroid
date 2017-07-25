package com.diucity.newsoulrecycler.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/1/6 0006.
 */


public class PhoneUtils {
    public static final String marshmallowMacAddress = "02:00:00:00:00:00";
    private static final String fileAddressMac = "/sys/class/net/wlan0/address";

    public static String getMacAddress(Context context) {
        WifiManager wifiMan = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = (null == wifiMan ? null : wifiMan.getConnectionInfo());
        if (!wifiMan.isWifiEnabled()){
            wifiMan.setWifiEnabled(true);
            wifiMan.setWifiEnabled(false);
        }
        return getReplaceString(info.getMacAddress());
       /* if (info.getMacAddress().equalsIgnoreCase(marshmallowMacAddress)){
            String ret = null;
            try {
                ret = getAdressMacByInterface();
                if (TextUtils.isEmpty(ret)) {
                    Log.d("ch", "byinterface");
                    return getReplaceString(ret);

                } else {
                    ret = getAddressMacByFile(wifiMan);
                    Log.d("ch", "file");
                    return getReplaceString(ret);

                }
            } catch (Exception e) {
                Log.e("MobileAcces", "Erreur lecture propriete Adresse MAC ");
                return getReplaceString(marshmallowMacAddress);
            }
        }else {
            return getReplaceString(info.getMacAddress());
        }*/
    }



    private static String getAdressMacByInterface() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (nif.getName().equalsIgnoreCase("wlan0")) {
                    byte[] macBytes = nif.getHardwareAddress();
                    if (macBytes == null) {
                        return "";
                    }

                    StringBuilder res1 = new StringBuilder();
                    for (byte b : macBytes) {
                        res1.append(String.format("%02X:", b));
                    }

                    if (res1.length() > 0) {
                        res1.deleteCharAt(res1.length() - 1);
                    }
                    return res1.toString();
                }
            }

        } catch (Exception e) {
            Log.e("MobileAcces", "Erreur lecture propriete Adresse MAC ");
        }
        return null;
    }

    /**
     * 替换字符
     *
     * @param content
     * @return
     */
    private static String getReplaceString(String content) {
        return content.replace(":", "").replace(" ", "").replace("\n", "").replace("\r", "");
    }

    private static String getAddressMacByFile(WifiManager wifiMan) throws Exception {
        String ret;
        wifiMan.setWifiEnabled(true);
        File fl = new File(fileAddressMac);
        FileInputStream fin = new FileInputStream(fl);
        ret = crunchifyGetStringFromStream(fin);
        fin.close();
        return ret;
    }

    // ConvertStreamToString() Utility - we name it as crunchifyGetStringFromStream()
    private static String crunchifyGetStringFromStream(InputStream crunchifyStream) throws IOException {
        if (crunchifyStream != null) {
            Writer crunchifyWriter = new StringWriter();

            char[] crunchifyBuffer = new char[2048];
            try {
                Reader crunchifyReader = new BufferedReader(new InputStreamReader(crunchifyStream, "UTF-8"));
                int counter;
                while ((counter = crunchifyReader.read(crunchifyBuffer)) != -1) {
                    crunchifyWriter.write(crunchifyBuffer, 0, counter);
                }
            } finally {
                crunchifyStream.close();
            }
            return crunchifyWriter.toString();
        } else {
            return "No Contents";
        }
    }
}
