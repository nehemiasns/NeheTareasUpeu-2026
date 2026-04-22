package pe.edu.upeu.crudong.dao;

import pe.edu.upeu.crudong.model.Organizacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrganizacionDAO {
    private List<Organizacion> organizaciones;

    public OrganizacionDAO() {
        this.organizaciones = new ArrayList<>();
        organizaciones.add(new Organizacion("Cruz Roja", "ACT-001", "Juan Perez", "Asistencia Humanitaria", LocalDate.now().plusMonths(6)));
        organizaciones.add(new Organizacion("Salvemos al Planeta", "ACT-002", "Maria Lopez", "Ecologia", LocalDate.now().plusDays(15)));
        organizaciones.add(new Organizacion("Ayuda Animal", "ACT-003", "Carlos Torres", "Proteccion Animal", LocalDate.now().minusDays(5)));
    }

    public List<Organizacion> listarTodas() {
        return new ArrayList<>(organizaciones);
    }

    public void agregar(Organizacion org) {
        organizaciones.add(org);
    }

    public void actualizar(String id, Organizacion orgActualizada) {
        for (int i = 0; i < organizaciones.size(); i++) {
            if (organizaciones.get(i).getId().equals(id)) {
                orgActualizada.setId(id);
                organizaciones.set(i, orgActualizada);
                return;
            }
        }
    }

    public void eliminar(String id) {
        organizaciones.removeIf(org -> org.getId().equals(id));
    }

    public Optional<Organizacion> buscarPorId(String id) {
        return organizaciones.stream()
            .filter(org -> org.getId().equals(id))
            .findFirst();
    }
}
