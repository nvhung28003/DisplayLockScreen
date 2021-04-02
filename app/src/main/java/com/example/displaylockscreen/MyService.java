package com.example.displaylockscreen;

import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class MyService extends Service{
	 BroadcastReceiver mReceiver;
	// Intent myIntent;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}


@Override
public void onCreate() {

	new CountDownTimer(300000, 1000) {

		public void onTick(long millisUntilFinished) {
			Log.d("!121", "onTick: ");
			//here you can have your logic to set text to edittext
		}

		public void onFinish() {
			Log.d("!121", "done: ");
		}

	}.start();

	if (Build.VERSION.SDK_INT >= 26) {
		String CHANNEL_ID = "my_channel_01";
		NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
				"Channel human readable title",
				NotificationManager.IMPORTANCE_DEFAULT);

		((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

		Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
				.setContentTitle("")
				.setContentText("").build();

		startForeground(1, notification);

	}
	 KeyguardManager.KeyguardLock k1;


	 KeyguardManager km =(KeyguardManager)getSystemService(KEYGUARD_SERVICE);
     k1= km.newKeyguardLock("IN");
     k1.disableKeyguard();




     IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
     filter.addAction(Intent.ACTION_SCREEN_OFF);

     mReceiver = new lockScreenReeiver();
     registerReceiver(mReceiver, filter);


    super.onCreate();


}


	@Override
public void onStart(Intent intent, int startId) {
	// TODO Auto-generated method stub

	super.onStart(intent, startId);
}



@Override
public void onDestroy() {
	unregisterReceiver(mReceiver);
	super.onDestroy();
}
}
