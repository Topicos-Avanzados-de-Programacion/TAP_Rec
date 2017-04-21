package cry.who.boy.tap_recordatorios;

/**
 * Created by Luis J. Ortiz on 20/04/2017.
 */

public class Rec {
    private int color;
    private String titulo;
    private String fecha;
    private String hora;
    private String descripcion;

    public Rec(int color, String titulo, String fecha, String hora, String descripcion) {
        this.color = color;
        this.titulo = titulo;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
