package com.example.displaylockscreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;


public class lockScreenReeiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {




        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {

         ;
            Intent intent11 = new Intent(context, LockScreenAppActivity.class);
            intent11.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent11.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            context.startActivity(intent11);



        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {


            Intent intent11 = new Intent(context, LockScreenAppActivity.class);
            intent11.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent11.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent11);
        } else if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {

            Intent intent11 = new Intent(context, LockScreenAppActivity.class);

            intent11.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent11.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent11);


        }

    }


}
