package com.example.latigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private SensorManager sm;
    private Sensor s;
    private SensorEventListener evento;
    private int mov=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm=(SensorManager) getSystemService(Context.SENSOR_SERVICE); //Accede a los sensores
        s=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //Representa al acelerometro
        if(s==null){
            finish();
        }

        evento=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                //Codigo que se genera por el evento del acelerometro

                if(event.values[0]<-4&&mov==0) {
                    mov++;
                }else{
                    if(event.values[0]>4&&mov==1){
                        mov++;
                    }
                }
                if(mov==2){
                    mov=0;
                    sonido();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        sm.registerListener(evento,s,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void sonido(){
        MediaPlayer mp=MediaPlayer.create(this,R.raw.latigo);
        mp.start();
    }
}