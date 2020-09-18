package com.abdelwahab.zayed.madarsofttask.model;

import android.text.Editable;
import android.widget.RadioGroup;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.abdelwahab.zayed.madarsofttask.R;

/***
 * @author Abdelwahab Zayed
 * about : POJO class for user data
 * */
@Entity
public class UserData {
    @PrimaryKey(autoGenerate = true)
    public Integer id;
    public String name;
    public Integer age;
    public String jobTitle;
    // Zero means Female one means Male -1 not selected
    public Integer gender;

    public UserData(String name, Integer age, String jobTitle, Integer gender) {
        this.name = name;
        this.age = age;
        this.jobTitle = jobTitle;
        this.gender = gender;
    }
    /***
     *This Constructor Validate inputs.
     */
    public UserData(Editable name, Editable age, Editable jobTitle, RadioGroup gender) {
        if(name==null){
            this.name="";
        }else {
            this.name=name.toString();
        }
        if(age==null || age.toString().equals("")){
            this.age=-1;
        }else {
            this.age=Integer.parseInt(age.toString());
        }
        if(jobTitle==null){
            this.jobTitle="";
        }else {
            this.jobTitle=jobTitle.toString();
        }
        if(gender==null || gender.toString().equals("")){
            this.gender=-1;
        }else {
            switch (gender.getCheckedRadioButtonId()){
                case R.id.radioButton_female:
                    this.gender=0;
                    break;
                case R.id.radioButton_male:
                    this.gender=1;
                    break;
                default:
                    this.gender=-1;
            }
        }
    }


    public UserData() {
        name="";
        age=0;
        jobTitle="";
        gender=-1;
    }
}
