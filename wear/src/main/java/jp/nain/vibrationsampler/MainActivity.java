package jp.nain.vibrationsampler;

import android.app.Activity;
import android.app.Notification;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends Activity {

    //-------------------------------------------------
    // 定数
    //-------------------------------------------------
    /** バイブレーションをリピートしない */
    private static final int DISABLE_REPEATING = -1;
    /** バイブレーションをミリ秒単位で指定。 {OFF, ON, OFF, ON, ...} */
    private static final long[] VIBRATE_PATTERN1 = {0, 200, 300, 200, 300, 200};
    private static final long[] VIBRATE_PATTERN2 = {0, 800, 300, 800};
    private static final long[] VIBRATE_PATTERN3 = {0, 1600};
    private static final long[] VIBRATE_PATTERN4 = {0, 400};

    //-------------------------------------------------
    // フィールド
    //-------------------------------------------------
    private Vibrator vibrator;


    //-------------------------------------------------
    // ライフサイクル
    //-------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(MainActivity.this);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    }

    @OnClick(R.id.textView_vib1)
    public void onClickVib1() {
        vibrator.vibrate(VIBRATE_PATTERN1, DISABLE_REPEATING);
    }
    @OnClick(R.id.textView_vib2)
    public void onClickVib2() {
        vibrator.vibrate(VIBRATE_PATTERN2, DISABLE_REPEATING);
    }
    @OnClick(R.id.textView_vib3)
    public void onClickVib3() {
        vibrator.vibrate(VIBRATE_PATTERN3, DISABLE_REPEATING);
    }
    @OnClick(R.id.textView_vib4)
    public void onClick4() {
        vibrator.vibrate(VIBRATE_PATTERN4, DISABLE_REPEATING);
    }
}
