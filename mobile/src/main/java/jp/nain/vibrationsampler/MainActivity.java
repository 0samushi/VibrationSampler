package jp.nain.vibrationsampler;

import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity {
    private static final int DISABLE_REPEATING = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.textView_vib1)
    public void onClickVib1 (){
        Log.i("NAIN", "クリック1");
        NotificationIntentService.startActionSend(this, 1);
    }
    @OnClick(R.id.textView_vib2)
    public void onClickVib2 () {
        Log.i("NAIN", "クリック2");
        NotificationIntentService.startActionSend(this, 2);
    }
    @OnClick(R.id.textView_vib3)
    public void onClickVib3 () {
        Log.i("NAIN", "クリック3");
        NotificationIntentService.startActionSend(this, 3);
    }
    @OnClick(R.id.textView_vib4)
    public void onClickVib4 () {
        Log.i("NAIN", "クリック4");
        NotificationIntentService.startActionSend(this, 4);
    }
}
