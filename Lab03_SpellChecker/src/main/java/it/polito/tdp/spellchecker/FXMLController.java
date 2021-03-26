package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	public Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblChoose;

    @FXML
    private ChoiceBox<?> dropLanguage;

    @FXML
    private TextArea txtInizio;

    @FXML
    private Button btnCheck;

    @FXML
    private TextArea txtFine;

    @FXML
    private Label lblErrori;

    @FXML
    private Label lblTempo;

    @FXML
    private Button btnClear;

    @FXML
    void doCheck(ActionEvent event) {

    }

    @FXML
    void doClear(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert lblChoose != null : "fx:id=\"lblChoose\" was not injected: check your FXML file 'Scene.fxml'.";
        assert dropLanguage != null : "fx:id=\"dropLanguage\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInizio != null : "fx:id=\"txtInizio\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtFine != null : "fx:id=\"txtFine\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblErrori != null : "fx:id=\"lblErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTempo != null : "fx:id=\"lblTempo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model m) {
    	this.model=m;
    }
}



