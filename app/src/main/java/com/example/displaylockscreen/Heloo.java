package com.example.displaylockscreen;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lockscreen.R;

public class Heloo  extends Activity {
    public static Heloo INSTANCE ;

    public static Heloo getInstance(){
        if (INSTANCE == null){
            INSTANCE = new Heloo();
        }
        return INSTANCE;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);
    }
}
