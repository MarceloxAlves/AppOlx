package br.com.marcelo.appolx;

import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by Marcelo on 07/02/2018.
 */

@Entity  public class Anuncio {
    @Id  long id;
    private String titulo;
    private double valor;
    private String data;

    Anuncio(){

    }

    public Anuncio(String titulo, double valor, String data) {
        this.titulo = titulo;
        this.valor = valor;
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getValor() {
        return valor;
    }

    public String getData() {
        return data;
    }
}
