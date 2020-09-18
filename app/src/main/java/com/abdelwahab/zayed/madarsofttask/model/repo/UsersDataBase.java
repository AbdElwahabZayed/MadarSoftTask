package com.abdelwahab.zayed.madarsofttask.model.repo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.abdelwahab.zayed.madarsofttask.model.UserData;

@Database(entities = {UserData.class},version = 1,exportSchema = false)
public abstract class UsersDataBase extends RoomDatabase {
    public static UsersDataBase instance;
    public abstract UserDao userDao();

    public static synchronized UsersDataBase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),UsersDataBase.class,"users_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
