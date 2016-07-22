package com.example.idenuncia.idenuncia.services;

import android.content.Context;

import com.example.idenuncia.idenuncia.R;
import com.example.idenuncia.idenuncia.model.User;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WebTaskCadastroUsuario extends WebTaskBase {

    private static String SERVICE_URL = "cadastro";
    private String nome;
    private String username;
    private String senha;
    private String email;

    public WebTaskCadastroUsuario(Context context, String nome, String username, String senha, String email) {
        super(context, SERVICE_URL);
        this.nome = nome;
        this.username = username;
        this.senha = senha;
        this.email = email;
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
        requestMap.put("nome", nome);
        requestMap.put("username", username);
        requestMap.put("senha", senha);
        requestMap.put("email", email);

        JSONObject json = new JSONObject(requestMap);
        String jsonString = json.toString();

        return jsonString;
    }
}
