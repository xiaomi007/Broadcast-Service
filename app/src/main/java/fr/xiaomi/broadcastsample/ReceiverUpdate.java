package fr.xiaomi.broadcastsample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by xiaomi on 14/12/19.
 */
public class ReceiverUpdate extends BroadcastReceiver {

    private static final String TAG = ReceiverUpdate.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "BR received:" + intent.toString());

        if(intent.hasExtra("update")){
            if (intent.getBooleanExtra("update", false)){
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("local"));
            }
        }



    }
}
