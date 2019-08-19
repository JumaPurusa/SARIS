package com.example.saris.Actvities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.saris.R;
import com.example.saris.Utils.NetworkHelper;
import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private CoordinatorLayout coordinatorLayout;
    private SweetAlertDialog sweetAlertDialog;
    private Button loginButton;

    private HttpURLConnection urlConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ActionBar actionBar = getSupportActionBar();
        assert  actionBar != null;
        actionBar.setTitle("Login");
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_close);

        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);


        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#FFF100"));
        sweetAlertDialog.setTitle("Loading");
        sweetAlertDialog.setCancelable(false);


        loginButton = findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(isFormValid()){

                                onStartLogin();
                        }

                    }
                }
        );



    }

    private boolean isFormValid(){
        if(TextUtils.isEmpty(usernameEditText.getText().toString())
            && TextUtils.isEmpty(passwordEditText.getText().toString())){

            Snackbar.make(coordinatorLayout,
                    "Please enter all the required fields",
                    2000).show();
            return false;
        }else{
            return true;
        }
    }

    private void onStartLogin(){

//        if(!sweetAlertDialog.isShowing())
//            sweetAlertDialog.show();
//
//
//        new LoginTask().execute(usernameEditText.getText().toString(),
//                passwordEditText.getText().toString());

        if(!sweetAlertDialog.isShowing())
            sweetAlertDialog.show();

        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {

                        if(sweetAlertDialog.isShowing())
                            sweetAlertDialog.cancel();

                        usernameEditText.setText("");
                        passwordEditText.setText("");

                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                }
        , 2000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                onBackPressed();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private class LoginTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {

            String username = params[0];
            String password = params[1];

            try {
                URL url = new URL(getString(R.string.web_server_url));
                urlConnection = (HttpURLConnection)url.openConnection();
                OutputStream outputStream = urlConnection.getOutputStream();
                urlConnection.setRequestMethod(getString(R.string.request_method));
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8")
                        + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                writer.write(data);
                writer.flush();
                writer.close();
                outputStream.close();


                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                StringBuilder builder = new StringBuilder();
                String line;

                while((line = reader.readLine()) != null){
                    builder.append(line).append("\n");
                }

                return builder.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            if(sweetAlertDialog.isShowing())
                sweetAlertDialog.cancel();


        }
    }
}
