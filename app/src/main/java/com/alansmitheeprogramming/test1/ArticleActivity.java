package com.alansmitheeprogramming.test1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ArticleActivity extends AppCompatActivity {

    private static final String TAG = ArticleActivity.class.getSimpleName();


    private AlphaAnimation inAnimation;
    private AlphaAnimation outAnimation;

    private FrameLayout progressBarHolder;
    private TextView mTeam1;
    private TextView mTeam2;
    private TextView mTime;
    private TextView mHeader1;
    private TextView mHeader2;
    private TextView mHeader3;
    private TextView mText1;
    private TextView mText2;
    private TextView mText3;
    private TextView mPrediction;

    private Article mArticle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        progressBarHolder = findViewById(R.id.progressBarHolder);
        mTeam1 = findViewById(R.id.team1);
        mTeam2 = findViewById(R.id.team2);
        mTime = findViewById(R.id.time);
        mHeader1 = findViewById(R.id.header1);
        mHeader2 = findViewById(R.id.header2);
        mHeader3 = findViewById(R.id.header3);
        mText1  = findViewById(R.id.text1);
        mText2  = findViewById(R.id.text2);
        mText3  = findViewById(R.id.text3);
        mPrediction  = findViewById(R.id.prediction);


        getData(getIntent().getStringExtra("article"));
    }

    private void getData(String article) {

        turnAnimationOn();
        String urlRequest = String.format("http://mikonatoruri.win/post.php?article=%s", article);

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlRequest)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "onFailure callback worked");
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    }
                });

                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                    JSONObject articlesInResponse = new JSONObject(jsonData);

                    if (response.isSuccessful()) {
                        mArticle = new Article(articlesInResponse);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                parseAndSetData();
                                turnAnimationOff();
                            }
                        });
                    } else {

                    }
                }
                catch (IOException e) {
                    Log.e(TAG, "Exception caught: ", e);
                }
                catch (JSONException e) {
                    Log.e(TAG, "Exception caught: ", e);
                }
            }
        });
    }

    private void parseAndSetData() {

        mTeam1.setText(mArticle.getTeam1().toUpperCase());
        mTeam2.setText(mArticle.getTeam2().toUpperCase());
        mTime.setText(mArticle.getTime().toUpperCase());
        mHeader1.setText(mArticle.getArticles().getHeader()[0].toUpperCase());
        mHeader2.setText(mArticle.getArticles().getHeader()[1].toUpperCase());
        mHeader3.setText(mArticle.getArticles().getHeader()[2].toUpperCase());
        mText1.setText(mArticle.getArticles().getText()[0]);
        mText2.setText(mArticle.getArticles().getText()[1]);
        mText3.setText(mArticle.getArticles().getText()[2]);
        mPrediction.setText(mArticle.getPrediction());
    }

    public void turnAnimationOn() {
        inAnimation = new AlphaAnimation(0f, 1f);
        inAnimation.setDuration(200);
        progressBarHolder.setAnimation(inAnimation);
        progressBarHolder.setVisibility(View.VISIBLE);
        mTeam1.setVisibility(View.INVISIBLE);
        mTeam2.setVisibility(View.INVISIBLE);
        mTime.setVisibility(View.INVISIBLE);
        mHeader1.setVisibility(View.INVISIBLE);
        mHeader2.setVisibility(View.INVISIBLE);
        mHeader3.setVisibility(View.INVISIBLE);
        mText1.setVisibility(View.INVISIBLE);
        mText2.setVisibility(View.INVISIBLE);
        mText3.setVisibility(View.INVISIBLE);
        mPrediction.setVisibility(View.INVISIBLE);
    }

    public void turnAnimationOff() {
        outAnimation = new AlphaAnimation(1f, 0f);
        outAnimation.setDuration(200);
        progressBarHolder.setAnimation(outAnimation);
        progressBarHolder.setVisibility(View.GONE);
        mTeam1.setVisibility(View.VISIBLE);
        mTeam2.setVisibility(View.VISIBLE);
        mTime.setVisibility(View.VISIBLE);
        mHeader1.setVisibility(View.VISIBLE);
        mHeader2.setVisibility(View.VISIBLE);
        mHeader3.setVisibility(View.VISIBLE);
        mText1.setVisibility(View.VISIBLE);
        mText2.setVisibility(View.VISIBLE);
        mText3.setVisibility(View.VISIBLE);
        mPrediction.setVisibility(View.VISIBLE);

    }

}
