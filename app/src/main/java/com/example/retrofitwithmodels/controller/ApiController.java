package com.example.retrofitwithmodels.controller;

import android.app.Activity;
import android.widget.Toast;

import com.example.retrofitwithmodels.model.RetroPhoto;
import com.example.retrofitwithmodels.service.GetDataService;
import com.example.retrofitwithmodels.service.RetrofitClient;
import com.example.retrofitwithmodels.view.ListInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiController {
    private Activity activity;
    ListInterface listInterface;

    public ApiController(Activity activity) {
        this.activity = activity;
    }

    // registering interface into controller
    public void registerResponse(ListInterface listInterface) {
        this.listInterface = listInterface;
    }

    public void getData() {
        GetDataService service = RetrofitClient.getRetrofitInstance().create(GetDataService.class);
        Call<List<RetroPhoto>> call = service.getAllPhotos();
        call.enqueue(new Callback<List<RetroPhoto>>() {
            @Override
            public void onResponse(Call<List<RetroPhoto>> call, Response<List<RetroPhoto>> response) {
                listInterface.generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {
                Toast.makeText(activity, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
