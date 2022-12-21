package com.pry.sublimadoscr.hecho;

public class Pedido {
    String codigo_id;


    String cantidad_p;
    String formapago;
    String cantidad_t;
    String mensaje;
    public Pedido(String codigo_id, String cantidad_p, String formapago, String cantidad_t, String mensaje) {
        this.codigo_id = codigo_id;
        this.cantidad_p = cantidad_p;
        this.formapago = formapago;
        this.cantidad_t = cantidad_t;
        this.mensaje = mensaje;
    }

    public String getCodigo_id() {
        return codigo_id;
    }

    public void setCodigo_id(String codigo_id) {
        this.codigo_id = codigo_id;
    }

    public String getCantidad_p() {
        return cantidad_p;
    }

    public void setCantidad_p(String cantidad_p) {
        this.cantidad_p = cantidad_p;
    }

    public String getFormapago() {
        return formapago;
    }

    public void setFormapago(String formapago) {
        this.formapago = formapago;
    }

    public String getCantidad_t() {
        return cantidad_t;
    }

    public void setCantidad_t(String cantidad_t) {
        this.cantidad_t = cantidad_t;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
