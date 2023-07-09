package com.android.lifecycle;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SavedInstaceActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtNumber1, edtNumber2;
    private TextView txtResult;
    private Button btnSum;

    private int firstNumber, secondNumber, result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNumber1 = (EditText) findViewById(R.id.edtNumber1);
        edtNumber2 = (EditText) findViewById(R.id.edtNumber2);
        txtResult = (TextView) findViewById(R.id.txtResult);
        //Lưu tiến trình
        if (savedInstanceState != null){
            edtNumber1.setText(String.valueOf(savedInstanceState.getInt("Number_1")));
            edtNumber2.setText(String.valueOf(savedInstanceState.getInt("Number_2")));

            txtResult.setText(String.valueOf(savedInstanceState.getInt("Result")));
        }

        btnSum = (Button) findViewById(R.id.btnSum);

        btnSum.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnSum){
            if (edtNumber1.getText().toString().isEmpty() || edtNumber2.getText().toString().isEmpty())
            {
                Toast.makeText(this, "Vui lòng nhập đầy đủ số", Toast.LENGTH_SHORT).show();
            } else {
                firstNumber = Integer.parseInt(edtNumber1.getText().toString());
                secondNumber = Integer.parseInt(edtNumber2.getText().toString());

                result = firstNumber + secondNumber;

                txtResult.setText(String.valueOf(result));
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        if (txtResult.getText().toString().isEmpty()){
            outState.putInt("Number_1", Integer.parseInt(edtNumber1.getText().toString()));
            outState.putInt("Number_2", Integer.parseInt(edtNumber2.getText().toString()));
        }
    }
}
