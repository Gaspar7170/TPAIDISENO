package com.example.tpaidiseno;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

import java.util.List;

public class PantallaAlternativa1 {

    @FXML
    private TableView<String> grillaBodegasActualizar;

    @FXML
    private TextField bodegaSeleccionadaTextbox;

    @FXML
    private Button seleccionBodegaBoton;

    @FXML
    private Label titleLabel;

    @FXML
    private Label mainGrid;

    private List<String> listadoBodegasParaSeleccion;

    @FXML
    public void initialize() {
        // Inicializa el controlador, aquí puedes cargar la lista de bodegas
    }

    @FXML
    public void alternativo1Control_Loaded() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("ATENCION");
        alert.setHeaderText(null);
        alert.setContentText("ATENCION: No se encontro ninguna bodega para actualizar");
        alert.showAndWait();
    }

    @FXML
    private void seleccionBodegaBoton_Click(ActionEvent event) {
        selOpTomarSeleccionBodega();
    }

    @FXML
    private void grillaBodegasActualizar_MouseDoubleClick(MouseEvent event) {
        selOpTomarSeleccionBodega();
    }

    private void selOpTomarSeleccionBodega() {
        int index = grillaBodegasActualizar.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            String nombre = listadoBodegasParaSeleccion.get(index);
            bodegaSeleccionadaTextbox.setText(nombre);
            // Aquí puedes agregar lógica para tomar la selección
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("ERROR: No se selecciono ninguna bodega");
            alert.showAndWait();
        }
    }
}

