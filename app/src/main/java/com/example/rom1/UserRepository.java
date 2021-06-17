package com.example.rom1;

import android.app.Application;
import android.app.AsyncNotedAppOp;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {
    UsersDB usersDB;
    UserDao userDao;
    LiveData<List<User>> user_list;

    public  UserRepository(Application application){
        usersDB = UsersDB.getDbInstance(application);
        userDao = usersDB.userDao();
        user_list = userDao.getAllUsers();
    }

    public void insert_user(User user){
        new inserAsyncTask(userDao).execute(user);

    }
    public void update_user(User user){

        new updateAsyncTask(userDao).execute(user);

    }

    public void delete_user(User user){

        new deleteAysnckTask(userDao).execute(user);

    }
    public void deleteAll(){
        new deleteAllAsyncTask(userDao);

    }

    public LiveData<List<User>> get_all(){
        return  user_list;


    }

    private  static  class inserAsyncTask extends AsyncTask<User, Void, Void>{

        UserDao userDao;

        public inserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {

            userDao.insertUser(users[0]);

            return null;
        }
    }

private static class updateAsyncTask extends AsyncTask<User, Void, Void>{

        UserDao userDao;

    public updateAsyncTask(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected Void doInBackground(User... users) {

        userDao.updateUser(users[0]);
        return null;
    }
}

private static class deleteAysnckTask extends AsyncTask<User, Void, Void>{

        UserDao userDao;

    public deleteAysnckTask(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected Void doInBackground(User... users) {

        userDao.deleteUser(users[0]);
        return null;
    }
}

private static class deleteAllAsyncTask extends AsyncTask <Void, Void, Void>{
UserDao userDao;

    public deleteAllAsyncTask(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        userDao.deleteAll();
        return null;
    }
}

}
