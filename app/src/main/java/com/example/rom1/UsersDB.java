package com.example.rom1;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.jetbrains.annotations.NotNull;


@Database(entities = {User.class}, version = 1)
public abstract class UsersDB extends RoomDatabase {

    public static UsersDB instance;

    public abstract  UserDao userDao();

    public static UsersDB getDbInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(), UsersDB.class, "users.db")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    /*private static RoomDatabase.Callback roomdbcallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new pipulateAsyncTask(instance).execute();
        }


    };

    private  static  class pipulateAsyncTask extends AsyncTask<Void, Void, Void>{

        UserDao userDao;

        private pipulateAsyncTask(UsersDB db) {
            this.userDao = db.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.insertUser(new User("vik", "ogutu"));
            userDao.insertUser(new User("Lal", "Ian"));
            userDao.insertUser(new User("mat", "Ryan"));

            return null;
        }
    }*/
}
