package cry.who.boy.tap_recordatorios;

/**
 * Created by Luis J. Ortiz on 20/04/2017.
 */

public class Rec {

    private String titulo;
    private String fecha;
    private String hora;
    private String descripcion;
    private int importancia;

    public Rec(String titulo, String fecha, String hora, String descripcion, int importancia) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
        this.importancia = importancia;
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

    public int getImportancia() {
        return importancia;
    }

    public void setImportancia(int importancia) {
        this.importancia = importancia;
    }
}
