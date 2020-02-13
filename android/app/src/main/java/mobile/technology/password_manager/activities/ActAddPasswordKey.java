package mobile.technology.password_manager.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mobile.technology.password_manager.R;

public class ActAddPasswordKey extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton fabSavePasswordKey;
    private EditText edtPasswordKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_add_password_key);

        toolbar = findViewById(R.id.toolbar_add_password_key);
        toolbar.setTitle(getResources().getText(R.string.msg_add_password_key));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        edtPasswordKey = (EditText)findViewById(R.id.edtPasswordKey);
        fabSavePasswordKey = (FloatingActionButton)findViewById(R.id.fab_save_password_key);
        YoYo.with(Techniques.Landing).duration(2000).repeat(0).playOn(fabSavePasswordKey);

        fabSavePasswordKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePasswordKey();
            }
        });

    }

    private void savePasswordKey(){

        String passwordKey = edtPasswordKey.getText().toString().trim();

        if(passwordKey.length() < 8){
            SweetAlertDialog alertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
            alertDialog.setTitleText((String) getResources().getText(R.string.msg_change_keyword));
            alertDialog.setContentText((String)getResources().getText(R.string.msg_change_keyword_desc));
            alertDialog.setConfirmText((String) getResources().getText(R.string.msg_ok));
            alertDialog.show();
        }else {
            boolean existDigit = false;
            boolean existSymbol = false;

            for (int i = 0; i < passwordKey.length(); i++){
                Character letter = passwordKey.charAt(i);
                existDigit = Character.isDigit(letter);
                //TODO
                // find symbols in password key
                int symbol_1 = letter.toString().indexOf("!");
                int symbol_2 = letter.toString().indexOf("@");
                int symbol_3 = letter.toString().indexOf("$");

                if(symbol_1 != -1 || symbol_2 != -1 || symbol_3 != -1){
                    existSymbol = true;
                }

                if(existDigit && existSymbol){
                    Toast.makeText(this, "exist Digit and Symbol", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
            int index_int_0 = passwordKey.indexOf(0);

            if(index_int_0 != -1){
                Toast.makeText(this, "exist 0", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
