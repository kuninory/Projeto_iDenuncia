package com.example.idenuncia.idenuncia.services;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.idenuncia.idenuncia.model.User;

public class SessionManagement {

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "IDenunciasPrefs";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_IDUSER = "idUser";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_NOMECOMPLETO = "nomeCompleto";
    public static final String KEY_EMAIL = "email";

    // Constructor
    public SessionManagement(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(User user) {

        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_IDUSER, user.getIdUser());
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putString(KEY_NOMECOMPLETO, user.getNomeCompleto());
        editor.putString(KEY_EMAIL, user.getEmail());

        editor.commit();
    }

    public void checkLogin() {

        if (!this.isLoggedIn()) {

            /*
            Não está logado!
            Intent i = new Intent(_context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            _context.startActivity(i);
             */
        }

    }

    public User getUserDetails() {
        User user = new User();

        user.setIdUser(pref.getString(KEY_IDUSER, null));
        user.setUsername(pref.getString(KEY_USERNAME, null));
        user.setNomeCompleto(pref.getString(KEY_NOMECOMPLETO, null));
        user.setEmail(pref.getString(KEY_EMAIL, null));

        return user;
    }

    public void logoutUser() {

        editor.clear();
        editor.commit();

        /*
        Não está logado!
        Intent i = new Intent(_context, LoginActivity.class);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        _context.startActivity(i);
        */
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}
