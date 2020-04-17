package mobile.technology.password_manager.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import mobile.technology.password_manager.R;

public class ActAuthentication extends AppCompatActivity {

    private Toolbar toolbarAuthentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_authentication);

        toolbarAuthentication = findViewById(R.id.toolbar_authentication);
        toolbarAuthentication.setTitle(getResources().getText(R.string.authentication));
        setSupportActionBar(toolbarAuthentication);



    }
}
