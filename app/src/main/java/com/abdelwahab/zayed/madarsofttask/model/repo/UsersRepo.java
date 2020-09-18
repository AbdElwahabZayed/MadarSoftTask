package com.abdelwahab.zayed.madarsofttask.model.repo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.abdelwahab.zayed.madarsofttask.model.UserData;

import java.util.List;

public class UsersRepo {
    private UserDao userDao;
    private LiveData<List<UserData>> allUsers;

    public UsersRepo(Application application) {
        UsersDataBase usersDataBase=UsersDataBase.getInstance(application);
        userDao=usersDataBase.userDao();
        allUsers=userDao.getAllUsers();
    }

    public LiveData<List<UserData>> getAllUsers(){
        return allUsers;
    }

    public void insertUser(UserData userData){
        userDao.insertUser(userData);
    }
}
