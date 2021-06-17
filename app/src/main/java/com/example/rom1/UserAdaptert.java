package com.example.rom1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdaptert extends RecyclerView.Adapter<UserAdaptert.UserViewHolder> {


Context context;
List<User> users = new ArrayList<>();

OnItemClickListener Listener;

public UserAdaptert(Context context){
    this.context = context;
}

public void setUsers(List<User> users){
    this.users = users;
    notifyDataSetChanged();

}
    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout, parent, false);
    return new UserViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull UserAdaptert.UserViewHolder holder, int position) {
    User user = users.get(position);
    holder.firstName.setText(user.getFirstName());
    holder.lastName.setText((user.getLastName()));

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public User getPositionAt(int position){
    return users.get(position);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView firstName, lastName;


        public UserViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.first_name);
            lastName = itemView.findViewById(R.id.last_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position =getAdapterPosition();
                    if (Listener !=null && position != RecyclerView.NO_POSITION);
                    Listener.onItemClick(users.get(position));
                }
            });

        }
    }

    public interface OnItemClickListener{
    void onItemClick(User user);
    }

    public void setOnItemClickListener(OnItemClickListener Listener){
    this.Listener = Listener;

    }
}
