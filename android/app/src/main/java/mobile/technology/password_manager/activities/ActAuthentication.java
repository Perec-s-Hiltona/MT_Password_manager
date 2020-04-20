package mobile.technology.password_manager.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import mobile.technology.password_manager.R;

public class ActAuthentication extends AppCompatActivity {

    private Toolbar toolbarAuthentication;
    private TextView txtInput;
    private ImageView imgViewFingerprint;
    private EditText edtLogin;
    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_authentication);

        toolbarAuthentication = findViewById(R.id.toolbar_authentication);
        toolbarAuthentication.setTitle(getResources().getText(R.string.authentication));
        setSupportActionBar(toolbarAuthentication);

        edtLogin = (EditText)findViewById(R.id.edtLoginAuthentication);
        edtPassword = (EditText)findViewById(R.id.edtPasswordAuthentication);
        txtInput = (TextView) findViewById(R.id.txt_input);
        imgViewFingerprint = (ImageView)findViewById(R.id.imView_fingerprint);

        userVerification();
    }

    private void userVerification(){
        //TODO
    }
}
