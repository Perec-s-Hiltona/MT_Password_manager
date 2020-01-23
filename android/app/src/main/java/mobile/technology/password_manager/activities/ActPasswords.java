package mobile.technology.password_manager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import mobile.technology.password_manager.R;

public class ActPasswords extends AppCompatActivity {

    private FloatingActionButton fabAddPassword;
    private Toolbar toolbarPasswords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_passwords);

        toolbarPasswords = findViewById(R.id.toolbar_passwords);
        toolbarPasswords.setTitle(getResources().getText(R.string.passwords_desc));
        setSupportActionBar(toolbarPasswords);

        // set back button on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // fab
        fabAddPassword = (FloatingActionButton)findViewById(R.id.fab_add_password);
        YoYo.with(Techniques.Landing).duration(2000).repeat(0).playOn(fabAddPassword);
        fabAddPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actAddPassword = new Intent(".act_add_password");
                startActivity(actAddPassword);
            }
        });
    }
}
