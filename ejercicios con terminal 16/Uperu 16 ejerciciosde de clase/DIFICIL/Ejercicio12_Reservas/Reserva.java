package DIFICIL.Ejercicio12_Reservas;

public class Reserva {
    private int id;
    private String cliente;
    private String fecha;
    private String habitacion;
    private EstadoReserva estado;

    public Reserva(int id, String cliente, String fecha, String habitacion) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.habitacion = habitacion;
        this.estado = EstadoReserva.PENDIENTE;
    }

    public int getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", cliente='" + cliente + '\'' +
                ", fecha='" + fecha + '\'' +
                ", habitacion='" + habitacion + '\'' +
                ", estado=" + estado +
                '}';
    }
}
