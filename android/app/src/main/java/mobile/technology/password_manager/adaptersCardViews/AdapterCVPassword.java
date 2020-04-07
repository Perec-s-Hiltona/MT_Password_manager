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

import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
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

        try {

            final CardViewPassword cardViewPassword = CardViewPasswordList.get(position);

            //animation
            YoYo.with(Techniques.ZoomInLeft).duration(1500).repeat(0).playOn(viewHolder.imgIconPassword);
            viewHolder.txtKeyName.setText(cardViewPassword.getKeyName());

            hidePersonalData(viewHolder);
            hideBankData(viewHolder);

            viewHolder.switchShowPersonalData.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if(viewHolder.switchShowPersonalData.isChecked()){

                        viewHolder.txtLogin.setVisibility(View.VISIBLE);
                        viewHolder.txtPassword.setVisibility(View.VISIBLE);
                        viewHolder.txtURL.setVisibility(View.VISIBLE);

                        viewHolder.txtLogin.setText(cardViewPassword.getLogin());
                        viewHolder.txtPassword.setText(cardViewPassword.getPassword());
                        viewHolder.txtURL.setText(cardViewPassword.getURL());

                    }else {

                        hidePersonalData(viewHolder);
                    }
                }
            });

            viewHolder.switchShowBankData.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if(viewHolder.switchShowBankData.isChecked()){

                        viewHolder.txtBankName.setText(cardViewPassword.getBankName());
                        viewHolder.txtCardNumber.setText(cardViewPassword.getCardNumber());
                        viewHolder.txtCardHolder.setText(cardViewPassword.getCardHolder());
                        viewHolder.txtCardExpiryMonth.setText(cardViewPassword.getCardExpiryMonth());
                        viewHolder.txtCardExpiryYear.setText(cardViewPassword.getCardExpiryYear());
                        viewHolder.txtCardPIN.setText(cardViewPassword.getCardPIN());
                        viewHolder.txtCardCVV.setText(cardViewPassword.getCardCVV());

                        viewHolder.txtBankName.setVisibility(View.VISIBLE);
                        viewHolder.txtCardNumber.setVisibility(View.VISIBLE);
                        viewHolder.txtCardHolder.setVisibility(View.VISIBLE);
                        viewHolder.txtCardExpiryMonth.setVisibility(View.VISIBLE);
                        viewHolder.txtCardExpiryYear.setVisibility(View.VISIBLE);

                        viewHolder.imgHidePIN.setVisibility(View.VISIBLE);
                        viewHolder.imgHideCVV.setVisibility(View.VISIBLE);

                    }else {

                        hideBankData(viewHolder);
                    }
                }
            });

            viewHolder.txtComment.setText(cardViewPassword.getComment());

            viewHolder.imgOptions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO show Popup menu
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hideBankData(final ViewHolder viewHolder){

        viewHolder.txtBankName.setVisibility(View.GONE);
        viewHolder.txtCardNumber.setVisibility(View.GONE);
        viewHolder.txtCardHolder.setVisibility(View.GONE);
        viewHolder.txtCardExpiryMonth.setVisibility(View.GONE);
        viewHolder.txtCardExpiryYear.setVisibility(View.GONE);
        viewHolder.txtCardPIN.setVisibility(View.GONE);
        viewHolder.txtCardCVV.setVisibility(View.GONE);

        viewHolder.imgHidePIN.setVisibility(View.GONE);
        viewHolder.imgHideCVV.setVisibility(View.GONE);
    }

    private void hidePersonalData(final ViewHolder viewHolder){

        viewHolder.txtLogin.setVisibility(View.GONE);
        viewHolder.txtPassword.setVisibility(View.GONE);
        viewHolder.txtURL.setVisibility(View.GONE);
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

        public Switch switchShowPersonalData;
        public Switch switchShowBankData;

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

            switchShowPersonalData = (Switch)itemView.findViewById(R.id.switch_show_personal_data);
            switchShowBankData = (Switch)itemView.findViewById(R.id.switch_show_bank_data);

            rltPasswordCV = (RelativeLayout)itemView.findViewById(R.id.rlt_password_cv);
        }
    }

    public static String getStringByID(Context context,String stringID){
        Resources resources = context.getResources();
        return resources.getString(resources.getIdentifier(stringID,
                "string", context.getPackageName()));
    }

}
