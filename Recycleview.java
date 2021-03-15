package com.solution.contactindatabase;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.solution.contactindatabase.Utils.App;
import com.solution.contactindatabase.Utils.Constants;
import com.solution.contactindatabase.adapter.DataShowAdapter;

import java.util.ArrayList;
import java.util.List;

public class Recycleview extends AppCompatActivity {
    List<String> contacts = new ArrayList<>();
    List<String> contactnumbers = new ArrayList<>();
    DataShowAdapter dataShowAdapter;
    RecyclerView recycle;
    private String name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);


        recycle = findViewById(R.id.recycle);

        String size = App.getSharedpref().getString("size");
        int size2 = Integer.valueOf(size);
        Toast.makeText(this, "Number of list" + size, Toast.LENGTH_SHORT).show();
        for (int i = 0; i <= size2; i++) {
            contacts.add(App.getSharedpref().getString(Constants.USER_NAME + i));
            contactnumbers.add(App.getSharedpref().getString(Constants.USER_NUMBER + i));
        }

        dataShowAdapter = new DataShowAdapter(this, contacts, contactnumbers, new DataShowAdapter.Select() {
            @Override
            public void click(int position, String showname, String shownumber) {
                name = App.getSharedpref().getString(Constants.USER_NAME );
                email = App.getSharedpref().getString(Constants.USER_NUMBER);

                contacts.add(name);
                contactnumbers.add(email);

            }
        });
        recycle.setAdapter(dataShowAdapter);


//        tvname=findViewById(R.id.showname);
//        tvnumber=findViewById(R.id.shownumber);


    }
}