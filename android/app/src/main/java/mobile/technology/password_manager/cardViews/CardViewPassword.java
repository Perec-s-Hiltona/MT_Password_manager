package mobile.technology.password_manager.cardViews;

public class CardViewPassword {

    private int itemID;
    private String keyName;
    private String login;
    private String password;
    private String URL;

    private String bankName;
    private String cardNumber;
    private String cardHolder;
    private String cardExpiryMonth;
    private String cardExpiryYear;
    private String cardCVV;
    private String cardPIN;
    private String comment;

    public CardViewPassword(int itemID,
                            String keyName,
                            String login, String password, String URL,
                            String bankName, String cardNumber, String cardHolder,
                            String cardExpiryMonth, String cardExpiryYear,
                            String cardCVV, String cardPIN,
                            String comment) {

        this.itemID = itemID;
        this.keyName = keyName;
        this.login = login;
        this.password = password;
        this.URL = URL;
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.cardExpiryMonth = cardExpiryMonth;
        this.cardExpiryYear = cardExpiryYear;
        this.cardCVV = cardCVV;
        this.cardPIN = cardPIN;
        this.comment = comment;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getCardExpiryMonth() {
        return cardExpiryMonth;
    }

    public void setCardExpiryMonth(String cardExpiryMonth) {
        this.cardExpiryMonth = cardExpiryMonth;
    }

    public String getCardExpiryYear() {
        return cardExpiryYear;
    }

    public void setCardExpiryYear(String cardExpiryYear) {
        this.cardExpiryYear = cardExpiryYear;
    }

    public String getCardCVV() {
        return cardCVV;
    }

    public void setCardCVV(String cardCVV) {
        this.cardCVV = cardCVV;
    }

    public String getCardPIN() {
        return cardPIN;
    }

    public void setCardPIN(String cardPIN) {
        this.cardPIN = cardPIN;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
