package com.integro.sjc.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.integro.sjc.R;
import com.integro.sjc.model.Department;
import com.integro.sjc.model.News;
import com.integro.sjc.model.Notification;

import java.util.ArrayList;

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.MyViewHolder> {
    Context context;
    ArrayList<Department> departmentArrayList;

    public DepartmentAdapter(Context context, ArrayList<Department> departmentArrayList) {
        this.context = context;
        this.departmentArrayList = departmentArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.card_departent,parent,false);
        return new DepartmentAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Department department=departmentArrayList.get(position);
        holder.tvDeptTitle.setText(department.getTitle());
        Glide.with(context).load(department.getImage()).into(holder.ivDeptImage);

    }

    @Override
    public int getItemCount()
    {
        return departmentArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvDeptTitle;
        ImageView ivDeptImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDeptTitle=itemView.findViewById(R.id.tv_deptTitle);
            ivDeptImage=itemView.findViewById(R.id.iv_ivDeptImage);
        }
    }
}
