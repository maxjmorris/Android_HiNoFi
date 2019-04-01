package com.example.android_hinofi_prototype.adapters;


import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;

import com.example.android_hinofi_prototype.R;
import com.example.android_hinofi_prototype.models.User;


import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.UserViewHolder> {

    private List<User> listUsers;

    public RecyclerAdapter(List<User> listUsers){
        this.listUsers = listUsers;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        //inflating recycling item view
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_user_recycler, viewGroup, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.textViewUsername.setText(listUsers.get(position).getUsername());
        holder.textViewEmail.setText(listUsers.get(position).getEmailAddress());
        holder.textViewPassword.setText(listUsers.get(position).getPassword());

    }

    @Override
    public int getItemCount() {
        Log.v(RecyclerAdapter.class.getSimpleName(),""+listUsers.size());
        return listUsers.size();
    }

    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder{

        public AppCompatTextView textViewUsername;
        public AppCompatTextView textViewEmail;
        public AppCompatTextView textViewPassword;

        public UserViewHolder(View view){
            super(view);
            textViewUsername = view.findViewById(R.id.textViewUsername);
            textViewEmail = view.findViewById(R.id.textViewEmail);
            textViewPassword = view.findViewById(R.id.textViewPassword);
        }
    }
}

