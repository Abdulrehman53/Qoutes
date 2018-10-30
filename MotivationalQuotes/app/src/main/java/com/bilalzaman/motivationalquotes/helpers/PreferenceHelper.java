package com.bilalzaman.motivationalquotes.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.bilalzaman.motivationalquotes.constants.Constants;

/**
 * Created by BilalZaman on 30/10/2018.
 */
public class PreferenceHelper {

    public static PreferenceHelper instance_;
    private Context context_;
    public synchronized static PreferenceHelper getInstance(){
        if(instance_==null){
            instance_ = new PreferenceHelper();
        }
        return instance_;
    }

    public void init(Context context){
        context_ = context;
    }

    public Context getContext()
    {
        return context_;
    }

    public void setContext(Context aContext)
    {
        this.context_ = aContext;
    }

    public void setInt(String key, int val){
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(context_);
        SharedPreferences.Editor ed=sp.edit();
        ed.putInt(key, val);
        ed.commit();
    }

    public int getInt(String key, int defval){
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(context_);
        return sp.getInt(key, defval);
    }

//    public void setString(String key, String val){
//        SharedPreferences preferences = context_.getSharedPreferences(Constants.PREF_NAMES, Activity.MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//
//        editor.putString( key, val );
//        editor.commit();
//		/*SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(context_);
//		Editor ed=sp.edit();
//		ed.putString(key, val);
//		ed.commit();*/
//    }
//    public void setAppString(String key, String val){
//        SharedPreferences preferences = context_.getSharedPreferences( Constants.PREF_APP, Activity.MODE_PRIVATE );
//        SharedPreferences.Editor editor = preferences.edit();
//
//        editor.putString( key, val );
//        editor.commit();
//		/*SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(context_);
//		Editor ed=sp.edit();
//		ed.putString(key, val);
//		ed.commit();*/
//    }
//    public String getAppString(String key, String defval){
//        SharedPreferences preferences = context_.getSharedPreferences(
//                Constants.PREF_APP, Activity.MODE_PRIVATE );
//        String value = preferences.getString(key, "");
//        return value;
//        //SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(context_);
//        //return sp.getString(key, defval);
//    }
    public String getString(String key, String defval){
        SharedPreferences preferences = context_.getSharedPreferences(
                Constants.PREF_NAMES, Activity.MODE_PRIVATE);
        String value = preferences.getString(key, "");
        return value;
		/*SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(context_);
		return sp.getString(key, defval);*/
    }

    public void setLong(String key, long val){
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(context_);
        SharedPreferences.Editor ed=sp.edit();
        ed.putLong(key, val);
        ed.commit();
    }

    public long getLong(String key, long defval){
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(context_);
        return sp.getLong(key, defval);
    }

    public void setBol(String key, boolean val){
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(context_);
        SharedPreferences.Editor ed=sp.edit();
        ed.putBoolean(key, val);
        ed.commit();
    }

    public boolean getBol(String key, boolean defval){
        if(context_ !=null) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context_);
            return sp.getBoolean(key, defval);
        }else {
            return true;
        }
    }

    public void clearAllPreferences(){
        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(context_);
        SharedPreferences.Editor ed=sp.edit();
        ed.clear();
        ed.commit();
        //instance_ =null;

    }
    public void removePreference(  String prefsName,
                                   String key ) {

        SharedPreferences preferences = context_.getSharedPreferences(
                prefsName, Activity.MODE_PRIVATE );
        SharedPreferences.Editor editor = preferences.edit();

        editor.remove( key );
        editor.commit();
    }
    public void removeall(  String prefsName) {

        SharedPreferences settings = context_.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        settings.edit().clear().commit();

    }

}
