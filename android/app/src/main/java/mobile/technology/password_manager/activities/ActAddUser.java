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
import mobile.technology.password_manager.ORM.UserData;
import mobile.technology.password_manager.R;
import mobile.technology.password_manager.general.MasterEncrypt;

public class ActAddUser extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton fabSaveUserData;
    private EditText edtUserLogin;
    private EditText edtUserPassword;
    private EditText edtUserPhone;
    private EditText edtUserMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_add_user);

        toolbar = findViewById(R.id.toolbar_add_password_key);
        toolbar.setTitle(getResources().getText(R.string.user_data));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        edtUserLogin = (EditText)findViewById(R.id.edtUserLogin);
        edtUserPassword = (EditText)findViewById(R.id.edtUserPassword);
        edtUserPhone = (EditText)findViewById(R.id.edtUserPhone);
        edtUserMail = (EditText)findViewById(R.id.edtUserMail);

        fabSaveUserData = (FloatingActionButton)findViewById(R.id.fab_save_user_data);
        YoYo.with(Techniques.Landing).duration(2000).repeat(0).playOn(fabSaveUserData);

        fabSaveUserData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    saveUserData();

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        });

    }

    private void saveUserData() throws Exception {

        String userLogin = edtUserLogin.getText().toString().trim();
        String userPassword = edtUserPassword.getText().toString().trim();
        String userPhone = edtUserPhone.getText().toString().trim();
        String userMail = edtUserMail.getText().toString().trim();

        if(userLogin.length() == 0){

            SweetAlertDialog alertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
            alertDialog.setTitleText((String) getResources().getText(R.string.msg_add_user_login));
            alertDialog.setContentText((String)getResources().getText(R.string.msg_user_data_assignment));
            alertDialog.setConfirmText((String) getResources().getText(R.string.msg_ok));
            alertDialog.show();

            return;
        }

        if(userPhone.length() == 0){
            userLogin = getResources().getText(R.string.no_data).toString();
        }

        if(userMail.length() == 0){
            userMail = getResources().getText(R.string.no_data).toString();
        }

        if(userPassword != null){

            if(checkPasswordKeyCondition(userPassword)){

                MasterEncrypt masterEncrypt = new MasterEncrypt();

                String encUserLogin = masterEncrypt.encryptData(userLogin, masterEncrypt.getZeroPassword());
                String encUserPassword = masterEncrypt.encryptData(userPassword, masterEncrypt.getZeroPassword());
                String encUserPhone = masterEncrypt.encryptData(userPhone, masterEncrypt.getZeroPassword());
                String encUserMail = masterEncrypt.encryptData(userMail, masterEncrypt.getZeroPassword());

                // encrypted data and save in Database
                UserData userData = new UserData(encUserLogin,encUserPassword,encUserPhone,encUserMail);
                userData.save();

                SweetAlertDialog alertDialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
                alertDialog.setTitleText((String)getResources().getText(R.string.saved));
                alertDialog.setContentText((String)getResources().getText(R.string.msg_main_password_verified));
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

                showMessageCheckPassword();
            }
        } else {

            showMessageCheckPassword();

            return false;
        }

        return false;
    }

    private void showMessageCheckPassword(){

        SweetAlertDialog alertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
        alertDialog.setTitleText((String) getResources().getText(R.string.msg_change_main_password));
        alertDialog.setContentText((String)getResources().getText(R.string.msg_main_password_change_desc));
        alertDialog.setConfirmText((String) getResources().getText(R.string.msg_ok));
        alertDialog.show();
    }
}
