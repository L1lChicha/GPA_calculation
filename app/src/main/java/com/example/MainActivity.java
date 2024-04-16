package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    public static int numberOfMarks;


    public TextView resultTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText nameField = findViewById(R.id.nameField);
        EditText surnameField = findViewById(R.id.surnameField);
        EditText numberOfMarksField = findViewById(R.id.numberOfMarksField);
        Button marksButton = findViewById(R.id.marksButton);




       // final int[] numberOfMarks = new int[1];

        final boolean[] nameFieldStatus = {false};
        final boolean[] surnameFieldStatus = {false};
        final boolean[] numberOfMarksStatus = {false};
        nameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable name) {

                Pattern pattern = Pattern.compile("\\d");
                Matcher matcher = pattern.matcher(name.toString());

                    if (matcher.find() == true) {
                        nameField.setError("поле не может содержать цифры");
                        nameFieldStatus[0] = false;
                    } else if(name.toString().isEmpty()) {
                        nameField.setError("поле не может быть пустым");
                        nameFieldStatus[0] = false;
                    }else {
                        nameField.setError(null);
                        nameFieldStatus[0] = true;
                    }
            }
        });
        surnameField.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable surname) {

                Pattern pattern = Pattern.compile("\\d");
                Matcher matcher = pattern.matcher(surname.toString());

                if (matcher.find() == true) {
                    surnameField.setError("поле не может содержать цифры");
                    surnameFieldStatus[0] = false;
                } else if(surname.toString().isEmpty()) {
                    surnameField.setError("поле не может быть пустым");
                    surnameFieldStatus[0] = false;
                }else{
                    surnameField.setError(null);
                    surnameFieldStatus[0] = true;
                }
                if(nameFieldStatus[0] && surnameFieldStatus[0] && numberOfMarksStatus[0]){
                    marksButton.setVisibility(View.VISIBLE);
                }else{
                    marksButton.setVisibility(View.INVISIBLE);
                }
            }
        });

        numberOfMarksField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable marks) {

                Pattern pattern = Pattern.compile("[a-z]");
                Matcher matcher = pattern.matcher(marks.toString());

                if (matcher.find()== true) {
                    numberOfMarksField.setError("поле не может содержать буквы");
                    numberOfMarksStatus[0] =false;
                } else if(marks.toString().isEmpty()) {
                    numberOfMarksField.setError("поле не может быть пустым");
                    numberOfMarksStatus[0] =false;
                }else if(Integer.parseInt(marks.toString()) <5 || Integer.parseInt(marks.toString()) > 15 ) {
                    numberOfMarksField.setError("введите количество оценок в промежутке от 5 до 15");
                    numberOfMarksStatus[0] =false;
                }else {
                    numberOfMarksField.setError(null);
                    numberOfMarksStatus[0] = true;
                    numberOfMarks= Integer.parseInt(numberOfMarksField.getText().toString());
                }
                if(nameFieldStatus[0] && surnameFieldStatus[0] && numberOfMarksStatus[0]){
                    marksButton.setVisibility(View.VISIBLE);
                }else{

                    marksButton.setVisibility(View.INVISIBLE);
                }
            }
        });

        marksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, second_activity.class);
                startActivity(intent);
            }
        });



    }

    protected void onRestart() {
        super.onRestart();

        Button marksButton = findViewById(R.id.marksButton);

        resultTextView = findViewById(R.id.resultTextView);
        SharedPreferences sharedPreferences = getSharedPreferences("MyShared", Context.MODE_PRIVATE);
        String value = sharedPreferences.getString("Result", "");
        if(!value.isEmpty()){
            resultTextView.setVisibility(View.VISIBLE);
            resultTextView.setText("Your average: " + value);

            if(Double.parseDouble(value) >= 3.0){
                marksButton.setText("SUPER!");
                marksButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getBaseContext(), "Gratulacje! Otrzymujesz zaliczenie!", Toast.LENGTH_SHORT)
                                .show();
                        finish();
                    }
                });
            } else {
                marksButton.setText("TYM RAZEM MI NIE POSZLO");
                marksButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getBaseContext(), "Wyslalem podanie o zaliczenie warunkowe", Toast.LENGTH_SHORT)
                                .show();
                        finish();
                    }
                });
            }
        }
    }

    @Override
    protected void onStart() {

        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}

