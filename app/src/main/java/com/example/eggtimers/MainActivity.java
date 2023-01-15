package com.example.eggtimers;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tim;
    SeekBar seekBar;
    boolean counter = false;
    Button btn;
    ImageView img;
    CountDownTimer count;
    MediaPlayer md;

    public void resettim(){
        tim.setText("00:00");
        seekBar.setProgress(0);
        seekBar.setEnabled(true);
        count.cancel();
        btn.setText("GO!");
        counter=false;
    }

    public void btnclc(View view)
    {
        img = findViewById(R.id.imageView);
        btn =findViewById(R.id.button);

        if (counter)
        {
            resettim();
        }
        else {

            counter = true;
            seekBar.setEnabled(false);
            btn.setText("Stop!");

            count = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    timeleft((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {
                    md = MediaPlayer.create(getApplicationContext(), R.raw.iphone_alarm);
                    md.start();
                   resettim();


                }
            }.start();
        }
    }
    public void timeleft(int i)
    {
        tim = findViewById(R.id.textView);
        int min = i/60;
        int sec= i-(min*60);
        String second=Integer.toString(sec);

        if (sec<=9)
        {
            second="0"+second;
        }

        tim.setText(Integer.toString(min)+ ":" +second );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seekBar);
        TextView tim = (TextView) findViewById(R.id.textView);
        img = findViewById(R.id.imageView);
        btn =findViewById(R.id.button);


        seekBar.setMax(600);
        seekBar.setProgress(0);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                timeleft(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}