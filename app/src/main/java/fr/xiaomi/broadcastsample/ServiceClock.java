package fr.xiaomi.broadcastsample;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by xiaomi on 14/12/19.
 */
public class ServiceClock extends Service {

    private static final String TAG = SystemClock.class.getSimpleName();

    private Subscription subscription;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {

        if (intent.getBooleanExtra("start", false)) {

            subscription = Observable.interval(1, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
                @Override
                public void call(Long aLong) {
                    Log.d(TAG, "obs:" + aLong);
                    Intent intent1 = new Intent(getApplication(), ReceiverUpdate.class);
                    intent1.putExtra("update", true);
                    sendBroadcast(intent1);
                }
            });


        } else {
            Log.d(TAG, "stop service");

            if (subscription != null && !subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }

            stopSelf();
        }

        return super.onStartCommand(intent, flags, startId);
    }
}
