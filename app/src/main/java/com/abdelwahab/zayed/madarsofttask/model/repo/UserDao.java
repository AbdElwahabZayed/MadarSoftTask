package com.abdelwahab.zayed.madarsofttask.model.repo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.abdelwahab.zayed.madarsofttask.model.UserData;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserData userData);

    @Query("SELECT * FROM UserData")
    LiveData<List<UserData>> getAllUsers();
}
