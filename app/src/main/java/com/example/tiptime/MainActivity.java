package com.example.tiptime;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tiptime.databinding.ActivityMainBinding;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
//      ActivityMainBinding binding = ActivityMainBinding.inflate(lay);
//      setContentView(binding.getRoot());

        binding.calculateButton.setOnClickListener(v -> calculateTip());
    }

    void calculateTip(){
        Log.d("called", "called");
        Log.d("textfiled", binding.costOfService.getText().toString());
        String textfield = binding.costOfService.getText().toString();

        Double cost = Double.parseDouble(textfield);
        Log.d("cost", String.valueOf(cost));
        int radioselection = binding.tipOptions.getCheckedRadioButtonId();
        double perc = getPercentage(radioselection);
        double tip = perc * cost;
        Log.d("tip", String.valueOf(tip));
        boolean checked = binding.roundUpSwitch.isChecked();
        if (checked){
            Log.d("logic", "checked");
            tip = Math.round(tip);
        }
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String money = formatter.format(tip);
        binding.tipResult.setText(money);

    }

    private double getPercentage (int selection) {
        Log.d("getperc",String.valueOf(selection));
        double percentage;
        switch(selection){
            case R.id.option_eighteen_percent:
                percentage = 0.18;
                break;
            case R.id.option_fifteen_percent:
                percentage = 0.15;
                break;
            case R.id.option_twenty_percent:
                percentage = 0.20;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + selection);
        }
        return percentage;
    }
}