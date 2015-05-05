package in.planyourhealth.planyourhealth;

import android.os.Bundle;
import android.app.Activity;

import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.HashMap;

public class OtherActivity extends Activity {
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        session= new SessionManagement(getApplicationContext());
        session.checkLogin();
        HashMap<String, String> user = session.getUserDetails();

        // name
        String email = user.get(SessionManagement.KEY_EMAIL);
        TextView userEmail = (TextView) findViewById(R.id.userEmail);
        userEmail.setText(email);
        Button btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    protected void onResume() {
        session.checkLogin();
        super.onResume();
    }



}