package com.example.inshortsapp.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.inshortsapp.R;

public class MyPortfolio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_portfolio);

        SeekBar mySeekBar = (SeekBar) findViewById(R.id.seekbar);
        final TextView project_details = (TextView) findViewById(R.id.project_details);
        project_details.setText(R.string.paper_pass);

        mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress < 20) {
                    project_details.setText(R.string.paper_pass);
                } else if (progress < 40) {
                    project_details.setText(R.string.component_hub);
                } else if (progress < 60) {
                    project_details.setText(R.string.blood_bank);
                } else if (progress < 80) {
                    project_details.setText(R.string.hess_chart);
                } else {
                    project_details.setText(R.string.thank_you);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
