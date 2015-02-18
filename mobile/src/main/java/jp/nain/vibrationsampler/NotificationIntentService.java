package jp.nain.vibrationsampler;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import java.util.Calendar;

public class NotificationIntentService extends IntentService {
    private static final String ACTION_SEND = "jp.nain.vibrationsampler.action.SEND";
    private static final String EXTRA_INDEX = "jp.nain.vibrationsampler.extra.INDEX";

    public static void startActionSend(Context context, int index) {
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.setAction(ACTION_SEND);
        intent.putExtra(EXTRA_INDEX, index);
        context.startService(intent);
    }

    public NotificationIntentService() {
        super("NotificationIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SEND.equals(action)) {
                final int param2 = intent.getIntExtra(EXTRA_INDEX, -1);
                handleActionSend(param2);
            }
        }
    }

    private void handleActionSend(int index) {
        //現在の時刻を取得
        Calendar calendar = Calendar.getInstance();

        //DataApiを用いてwear側にPush通知が来たことを知らせる
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .addApi(Wearable.API)
                .build();
        googleApiClient.blockingConnect();
        PutDataMapRequest dataMapRequest = PutDataMapRequest.create("/push");
        dataMapRequest.getDataMap().putInt("index", index);
        dataMapRequest.getDataMap().putString("time", calendar.getTime().toString());
        PutDataRequest request = dataMapRequest.asPutDataRequest();
        Wearable.DataApi.putDataItem(googleApiClient, request);
        googleApiClient.disconnect();
        Log.i("NAIN", "通知送りました");
    }
}
