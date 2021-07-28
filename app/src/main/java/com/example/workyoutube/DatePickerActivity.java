package com.example.workyoutube;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

import static com.example.workyoutube.TextFragment.KEY_INDEX;

public class DatePickerActivity extends AppCompatActivity {

    DatePicker datePicker;
    Button buttonGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        initial();
        showGetDate();

    }

    private void initial() {
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        buttonGo = (Button) findViewById(R.id.button);
    }

    private void showGetDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        datePicker.init(year, month, day, null);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(day).append("-").append(month + 1).append("-")
                .append(year).append(" ");

        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                buttonGo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String dateTime = datePicker.getDayOfMonth() + "-" + datePicker.getMonth() + "-" + datePicker.getYear();
                        Intent intent = new Intent();
                        intent.putExtra("dateTime", dateTime);
                        Log.d("myLog", String.valueOf(dateTime));
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
            }
        });

    }
}