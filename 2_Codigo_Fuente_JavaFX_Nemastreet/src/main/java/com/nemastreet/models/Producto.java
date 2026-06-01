package com.nemastreet.models;

public class Producto {
    private String id;
    private String nombre;
    private double precio;
    private int stock;
    private String imagenUrl;
    private String tallas;
    private String colores;

    public Producto(String id, String nombre, double precio, int stock, String imagenUrl, String tallas, String colores) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.imagenUrl = imagenUrl;
        this.tallas = tallas;
        this.colores = colores;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }

    public String getTallas() { return tallas; }
    public void setTallas(String tallas) { this.tallas = tallas; }

    public String getColores() { return colores; }
    public void setColores(String colores) { this.colores = colores; }
}
