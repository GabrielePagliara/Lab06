package it.polito.tdp.meteo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import it.polito.tdp.meteo.DAO.MeteoDAO;

public class Model {

	private MeteoDAO meteoDAO;
	private Citta cittaMILANO;
	private Citta cittaTORINO;
	private Citta cittaGENOVA;

	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;

	private int costoTOT;

	public Model() {
		meteoDAO = new MeteoDAO();

	}

	// of course you can change the String output with what you think works best
	public float getUmiditaMedia(int mese, String localita) {
		return meteoDAO.getAvgRilevamentiLocalitaMese(mese, localita);
	}

	// of course you can change the String output with what you think works best
	public List<String> trovaSequenza(int mese) {

		List<String> risultato = new ArrayList<>();

		cittaMILANO = new Citta("Milano", meteoDAO.getAllRilevamentiLocalitaMese(mese, "Milano"));
		cittaTORINO = new Citta("Torino", meteoDAO.getAllRilevamentiLocalitaMese(mese, "Torino"));
		cittaGENOVA = new Citta("Genova", meteoDAO.getAllRilevamentiLocalitaMese(mese, "Genova"));

		Vector<Citta> cittaVector = new Vector<Citta>();
		cittaVector.add(cittaMILANO);
		cittaVector.add(cittaTORINO);
		cittaVector.add(cittaGENOVA);

		List<String> p = new ArrayList<>();
		sequenza(p, cittaVector, 0, risultato);
		return risultato;
	}

	// metodo ricorsivo
	private void sequenza(List<String> parziale, Vector<Citta> vettoreCitta, int livello, List<String> risultato) {

		if (parziale.size() == 3) {
			
			// caso terminale
			System.out.println(parziale);
			System.out.println(costoTOT);

			// risultato.add(null);

		} else {
			for (int i = 0; i < vettoreCitta.size(); i++) {

				// creo tentativo dove inserisco l'oggetto Città
				Citta tentativo = vettoreCitta.get(i);
				//aumento il contatore di apparenza di quella città 
				tentativo.increaseCounter();
				
				//System.out.println(tentativo.getCounter());

				//se il contatore è minore di 3 entra 
				if (tentativo.getCounter() <= NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN) {

					costoTOT += tentativo.getRilevamenti().get(livello).getUmidita();

					List<String> nuovaParziale = parziale;
					nuovaParziale.add(tentativo.getNome());

					// permette quando si fa la ricorsione di richiamare solo la stessa città
					// [milano, milano, milano]
					Vector<Citta> nuovoVector = new Vector<Citta>();
					nuovoVector.add(tentativo);
					 
					//RICORSIONE
					sequenza(nuovaParziale, nuovoVector, livello + 1, risultato);

					// backtrackig
					
					//ripristinare la lista
					nuovaParziale.clear();
					//nuovaParziale = nuovaParziale.subList(0, nuovaParziale.size()-3);
					//ripristinare il vector 
					//vettoreCitta.remove(tentativo);
					
					//ripristinare il counter
					System.out.print("ok \n");
					tentativo.setCounter(0);
															
					costoTOT = 0;

				} else {
					vettoreCitta.add(cittaMILANO);
					vettoreCitta.add(cittaTORINO);
					vettoreCitta.add(cittaGENOVA);
					
					

				
				
				
				
				
				//if ((tentativo.getCounter() > NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN)
					//	&&(tentativo.getCounter() < NUMERO_GIORNI_CITTA_MAX)) {
			
					
				}

			}

		}

	}
}
