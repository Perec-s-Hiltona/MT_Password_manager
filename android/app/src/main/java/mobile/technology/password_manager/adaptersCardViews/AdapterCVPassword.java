package mobile.technology.password_manager.adaptersCardViews;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mobile.technology.password_manager.R;
import mobile.technology.password_manager.cardViews.CardViewPassword;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class AdapterCVPassword extends RecyclerView.Adapter<AdapterCVPassword.ViewHolder> {

    private List<CardViewPassword> CardViewPasswordList;
    private Context context;

    public AdapterCVPassword(List<CardViewPassword> CardViewPasswordList, Context context){
        this.CardViewPasswordList = CardViewPasswordList;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cv_password,
                viewGroup,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final CardViewPassword CardViewPassword = CardViewPasswordList.get(position);

        //animation
        YoYo.with(Techniques.ZoomInLeft).duration(1500).repeat(0).playOn(viewHolder.imgIconPassword);

        //set data to textView
        viewHolder.txtKeyName.setText(CardViewPassword.getKeyName());

        viewHolder.txtLogin.setText(CardViewPassword.getLogin());
        viewHolder.txtPassword.setText(CardViewPassword.getPassword());
        viewHolder.txtURL.setText(CardViewPassword.getURL());

        viewHolder.txtBankName.setText(CardViewPassword.getBankName());
        viewHolder.txtCardNumber.setText(CardViewPassword.getCardNumber());
        viewHolder.txtCardHolder.setText(CardViewPassword.getCardHolder());
        viewHolder.txtCardExpiryMonth.setText(CardViewPassword.getCardExpiryMonth());
        viewHolder.txtCardExpiryYear.setText(CardViewPassword.getCardExpiryYear());
        viewHolder.txtCardPIN.setText(CardViewPassword.getCardPIN());
        viewHolder.txtCardCVV.setText(CardViewPassword.getCardCVV());

        viewHolder.txtComment.setText(CardViewPassword.getComment());

        viewHolder.imgOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO show Popup menu
            }
        });
    }

    @Override
    public int getItemCount() {
        return CardViewPasswordList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtKeyName;
        public TextView txtLogin;
        public TextView txtPassword;
        public TextView txtURL;
        public TextView txtBankName;
        public TextView txtCardNumber;
        public TextView txtCardHolder;
        public TextView txtCardExpiryMonth;
        public TextView txtCardExpiryYear;
        public TextView txtCardPIN;
        public TextView txtCardCVV;
        public TextView txtComment;

        public ImageView imgHidePIN;
        public ImageView imgHideCVV;
        public ImageView imgIconPassword;
        public ImageView imgOptions;

        public RelativeLayout rltPasswordCV;

        public ViewHolder(View itemView) {
            super(itemView);

            txtKeyName = (TextView)itemView.findViewById(R.id.txt_key_name);
            txtLogin = (TextView)itemView.findViewById(R.id.txt_login);
            txtPassword = (TextView)itemView.findViewById(R.id.txt_password);
            txtURL = (TextView)itemView.findViewById(R.id.txt_URL);
            txtBankName = (TextView)itemView.findViewById(R.id.txt_bank_name);
            txtCardNumber = (TextView)itemView.findViewById(R.id.txt_card_number);
            txtCardHolder = (TextView)itemView.findViewById(R.id.txt_card_holder);
            txtCardExpiryMonth = (TextView)itemView.findViewById(R.id.txt_card_expiry_month);
            txtCardExpiryYear = (TextView)itemView.findViewById(R.id.txt_card_expiry_year);
            txtCardPIN = (TextView)itemView.findViewById(R.id.txt_card_PIN);
            txtCardCVV = (TextView)itemView.findViewById(R.id.txt_card_CVV);
            txtComment = (TextView)itemView.findViewById(R.id.txt_comment);

            imgOptions = (ImageView)itemView.findViewById(R.id.imView_options);
            imgIconPassword = (ImageView)itemView.findViewById(R.id.imView_icon_password);
            imgHidePIN = (ImageView) itemView.findViewById(R.id.imView_hide_PIN);
            imgHideCVV = (ImageView) itemView.findViewById(R.id.imView_hide_CVV);

            rltPasswordCV = (RelativeLayout)itemView.findViewById(R.id.rlt_password_cv);
        }
    }

    public static String getStringByID(Context context,String stringID){
        Resources resources = context.getResources();
        return resources.getString(resources.getIdentifier(stringID,
                "string", context.getPackageName()));
    }


}
