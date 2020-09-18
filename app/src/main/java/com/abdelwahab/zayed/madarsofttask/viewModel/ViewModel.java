package com.abdelwahab.zayed.madarsofttask.viewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.abdelwahab.zayed.madarsofttask.model.UserData;
import com.abdelwahab.zayed.madarsofttask.model.repo.UsersRepo;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ViewModel extends AndroidViewModel {

    private UsersRepo usersRepo;
    //define object as live data to keep ui up to date.
    private LiveData<List<UserData>> allUsers;

    // this constructor is requested to handel DB
    public ViewModel(@NonNull Application application) {
        super(application);
        //Create Users Repo Instance
        usersRepo=new UsersRepo(application);

        //gets List of users
        allUsers=usersRepo.getAllUsers();
    }

    public LiveData<List<UserData>> getAllUsers(){
        return allUsers;
    }

    public void insertUser(final UserData userData){
        new Completable(){
            @Override
            protected void subscribeActual(CompletableObserver observer) {
                usersRepo.insertUser(userData);
            }
            //this determine which thread rx will work on  to data on db
        }.subscribeOn(Schedulers.computation())
                //this determine main thread to work after work done
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

    }


}
