package com.example.rom1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User users);

    @Delete
    void deleteUser(User users);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateUser(User users);

   @Query("SELECT * FROM users")
   LiveData<List<User>> getAllUsers();

   @Query("DELETE FROM users")
    void deleteAll();


   /* @Query("SELECT * FROM users WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM users WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);*/

}
