package com.example.zonrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<ModelList> arrayList;
    RecyclerView recyclerView;
    int resimg[] = {R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
    };
    String pname[] = {"Printer", "Mic", "Guitar", "School Bag", "Badminton", "Cricket Bat", "Hockey Stick", "Pen", "Guitar"};
    String prating[] = {"3.4", "4.3", "3.0", "3.0", "4.5", "4.0", "4.5", "4.0", "3.5"};
    String pprice[] = {"100$", "50$", "40$", "60$", "20$", "35$", "55$", "60$", "45$"};
    ArrayList<String> list;
    Spinner spinner;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        spinner = (Spinner) findViewById(R.id.spinner);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        arrayList = new ArrayList<>();
        list = new ArrayList<>();

        list.add("Vertical List");
        list.add("Horizontal List");
        list.add("Grid Layout Manager");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_list, list);
        spinner.setAdapter(adapter);

        addItems();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void  onItemSelected(AdapterView parent, View view, int position, long id) {
                if(position == 0) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    addItems();
                }else if (position == 1) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    addItems();
                }else {
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    addItems();
                }

            }
            @Override
            public void  onNothingSelected(AdapterView parent) {

            }
        });
    }

    public void addItems() {
        arrayList.clear();
        for (int i = 0; i < resimg.length; i++) {
            ModelList modelList = new ModelList();
            modelList.setResImg(resimg[i]);
            modelList.setPname(pname[i]);
            modelList.setPprice(pprice[i]);
            modelList.setPrating(prating[i]);
            arrayList.add(modelList);
        }

        customAdapter = new CustomAdapter(getApplicationContext(), arrayList);
        recyclerView.setAdapter(customAdapter);

        customAdapter.setOnItemClickListener(new CustomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Toast.makeText(getApplicationContext(), arrayList.get(position).getPname(), Toast.LENGTH_LONG).show();
            }
        });
    }
}