package com.example.displaylockscreen;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;

import androidx.annotation.RequiresApi;

import com.lockscreen.R;

public class LockScreenAppActivity extends Activity {

    /**
     * Called when the activity is first created.
     */



    KeyguardManager.KeyguardLock k1;
    int windowwidth;
    int windowheight;
    ImageView droid, home;
    int home_x, home_y;
    int[] droidpos;

    private LayoutParams layoutParams;

    @Override
    public void onAttachedToWindow() {
        // TODO Auto-generated method stub


        super.onAttachedToWindow();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG | WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.main);
        droid = (ImageView) findViewById(R.id.droid);


        System.out.println("measured width" + droid.getMeasuredWidth());
        System.out.println(" width" + droid.getWidth());


        if (getIntent() != null && getIntent().hasExtra("kill") && getIntent().getExtras().getInt("kill") == 1) {

            finish();
        }

        try {
            // initialize receiver


        //    startService(new Intent(this, MyService.class));


            Intent notificationIntent = new Intent(this, MyService.class);

            startForegroundService(notificationIntent);



  /*      KeyguardManager km =(KeyguardManager)getSystemService(KEYGUARD_SERVICE);
        k1 = km.newKeyguardLock("IN");
        k1.disableKeyguard();*/
            StateListener phoneStateListener = new StateListener();
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);

            windowwidth = getWindowManager().getDefaultDisplay().getWidth();
            System.out.println("windowwidth" + windowwidth);
            windowheight = getWindowManager().getDefaultDisplay().getHeight();
            System.out.println("windowheight" + windowheight);

            MarginLayoutParams marginParams2 = new MarginLayoutParams(droid.getLayoutParams());

            marginParams2.setMargins((windowwidth / 24) * 10, ((windowheight / 32) * 8), 0, 0);

            LayoutParams layoutdroid = new LayoutParams(marginParams2);

            droid.setLayoutParams(layoutdroid);

            LinearLayout homelinear = (LinearLayout) findViewById(R.id.homelinearlayout);
            homelinear.setPadding(0, 0, 0, (windowheight / 32) * 3);
            home = (ImageView) findViewById(R.id.home);

            MarginLayoutParams marginParams1 = new MarginLayoutParams(home.getLayoutParams());

            marginParams1.setMargins((windowwidth / 24) * 10, 0, (windowheight / 32) * 8, 0);
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(marginParams1);

            home.setLayoutParams(layout);


            home.setOnClickListener(v -> finish());

            droid.setOnClickListener(v -> {
                Intent intent = new Intent(this, Heloo.class);
                startActivity(intent);
            });

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    class StateListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING:
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    System.out.println("call Activity off hook");
                    finish();


                    break;
                case TelephonyManager.CALL_STATE_IDLE:
                    break;
            }
        }
    }


    @Override
    public void onBackPressed() {
        // Don't allow back to dismiss.
        return;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) || (keyCode == KeyEvent.KEYCODE_POWER) || (keyCode == KeyEvent.KEYCODE_VOLUME_UP) || (keyCode == KeyEvent.KEYCODE_CAMERA)) {
            //this is where I can do my stuff
            return true; //because I handled the event
        }
        if ((keyCode == KeyEvent.KEYCODE_HOME)) {

            return true;
        }
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            return true;
        }

        return false;

    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_POWER || (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN) || (event.getKeyCode() == KeyEvent.KEYCODE_POWER)) {
            //Intent i = new Intent(this, NewActivity.class);
            //startActivity(i);
            return false;
        }
        if ((event.getKeyCode() == KeyEvent.KEYCODE_HOME)) {
            System.out.println("alokkkkkkkkkkkkkkkkk");
            return true;
        }
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }
}