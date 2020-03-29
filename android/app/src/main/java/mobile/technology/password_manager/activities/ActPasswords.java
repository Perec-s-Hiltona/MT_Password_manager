package mobile.technology.password_manager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mobile.technology.password_manager.ORM.AppSettings;
import mobile.technology.password_manager.R;

public class ActPasswords extends AppCompatActivity {

    private FloatingActionButton fabAddPassword;
    private Toolbar toolbarPasswords;
    String passwordKey;


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

                checkExistPasswordKey();
            }
        });
    }

    private void checkExistPasswordKey(){

        try {

            List<AppSettings> appSettingsList = AppSettings.listAll(AppSettings.class);

            if(appSettingsList.size() > 0){

                Intent actAddPassword = new Intent(".act_add_password");
                startActivity(actAddPassword);

            } else {
                goActAddPasswordKey();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void goActAddPasswordKey(){

        final SweetAlertDialog alertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
        alertDialog.setTitleText((String) getResources().getText(R.string.msg_add_password_key));
        alertDialog.setContentText((String)getResources().getText(R.string.msg_add_password_key_desc));
        alertDialog.setConfirmText((String) getResources().getText(R.string.msg_ok));

        alertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                Intent intentAddPasswordKey = new Intent(".act_add_main_password");
                startActivity(intentAddPasswordKey);

                alertDialog.dismissWithAnimation();
            }
        });
        alertDialog.show();
    }
}
