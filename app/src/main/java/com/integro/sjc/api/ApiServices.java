package com.integro.sjc.api;

import com.integro.sjc.model.AboutSjcList;
import com.integro.sjc.model.DepartmentList;
import com.integro.sjc.model.NewsList;
import com.integro.sjc.model.NotificationList;
import com.integro.sjc.model.OurCoursesList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServices {

    @FormUrlEncoded
    @POST("sjc_news.php")
    Call<NewsList> getNewsList(@Field("updated_at")String updated_at);

    @FormUrlEncoded
    @POST("sjc_notification.php")
    Call<NotificationList> getNotificationList(@Field("updated_at")String updated_at);

    @FormUrlEncoded
    @POST("sjc_dept.php")
    Call<DepartmentList> getDepartmentList(@Field("updated_at")String updated_at);

    @FormUrlEncoded
    @POST("sjc_about.php")
    Call<AboutSjcList> getAboutSjcList(@Field("updated_at")String updated_at);

    @FormUrlEncoded
    @POST("sjc_courses.php")
    Call<OurCoursesList> getOurCoursesList(@Field("updated_at")String updated_at);

    



}
