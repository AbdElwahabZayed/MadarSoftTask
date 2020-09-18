package com.abdelwahab.zayed.madarsofttask.view;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.abdelwahab.zayed.madarsofttask.R;
import com.abdelwahab.zayed.madarsofttask.adapter.UserRecyclerAdapter;
import com.abdelwahab.zayed.madarsofttask.databinding.ActivityShowSavedDataBinding;
import com.abdelwahab.zayed.madarsofttask.model.UserData;
import com.abdelwahab.zayed.madarsofttask.viewModel.ViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ShowSavedDataActivity extends AppCompatActivity implements OnClickHandler {

    private ViewModel viewModel;
    private ActivityShowSavedDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Assign view model instance.
        viewModel =new ViewModelProvider(this).get(ViewModel.class);



        // Obtain binding
        binding= DataBindingUtil.setContentView(this, R.layout.activity_show_saved_data);

        //assign lifecycle owner
        binding.setLifecycleOwner(this);


        binding.setShowSavedDataActivity(this);

        observeRecycler();


    }

    private void observeRecycler() {

        viewModel.getAllUsers().observe(this, new Observer<List<UserData>>() {
            @Override
            public void onChanged(List<UserData> userDataList) {
                if(userDataList.size()>0) {
                    binding.savedDataRecycler.setVisibility(View.VISIBLE);
                    binding.imageViewNoData.setVisibility(View.GONE);
                    binding.textViewThereIsNoData.setVisibility(View.GONE);
                }else {//will show snackbar only in first time
                    Snackbar.make(binding.myCoordinatorLayout, "Tab this button to add user", Snackbar.LENGTH_LONG)
                            .setAction("^", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            })
                            .setActionTextColor(getResources().getColor(R.color.my_color ,null))
                            .show();
                }
                setupRecycler(userDataList);
            }
        });
    }

    private void setupRecycler(List<UserData> userDataList) {
        UserRecyclerAdapter userRecyclerAdapter=new UserRecyclerAdapter(this);

        userRecyclerAdapter.setMyUsersList(userDataList);

        binding.savedDataRecycler.setLayoutManager(new LinearLayoutManager(this));

        binding.savedDataRecycler.setAdapter(userRecyclerAdapter);
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, AddDataActivity.class));
    }
}