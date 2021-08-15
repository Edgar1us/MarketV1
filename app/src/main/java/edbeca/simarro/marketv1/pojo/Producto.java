package edbeca.simarro.marketv1.pojo;

import java.io.Serializable;

public class Producto implements Serializable {

    private int idProducto;
    private String nombre;
    private Double precio;
    private String descripcion;
    private String estado;
    private int tiempo;
    private Usuario usuario;

    public Producto() {
    }

    public Producto(String nombre, Double precio, String descripcion, String estado, int tiempo) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.estado = estado;
        this.tiempo = tiempo;
    }

    public Producto(String nombre, Double precio, String descripcion, String estado, int tiempo, Usuario usuario) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.estado = estado;
        this.tiempo = tiempo;
        this.usuario = usuario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Producto{" +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", tiempo=" + tiempo +
                '}';
    }
}
