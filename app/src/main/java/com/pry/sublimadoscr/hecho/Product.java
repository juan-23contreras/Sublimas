package com.pry.sublimadoscr.hecho;

public class Product {
    private String   $codigo;
    private String   $id;
    private String $categoria;
    private String nombre;
    private String $precio ;
    private String  $descripcion ;
    private String   $cantidad ;

    public Product(String $id,String $codigo, String $categoria, String nombre, String $precio, String $descripcion, String $cantidad, String $photo, String $caracteristicas) {
        this.$id= $id;
        this.$codigo = $codigo;
        this.$categoria = $categoria;
        this.nombre = nombre;
        this.$precio = $precio;
        this.$descripcion = $descripcion;
        this.$cantidad = $cantidad;
        this.$photo = $photo;
        this.$caracteristicas = $caracteristicas;
    }

    private String    $photo;
    private String $caracteristicas;


    public String get$codigo() {
        return $codigo;
    }

    public void set$codigo(String $codigo) {
        this.$codigo = $codigo;
    }

    public String get$categoria() {
        return $categoria;
    }

    public void set$categoria(String $categoria) {
        this.$categoria = $categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String get$precio() {
        return $precio;
    }

    public void set$precio(String $precio) {
        this.$precio = $precio;
    }

    public String get$descripcion() {
        return $descripcion;
    }

    public void set$descripcion(String $descripcion) {
        this.$descripcion = $descripcion;
    }

    public String get$cantidad() {
        return $cantidad;
    }

    public void set$cantidad(String $cantidad) {
        this.$cantidad = $cantidad;
    }

    public String get$photo() {
        return $photo;
    }

    public void set$photo(String $photo) {
        this.$photo = $photo;
    }

    public String get$caracteristicas() {
        return $caracteristicas;
    }

    public void set$caracteristicas(String $caracteristicas) {
        this.$caracteristicas = $caracteristicas;
    }

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }
}
