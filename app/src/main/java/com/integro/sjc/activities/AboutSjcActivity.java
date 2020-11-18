package com.integro.sjc.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.integro.sjc.R;
import com.integro.sjc.adapters.AboutSjcAdapter;
import com.integro.sjc.adapters.DepartmentAdapter;
import com.integro.sjc.adapters.NewsAdapter;
import com.integro.sjc.api.ApiClients;
import com.integro.sjc.api.ApiServices;
import com.integro.sjc.model.AboutSjc;
import com.integro.sjc.model.AboutSjcList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AboutSjcActivity extends AppCompatActivity {

    ApiServices apiServices;
    RecyclerView rvAboutSjc;
    LinearLayoutManager manager;
    AboutSjcAdapter aboutSjcAdapter;
    ArrayList<AboutSjc> aboutSjcArrayList;
    Call<AboutSjcList> aboutSjcListCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_sjc);
        getSupportActionBar().hide();

        apiServices = ApiClients.getClients().create(ApiServices.class);
        rvAboutSjc = findViewById(R.id.rv_aboutSjc);
        manager = new LinearLayoutManager(this);
        rvAboutSjc.setLayoutManager(manager);
        aboutSjcArrayList = new ArrayList<>();
        getAboutSjcList();
    }

    public void getAboutSjcList() {
        String date = "2020-08-31 10:45:28";
        aboutSjcListCall = apiServices.getAboutSjcList(date);
        aboutSjcListCall.enqueue(new Callback<AboutSjcList>() {
            @Override
            public void onResponse(Call<AboutSjcList> call, Response<AboutSjcList> response) {

                Log.i("RESONSE", "" + response.isSuccessful());
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == 1) {
                        int size = response.body().getAboutSjcArrayList().size();
                        if (size > 0) {
                            aboutSjcArrayList.addAll(response.body().getAboutSjcArrayList());
                            rvAboutSjc.setLayoutManager(manager);
                            rvAboutSjc.setHasFixedSize(true);
                            aboutSjcAdapter = new AboutSjcAdapter(getApplicationContext(), aboutSjcArrayList);
                            rvAboutSjc.setAdapter(aboutSjcAdapter);
                        } else {
                            Toast.makeText(getApplicationContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<AboutSjcList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());

            }
        });
    }

}
