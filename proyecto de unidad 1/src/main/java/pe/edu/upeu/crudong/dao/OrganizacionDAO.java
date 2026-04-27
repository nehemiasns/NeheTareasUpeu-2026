package pe.edu.upeu.crudong.dao;

import pe.edu.upeu.crudong.model.Organizacion;
<<<<<<< HEAD
import pe.edu.upeu.crudong.utils.ConnS;

import java.sql.*;
=======

>>>>>>> 1803918781833cc43462b40711c1c79ee20402d0
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrganizacionDAO {
<<<<<<< HEAD

    public List<Organizacion> listarTodas() {
        List<Organizacion> organizaciones = new ArrayList<>();
        String sql = "SELECT * FROM organizaciones";
        try (Connection conn = ConnS.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                organizaciones.add(new Organizacion(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("numero_acta"),
                        rs.getString("representante_legal"),
                        rs.getString("objeto_social"),
                        LocalDate.parse(rs.getString("vigencia"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return organizaciones;
    }

    public void agregar(Organizacion org) {
        String sql = "INSERT INTO organizaciones(id, nombre, numero_acta, representante_legal, objeto_social, vigencia) VALUES(?,?,?,?,?,?)";
        try (Connection conn = ConnS.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, org.getId());
            pstmt.setString(2, org.getNombre());
            pstmt.setString(3, org.getNumeroActa());
            pstmt.setString(4, org.getRepresentanteLegal());
            pstmt.setString(5, org.getObjetoSocial());
            pstmt.setString(6, org.getVigencia().toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizar(String id, Organizacion orgActualizada) {
        String sql = "UPDATE organizaciones SET nombre = ?, numero_acta = ?, representante_legal = ?, objeto_social = ?, vigencia = ? WHERE id = ?";
        try (Connection conn = ConnS.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, orgActualizada.getNombre());
            pstmt.setString(2, orgActualizada.getNumeroActa());
            pstmt.setString(3, orgActualizada.getRepresentanteLegal());
            pstmt.setString(4, orgActualizada.getObjetoSocial());
            pstmt.setString(5, orgActualizada.getVigencia().toString());
            pstmt.setString(6, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
=======
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
>>>>>>> 1803918781833cc43462b40711c1c79ee20402d0
        }
    }

    public void eliminar(String id) {
<<<<<<< HEAD
        String sql = "DELETE FROM organizaciones WHERE id = ?";
        try (Connection conn = ConnS.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Organizacion> buscarPorId(String id) {
        String sql = "SELECT * FROM organizaciones WHERE id = ?";
        try (Connection conn = ConnS.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Organizacion(
                            rs.getString("id"),
                            rs.getString("nombre"),
                            rs.getString("numero_acta"),
                            rs.getString("representante_legal"),
                            rs.getString("objeto_social"),
                            LocalDate.parse(rs.getString("vigencia"))
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
=======
        organizaciones.removeIf(org -> org.getId().equals(id));
    }

    public Optional<Organizacion> buscarPorId(String id) {
        return organizaciones.stream()
            .filter(org -> org.getId().equals(id))
            .findFirst();
>>>>>>> 1803918781833cc43462b40711c1c79ee20402d0
    }
}
