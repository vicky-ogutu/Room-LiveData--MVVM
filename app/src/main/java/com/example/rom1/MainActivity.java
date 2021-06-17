package com.example.rom1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button btn;
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.AddBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddUser.class);
                startActivityForResult(intent, 1);
            }
        });

        recyclerView =findViewById(R.id.recyclerviewUser);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserAdaptert userAdaptert = new UserAdaptert(this);
        recyclerView.setAdapter(userAdaptert);


        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getall().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                userAdaptert.setUsers(users);
            }
        });

new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
    @Override
    public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {

        userViewModel.deleteUser(userAdaptert.getPositionAt(viewHolder.getAdapterPosition()));
        Toast.makeText(MainActivity.this, "deleted successful", Toast.LENGTH_LONG).show();

    }
}).attachToRecyclerView(recyclerView);

userAdaptert.setOnItemClickListener(new UserAdaptert.OnItemClickListener() {
    @Override
    public void onItemClick(User user) {
        Intent intent = new Intent(MainActivity.this, AddUser.class);
        intent.putExtra(AddUser.EXTRA_UID, user.getUid());
        intent.putExtra(AddUser.EXTRA_FIRSTNAME, user.getFirstName());
        intent.putExtra(AddUser.EXTRA_LASTNAME, user.getLastName());

        startActivityForResult(intent, 2);

    }
});
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==1 && resultCode == RESULT_OK){

            String firstname = data.getStringExtra(AddUser.EXTRA_FIRSTNAME);
            String lastname = data.getStringExtra(AddUser.EXTRA_LASTNAME);

            User user = new User(firstname, lastname);
            userViewModel.inserUser(user);
        }else if(requestCode ==2 && resultCode == RESULT_OK){
            int id = data.getIntExtra(AddUser.EXTRA_UID, -1);
            if (id == -1){
                Toast.makeText(this, "cant update", Toast.LENGTH_LONG).show();
                return;
            }else{


            String first = data.getStringExtra(AddUser.EXTRA_FIRSTNAME);
            String lastn = data.getStringExtra(AddUser.EXTRA_LASTNAME);

            User user = new User (first, lastn);
            user.setUid(id);
            userViewModel.updateUser(user);
            Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();}
        }

    }
}