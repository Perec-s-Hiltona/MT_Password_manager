package mobile.technology.password_manager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mobile.technology.password_manager.ORM.AppSettings;
import mobile.technology.password_manager.R;
import mobile.technology.password_manager.general.MasterEncrypt;

public class ActAddMainPassword extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton fabSavePasswordKey;
    private EditText edtPasswordKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_add_main_password);

        toolbar = findViewById(R.id.toolbar_add_password_key);
        toolbar.setTitle(getResources().getText(R.string.add_main_password));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        edtPasswordKey = (EditText)findViewById(R.id.edtPasswordKey);
        fabSavePasswordKey = (FloatingActionButton)findViewById(R.id.fab_save_password_key);
        YoYo.with(Techniques.Landing).duration(2000).repeat(0).playOn(fabSavePasswordKey);

        fabSavePasswordKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    saveMainPassword();

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        });

    }

    private void saveMainPassword() throws Exception {

        String mainPassword = edtPasswordKey.getText().toString().trim();

        if(mainPassword != null){

            if(checkPasswordKeyCondition(mainPassword)){

                MasterEncrypt masterEncrypt = new MasterEncrypt();
                String encryptedMainPassword = masterEncrypt.encryptData(mainPassword, masterEncrypt.getZeroPassword());

                // encrypt password key and save in Database
                AppSettings appSettings = new AppSettings();
                appSettings.setMainEncryptedPassword(encryptedMainPassword);
                appSettings.save();

                SweetAlertDialog alertDialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
                alertDialog.setTitleText((String)getResources().getText(R.string.saved));
                alertDialog.setContentText((String)getResources().getText(R.string.main_password_verified));
                alertDialog.show();
            }
        }
    }


    private boolean checkPasswordKeyCondition(String passwordKey){

        boolean existDigit = false;
        boolean existSymbol = false;

        if(passwordKey.length() >= 8 ){

            // check exist numbers in string
            List<String> listDigits = Arrays.asList(
                    "0","1","2","3","4","5","6","7","8","9");

            for (String digit: listDigits){

                if(passwordKey.contains(digit)){
                    existDigit = true;
                }
            }

            // check symbol in string
            List<String> listSymbols = Arrays.asList(
                    "!", "@", "#", "$", "%", "^", "&", "*", "(",")",
                    "_", "-", "+", "=", "±", "{", "}", ":", ";","|",
                    "~", "`", "[","`", "]"  ,"<", ">", "§");

            for(String symbol : listSymbols){

                if(passwordKey.contains(symbol)){
                    existSymbol = true;
                }
            }


            if(existDigit && existSymbol){

                return true;

            } else {

                showMessageWarning();
            }
        } else {

            showMessageWarning();

            return false;
        }

        return false;
    }

    private void showMessageWarning(){

        SweetAlertDialog alertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
        alertDialog.setTitleText((String) getResources().getText(R.string.change_main_password));
        alertDialog.setContentText((String)getResources().getText(R.string.main_password_change_desc));
        alertDialog.setConfirmText((String) getResources().getText(R.string.ok));
        alertDialog.show();
    }
}
