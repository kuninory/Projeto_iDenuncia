package com.example.idenuncia.idenuncia.services;

import android.content.Context;

import com.example.idenuncia.idenuncia.R;
import com.example.idenuncia.idenuncia.model.Denuncia;
import com.example.idenuncia.idenuncia.model.LatLnge;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

public class WebTaskDenunciar extends WebTaskBase{
    private static String SERVICE_URL = "regDenuncias";

    private String SpinnerTipsDenuncia;
    private String descDenuncia;
    public WebTaskDenunciar(Context context, String SpinnerTipsDenuncia, String descDenuncia) {
        super(context, SERVICE_URL);
        this.SpinnerTipsDenuncia = SpinnerTipsDenuncia;
        this.descDenuncia = descDenuncia;
    }

    @Override
    public void handleResponse(String response) {
        Denuncia denuncia = new Denuncia();
        LatLnge localizacao = new LatLnge();

        try {
            JSONObject responseAsJSON = new JSONObject(response);
            int tipDUser = responseAsJSON.getInt("tipoDenuncia");
            denuncia.setTipoDenuncia(tipDUser);
            String username = responseAsJSON.getString("localizacao");
            denuncia.setLocalizacao(localizacao);
            String descDenuncia = responseAsJSON.getString("descDenuncia");
            denuncia.setDescricao(descDenuncia);
            EventBus.getDefault().post(denuncia);

        } catch (JSONException e) {
            if (!isSilent()) {
                EventBus.getDefault().post(new Error(getContext().getString(R.string.label_error_invalid_response)));
            }
        }
    }

    @Override
    public String getRequestBody() {
        return null;
    }

}
