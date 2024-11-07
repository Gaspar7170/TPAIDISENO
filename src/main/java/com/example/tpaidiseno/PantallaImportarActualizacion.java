package com.example.tpaidiseno;

import com.example.tpaidiseno.Entidades.Bodega;
import com.example.tpaidiseno.Gestores.GestorImportarActualizacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.List;

public class PantallaImportarActualizacion {

    @FXML
    private GridPane gridBodegasActualizar = new GridPane();

    //@FXML
    //private TableView<String> grillaBodegasActualiza = new TableView<>(); // Cambiado a TableView<String>

    @FXML
    private Button seleccionBodegaBoton_Click;

    @FXML
    private TextField bodegaSeleccionadaTextbox;

    @FXML
    private GridPane gridMostrarResumenVino;

    @FXML
    private ListView<String> grillaBodegasActualizar;

    @FXML
    private TableView<Object> grillaResumenesVino = new TableView<>();

    private GestorImportarActualizacion gestorImportar;
    private List<Bodega> listadoBodegasParaSeleccion;

    @FXML
    private void initialize() {
        gestorImportar = new GestorImportarActualizacion(this);
        gestorImportar.opcionImportarActualizacion();

        gridMostrarResumenVino.setVisible(false);
    }

    @FXML
    private void grillaBodegasActualizar_MouseDoubleClick(MouseEvent event) {
        selOpTomarSeleccionBodega();
    }

    @FXML
    private void seleccionBodegaBoton_Click() {
        selOpTomarSeleccionBodega();
    }
    /*
    private void selOpTomarSeleccionBodega() {
        grillaResumenesVino.setItems(null);
        int index = grillaBodegasActualizar.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            String nombre = listadoBodegasParaSeleccion.get(index);
            bodegaSeleccionadaTextbox.setText(nombre);
            gestorImportar.tomarSeleccionBodega(nombre);
        } else {
            showErrorMessage("ERROR: No se seleccionó ninguna bodega");
        }
    }


     */

    public void mostrarBodegasParaActualizar(List<Bodega> bodegasActualizar) {
        listadoBodegasParaSeleccion = bodegasActualizar;

        // Convierte cada Bodega a su nombre (o algún otro atributo de tipo String)
        ObservableList<String> data = FXCollections.observableArrayList();
        for (Bodega bodega : bodegasActualizar) {
            data.add(bodega.getNombre()); // Usa el método adecuado para obtener el nombre
        }
        grillaBodegasActualizar.setItems(data);
    }

    public void selOpTomarSeleccionBodega() {
        grillaResumenesVino.setItems(null);

        int index = grillaBodegasActualizar.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            Bodega bodegaSeleccionada = listadoBodegasParaSeleccion.get(index);
            bodegaSeleccionadaTextbox.setText(bodegaSeleccionada.getNombre());
            gestorImportar.tomarSeleccionBodega(bodegaSeleccionada);
        } else {
            showErrorMessage("ERROR: No se seleccionó ninguna bodega");
        }
    }





    public void mostrarResumenVinos(List<String> data) {
        gridMostrarResumenVino.setVisible(true);
        ObservableList<Object> resumenData = FXCollections.observableArrayList(data);
        grillaResumenesVino.setItems(resumenData);
    }

    private void showErrorMessage(String message) {
        // Muestra un mensaje de error utilizando un diálogo o una etiqueta
    }
}
