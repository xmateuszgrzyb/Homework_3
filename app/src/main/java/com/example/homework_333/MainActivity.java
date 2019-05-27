package com.example.homework_333;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static java.lang.Math.min;
import static java.lang.StrictMath.abs;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Context mContext;
    static public SensorManager mSensorManager;
    private Sensor mSensor;
    private ImageView beforeAnswerImgView;
    private ImageView afterAnswerImgView;
    private long lastUpdate = -1;
    private Animation shake;
    private boolean animFlag = false;
    private int sensorType;
    private boolean layoutReady;
    private ConstraintLayout mainContainer;
    private int screenWidth;
    private int screenHeight;
    private int imgEdgeSize;
    private TextView sensorLabelTextView;
    private TextView answerTextView;
    private long time_measurement;
    private long time_measurement2;
    private long difference;
    AnimatorSet animatorSet = new AnimatorSet();
    private String[]  answers = {
            "It is certain",
            "It is decidedly so",
            "Without a doubt",
            "Yes - definitely",
            "You may rely on it",
            "As I see it, yes",
            "Most likely",
            "Outlook good",
            "Yes",
            "Signs point to yes",
            "Reply hazy, try again",
            "Ask again later",
            "Better not tell you now",
            "Cannot predict now",
            "Concentrate and ask again",
            "Don't count on it",
            "My reply is no",
            "My sources say no",
            "Outlook not so good",
            "Very doubtful"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mContext = this;
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null){
          mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
          sensorType = Sensor.TYPE_LIGHT;
        }else {
           Toast.makeText(this,"There is no light sensor in this device!", Toast.LENGTH_SHORT).show();
       }

        answerTextView = findViewById(R.id.answer);
        sensorLabelTextView = findViewById(R.id.instruction);
        beforeAnswerImgView = findViewById(R.id.beforeAnswerImgView);
        afterAnswerImgView = findViewById(R.id.afterAnswerImgView);



        beforeAnswerImgView.setVisibility(View.VISIBLE);
        beforeAnswerImgView.setAlpha(1f);
        afterAnswerImgView.setVisibility(View.VISIBLE);
        afterAnswerImgView.setAlpha(0f);
        answerTextView.setVisibility(View.VISIBLE);
        answerTextView.setAlpha(0f);
        layoutReady = true;
    }
    @Override
    public void onSensorChanged(final SensorEvent event) {
        long timeMicro;

        if (lastUpdate == -1) {
            lastUpdate = event.timestamp;
            timeMicro = 0;
        } else {
            timeMicro = (event.timestamp - lastUpdate) / 1000L;
            lastUpdate = event.timestamp;
        }


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Time difference: ").append(timeMicro).append("\u03bcs\n");

        for (int i = 0; i < 1; i++) {
            stringBuilder.append(String.format("Val[%d]=%.4f\n", i, event.values[i]));
        }

        ObjectAnimator shake1 = ObjectAnimator.ofFloat(beforeAnswerImgView,"x",-100f,300f,170f);
        shake1.setDuration(450);
        shake1.setRepeatCount(Animation.INFINITE);
        shake1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animFlag = false;

                ObjectAnimator answerFadeAnimator;
                ObjectAnimator answerTextFadeAnimator;


                beforeAnswerImgView.setImageResource(R.drawable.hw3ball_empty);

                answerFadeAnimator = ObjectAnimator.ofFloat(afterAnswerImgView, "alpha", 1f, 0f);
                answerFadeAnimator.setInterpolator(new AccelerateInterpolator());
                answerFadeAnimator.setDuration(1000);



                if (difference < 100000000){
                    answerTextView.setText(answers[0]);}
                if (difference >= 100000000 && difference <200000000)
                    answerTextView.setText(answers[1]);
                if (difference >= 200000000 && difference <300000000)
                    answerTextView.setText(answers[2]);
                if (difference >= 300000000 && difference <400000000)
                    answerTextView.setText(answers[3]);
                if (difference >= 400000000 && difference <500000000)
                    answerTextView.setText(answers[4]);
                if (difference >= 500000000 && difference <600000000)
                    answerTextView.setText(answers[5]);
                if (difference >= 600000000 && difference <700000000)
                    answerTextView.setText(answers[6]);
                if (difference >= 700000000 && difference <800000000)
                    answerTextView.setText(answers[7]);
                if (difference >= 800000000 && difference <900000000)
                    answerTextView.setText(answers[8]);
                if (difference >= 900000000 && difference <1000000000)
                    answerTextView.setText(answers[9]);
                if (difference >= 1000000000 && difference <1100000000)
                    answerTextView.setText(answers[10]);
                if (difference >= 1100000000 && difference <1200000000)
                    answerTextView.setText(answers[11]);
                if (difference >= 1200000000 && difference <1300000000)
                    answerTextView.setText(answers[12]);
                if (difference >= 1300000000 && difference <1400000000)
                    answerTextView.setText(answers[13]);
                if (difference >= 1400000000 && difference <1500000000)
                    answerTextView.setText(answers[14]);
                if (difference >= 1500000000 && difference <1600000000)
                    answerTextView.setText(answers[15]);
                if (difference >= 1600000000 && difference <1700000000)
                    answerTextView.setText(answers[16]);
                if (difference >= 1700000000 && difference <1800000000)
                    answerTextView.setText(answers[17]);
                if (difference >= 1800000000 && difference <1900000000)
                    answerTextView.setText(answers[18]);
                if (difference >= 1900000000)
                    answerTextView.setText(answers[19]);




                //Random r = new Random();
                //int randomNumber = r.nextInt(answers.length);

                //answerTextView.setText(answers[randomNumber]);
                answerTextFadeAnimator = ObjectAnimator.ofFloat(answerTextView, "alpha", 1f, 0f);
                answerTextFadeAnimator.setInterpolator(new AccelerateInterpolator());
                answerTextFadeAnimator.setDuration(4000);


                AnimatorSet animSet = new AnimatorSet();
                animSet.play(answerFadeAnimator).with(answerTextFadeAnimator);
                animSet.start();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        beforeAnswerImgView.setImageResource(R.drawable.hw3ball_front);
                    }
                },4000);



            }
            @Override
            public void onAnimationStart(Animator animation){
                super.onAnimationStart(animation);
                animFlag = true;
                beforeAnswerImgView.setImageResource(R.drawable.hw3ball_front);
                answerTextView.setText("");
            }

            @Override
            public void onAnimationPause(Animator animation){
                super.onAnimationPause(animation);
            }
        });

        if (event.values[0] < 20 && !animFlag)
        {
            time_measurement = event.timestamp;
            animatorSet.play(shake1);
            animatorSet.start();
        }
        if (event.values[0] >= 20 && animFlag)
        {
            time_measurement2 = event.timestamp;
            difference = time_measurement2 - time_measurement;
            animatorSet.end();
        }

        TextView valueTextView = findViewById(R.id.sensorValues);
        valueTextView.setText(stringBuilder.toString());


    }

    private void AnimateBall() {
        Animation shake = AnimationUtils.loadAnimation(mContext, R.anim.shakeanimation);
        afterAnswerImgView.startAnimation(shake);

    }
    private void StopAnimateBall() {
        Animation shake = AnimationUtils.loadAnimation(mContext, R.anim.shakeanimation);
        shake.cancel();
        shake.reset();
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }


    private void handleLightSensor(float sensorValue, long timeMicro){
        if (sensorType == Sensor.TYPE_LIGHT && sensorValue >= 20)
        {
            lightSensorAnimation(false, timeMicro);
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(mSensor != null)
            mSensorManager.registerListener(this, mSensor, 100000);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        if (mSensor != null)
            mSensorManager.unregisterListener(this, mSensor);
    }

    private void lightSensorAnimation(boolean showAnswer, long timeMicro) {
        ObjectAnimator questionFadeAnimator;
        ObjectAnimator answerFadeAnimator;
        ObjectAnimator answerTextFadeAnimator;

        float questionFromAlpha = showAnswer ? 0f : 1f;
        float questionToAlpha = showAnswer ? 1f : 0f;
        float answerFromAlpha = showAnswer ? 1f : 0f;
        float answerToAlpha = showAnswer ? 0f : 1f;
        float answerTextFromAlpha = showAnswer ? 1f : 0f;
        float answerTextToAlpha = showAnswer ? 0f : 1f;


        questionFadeAnimator = ObjectAnimator.ofFloat(beforeAnswerImgView, "alpha", questionFromAlpha, questionToAlpha);
        questionFadeAnimator.setInterpolator(new AccelerateInterpolator());
        questionFadeAnimator.setDuration(2200);

        answerFadeAnimator = ObjectAnimator.ofFloat(afterAnswerImgView, "alpha", answerFromAlpha, answerToAlpha);
        answerFadeAnimator.setInterpolator(new AccelerateInterpolator());
        answerFadeAnimator.setDuration(2200);


        Random r = new Random();
        int randomNumber = r.nextInt(answers.length);

        answerTextView.setText(answers[randomNumber]);
        answerTextFadeAnimator = ObjectAnimator.ofFloat(answerTextView, "alpha", answerTextFromAlpha, answerTextToAlpha);
        answerTextFadeAnimator.setInterpolator(new AccelerateInterpolator());
        answerTextFadeAnimator.setDuration(2200);

        AnimatorSet animSet = new AnimatorSet();
        animSet.play(questionFadeAnimator).with(answerFadeAnimator).with(answerTextFadeAnimator);
        animSet.start();
    }
}
