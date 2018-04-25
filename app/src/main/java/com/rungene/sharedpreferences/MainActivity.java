package com.rungene.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements TextWatcher,

        CompoundButton.OnCheckedChangeListener{
    private EditText etUsername, etPass;
    private CheckBox rem_userpass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        etUsername = (EditText)findViewById(R.id.username);
        etPass = (EditText)findViewById(R.id.pass);
        rem_userpass = (CheckBox)findViewById(R.id.checkBox);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
managePrefes();
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
managePrefes();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        managePrefes();

    }
    private void managePrefes(){
        if (rem_userpass.isChecked()){
            editor.putString(KEY_USERNAME,etUsername.getText().toString().trim());
            editor.putString(KEY_PASS,etPass.getText().toString().trim());
            editor.putBoolean(KEY_REMEMBER,true);
        }else {
            editor.putBoolean(KEY_REMEMBER,false);
            editor.remove(KEY_PASS);//editor.putString (KEY_PASS,"");
            editor.remove(KEY_USERNAME);//editor.putString(KEY_USERNAME,"");
            editor.apply();

        }

    }
}
