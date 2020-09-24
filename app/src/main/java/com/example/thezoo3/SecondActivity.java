/*
Joshua Farren
cpsc 4326
Hw 6
 */

package com.example.thezoo3;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView2;

    protected Button previousButton, nextButton, infoButton;
    protected Button b;
    int[] images = {R.drawable.zoo,R.drawable.lion,R.drawable.elephant,
            R.drawable.hyena, R.drawable.zebra};
    int currentImage;

    int state;
    int home = 0;
    int lastState;

    MediaPlayer mp;
    SoundPool sp;
    int sound_click, elephant_sound, sound_lion, sound_zebra, sound_hyena;
    int num_sounds_loaded;
    boolean sounds_loaded;


    @Override
    protected void onCreate(Bundle inBundle) {
        super.onCreate(inBundle);
        setContentView(R.layout.activity_second);

        imageView2 = (ImageView) findViewById (R.id.imageView);
        previousButton = (Button) findViewById(R.id.previous_button);
        nextButton = (Button) findViewById(R.id.next_button);
        infoButton = (Button) findViewById(R.id.info_button);


        if(inBundle != null){
            currentImage = inBundle.getInt("currentImage");
        }
        else{
            currentImage = 0;
        }

        previousButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        infoButton.setOnClickListener(this);

        num_sounds_loaded = 0;
        sounds_loaded = false;

        /*there seems to be a problem with the if else statement below. After taking it out, the
        object is initializing properly.
        ----------------------------------------------------------------------------------------
        */
        sp = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        /*
        ----------------------------------------------------------------------------------------
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            sp = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        }
        else{
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            SoundPool sounds = new SoundPool.Builder()
                    .setAudioAttributes(attributes)
                    .build();
        }
*/

        sp.setOnLoadCompleteListener((soundPool, sampleId, status) -> {
            num_sounds_loaded++;
            if(num_sounds_loaded == 5)
                sounds_loaded = true;

        });
        sound_click = sp.load(this, R.raw.keyclick, 1);
        sound_lion = sp.load(this, R.raw.lion, 1);
        elephant_sound = sp.load(this, R.raw.elephant, 1);
        sound_hyena = sp.load(this, R.raw.hyena, 1);
        sound_zebra = sp.load(this, R.raw.zebra, 1);
        //Log.i("project logging", "onCreate");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.info_button:
                if (currentImage == 0) {
                    break;
                } else if (currentImage == 1) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.nationalgeographic.com/animals" +
                            "/mammals/a/african-lion/"));
                    startActivity(intent);
                    //finish();
                    break;
                } else if (currentImage == 2) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nationalgeographic.com/animals" +
                            "/mammals/a/african-elephant/"));
                    startActivity(intent);
                    // finish();
                    break;
                } else if (currentImage == 3) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nationalgeographic.com/animals" +
                            "/mammals/s/spotted-hyena/"));
                    startActivity(intent);
                    // finish();
                    break;
                } else if (currentImage == 4) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nationalgeographic.com/animals" +
                            "/mammals/p/plains-zebra/"));
                    startActivity(intent);
                    // finish();
                    break;
                }

            case R.id.previous_button:
                if (sounds_loaded) {
                    sp.play(sound_click, 1, 1, 0, 0, 1);

                }

                if (currentImage == 0 || currentImage == 1) {


                    break;
                } else {
                    if (sounds_loaded) {
                        sp.play(sound_click, 1, 1, 0, 0, 1);
                    }

                    currentImage--;
                    imageView2.setImageResource(images[currentImage]);


                    if (sounds_loaded && currentImage == 1) {
                        sp.play(sound_lion, 1, 1, 0, 0, 1);
                    } else if (sounds_loaded && currentImage == 2) {
                        sp.play(elephant_sound, 1, 1, 0, 0, 1);
                    } else if (sounds_loaded && currentImage == 3) {
                        sp.play(sound_hyena, 1, 1, 0, 0, 1);
                    } else if (sounds_loaded && currentImage == 4) {
                        sp.play(sound_zebra, 1, 1, 0, 0, 1);
                    }


                    break;
                }

            case R.id.next_button:
                if (sounds_loaded)
                    sp.play(sound_click, 1, 1, 0, 0, 1);
                if (currentImage == 4) {
                    imageView2.setImageResource(images[currentImage]);
                    sp.play(sound_zebra, 1, 1, 0, 0, 1);

                    break;
                } else {
                    currentImage++;
                    imageView2.setImageResource(images[currentImage]);

                    if (sounds_loaded && currentImage == 1) {
                        sp.play(sound_lion, 1, 1, 0, 0, 1);
                    } else if (sounds_loaded && currentImage == 2) {
                        sp.play(elephant_sound, 1, 1, 0, 0, 1);
                    } else if (sounds_loaded && currentImage == 3) {
                        sp.play(sound_hyena, 1, 1, 0, 0, 1);
                    } else if (sounds_loaded && currentImage == 4) {
                        sp.play(sound_zebra, 1, 1, 0, 0, 1);
                    }
                    break;
                }


            case R.id.imageView:
                if (sounds_loaded && currentImage == 1) {
                    sp.play(sound_lion, 1, 1, 0, 0, 1);
                } else if (sounds_loaded && currentImage == 2) {
                    sp.play(elephant_sound, 1, 1, 0, 0, 1);
                } else if (sounds_loaded && currentImage == 3) {
                    sp.play(sound_hyena, 1, 1, 0, 0, 1);
                } else if (sounds_loaded && currentImage == 4) {
                    sp.play(sound_zebra, 1, 1, 0, 0, 1);
                }

        }
    }


    @Override
    public void onBackPressed(){

        Intent intent = new Intent(SecondActivity.this, SecondActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }


    @Override
    protected void onPause() {
        if(mp != null){
            mp.pause();
            mp.release();
            mp = null;
        }
        super.onPause();
        lastState = currentImage;
        Log.i("project logging", "onPause()");

    }
    @Override
    protected void onResume() {
        super.onResume();
        imageView2.setImageResource(images[currentImage]);
        mp = null;
        mp = MediaPlayer.create(this, R.raw.jungle);
        if(mp != null){
            mp.setLooping(true);
            mp.start();
        }
        Log.i("project logging", "onResume()");
    }

    @Override
    protected void onSaveInstanceState (Bundle outBundle){
        super.onSaveInstanceState(outBundle);
        outBundle.putInt("currentImage", currentImage);
    }

}













