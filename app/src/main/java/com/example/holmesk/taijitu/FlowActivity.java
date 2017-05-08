package com.example.holmesk.taijitu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class FlowActivity extends AppCompatActivity {

    private MyProgressView mTasksView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);

        initView();
    }

    private void initView() {
        mTasksView = (MyProgressView) findViewById(R.id.flow_prgress_view);
    }

    public void beginAnim(View view) {
        mTasksView.setUsedFlow("200.0M");
        mTasksView.setmShowProgress(60);
    }
}
