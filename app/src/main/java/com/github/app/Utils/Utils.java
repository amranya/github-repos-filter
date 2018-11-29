package com.github.app.Utils;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;

import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.net.ssl.SSLContext;

/**
 * Created by iyadz_000 on 11/26/2018.
 */

public class Utils {

    public static String dateBefore(int days){

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, -days);
        String dateBefore = df.format(c.getTime());

        Log.d("TimeBefore", dateBefore);

        return dateBefore;

    }

    /**
     * Initialize SSL
     * @param mContext
     */
    public static void initializeSSLContext(Context mContext){
        try {
            SSLContext.getInstance("TLSv1.2");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            ProviderInstaller.installIfNeeded(mContext.getApplicationContext());
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }


}
