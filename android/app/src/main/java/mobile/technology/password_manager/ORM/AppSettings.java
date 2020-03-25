package mobile.technology.password_manager.ORM;

import com.orm.SugarRecord;


public class AppSettings extends SugarRecord {

    private String typePassword;
    private String valuePassword;

    public AppSettings(){

    }

    public AppSettings(String typePassword, String valuePassword) {
        this.typePassword = typePassword;
        this.valuePassword = valuePassword;
    }

    public String getTypePassword() {
        return typePassword;
    }

    public void setTypePassword(String typePassword) {
        this.typePassword = typePassword;
    }

    public String getValuePassword() {
        return valuePassword;
    }

    public void setValuePassword(String valuePassword) {
        this.valuePassword = valuePassword;
    }
}
