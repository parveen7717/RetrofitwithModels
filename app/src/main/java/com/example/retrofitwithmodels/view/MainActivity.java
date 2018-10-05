package com.example.retrofitwithmodels.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.retrofitwithmodels.R;
import com.example.retrofitwithmodels.adapter.CustomAdapter;
import com.example.retrofitwithmodels.controller.ApiController;
import com.example.retrofitwithmodels.model.RetroPhoto;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ListInterface {
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;
    ApiController apiController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiController = new ApiController(this);
        progressDoalog = new ProgressDialog(this);
        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        // registering interface into view
        apiController.registerResponse(this);

        /*Create handle for the RetrofitInstance interface*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                apiController.getData();
                progressDoalog.dismiss();
            }
        }, 2000);

    }

    @Override
    public void generateDataList(List<RetroPhoto> photoList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(this, photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
