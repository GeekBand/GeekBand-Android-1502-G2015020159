package com.edwardxuzhu.moran.moran.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.edwardxuzhu.moran.moran.R;
import com.edwardxuzhu.moran.moran.ui.util.StringUtil;

public class RegisterActivity extends AppCompatActivity {

    //Define Variable Member
    private AutoCompleteTextView mEmail;
    private TextView mPassword;
    private TextView mNickname;
    private TextView mConfirmPassword;
    private Button mSignIn;
    private Button mSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmail = (AutoCompleteTextView) findViewById(R.id.email);
        mPassword = (TextView) findViewById(R.id.password);
        mConfirmPassword = (TextView) findViewById(R.id.confirm_password);
        mNickname = (TextView) findViewById(R.id.nickname);
        mSignIn = (Button) findViewById(R.id.sign_in_button);
        mSignUp = (Button) findViewById(R.id.sign_up_button);

        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp();
            }
        });
    }

    public void SignUp(){
        mEmail.setError(null);
        mPassword.setError(null);
        mConfirmPassword.setError(null);
        mNickname.setError(null);

        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String confirm_password = mConfirmPassword.getText().toString().trim();
        String nickname = mNickname.getText().toString().trim();

        boolean isValid = false;
        View focusView = null;

        if(StringUtil.isEmpty(password)){
            mPassword.setError(getString(R.string.error_empty_password));
            isValid = false;
            focusView = mPassword;
        } else if(StringUtil.isValidPassword(password)){
            mPassword.setError(getString(R.string.error_length_password));
            isValid = false;
            focusView = mPassword;
        }

        if(StringUtil.isSamePassword(password, confirm_password)){
            mConfirmPassword.setError(getString(R.string.error_match_password));
        }

        if(StringUtil.isEmpty(email)){
            mEmail.setError(getString(R.string.error_empty_email));
        } else if(StringUtil.isValidEmail(email)){
            mEmail.setError(getString(R.string.error_pattern_email));
        }

        if(StringUtil.isEmpty(nickname)){
            mNickname.setError(getString(R.string.error_empty_nickname));
        }else if(StringUtil.isRepeatNickname(nickname)){

        }

        if(!isValid){
            focusView.requestFocus();

        } else if(email.equals("test@test.com") && password.equals("123456") && confirm_password.equals("123456") && nickname.equals("tester") ){
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
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
