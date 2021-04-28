package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat currencyFormatValue = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormatValue = NumberFormat.getPercentInstance();

    private double billAmount = 0.0;
    private double tipPercent = 0.25;
    private TextView txtBillAmount;
    private TextView txtTippercent;
    private TextView txtTip;
    private TextView txtTotalBillAmount;

    private double totalSalary = 0.0;
    private double  savingPercent = 0.25;
    private TextView txtSavePercent;
    private TextView txtMoneySaved;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBillAmount = findViewById(R.id.txtBillAmount);
        txtTippercent = findViewById(R.id.txtTipPercent);
        txtTip = findViewById(R.id.txtTip);
        txtTotalBillAmount = findViewById(R.id.txtTotalBillAmount);

        txtTip.setText(currencyFormatValue.format(0));
        txtTotalBillAmount.setText(currencyFormatValue.format(0));

        txtSavePercent = findViewById(R.id.txtSavePercent);
        txtMoneySaved = findViewById(R.id.txtMoneySaved);

        txtMoneySaved.setText(currencyFormatValue.format(0));


        EditText edtMoneyAmount = findViewById(R.id.edtMoneyAmount);
        edtMoneyAmount.addTextChangedListener(tipEdtMoneyAmountTextWatcher);

        SeekBar seekbaarPercent = findViewById(R.id.seekbaarPercent);
        seekbaarPercent.setOnSeekBarChangeListener(tipSeekbaarChangeListener);

        EditText edtSalary = findViewById(R.id.edtSalary);
        edtSalary.addTextChangedListener(edtSalaryChangedTextWatcher);

        SeekBar seekbaarSavePercent = findViewById(R.id.seekbaarSavePercent);
        seekbaarSavePercent.setOnSeekBarChangeListener(seekbarSavingsPercentChangedListener);

    }

    private final TextWatcher tipEdtMoneyAmountTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try {

                billAmount = Double.parseDouble(s.toString()) /100.0;
                txtBillAmount.setText(currencyFormatValue.format(billAmount));

            } catch (Exception e) {

                txtBillAmount.setText("");
                billAmount = 0.0;
            }

            calculateTip();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private final SeekBar.OnSeekBarChangeListener tipSeekbaarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            tipPercent = progress / 100.0;
            calculateTip();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void calculateTip() {

        txtTippercent.setText(percentFormatValue.format(tipPercent));
        double tipValue = billAmount * tipPercent;
        double totalValue = billAmount + tipValue;

        txtTip.setText(currencyFormatValue.format(tipValue));
        txtTotalBillAmount.setText(currencyFormatValue.format(totalValue));
    }


    private final TextWatcher edtSalaryChangedTextWatcher =  new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try {

                totalSalary = Double.parseDouble(s.toString());
                calculateSaving();
                
            } catch(Exception e) {

                totalSalary = 0.0;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private final SeekBar.OnSeekBarChangeListener seekbarSavingsPercentChangedListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            savingPercent = progress/100.0;
            calculateSaving();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void calculateSaving() {
        txtSavePercent.setText(percentFormatValue.format(savingPercent));
        double savedMoney = (totalSalary * savingPercent);
        txtMoneySaved.setText(currencyFormatValue.format(savedMoney));
    }
}