package com.solution.contactindatabase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.solution.contactindatabase.Utils.App;
import com.solution.contactindatabase.Utils.Constants;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    Button savebutton;
    private static final String SHARED_PREF_NAME = "surbhi";
    private CircularImageView circularImageView;
    TextView numbershow;
    // SharedPref share;
    TextView nameshow;
    private Uri uri;
    private String imagePath;

    String str;
    String strnumber;
    private ArrayList<Uri> ImageUri = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        findid();
        numbershow = findViewById(R.id.number);
        nameshow = findViewById(R.id.name);
        // savebutton = findViewById(R.id.savebutton);

        Intent intent = getIntent();
        str = intent.getStringExtra("Your Name is");
        strnumber = intent.getStringExtra("number is");
        numbershow.setText(strnumber);
        nameshow.setText(str);
    }

    private void findid() {
        circularImageView = findViewById(R.id.circularImage);
        savebutton = findViewById(R.id.savebutton);
        circularImageView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
//                Intent intent=new Intent(Intent.ACTION_PICK);
//                intent.setType("images/*");

                ImagePicker.Companion.with(Profile.this).crop().compress(512).start();
            }
        });
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=0;
                if(!App.getSharedpref().getString("size").equalsIgnoreCase("")){
                   String b= App.getSharedpref().getString("size");
                   int size=Integer.valueOf(b);
                   size++;
                    Toast.makeText(Profile.this, str, Toast.LENGTH_SHORT).show();
                    App.getSharedpref().saveString("size",String.valueOf(size));
                    App.getSharedpref().saveString(Constants.USER_NAME+size,str);
                    App.getSharedpref().saveString(Constants.USER_NUMBER+size,strnumber);
                }else{
                    App.getSharedpref().saveString("size",String.valueOf(i));
                    App.getSharedpref().saveString(Constants.USER_NAME+i,str);
                    App.getSharedpref().saveString(Constants.USER_NUMBER+i,strnumber);
                }
                Intent intent = new Intent(Profile.this, Recycleview.class);
                startActivity(intent);

                Toast.makeText(Profile.this, "Login Succeessfull", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            uri = data.getData();
            imagePath = uri.getPath();
            Toast.makeText(this, imagePath, Toast.LENGTH_SHORT).show();
            ImageUri.add(uri);
            circularImageView.setImageURI(uri);


        } else {
            Toast.makeText(this, "Result not ok", Toast.LENGTH_SHORT).show();
        }

//        String name = sharedPreferences.getString(KEY_NAME, null);
//        String email = sharedPreferences.getString(KEY_EMAIL, null);
//        if (name != null) {
//            Intent intent = new Intent(Profile.this, Recycleview.class);
//            startActivity(intent);
//        }

    }

}