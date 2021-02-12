package com.example.trivial;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {

    private static final String TAG_DEBUG = "midebug";

    MediaPlayer musica;
    private static int length;

    public MusicService() {

    }


    public void switchMusica() {
        if (musica.isPlaying()) {
            musica.pause();
        } else {
            musica.start();
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG_DEBUG, "Creando servicio");
        musica = MediaPlayer.create(this, R.raw.mii_theme);
        musica.setLooping(true);

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //inicia tarea
        Log.d(TAG_DEBUG, "Inicia servicio...");
//        tareaAEjecutar();
        musica.seekTo(length);
        musica.start();
        return START_STICKY;

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        length = musica.getCurrentPosition();
        musica.pause();
    }


}