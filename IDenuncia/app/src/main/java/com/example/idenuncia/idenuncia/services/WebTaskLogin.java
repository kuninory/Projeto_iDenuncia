package com.example.idenuncia.idenuncia.services;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.example.idenuncia.idenuncia.R;
import com.example.idenuncia.idenuncia.model.User;

public class WebTaskLogin extends WebTaskBase {

    private static String SERVICE_URL = "login";
    private String username;
    private String senha;

    public WebTaskLogin(Context context, String username, String senha) {
        super(context, SERVICE_URL);
        this.username = username;
        this.senha = senha;
    }

    @Override
    public void handleResponse(String response) {
        User user = new User();
        try {
            JSONObject responseAsJSON = new JSONObject(response);
            String idUser = responseAsJSON.getString("idUser");
            user.setIdUser(idUser);
            String username = responseAsJSON.getString("username");
            user.setUsername(username);
            String nomeCompleto = responseAsJSON.getString("nomeCompleto");
            user.setNomeCompleto(nomeCompleto);
            String email = responseAsJSON.getString("email");
            user.setEmail(email);
            EventBus.getDefault().post(user);

        } catch (JSONException e) {
            if (!isSilent()) {
                EventBus.getDefault().post(new Error(getContext().getString(R.string.label_error_invalid_response)));
            }
        }
    }

    private User readUser(JSONObject userAsJSON) throws JSONException {
        User user = new User();
        user.setIdUser(userAsJSON.getString("idUser"));
        user.setUsername(userAsJSON.getString("username"));
        user.setNomeCompleto(userAsJSON.getString("nomeCompleto"));
        user.setEmail(userAsJSON.getString("email"));
        return user;
    }

    @Override
    public String getRequestBody() {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("username", username);
        requestMap.put("senha", senha);

        JSONObject json = new JSONObject(requestMap);
        String jsonString = json.toString();

        return jsonString;
    }

}
