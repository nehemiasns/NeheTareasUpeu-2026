package DIFICIL.Ejercicio15_Excepciones;

public class CapacidadExcedidaException extends RuntimeException {
    public CapacidadExcedidaException(String mensaje) {
        super(mensaje);
    }
}
