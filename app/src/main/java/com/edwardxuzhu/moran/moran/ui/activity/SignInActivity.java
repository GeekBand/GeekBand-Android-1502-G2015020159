package com.edwardxuzhu.moran.moran.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.edwardxuzhu.moran.moran.R;
import com.edwardxuzhu.moran.moran.ui.util.StringUtil;

public class SignInActivity extends AppCompatActivity {

    //Define Variable Member
    private AutoCompleteTextView mEmail;
    private TextView mPassword;
    private Button mSignIn;
    private Button mSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //Set Up the widget
        mEmail = (AutoCompleteTextView) findViewById(R.id.email);
        mPassword = (TextView)findViewById(R.id.password);
        mSignUp = (Button) findViewById(R.id.sign_up_button);
        mSignIn = (Button) findViewById(R.id.sign_in_button);

        //SignIn Button Listener
        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn();
            }
        });

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean isEmailValid(String email){
        return email.contains("@");
    }

    private boolean isPasswordValid(String password){
        return password.length() >= 6;
    }

    public void SignIn(){
        mEmail.setError(null);
        mPassword.setError(null);

        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        boolean isValid = true;
        View focusView = null;

        if(StringUtil.isEmpty(password)){
            mPassword.setError(getString(R.string.error_empty_password));
            focusView = mPassword;
            isValid = false;
        } else if(isPasswordValid(password) == false){
            mPassword.setError(getString(R.string.error_length_password));
            focusView = mPassword;
            isValid = false;
        }

        if(StringUtil.isEmpty(email)){
            mEmail.setError(getString(R.string.error_empty_email));
            focusView = mEmail;
            isValid = false;
        }else if(StringUtil.isValidEmail(email)){
            mEmail.setError(getString(R.string.error_pattern_email));
            focusView = mEmail;
            isValid = false;
        }

        if(isValid == false){
            focusView.requestFocus();
        } else if(email.equals("test@test.com")&& password.equals("123456")){
            Toast.makeText(getApplicationContext(),
                    getString(R.string.success_signin),Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(getApplicationContext(),
                    getString(R.string.error_invalid),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
