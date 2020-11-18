package com.integro.sjc.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.integro.sjc.R;
import com.integro.sjc.adapters.DepartmentAdapter;
import com.integro.sjc.api.ApiClients;
import com.integro.sjc.api.ApiServices;
import com.integro.sjc.model.Department;
import com.integro.sjc.model.DepartmentList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DepartmentActivity extends AppCompatActivity {
    ApiServices apiServices;
    RecyclerView rvDepartment;
   // LinearLayoutManager manager;
    StaggeredGridLayoutManager manager;
    DepartmentAdapter departmentAdapter;
    ArrayList<Department> departmentArrayList;
    Call<DepartmentList> departmentListCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        getSupportActionBar().hide();

        apiServices = ApiClients.getClients().create(ApiServices.class);
        rvDepartment = findViewById(R.id.rv_dept);
        //manager = new LinearLayoutManager(this);
        manager = new StaggeredGridLayoutManager(2,RecyclerView.VERTICAL);
        rvDepartment.setLayoutManager(manager);
        departmentArrayList = new ArrayList<>();
        getDepartmentList();
    }

    public void getDepartmentList() {
        String date = "2020-08-31 11:03:07";
        departmentListCall = apiServices.getDepartmentList(date);
        departmentListCall.enqueue(new Callback<DepartmentList>() {
            @Override
            public void onResponse(Call<DepartmentList> call, Response<DepartmentList> response) {
                Log.i("RESONSE", "" + response.isSuccessful());
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == 1) {
                        int size = response.body().getDepartmentArrayList().size();
                        if (size > 0) {
                            departmentArrayList.addAll(response.body().getDepartmentArrayList());
                            departmentAdapter = new DepartmentAdapter(getApplicationContext(), departmentArrayList);
                            rvDepartment.setAdapter(departmentAdapter);
                            rvDepartment.setHasFixedSize(true);
                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Something went wrong, try again", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<DepartmentList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());

            }
        });
    }
}



           /* @Override
            public void onResponse(Call<DepartmentList> call, Response<DepartmentList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getDepartmentArrayList() != null) {
                        int size = response.body().getDepartmentArrayList().size();
                        Log.d("RESPONSE", "news Size " + size);
*//*
                        for (int i = 0; i < size; i++) {
                            initiativesArrayList.add(response.body().getInitiativesArrayList().get(i));
                        }
*//*
                        departmentArrayList.addAll(response.body().getDepartmentArrayList());
                        if (departmentArrayList.size() > 0) {
                            departmentAdapter = new DepartmentAdapter(getApplicationContext(), departmentArrayList);
                            rvDepartment.setAdapter(departmentAdapter);
                            rvDepartment.setHasFixedSize(true);
                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Something went wrong, try again", Toast.LENGTH_SHORT).show();

                }
            }


            @Override
            public void onFailure(Call<DepartmentList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onfailure:" + t.getMessage());

            }
        });

    }*/