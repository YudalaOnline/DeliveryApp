package deliveryapp.yudala.com.deliveryapp;

/**
 * Created by Kpono on 2/21/2017.
 */



        import android.content.Context;
        import android.content.SharedPreferences;

public class Data {
    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;

    private static final String PREF_NAME = "LoginData";
    private static final int PRIVATE_MODE = 0;
    private static final String USERNAME="username";
    private static final String PASSWORD="password";
    private static final String NAME="name";

    private Context context;

    public Data(Context context){
        this.context = context;
        pref = this.context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = pref.edit();
    }

    public static void setNAME(String NAME) {
        editor.putString(Data.NAME,NAME);
        editor.commit();
    }

    public static String getNAME() {
        return pref.getString(NAME,null);
    }

    public static void setUSERNAME(String USERNAME) {
        editor.putString(Data.USERNAME,USERNAME);
        editor.commit();
    }

    public static String getUSERNAME() {
        return pref.getString(USERNAME,null);
    }

    public static void setPASSWORD(String PASSWORD) {
        editor.putString(Data.PASSWORD,PASSWORD);
        editor.commit();
    }

    public static String getPASSWORD() {
        return pref.getString(PASSWORD,null);
    }
}
