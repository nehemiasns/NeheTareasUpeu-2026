package pe.edu.upeu.crudong.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pe.edu.upeu.crudong.dao.OrganizacionDAO;
import pe.edu.upeu.crudong.model.Organizacion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MainController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtActa;
    @FXML
    private TextField txtRepresentante;
    @FXML
    private TextField txtObjetoSocial;
    @FXML
    private DatePicker dpVigencia;

    @FXML
    private TableView<Organizacion> tvOrganizaciones;
    @FXML
    private TableColumn<Organizacion, String> colNombre;
    @FXML
    private TableColumn<Organizacion, String> colActa;
    @FXML
    private TableColumn<Organizacion, String> colRepresentante;
    @FXML
    private TableColumn<Organizacion, String> colObjetoSocial;
    @FXML
    private TableColumn<Organizacion, LocalDate> colVigencia;

    private OrganizacionDAO dao;
    private ObservableList<Organizacion> masterData;
    private String idSeleccionado = null;

    @FXML
    public void initialize() {
        dao = new OrganizacionDAO();
        masterData = FXCollections.observableArrayList(dao.listarTodas());
        tvOrganizaciones.setItems(masterData);

        tvOrganizaciones.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDetalles(newValue));

        tvOrganizaciones.setRowFactory(tv -> new TableRow<Organizacion>() {
            @Override
            protected void updateItem(Organizacion item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setStyle("");
                } else {
                    if (item.getVigencia() != null) {
                        long diasParaVencer = ChronoUnit.DAYS.between(LocalDate.now(), item.getVigencia());
                        if (diasParaVencer < 0) {
                            setStyle("-fx-background-color: #ffcccc;"); // Rojo: Vencido
                        } else if (diasParaVencer <= 30) {
                            setStyle("-fx-background-color: #ffebcc;"); // Naranja: Por vencer (<30 dias)
                        } else {
                            setStyle("");
                        }
                    } else {
                        setStyle("");
                    }
                }
            }
        });
    }

    private void cargarDatos() {
        masterData.setAll(dao.listarTodas());
    }

    private void mostrarDetalles(Organizacion org) {
        if (org != null) {
            idSeleccionado = org.getId();
            txtNombre.setText(org.getNombre());
            txtActa.setText(org.getNumeroActa());
            txtRepresentante.setText(org.getRepresentanteLegal());
            txtObjetoSocial.setText(org.getObjetoSocial());
            dpVigencia.setValue(org.getVigencia());
        }
    }

    @FXML
    void limpiarFormulario() {
        txtNombre.clear();
        txtActa.clear();
        txtRepresentante.clear();
        txtObjetoSocial.clear();
        dpVigencia.setValue(null);
        idSeleccionado = null;
        tvOrganizaciones.getSelectionModel().clearSelection();
    }

    @FXML
    void guardar(ActionEvent event) {
        if (!validarFormulario())
            return;

        Organizacion nueva = new Organizacion(
                txtNombre.getText(),
                txtActa.getText(),
                txtRepresentante.getText(),
                txtObjetoSocial.getText(),
                dpVigencia.getValue());
        dao.agregar(nueva);
        cargarDatos();
        limpiarFormulario();
    }

    @FXML
    void actualizar(ActionEvent event) {
        if (idSeleccionado == null) {
            mostrarAlerta("Error", "Seleccione una organización para actualizar");
            return;
        }
        if (!validarFormulario())
            return;

        Organizacion actualizada = new Organizacion(
                txtNombre.getText(),
                txtActa.getText(),
                txtRepresentante.getText(),
                txtObjetoSocial.getText(),
                dpVigencia.getValue());

        dao.actualizar(idSeleccionado, actualizada);
        cargarDatos();
        limpiarFormulario();
    }

    @FXML
    void eliminar(ActionEvent event) {
        if (idSeleccionado == null) {
            mostrarAlerta("Error", "Seleccione una organización para eliminar");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmar");
        confirm.setHeaderText(null);
        confirm.setContentText("¿Está seguro de eliminar este registro?");

        confirm.showAndWait().ifPresent(res -> {
            if (res == ButtonType.OK) {
                dao.eliminar(idSeleccionado);
                cargarDatos();
                limpiarFormulario();
            }
        });
    }

    private boolean validarFormulario() {
        if (txtNombre.getText().isEmpty() || txtActa.getText().isEmpty()
                || txtRepresentante.getText().isEmpty() || txtObjetoSocial.getText().isEmpty()
                || dpVigencia.getValue() == null) {
            mostrarAlerta("Advertencia", "Todos los campos son obligatorios");
            return false;
        }
        return true;
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
