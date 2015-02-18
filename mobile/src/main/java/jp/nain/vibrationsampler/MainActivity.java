package jp.nain.vibrationsampler;

import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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

    @OnClick(R.id.button_vibration1)
    public void onClickVib1 (){
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        long[] pattern = {3000, 1000, 2000, 500, 3000, 1000}; // OFF, ON, OFF, ON, ...
        vibrator.vibrate(pattern, DISABLE_REPEATING);
    }
}
