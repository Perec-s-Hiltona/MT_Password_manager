package mobile.technology.password_manager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mobile.technology.password_manager.R;

public class ActAddPassword extends AppCompatActivity implements  CompoundButton.OnCheckedChangeListener{

    private FloatingActionButton fabSavePassword;
    private View viewLine1, viewLine2, viewLine3, viewLine4, viewLine5;

    private EditText edtKeyName;
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
    private EditText edtComment;

    private Switch switchAddBankData;

    private CardView cardViewBankCard;

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

        edtKeyName = (EditText)findViewById(R.id.edtKeyName);
        edtLogin = (EditText)findViewById(R.id.edtLogin);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        edtURL = (EditText)findViewById(R.id.edtURL);
        edtBankName = (EditText)findViewById(R.id.edtBankName);
        edtCardNumber = (EditText)findViewById(R.id.edtCardNumber);
        edtCardHolder = (EditText)findViewById(R.id.edtCardHolder);
        edtCardExpiryMonth = (EditText)findViewById(R.id.edtCardExpiryMonth);
        edtCardExpiryYear = (EditText)findViewById(R.id.edtCardExpiryYear);
        edtCardCVV = (EditText)findViewById(R.id.edtCardCVV);
        edtCardPIN = (EditText)findViewById(R.id.edtCardPIN);
        edtComment = (EditText)findViewById(R.id.edtComment);

        cardViewBankCard = (CardView) findViewById(R.id.cardView_bank_card);

        // setInvisible
        cardViewBankCard.setVisibility(View.GONE);
        viewLine5.setVisibility(View.GONE);

        // animations
        List<View> viewList = new ArrayList<>();
        viewList.add(viewLine1);
        viewList.add(viewLine2);
        viewList.add(viewLine3);
        viewList.add(viewLine4);
        viewList.add(viewLine5);

        for (View view : viewList){
            YoYo.with(Techniques.ZoomInLeft).duration(2000).repeat(0).playOn(view);
        }

        // switcher
        switchAddBankData = (Switch)findViewById(R.id.switch_add_bank_data);
        if(switchAddBankData != null){
            switchAddBankData.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);
        }

        // fab
        fabSavePassword = (FloatingActionButton)findViewById(R.id.fab_save_password);
        YoYo.with(Techniques.Landing).duration(2000).repeat(0).playOn(fabSavePassword);
        fabSavePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePassword();
            }
        });
    }

    // for switcher
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            cardViewBankCard.setVisibility(View.VISIBLE);
            viewLine5.setVisibility(View.VISIBLE);
        }else {
            cardViewBankCard.setVisibility(View.GONE);
            viewLine5.setVisibility(View.GONE);
        }
    }

    private void savePassword(){
        Toast.makeText(this, "Pressed SAVE", Toast.LENGTH_SHORT).show();
        String keyName = edtKeyName.getText().toString();

        if(keyName.length() == 0){
            SweetAlertDialog alertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
            alertDialog.setTitleText((String) getResources().getText(R.string.msg_add_data));
            alertDialog.setContentText((String)getResources().getText(R.string.key_name));
            alertDialog.setConfirmText((String) getResources().getText(R.string.msg_ok));
            alertDialog.show();
        }else {
            String login = edtLogin.getText().toString();
            String password = edtPassword.getText().toString();
            String url = edtURL.getText().toString();

            if (switchAddBankData.isChecked()){
                String bankName = edtBankName.getText().toString();
                String cardNumber = edtCardNumber.getText().toString();
                String cardHolder = edtCardHolder.getText().toString();
                String cardExpiryMonth = edtCardExpiryMonth.getText().toString();
                String cardExpiryYear = edtCardExpiryYear.getText().toString();
                String cardCVV = edtCardCVV.getText().toString();
                String cardPIN = edtCardPIN.getText().toString();
            }
            String comment = edtComment.getText().toString();
        }
    }
    /*
    private String encrypt(String Data, String password)throws Exception{

        SecretKeySpec key = generateKey(password);
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = Base64.encodeToString(encVal, Base64.DEFAULT);
        return encryptedValue;

    }

    private SecretKeySpec generateKey(String password) throws Exception{
    }
    */
}
