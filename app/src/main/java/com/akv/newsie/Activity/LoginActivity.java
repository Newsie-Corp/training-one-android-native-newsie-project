package com.akv.newsie.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.akv.newsie.R;
import com.akv.newsie.Util.Config;
import com.akv.newsie.Util.SessionManager;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "";
    private EditText username;
    private EditText password;
    private Button buttonLogin;

    SessionManager sessionManager;

    public final String LOGIN_URL = "https://talentpool.oneindonesia.id/api/user/login";
    public final String KEY = "454041184B0240FBA3AACD15A1F7A8BB";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);
        sessionManager = new SessionManager(this);
    }


    @Override
    public void onClick(View view) {


        if(view == buttonLogin){
            String user = username.getText().toString();
            String pass = password.getText().toString();

            final ProgressDialog loading = ProgressDialog.show(this,"Logging You In...","Please wait...",false,false);
            //Creating a string request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            String respStatus = "";
                            try{
                                JSONObject obj = new JSONObject(response);
                                String status = obj.getString("status");
                                respStatus = status;
                            }catch (JSONException e){
                                e.printStackTrace();
                            }


                            if(respStatus.equals("true")){

                                loading.dismiss();
                                //If the server response is not success
                                //Displaying an error message on toast
                                Toast.makeText(LoginActivity.this, "Username atau password yang Anda masukkan salah.", Toast.LENGTH_LONG).show();


                            } else {
                                //Creating a shared preference
                                SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                                //Creating editor to store values to shared preferences
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                //Adding values to editor
                                editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);

                                //Saving values to editor
                                editor.commit();

                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                                finish();
                                sessionManager.createSession(user);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            loading.dismiss();
                            //You can handle error here if you want

                            Log.d(TAG, "onErrorResponse: " + error.toString());
                            Toast.makeText(LoginActivity.this, "Cek koneksi internet Anda.", Toast.LENGTH_LONG).show();
                        }
                    }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("x-api-key", KEY);
                    return params;
                }

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    //Adding parameters to request
                    params.put("username", user);
                    params.put("password", pass);

                    //returning parameter
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
            stringRequest.setShouldCache(false);
            requestQueue.getCache().clear();

        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder ab = new AlertDialog.Builder(LoginActivity.this);
        ab.setTitle("exit");
        ab.setMessage("Are you sure to exit from application?");
        ab.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();


                Intent a = new Intent(Intent.ACTION_MAIN);
                a.addCategory(Intent.CATEGORY_HOME);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(a);
            }
        });
        ab.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        ab.show();
    }
}