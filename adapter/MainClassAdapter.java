package com.solution.contactindatabase.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.solution.contactindatabase.MainActivity;
import com.solution.contactindatabase.Profile;
import com.solution.contactindatabase.R;

import java.util.ArrayList;
import java.util.List;

public class MainClassAdapter extends RecyclerView.Adapter<MainClassAdapter.Myadapter> {
    Context context;
    List<String> contact=new ArrayList<>();
    List<String> contactnumber=new ArrayList<>();
    Select select;

//    public MainClassAdapter(Context context, List<String> contact, Select select) {
//        this.context = context;
//        this.contact = contact;
//        this.select = select;
//    }

    public MainClassAdapter(Context context, List<String> contact, List<String> contactnumber, Select select) {
        this.context = context;
        this.contact = contact;
        this.contactnumber = contactnumber;
        this.select = select;
    }

    @NonNull
    @Override
    public MainClassAdapter.Myadapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.contactlist,parent,false);
        return new Myadapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainClassAdapter.Myadapter holder, int position) {

        holder.name.setText(contact.get(position));
        holder.number.setText(contactnumber.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select.click(position,holder.name.getText().toString(),holder.number.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return contact.size();
    }

    public class Myadapter extends RecyclerView.ViewHolder {
        TextView name,number;

        public Myadapter(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            number=itemView.findViewById(R.id.number);
            //number=itemView.findViewById(R.id.number);

        }
    }
 public    interface Select{
        void click(int position,String name,String number);
    }
}
