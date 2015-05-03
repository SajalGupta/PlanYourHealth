package in.planyourhealth.planyourhealth;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
    private EditText user, pass, email, phone;
    private Button bLogin, bRegister;
    // Progress Dialog
    private ProgressDialog pDialog;
    ArrayList<NameValuePair> nameValuePairs;
    InputStream is = null;
    String result = null;
    String line = null;
    int code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        phone = (EditText) findViewById(R.id.phone);
        Bundle registerData = getIntent().getExtras();
        email.setText(registerData.getString("email"));
        email.setKeyListener(null);
        email.setEnabled(false);
        bRegister = (Button) findViewById(R.id.btnRegister);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Registering");
        bRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = user.getText().toString();
                String emailString = email.getText().toString();
                String passwordString = pass.getText().toString();
                String phoneString = phone.getText().toString();
                if (userName.isEmpty() || emailString.isEmpty() || passwordString.isEmpty() || phoneString.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {

                    insert(userName, emailString, passwordString, phoneString);
                    pDialog.show();
                }
            }
        });
        bLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);
        bLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, Login.class);
                startActivity(i);
            }
        });

    }

    public void insert(String username, String email, String password, String phone) {
        nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("username", username));
        nameValuePairs.add(new BasicNameValuePair("password", password));
        nameValuePairs.add(new BasicNameValuePair("email", email));
        nameValuePairs.add(new BasicNameValuePair("phone", phone));
        try {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        HttpClient httpclient = new DefaultHttpClient();
                        HttpPost httppost = new HttpPost("http://planyourhealth.netau.net//register.php");
                        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                        HttpResponse response = httpclient.execute(httppost);
                        HttpEntity entity = response.getEntity();
                        is = entity.getContent();
                        Log.e("pass 1", "connection success ");

                        BufferedReader reader = new BufferedReader
                                (new InputStreamReader(is, "iso-8859-1"), 8);
                        StringBuilder sb = new StringBuilder();
                        while ((line = reader.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        is.close();
                        result = sb.toString();
                        finalpass(result);
                        Log.e("pass 2", "connection success ");


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();

        } catch (Exception e) {
            pDialog.dismiss();
            Log.e("Fail 1", e.toString());
            Toast.makeText(getApplicationContext(), "Invalid IP Address",
                    Toast.LENGTH_LONG).show();
        }


    }

    public void finalpass(String result) throws JSONException {
        Log.i("finalpass", "finalPassCalled");
        JSONObject json_data = new JSONObject(result);
        code = (json_data.getInt("code"));
        Log.i("JSONData", code + "");
        pDialog.dismiss();
        if (code == 1) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                }
            });

        }
        if (code == 2) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(RegisterActivity.this, "Email already registered", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getBaseContext(), "Sorry, Try Again",
                    Toast.LENGTH_LONG).show();
        }


    }
}




