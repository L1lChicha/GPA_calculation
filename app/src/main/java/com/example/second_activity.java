package com.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class second_activity extends AppCompatActivity {


    RecyclerView recyclerView;

    MyAdapter myAdapter;
    Button buttonAverage ;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);


        recyclerView = findViewById(R.id.recyclerView);

        List<Item>  items = new ArrayList<>();
        items.add(new Item("math"));
        items.add(new Item("eng"));
        items.add(new Item("sci"));
        items.add(new Item("hist"));
        items.add(new Item("geo"));
        items.add(new Item("PE"));
        items.add(new Item("chem"));
        items.add(new Item("bio"));
        items.add(new Item("art"));
        items.add(new Item("mus"));
        items.add(new Item("econ"));
        items.add(new Item("psych"));
        items.add(new Item("comp sci"));
        items.add(new Item("land"));
        items.add(new Item("pol"));

        int numberOfSubjects = MainActivity.numberOfMarks;
        List<Item> newItems = new ArrayList<>() ;

        for(int i = 0; i < numberOfSubjects; i++) {
            newItems.add(items.get(i));
        }

        myAdapter = new MyAdapter(newItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(myAdapter);

        buttonAverage = findViewById(R.id.button_average);
        buttonAverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double result = 0.0;
                double sum = 0;
                int count = myAdapter.getItemCount();
                for(int i = 0; i < count; i++){
                    RadioGroup radioGroup = Objects.requireNonNull(recyclerView.findViewHolderForAdapterPosition(i)).itemView.findViewById(R.id.radioGroup);
                    RadioButton checkedButton = findViewById(radioGroup.getCheckedRadioButtonId());
                    sum += Double.parseDouble(checkedButton.getText().toString());
                }
                result = sum / count;

                SharedPreferences sharedPreferences = getSharedPreferences("MyShared", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Result", ""+ result);
                editor.apply();
                finish();
            }

        });



    }
}