package mobile.technology.password_manager.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import mobile.technology.password_manager.R;

public class ActSettings extends AppCompatActivity {

    private Toolbar toolbarSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_settings);

        toolbarSettings = findViewById(R.id.toolbar_settings);
        toolbarSettings.setTitle(getResources().getText(R.string.settings));
        setSupportActionBar(toolbarSettings);

        // set back button on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }
}
