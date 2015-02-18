package jp.nain.vibrationsampler;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.WearableListenerService;

public class DataLayerListenerService extends WearableListenerService {
    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        Log.i("NAIN", "データチェンジド");
        for (DataEvent event : dataEvents) {
            if (event.getType() == DataEvent.TYPE_CHANGED) {
                DataItem item = event.getDataItem();
                if (item.getUri().getPath().compareTo("/push") == 0) {
                    Log.i("NAIN", "通知出すよ");
                    DataMapItem dataMapItem = DataMapItem.fromDataItem(item);
                    int index = dataMapItem.getDataMap().getInt("index");
                    Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("モバイルから通知")
                            .setContentText("サンプルテキスト")
                            .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS)
                            .setLocalOnly(true);

                    switch (index) {
                        case 1:
                            vibrator.vibrate(MainActivity.VIBRATE_PATTERN1, -1);
                            builder.setContentText("◯");
                            builder.setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.box_red));
                            break;
                        case 2:
                            vibrator.vibrate(MainActivity.VIBRATE_PATTERN2, -1);
                            builder.setContentText("△");
                            builder.setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.box_green));
                            break;
                        case 3:
                            vibrator.vibrate(MainActivity.VIBRATE_PATTERN3, -1);
                            builder.setContentText("×");
                            builder.setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.box_blue));
                            break;
                        case 4:
                            vibrator.vibrate(MainActivity.VIBRATE_PATTERN4, -1);
                            builder.setContentText("□");
                            builder.setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.box_yellow));
                            break;
                        default:break;
                    }
                    NotificationManagerCompat manager = NotificationManagerCompat.from(this);
                    manager.notify(0, builder.build());

                }
            }
        }
        dataEvents.release();
    }
}
