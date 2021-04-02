package it.polito.tdp.lab04;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;
	private List<Corso> corsi;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> btnCombo;

    @FXML
    private Button btnCercaIscrittiCorso;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnCompletamento;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	txtResult.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	
    	String string = txtMatricola.getText();
    	Integer matricola;
    	
    	try {
    		matricola = Integer.parseInt(string);
    	}catch(NumberFormatException ne) {
    		txtResult.setText("Inserire solo valori numerici!");
    		return;
    	}
    	
    	Studente studente = model.getStudenteFromMatricola(matricola);
    	if(studente == null) {
    		txtResult.setText("Matricola non presente nel database!");
    		return;	
    	}
    	
    	List<Corso> corsi = model.getCorsiFromMatricola(matricola);
    		
   		StringBuilder sb = new StringBuilder();
		for(Corso c : corsi) {
			sb.append(String.format("%-11s ", c.getCodins()));
			sb.append(String.format("%-11d ", c.getNumeroCrediti()));
			sb.append(String.format("%-50s ", c.getNome()));
			sb.append(String.format("%-11d\n", c.getPeriodoDidattico()));
		}
		txtResult.appendText(sb.toString());
    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	txtResult.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	
    	try{
    		String codice = model.getCodiceByNomecorso(btnCombo.getValue().toString());
    	
    		try {
    			StringBuilder sb = new StringBuilder();
    			for(Studente s : this.model.getStudentiIscrittiAlCorso(codice)) {
    				sb.append(String.format("%-12d", s.getMatricola()));
    				sb.append(String.format("%-50s", s.getCognome()));
    				sb.append(String.format("%-50s", s.getNome()));
    				sb.append(String.format("%-50s\n", s.getCDS()));
    			}
    			txtResult.appendText(sb.toString());
    			
    		}catch(NullPointerException npe) {
    			this.txtResult.setText("Nessuna matricola iscritta al corso!");
    	   		return;
    		}
    	}catch(NullPointerException npe) {
			this.txtResult.setText("Nessun corso selezionato!");
	   		return;
     	}
    }

    @FXML
    void doCompletamentoAutomatico(ActionEvent event) {
    	txtResult.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	
    	String string = txtMatricola.getText();
    	Integer matricola;
    	
    	try {
    		matricola = Integer.parseInt(string);
    	}catch(NumberFormatException ne) {
    		txtResult.setText("Inserire solo valori numerici!");
    		return;
    	}
    	
    	try {
    		Studente s = model.getStudenteFromMatricola(matricola);
        	
    		txtCognome.setText(s.getCognome());
    		txtNome.setText(s.getNome());
    	}catch(Exception e ){
    		txtResult.setText("Matricola non presente nel database!");
    		return;	
    	}
    }

 

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtResult.clear();
    }

    @FXML
    void initialize() {
    	assert btnCombo != null : "fx:id=\"btnCombo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCompletamento != null : "fx:id=\"btnCompletamento\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		txtResult.setStyle("-fx-font-family: monospace");
		
		corsi = this.model.getTuttiICorsi();
		Collections.sort(corsi);
		btnCombo.getItems().addAll(corsi);
		
	}
}
