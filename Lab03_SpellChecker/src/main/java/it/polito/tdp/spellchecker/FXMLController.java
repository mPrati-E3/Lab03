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
    private ChoiceBox<String> dropLanguage;

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
    	
    	txtFine.clear();
    	
    	Long T = System.nanoTime();
    	String S = model.ControllaTesto(txtInizio.getText(),dropLanguage.getValue());
    	T=System.nanoTime() - T;
    	
    	txtFine.appendText(S);
    	if (dropLanguage.getValue().equals("Italiano")) {
    		lblTempo.setText("Tempo di controllo ortografico: "+T);
    	} else {
    		lblTempo.setText("Check time: "+T);
    	}

    }

    @FXML
    void doClear(ActionEvent event) {
    	
    	Long T1 = System.nanoTime();
    	txtInizio.clear();
    	T1=System.nanoTime() - T1;
    	
    	Long T2=System.nanoTime();
    	txtFine.clear();
    	T2=System.nanoTime() - T2;
    	
    	if (dropLanguage.getValue().equals("Italiano")) {
    		lblTempo.setText("Tempo pulizia prima text area: "+T1+" --- Tempo pulizia seconda text area: "+T2);
    	} else {
    		lblTempo.setText("Clean time first text area: "+T1+" --- Clean time second text area: "+T2);
    	}
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

        dropLanguage.getItems().add("English");
        dropLanguage.getItems().add("Italiano");
    }
    
    public void setModel(Model m) {
    	this.model=m;
    }
}



