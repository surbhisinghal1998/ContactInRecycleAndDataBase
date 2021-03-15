package com.solution.contactindatabase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.solution.contactindatabase.R;
import com.solution.contactindatabase.Utils.App;
import com.solution.contactindatabase.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class DataShowAdapter extends RecyclerView.Adapter<DataShowAdapter.Myadapter> {
    Context context;
    DataShowAdapter.Select select;
    List<String> contacts=new ArrayList<>();
    List<String> contactnumbers =new ArrayList<>();

//    public DataShowAdapter(Context context, Select select) {
//        this.context = context;
//        this.select = select;
//    }


    public DataShowAdapter(Context context,  List<String> contacts, List<String> contactnumbers,Select select) {
        this.context = context;
        this.select = select;
        this.contacts = contacts;
        this.contactnumbers = contactnumbers;
    }

    @NonNull
    @Override
    public DataShowAdapter.Myadapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.data,parent,false);
        return new DataShowAdapter.Myadapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataShowAdapter.Myadapter holder, int position) {

        holder.showname.setText(contacts.get(position));
        holder.shownumber.setText(contactnumbers.get(position));

        //holder.shownumber.setText(App.getSharedpref().getString(Constants.USER_NUMBER));
       // holder.showname.setText(contacts.get(position));
     //   holder.shownumber.setText(contactnumbers.get(position));
    }

    @Override
    public int getItemCount() {
        //return contacts.size();
        return contacts.size();
    }

    public class Myadapter extends RecyclerView.ViewHolder {
        TextView showname,shownumber;

        public Myadapter(@NonNull View itemView) {
            super(itemView);
            showname=itemView.findViewById(R.id.showname);
            shownumber=itemView.findViewById(R.id.shownumber);
            //number=itemView.findViewById(R.id.number);

        }
    }
    public interface Select{
        void click(int position,String showname,String shownumber);
    }
}
