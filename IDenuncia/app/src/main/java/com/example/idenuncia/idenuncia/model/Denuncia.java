package com.example.idenuncia.idenuncia.model;

import java.io.Serializable;
import java.util.Date;

public class Denuncia implements Serializable {
    private String idDenuncia;
    private String idUser;
    private int contadorDenun;
    private Date data;
    private int tipoDenuncia;
    private String nomeTipoDenuncia;
    private LatLnge localizacao;
    private String descricao;

    public String getNomeTipoDenuncia() {
        return nomeTipoDenuncia;
    }

    public void setNomeTipoDenuncia(String nomeTipoDenuncia) {
        this.nomeTipoDenuncia = nomeTipoDenuncia;
    }

    public String getIdDenuncia() {
        return idDenuncia;
    }

    public void setIdDenuncia(String idDenuncia) {
        this.idDenuncia = idDenuncia;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public int getContadorDenun() {
        return contadorDenun;
    }

    public void setContadorDenun(int contadorDenun) {
        this.contadorDenun = contadorDenun;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getTipoDenuncia() {
        return tipoDenuncia;
    }

    public void setTipoDenuncia(int tipoDenuncia) {
        this.tipoDenuncia = tipoDenuncia;
    }

    public LatLnge getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(LatLnge localizacao) {
        this.localizacao = localizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
