package com.abdelwahab.zayed.madarsofttask.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.abdelwahab.zayed.madarsofttask.R;
import com.abdelwahab.zayed.madarsofttask.databinding.ActivityAddDataBinding;

import com.abdelwahab.zayed.madarsofttask.model.UserData;
import com.abdelwahab.zayed.madarsofttask.viewModel.ViewModel;

public class AddDataActivity extends AppCompatActivity implements OnClickHandler {
    //my view model instance
    private ViewModel viewModel;
    private ActivityAddDataBinding  binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Assign view model instance.
        viewModel =new ViewModelProvider(this).get(ViewModel.class);



        // Obtain binding
        binding=DataBindingUtil.setContentView(this, R.layout.activity_add_data);

        //assign lifecycle owner
        binding.setLifecycleOwner(this);


        binding.setClickHandler(this);

        setEditTextToNormal();
    }

    private void setEditTextToNormal() {
        binding.textInputLayoutName.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textInputLayoutName.setError(null);
            }
        });
        binding.textInputLayoutAge.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textInputLayoutAge.setError(null);
            }
        });
        binding.textInputLayoutJobTitle.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textInputLayoutJobTitle.setError(null);
            }
        });
        binding.radioButtonMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                binding.txtGenderTitle.setTextColor(getResources().getColor(R.color.my_color,null));
            }
        });
        binding.radioButtonFemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                binding.txtGenderTitle.setTextColor(getResources().getColor(R.color.my_color,null));
            }
        });

    }

    @Override
    public void onClick(View view) {
        UserData userData=new UserData(binding.textInputLayoutName.getEditText().getText(),
                binding.textInputLayoutAge.getEditText().getText(),
                binding.textInputLayoutJobTitle.getEditText().getText(),
                binding.genderRadioGroup);
        checkUserData(userData);
    }

    private void checkUserData(UserData userData) {
        if (userData.name.isEmpty()){
            binding.textInputLayoutName.setError("Field can't be empty");
            return;
        }
        if (userData.age==-1){
            binding.textInputLayoutAge.setError("Field can't be empty");
            return;
        }
        if (userData.jobTitle.isEmpty()){
            binding.textInputLayoutJobTitle.setError("Field can't be empty");
            return;
        }
        if(userData.gender==-1){
            binding.txtGenderTitle.setTextColor(getResources().getColor(R.color.error_color,null));
            return;
        }
        saveData(userData);
    }

    private void saveData(UserData userData) {
        viewModel.insertUser(userData);
        startActivity(new Intent(this, ShowSavedDataActivity.class));
    }


}