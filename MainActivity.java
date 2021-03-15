package com.solution.contactindatabase;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.solution.contactindatabase.adapter.MainClassAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
////
//TextView textcontact;
    //to store phonebook
    List<String> contact =new ArrayList<>();
    List<String> contactnumber =new ArrayList<>();
MainClassAdapter mainClassAdapter;
RecyclerView recyclerView;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getContact();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclecontact);
//      give runtime permission for read contact
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            //Now request the permission
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 1);
        } else {
            getContact();
        }


    }

    private void getContact() {
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        //to fetch all the contact from cursor
        while (cursor.moveToNext()) {
            //pass the data to string from cursor
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String mobile = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            //now add data to arraylist
            contact.add( name + "\n");
contactnumber.add(mobile);
            mainClassAdapter=new MainClassAdapter(this, contact,contactnumber, new MainClassAdapter.Select() {
                @Override
                public void click(int position, String name, String mobile) {
                    Intent intent=new Intent(MainActivity.this,Profile.class);
                    intent.putExtra("Your Name is",name);
                    intent.putExtra("number is",mobile);
                    startActivity(intent);
                    //  textcontact.getText(name +""+mobile);
                    Toast.makeText(MainActivity.this, name+  ""+mobile, Toast.LENGTH_SHORT).show();
                }

            });
recyclerView.setAdapter(mainClassAdapter);
        }
    }

}