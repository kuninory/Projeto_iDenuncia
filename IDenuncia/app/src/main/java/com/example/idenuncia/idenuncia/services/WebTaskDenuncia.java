package com.example.idenuncia.idenuncia.services;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.example.idenuncia.idenuncia.R;
import com.example.idenuncia.idenuncia.model.Denuncia;

public class WebTaskDenuncia extends WebTaskBase {

    private static String SERVICE_URL = "buscaDenuncias";
    private String minhasDenun;
    private String limite;
    private String ultimaDenunCarregada;

    public WebTaskDenuncia(Context context, String minhasDenun, String limite,
                           String ultimaDenunCarregada) {
        super(context, SERVICE_URL);
        this.minhasDenun = minhasDenun;
        this.limite = limite;
        this.ultimaDenunCarregada = ultimaDenunCarregada;
    }

    @Override
    public void handleResponse(String response) {
        ArrayList<Denuncia> denunciaList = new ArrayList<Denuncia>();
        try {
            JSONObject responseAsJSON = new JSONObject(response);
            JSONArray denuncias = responseAsJSON.getJSONArray("denuncias");

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

            for (int i = 0; i < denuncias.length(); i++) {

                JSONObject jsonDenuncia = denuncias.getJSONObject(i);
                String idDenuncia = jsonDenuncia.getString("idDenuncia");
                int tipoDenuncia = jsonDenuncia.getInt("tipoDenuncia");
                int contadorDenun = jsonDenuncia.getInt("contadorDenun");
                Date data = new Date();
                try {
                    data = format.parse(jsonDenuncia.getString("data"));
                } catch (ParseException e) {
                }

                Denuncia denuncia = new Denuncia();
                denuncia.setIdDenuncia(idDenuncia);
                denuncia.setTipoDenuncia(tipoDenuncia);
                denuncia.setNomeTipoDenuncia("Tipo da Denúncia");
                denuncia.setContadorDenun(contadorDenun);
                denuncia.setData(data);
                denuncia.setDescricao("");

                denunciaList.add(denuncia);
            }

            EventBus.getDefault().post(denunciaList);

        } catch (JSONException e) {
            if (!isSilent()) {
                EventBus.getDefault().post(new Error(getContext().getString(R.string.label_error_invalid_response)));
            }
        }
    }

    @Override
    public String getRequestBody() {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("minhasDenun", minhasDenun);
        requestMap.put("limite", limite);
        requestMap.put("ultimaDenunCarregada", ultimaDenunCarregada);

        JSONObject json = new JSONObject(requestMap);
        String jsonString = json.toString();

        return jsonString;
    }

}
