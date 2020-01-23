package mobile.technology.password_manager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import mobile.technology.password_manager.R;

public class ActAddPassword extends AppCompatActivity {

    private FloatingActionButton fabSavePassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_add_password);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_add_password);
        toolbar.setTitle(getResources().getText(R.string.add) + " " + getResources().getText(R.string.password));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
}
