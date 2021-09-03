package edbeca.simarro.marketv1.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaccion implements Serializable {

    private int id;
    private int tipo;
    private Date fecha;
    private String descripcion;
    private Double importe;
    private Usuario usuarioOrigen;
    private Usuario usuarioDestino;

    public Transaccion() {
    }

    public Transaccion(int tipo, Date fecha, String descripcion, Double importe, Usuario usuarioOrigen, Usuario usuarioDestino) {
        this.tipo = tipo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.importe = importe;
        this.usuarioOrigen = usuarioOrigen;
        this.usuarioDestino = usuarioDestino;
    }

    public Transaccion(int id, int tipo, Date fecha, String descripcion, Double importe, Usuario usuarioOrigen, Usuario usuarioDestino) {
        this.id = id;
        this.tipo = tipo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.importe = importe;
        this.usuarioOrigen = usuarioOrigen;
        this.usuarioDestino = usuarioDestino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Usuario getUsuarioOrigen() {
        return usuarioOrigen;
    }

    public void setUsuarioOrigen(Usuario usuarioOrigen) {
        this.usuarioOrigen = usuarioOrigen;
    }

    public Usuario getUsuarioDestino() {
        return usuarioDestino;
    }

    public void setUsuarioDestino(Usuario usuarioDestino) {
        this.usuarioDestino = usuarioDestino;
    }

    @Override
    public String toString(){
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        return "\nFecha operacion: " + formateador.format(this.fecha) + "\nDescripcion: " + this.descripcion + "\nImporte: " +
                this.importe + "\n";
    }

    public String mostrarDatos(){
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        return "\nFecha operacion: " + formateador.format(this.fecha) + "\nDescripcion: " + this.descripcion + "\nImporte: " +
                this.importe + "\n" + "\nUsuario origen: " + this.usuarioOrigen + "\nUsuario destino: " + this.usuarioDestino + "\n";
    }

}
