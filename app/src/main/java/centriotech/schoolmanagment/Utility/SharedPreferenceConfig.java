package centriotech.schoolmanagment.Utility;

import android.content.Context;
import android.content.SharedPreferences;

import centriotech.schoolmanagment.R;

public class SharedPreferenceConfig {

    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";
    private SharedPreferences sharedPreferences;
    Context context;
    public SharedPreferenceConfig(Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences(context.getResources().getString(R.string.login_preference_company),Context.MODE_PRIVATE);

    }
    public void writeLoginStatus(boolean status){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.login_status_preference),status);
        editor.commit();


    }

    public void writeLogoutStatus(boolean status){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.login_status_preference),status);
        editor.clear();
        editor.commit();


    }

    public boolean logout() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

    public boolean readLoginStatus(){
        boolean status=false;
        status=sharedPreferences.getBoolean(context.getResources().getString(R.string.login_status_preference),false);
        return status;
    }

    public void putnum(String num){

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.putString("user",num);
        editor.commit();

    }
    public String getGR(String num){
        String userpassword=  sharedPreferences.getString(num,null);
        return  userpassword;


    }
    public void putGR(String classes){

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("GR",classes);
        editor.commit();

    }
    public String getImage(String image){
        String userpassword=  sharedPreferences.getString(image,null);
        return  userpassword;


    }
    public void putImage(String image){

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("image",image);
        editor.commit();

    }
    public String getRoll(String num){
        String userpassword=  sharedPreferences.getString(num,null);
        return  userpassword;


    }
    public void putRoll(String classes){

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("Roll",classes);
        editor.commit();

    }
    public String getnum(String num){
        String userpassword=  sharedPreferences.getString(num,null);
        return  userpassword;


    }
    public void putClass(String classes){

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("class",classes);
        editor.commit();

    }
    public String getClass(String classes){
        String userpassword=  sharedPreferences.getString(classes,null);
        return  userpassword;


    }
    public void putDivision(String division){

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("division",division);
        editor.commit();

    }
    public String getDivision(String division){
        String userpassword=  sharedPreferences.getString(division,null);
        return  userpassword;


    }
    public void putFrag(String fragment){

        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("fragment",fragment);
        editor.commit();

    }
    public String getFrag(String fragment){
        String userpassword=  sharedPreferences.getString(fragment,null);
        return  userpassword;


    }
}
