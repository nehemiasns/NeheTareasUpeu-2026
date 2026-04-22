package pe.edu.upeu.crudong.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organizacion {
    private String id;
    private String nombre;
    private String numeroActa;
    private String representanteLegal;
    private String objetoSocial;
    private LocalDate vigencia;

    public Organizacion(String nombre, String numeroActa, String representanteLegal, String objetoSocial,
            LocalDate vigencia) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.numeroActa = numeroActa;
        this.representanteLegal = representanteLegal;
        this.objetoSocial = objetoSocial;
        this.vigencia = vigencia;
    }
}
