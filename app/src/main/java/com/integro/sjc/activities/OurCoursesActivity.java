package com.integro.sjc.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.integro.sjc.R;
import com.integro.sjc.adapters.AboutSjcAdapter;
import com.integro.sjc.adapters.OurCoursesAdapter;
import com.integro.sjc.api.ApiClients;
import com.integro.sjc.api.ApiServices;
import com.integro.sjc.model.AboutSjc;
import com.integro.sjc.model.AboutSjcList;
import com.integro.sjc.model.OurCourses;
import com.integro.sjc.model.OurCoursesList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class OurCoursesActivity extends AppCompatActivity {
    ApiServices apiServices;
    RecyclerView rvOurCourses;
    LinearLayoutManager manager;
    OurCoursesAdapter ourCoursesAdapter;
    ArrayList<OurCourses> ourCoursesArrayList;
    Call<OurCoursesList> ourCoursesListCall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_courses);
        getSupportActionBar().hide();

        apiServices = ApiClients.getClients().create(ApiServices.class);
        rvOurCourses = findViewById(R.id.rv_OurCourses);
        manager = new LinearLayoutManager(this);
        rvOurCourses.setLayoutManager(manager);
        ourCoursesArrayList = new ArrayList<>();
        getOurCoursesList();
    }
    public void getOurCoursesList() {
        String date = "2020-08-31 10:42:48";
        ourCoursesListCall = apiServices.getOurCoursesList(date);
        ourCoursesListCall.enqueue(new Callback<OurCoursesList>() {
            @Override
            public void onResponse(Call<OurCoursesList> call, Response<OurCoursesList> response) {

                Log.i("RESONSE", "" + response.isSuccessful());
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == 1) {
                        int size = response.body().getOurCoursesArrayList().size();
                        if (size > 0) {
                            ourCoursesArrayList.addAll(response.body().getOurCoursesArrayList());
                            rvOurCourses.setLayoutManager(manager);
                            rvOurCourses.setHasFixedSize(true);
                            ourCoursesAdapter = new OurCoursesAdapter(getApplicationContext(), ourCoursesArrayList);
                            rvOurCourses.setAdapter(ourCoursesAdapter);
                        } else {
                            Toast.makeText(getApplicationContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<OurCoursesList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());

            }

        });
    }
}
