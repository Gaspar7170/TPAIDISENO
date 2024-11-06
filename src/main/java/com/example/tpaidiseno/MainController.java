package com.example.tpaidiseno;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;

import java.io.InputStream;

public class MainController {
    @FXML
    private GridPane showGrid;

    @FXML
    private Button homeButton;

    @FXML
    private Button caso5CUButton;

    @FXML
    private Button casoAlternativo1Button;

    @FXML
    private ImageView profile;

    @FXML
    private void initialize() {
        // Inicialización de componentes

        try {
            profile = new ImageView();
            InputStream inputStream = getClass().getResourceAsStream("/avatar.png");

            if (inputStream == null) {
                System.err.println("No se pudo encontrar el archivo: avatar.png");
                return; // O maneja el error de otra forma
            }

            profile.setImage(new Image(inputStream));
            profile.setFitWidth(100); // Ajusta el ancho
            profile.setFitHeight(100); // Ajusta la altura
            // Inicializar otras imágenes y componentes
            showGrid.add(profile,1,1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleHomeButton(ActionEvent event) {
        showGrid.getChildren().clear();
        initialize();
    }

    @FXML
    private void handleImportarActualizacion(ActionEvent event) {
        habilitarPantalla();
    }

    private void habilitarPantalla() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("pantalla-importar.fxml"));
            if (loader.getLocation() == null) {
                System.err.println("No se pudo encontrar el archivo FXML: pantalla-importar.fxml");
                return;
            }
            showGrid.getChildren().clear();
            showGrid.getChildren().add(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCasoAlternativo1(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("pantalla-alternativa1.fxml"));
            showGrid.getChildren().clear();
            showGrid.getChildren().add(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}