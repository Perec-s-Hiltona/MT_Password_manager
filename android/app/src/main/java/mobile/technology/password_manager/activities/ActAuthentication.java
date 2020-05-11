package mobile.technology.password_manager.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mobile.technology.password_manager.ORM.UserData;
import mobile.technology.password_manager.R;
import mobile.technology.password_manager.general.MasterAuthorization;
import mobile.technology.password_manager.general.MasterEncrypt;

public class ActAuthentication extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbarAuthentication;
    private TextView txtInput,txtRestorePassword, txtSendToMail, txtSendToPhone;
    private ImageView imgViewFingerprint;

    private LinearLayout lnlSendPassword;

    private EditText edtLogin;
    private EditText edtPassword;
    private MasterEncrypt masterEncrypt;

    private String login;
    private String password;
    private String phone;
    private String mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_authentication);

        toolbarAuthentication = findViewById(R.id.toolbar_authentication);
        toolbarAuthentication.setTitle(getResources().getText(R.string.authentication));
        setSupportActionBar(toolbarAuthentication);

        edtLogin = (EditText)findViewById(R.id.edtLoginAuthentication);
        edtPassword = (EditText)findViewById(R.id.edtPasswordAuthentication);

        imgViewFingerprint = (ImageView)findViewById(R.id.imView_fingerprint);

        masterEncrypt = new MasterEncrypt();

        txtInput = (TextView) findViewById(R.id.txt_input);
        txtRestorePassword = (TextView)findViewById(R.id.txt_restore_password);
        txtSendToMail = (TextView)findViewById(R.id.txt_send_to_mail);
        txtSendToPhone = (TextView)findViewById(R.id.txt_send_to_phone);

        lnlSendPassword = (LinearLayout)findViewById(R.id.lnl_send_password);
        lnlSendPassword.setVisibility(View.GONE);

        // setOnClickListener
        txtInput.setOnClickListener((View.OnClickListener) this);
        txtRestorePassword.setOnClickListener((View.OnClickListener) this);
        txtSendToMail.setOnClickListener((View.OnClickListener) this);
        txtSendToPhone.setOnClickListener((View.OnClickListener) this);

        imgViewFingerprint.setOnClickListener((View.OnClickListener) this);

        getUserDataFromDB();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.txt_input:
                checkUserLoginPassword();
                break;

            case R.id.txt_restore_password:
                lnlSendPassword.setVisibility(View.VISIBLE);
                break;

            case R.id.txt_send_to_phone:
                sendToPhone(login, password);
                break;
        }
    }

    private void getUserDataFromDB() {

        List<UserData> userDataList = UserData.listAll(UserData.class);

        if(userDataList.size() == 0){
            finish();
        }

        for (UserData userData : userDataList) {

            try {

                login = masterEncrypt.decryptData(userData.getLogin(), masterEncrypt.getZeroPassword());
                password = masterEncrypt.decryptData(userData.getPassword(), masterEncrypt.getZeroPassword());
                phone = masterEncrypt.decryptData(userData.getPhone(), masterEncrypt.getZeroPassword());
                mail = masterEncrypt.decryptData(userData.getMail(), masterEncrypt.getZeroPassword());

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }
    }

    private void checkUserLoginPassword(){
        try {

            if(login.equals(edtLogin.getText().toString().trim()) && password.equals(edtPassword.getText().toString().trim())){

                MasterAuthorization.setIsAuthorization(true);
                finish();

            } else {

                final SweetAlertDialog alertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
                alertDialog.setTitleText((String) getResources().getText(R.string.msg_error_auth));
                alertDialog.setConfirmText((String) getResources().getText(R.string.msg_ok));

                alertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                        YoYo.with(Techniques.Tada).duration(1000).repeat(0).playOn(edtLogin);
                        YoYo.with(Techniques.Tada).duration(2000).repeat(0).playOn(edtPassword);

                        alertDialog.dismissWithAnimation();
                    }
                });

                alertDialog.show();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void sendToPhone(String login, String password){
        //TODO
    }
}
