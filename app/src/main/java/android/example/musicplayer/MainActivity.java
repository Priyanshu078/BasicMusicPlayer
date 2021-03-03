package android.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    String SongName = "";
    String ArtistName = "";
    float currentTime;
    float totalDuration;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playPause = (Button) findViewById(R.id.playPause);
        Button up = (Button) findViewById(R.id.up);
        Button down = (Button) findViewById(R.id.down);
        Button backward = (Button) findViewById(R.id.backward);
        Button forward = (Button) findViewById(R.id.forward);
        TextView time = (TextView) findViewById(R.id.time);
        TextView duration = (TextView) findViewById(R.id.duration);

        MediaPlayer song1 = MediaPlayer.create(MainActivity.this,R.raw.ring1);
        MediaPlayer song2 = MediaPlayer.create(MainActivity.this,R.raw.ring2);
//        MediaPlayer song3 = MediaPlayer.create(MainActivity.this,R.raw.ring3);
//        MediaPlayer song4 = MediaPlayer.create(MainActivity.this,R.raw.ring4);
//        MediaPlayer song5 = MediaPlayer.create(MainActivity.this,R.raw.ring5);

        AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
//        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
//        seekBar.setClickable(true);
//        Handler myhandler = new Handler();

        currentTime = song1.getCurrentPosition()/1000;
        totalDuration = song1.getDuration()/1000;
        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                song2.stop();
                i = i+1;
                if(i%2 == 1) {
                    song1.start();
                    playPause.setText("" + "pause");
                }
                else{
                    song1.pause();
                    playPause.setText(""+ "play");
                }
                currentTime = song1.getCurrentPosition()/1000;
                time.setText(""+ currentTime);
                duration.setText(""+ totalDuration);

//                seekBar.setProgress((int)currentTime);
//                myhandler.postDelayed(UpdateSongTime,100);
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioManager.adjustVolume(AudioManager.ADJUST_LOWER,AudioManager.FLAG_PLAY_SOUND);
//                song1.stop();
//                song2.start();
//                i = i+1;
//                if(i%2 == 1) {
//                    song2.start();
//                    playPause.setText("" + "pause");
//                }
//                else{
//                    song2.pause();
//                    playPause.setText(""+ "play");
//                }
//                currentTime = song2.getCurrentPosition()/1000;
//                totalDuration = song2.getDuration()/1000;
//                time.setText(""+ currentTime);
//                duration.setText(""+ totalDuration);
            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioManager.adjustVolume(AudioManager.ADJUST_RAISE,AudioManager.FLAG_PLAY_SOUND);
            }
        });

        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int backwardTime = 2000;
                currentTime = song1.getCurrentPosition()/1000;
                int currenttime = song1.getCurrentPosition();
                if((currenttime - backwardTime) > 0){
                    song1.seekTo(currenttime - backwardTime);
                    Toast toast = Toast.makeText(MainActivity.this,"Song is backwarded by 2 seconds",Toast.LENGTH_SHORT);
                    toast.show();
                }
                time.setText(""+ currentTime);
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int forwardTime = 2000;
                currentTime = song1.getCurrentPosition()/1000;
                int currenttime = song1.getCurrentPosition();
                if((currenttime+forwardTime) < song1.getDuration()) {
                    song1.seekTo(currenttime + forwardTime);
                    Toast toast = Toast.makeText(MainActivity.this, "Song is forwarded by 2 seconds", Toast.LENGTH_SHORT);
                    toast.show();
                }
                time.setText(""+ currentTime);
            }
        });

//         Runnable UpdateSongTime = new Runnable() {
//            public void run() {
//                currentTime = song1.getCurrentPosition();
//                time.setText(String.format("%d min, %d sec",
//                        TimeUnit.MILLISECONDS.toMinutes((long) currentTime),
//                        TimeUnit.MILLISECONDS.toSeconds((long) currentTime) -
//                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
//                                        toMinutes((long) currentTime)))
//                );
//                seekBar.setProgress((int)currentTime);
//                myhandler.postDelayed(this, 100);
//            }
//        };
    }
}