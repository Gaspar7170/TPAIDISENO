package com.example.tpaidiseno.Pantallas;


import com.example.tpaidiseno.Gestores.GestorImportarActualizacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.List;

public class PantallaImportarActualizacion {

    // Atributos
    private GestorImportarActualizacion gestorImportar;
    private List<String> listadoBodegasParaSeleccion;

    @FXML
    private GridPane gridMostrarResumenVino;
    @FXML
    private TableView<String> grillaBodegasActualizar;
    @FXML
    private TableView<String[]> grillaResumenesVino;
    @FXML
    private TextField bodegaSeleccionadaTextbox;

    public PantallaImportarActualizacion() {
        // Constructor vacío
    }

    // Paso 1 del Caso de Uso
    @FXML
    private void initialize() {
        // Ocultar el panel de resumen al inicio
        gridMostrarResumenVino.setVisible(false);

        // Crear el gestor asociado a esta pantalla
        gestorImportar = new GestorImportarActualizacion(this);
        gestorImportar.opcionImportarActualizacion();
    }

    // Paso 2 y 3 del Caso de Uso
    public void mostrarBodegasParaActualizar(List<String> bodegasActualizar) {
        listadoBodegasParaSeleccion = bodegasActualizar;

        // Crear datos para la tabla
        ObservableList<String> datosMostrar = FXCollections.observableArrayList(listadoBodegasParaSeleccion);
        grillaBodegasActualizar.setItems(datosMostrar);
    }

    // Paso 4 del Caso de Uso
    @FXML
    private void grillaBodegasActualizar_MouseDoubleClick() {
        selOpTomarSeleccionBodega();
    }

    @FXML
    private void seleccionBodegaBoton_Click(ActionEvent event) {
        selOpTomarSeleccionBodega();
    }

    private void selOpTomarSeleccionBodega() {
        grillaResumenesVino.setItems(null);
        int index = grillaBodegasActualizar.getSelectionModel().getSelectedIndex();

        if (index != -1) {
            String nombre = listadoBodegasParaSeleccion.get(index);
            bodegaSeleccionadaTextbox.setText(nombre);
            gestorImportar.tomarSeleccionBodega(nombre);
        } else {
            // Caso Alternativo 1
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No se seleccionó ninguna bodega");
            alert.showAndWait();
        }
    }

    // Paso 6 del Caso de Uso
    public void mostrarResumenVinos(List<String[]> datos) {
        gridMostrarResumenVino.setVisible(true);

        ObservableList<String[]> datosResumen = FXCollections.observableArrayList(datos);
        grillaResumenesVino.setItems(datosResumen);
    }
}
