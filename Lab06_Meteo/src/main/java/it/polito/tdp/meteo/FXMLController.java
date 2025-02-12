/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.meteo;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.meteo.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxMese"
    private ChoiceBox<Integer> boxMese; // Value injected by FXMLLoader

    @FXML // fx:id="btnUmidita"
    private Button btnUmidita; // Value injected by FXMLLoader

    @FXML // fx:id="btnCalcola"
    private Button btnCalcola; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaSequenza(ActionEvent event) {
    	//richiama dal model
    	txtResult.clear();
    	Integer mese = boxMese.getValue();
    	List<String> risultato = model.trovaSequenza(mese);
    	
    	//stampa sequenza
    }

    @FXML
    void doCalcolaUmidita(ActionEvent event) {
    	txtResult.clear();
    	Integer mese = boxMese.getValue();
    	txtResult.appendText("L'umidita' media di Milano il mese "+mese+" è stata del "+model.getUmiditaMedia(mese,"Milano")+"% \n");
    	txtResult.appendText("L'umidita' media di Genova il mese "+mese+" è stata del "+model.getUmiditaMedia(mese,"Genova")+"% \n");
    	txtResult.appendText("L'umidita' media di Torino il mese "+mese+" è stata del "+model.getUmiditaMedia(mese,"Torino")+"% \n");


    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	boxMese.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
        assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnUmidita != null : "fx:id=\"btnUmidita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }


	public void setModel(Model model) {
		this.model = model;
		
	}
    
    
    
    
}

