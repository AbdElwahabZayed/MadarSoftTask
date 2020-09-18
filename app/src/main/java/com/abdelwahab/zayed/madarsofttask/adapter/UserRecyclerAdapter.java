package com.abdelwahab.zayed.madarsofttask.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.abdelwahab.zayed.madarsofttask.R;
import com.abdelwahab.zayed.madarsofttask.model.UserData;
import java.util.List;


public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.AdapterHolder> {
    private Context context;
    private List<UserData> myUsersList=null;

    public UserRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setMyUsersList(List<UserData> myUsersList){
        this.myUsersList=myUsersList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserRecyclerAdapter.AdapterHolder adapterHolder=new UserRecyclerAdapter.AdapterHolder(LayoutInflater.from(context)
                .inflate(R.layout.carditem,parent,false));
        return adapterHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, int position) {
        UserData  userData = myUsersList.get(position);
        holder.name.setText(userData.name);
        holder.age.setText(""+userData.age);
        holder.jobTitle.setText(userData.jobTitle);
        switch (userData.gender){
            case 0:
                holder.gender.setText("Female");
                break;
            case 1:
                holder.gender.setText("Male");
                break;
            default:
                holder.gender.setText("");
        }

    }

    @Override
    public int getItemCount() {
        return myUsersList!=null?myUsersList.size():0;
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView name,age,gender,jobTitle;
        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.textView_name_card);
            gender=itemView.findViewById(R.id.textView_gender);
            age=itemView.findViewById(R.id.textView_age_card);
            jobTitle=itemView.findViewById(R.id.textView_job_title_card);
        }

    }
}
