package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import it.polito.tdp.spellchecker.model.Model;
import it.polito.tdp.spellchecker.model.RichWord;
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
    
    //converto il testo iniziale in una lista di stringhe eliminando i simboli strani
    //e dividendo le parole per spazio utilizzando StringTokenizer
    private List<String> ConvertiTesto (String text){
    	
    	List<String> Convertito = new ArrayList<String>();
    	
    	text = text.toLowerCase();
    	text.replaceAll("\n", " ");
    	text.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_'()\\[\\]\"]", " ");
    	
    	StringTokenizer ST = new StringTokenizer(text, " ");  	
    	while (ST.hasMoreTokens()) {
    		Convertito.add(ST.nextToken());
    	}
    	
    	return Convertito;
    }

    @FXML
    void doCheck(ActionEvent event) {
    	
    	//controllo che la textArea iniziale non sia vuota
    	if (txtInizio.getText().equals("")) {
    		txtFine.appendText("Il testo iniziale non può essere vuoto! \n");
    		return;
    	}
    	
    	txtFine.clear();
    	int Errori = 0;
    	
    	Long T = System.nanoTime();
    	List<RichWord> S = model.ControllaTesto(ConvertiTesto(txtInizio.getText()),dropLanguage.getValue());
    	T=System.nanoTime() - T;
    	
    	for (int i=0; i<S.size(); i++) {
    		if (!S.get(i).isCorretta()) {
        		txtFine.appendText("> "+S.get(i).getParola()+"\n");
    			Errori++;
    		} else {
    			txtFine.appendText(S.get(i).getParola()+"\n");
    		}
    	}
    	
    	if (dropLanguage.getValue().equals("Italiano")) {
    		lblTempo.setText("Tempo di controllo ortografico: "+T/10e6+" ms");
    		lblErrori.setText("Errori: "+Errori);
    	} else {
    		lblTempo.setText("Check time: "+T/10e6+" ms");
    		lblErrori.setText("Errors: "+Errori);
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
    		lblTempo.setText("Tempo pulizia prima text area: "+T1/10e6+" ms --- Tempo pulizia seconda text area: "+T2/10e6+" ms");
    	} else {
    		lblTempo.setText("Clean time first text area: "+T1/10e6+" ms --- Clean time second text area: "+T2/10e6+" ms");
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
        dropLanguage.setValue("English");
    }
    
    public void setModel(Model m) {
    	this.model=m;
    }
}



