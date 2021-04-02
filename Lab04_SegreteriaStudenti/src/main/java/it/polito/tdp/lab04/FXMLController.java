package it.polito.tdp.lab04;

import java.net.URL;
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
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> btnCombo;

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

    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	try{
    		String codice = model.getCodiceByNomecorso(btnCombo.getValue().toString());
    	
    		try {
    			for(Studente s : this.model.getStudentiIscrittiAlCorso(codice)) {
    				this.txtResult.appendText(s.getMatricola()+"\t\t\t");
    				this.txtResult.appendText(s.getCognome()+"  ");
    				this.txtResult.appendText(s.getNome()+"\t\t\t\t");
    				this.txtResult.appendText(s.getCDS());
    				this.txtResult.appendText("\n");
    			}
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
    	
    	String string = txtMatricola.getText();
    	Integer matricola;
    	try {
    		matricola = Integer.parseInt(string);
    	}catch(NullPointerException npe) {
    		txtResult.setText("Inserire matricola!");
    		return;
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
		
		this.btnCombo.getItems().add(null);
		for(Corso c : this.model.getTuttiICorsi()) {
			this.btnCombo.getItems().add(c.getNome());
		}
	}
}
