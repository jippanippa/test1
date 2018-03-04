package com.alansmitheeprogramming.test1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

//    private static AlphaAnimation inAnimation;
//    private static AlphaAnimation outAnimation;
//    private static FrameLayout progressBarHolder;
    private RecyclerView mRecyclerView;
    private EventAdapter mAdapter;
    private Spinner mSpinner;

    private Event[] mEvents;
    private ArrayAdapter<CharSequence> mSpinnerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        progressBarHolder = findViewById(R.id.progressBarHolderMain);
        mRecyclerView = findViewById(R.id.recyclerView);
        mSpinner = findViewById(R.id.spinner);
        mSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.categories, R.layout.spinner_item);
        mSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mSpinnerAdapter);
        mSpinner.setSelection(5);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                mSpinner.setSelection(position);
                getData(mSpinner.getSelectedItem().toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void getData(String category) {
        if(category.toLowerCase().equals("футбол")) { category = "football"; }
        else if(category.toLowerCase().equals("хоккей")) { category = "hockey"; }
        else if(category.toLowerCase().equals("теннис")) { category = "tennis"; }
        else if(category.toLowerCase().equals("баскетбол")) { category = "basketball"; }
        else if(category.toLowerCase().equals("волейбол")) { category = "volleyball"; }
        else { category = "cybersport"; }

        String urlRequest = "http://mikonatoruri.win/list.php?category=" + category;

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

                    }
                });
                alertUserAboutError();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        turnAnimationOn();
                    }
                });

                try {
                    String jsonData = response.body().string();
                    JSONArray eventsInResponse = new JSONObject(jsonData).getJSONArray("events");
                    Log.v(TAG, jsonData);

                    if (response.isSuccessful()) {
                        parseAndSetData(eventsInResponse);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateDisplay();
//                                turnAnimationOff();
                            }
                        });
                    } else {
                        alertUserAboutError();
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

    private void parseAndSetData(JSONArray jsonArray) throws JSONException {
        mEvents = new Event[jsonArray.length()];
        for(int i = 0; i < jsonArray.length(); i++) {
            mEvents[i] = new Event(jsonArray.getJSONObject(i));
            System.out.println(mEvents[i].getPlace());
        }

    }

    public void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "error_dialog");
    }

    private void updateDisplay() {
        mAdapter = new EventAdapter(this, mEvents);
        mRecyclerView.setAdapter(mAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    public static class AlertDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Context context = getActivity();
            AlertDialog.Builder builder = new AlertDialog.Builder(context)
                    .setTitle("Ой-ёй-йой!")
                    .setMessage("Произошла ошибка, попробуйте ещё раз.")
                    .setPositiveButton("ОК", null);

            AlertDialog dialog = builder.create();
            return dialog;
        }
    }

}
