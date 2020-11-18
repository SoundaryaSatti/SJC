package com.integro.sjc.fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.integro.sjc.R;
import com.integro.sjc.activities.AboutSjcActivity;
import com.integro.sjc.activities.DepartmentActivity;
import com.integro.sjc.activities.MainActivity;
import com.integro.sjc.activities.OurCoursesActivity;

public class HomeFragment extends Fragment {
    TextView tvAboutSjc,tvOurCourses,tvOurCourses1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        tvAboutSjc=v.findViewById(R.id.tv_AboutSjc);
        tvOurCourses=v.findViewById(R.id.tv_OurCourses);
        tvOurCourses1=v.findViewById(R.id.tv_OurCourses1);
        tvAboutSjc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),AboutSjcActivity.class);
                startActivity(intent);
            }
        });
        tvOurCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentOurCourses=new Intent(getContext(), OurCoursesActivity.class);
                startActivity(intentOurCourses);
            }
        });
        tvOurCourses1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentOurCourses1=new Intent(getContext(), OurCoursesActivity.class);
                startActivity(intentOurCourses1);
            }
        });
         return v;
    }

}


