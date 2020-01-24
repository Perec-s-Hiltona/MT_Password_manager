package mobile.technology.password_manager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import mobile.technology.password_manager.R;

public class ActAddPassword extends AppCompatActivity {

    private FloatingActionButton fabSavePassword;
    private View viewLine1, viewLine2, viewLine3, viewLine4, viewLine5;

    private EditText edtPasswordName;
    private EditText edtLogin;
    private EditText edtPassword;
    private EditText edtURL;
    private EditText edtBankName;
    private EditText edtCardNumber;
    private EditText edtCardHolder;
    private EditText edtCardExpiryMonth;
    private EditText edtCardExpiryYear;
    private EditText edtCardCVV;
    private EditText edtCardPIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_add_password);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_add_password);
        toolbar.setTitle(getResources().getText(R.string.add) + " " + getResources().getText(R.string.password));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        viewLine1 = (View)findViewById(R.id.view_line_password1);
        viewLine2 = (View)findViewById(R.id.view_line_password2);
        viewLine3 = (View)findViewById(R.id.view_line_password3);
        viewLine4 = (View)findViewById(R.id.view_line_password4);
        viewLine5 = (View)findViewById(R.id.view_line_password5);
    }
}
