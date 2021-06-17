package com.example.rom1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    UserRepository userRepository;
    LiveData<List<User>> users;

    public UserViewModel(@NonNull @NotNull Application application) {
        super(application);

        userRepository = new UserRepository(application);
        users = userRepository.get_all();
    }

    public void inserUser(User user){
        userRepository.insert_user(user);
    }

    public void updateUser(User user){
        userRepository.update_user(user);
    }

    public void deleteUser(User user){
        userRepository.delete_user(user);

    }

    public  void delete_all(){
        userRepository.deleteAll();
    }

    public LiveData<List<User>> getall(){
      return   users;
    }
}
