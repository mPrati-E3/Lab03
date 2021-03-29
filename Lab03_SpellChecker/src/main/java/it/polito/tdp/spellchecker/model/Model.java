package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Model {
	
	private List<String> Dictionary = new ArrayList<String>();
	private String Linguaggio = "English";
	
	private boolean LoadFromFile (String Lingua) {
		
		//Se il mio dizionario non è vuoto e ho già precedentemente impostato la lingua corretta,
		//è inutile che ricarico tutto quanto dal file
		if (this.Dictionary!=null && this.Linguaggio.equals(Lingua)) {
			return true;
		}
		
		//resetto il dizionario ed il linguaggio
		this.Dictionary=new ArrayList<String>();
		this.Linguaggio=Lingua;
		
		//variabile per il path del file
		final String PATH = "src\\main\\resources\\";
		
		//scelgo il nome del file da caricare a seconda del linguaggio scelto
		String FileName;
		if (Lingua.equals("Italiano")) {
			FileName=PATH+"Italian.txt";
		} else {
			FileName=PATH+"English.txt";
		}
		
		//provo a caricare i dati dal file
		try {
			
			FileReader FR = new FileReader(FileName);
			BufferedReader BR = new BufferedReader(FR);
			String WORD = BR.readLine();
			
			while (WORD !=null) {
	
				this.Dictionary.add(WORD);
				
				WORD = BR.readLine();
			}
			
			BR.close();
			
			Collections.sort(this.Dictionary);
			
		} catch (IOException e) {
			return false;	
		}
		
		return true;
	}
	

	public List<RichWord> ControllaTesto(List<String> text, String Lingua) {
		
		List<RichWord> LR = new ArrayList<RichWord>();
		RichWord BOX = new RichWord();
		
		//se il file è caricato correttamente...
		if (this.LoadFromFile(Lingua)) {
			
			//per ogni parola (S) nel testo di input (text) faccio la ricerca dicotomica nel dizionario
			//in modo da capire se la parola è o meno corretta
			for (String S : text) {
				BOX = new RichWord(S);

				if (binarySearch(S.toLowerCase())) {
					BOX.setCorretta(true);
				} else {
					BOX.setCorretta(false);
				}
				
				LR.add(BOX);
			} 
	
		//...altrimenti riporto un errore
		} else {
			if (Lingua.equals("Italiano")) {
				BOX = new RichWord("Il caricamento del file non è andato a buon fine! \n");
			} else {
				BOX = new RichWord("Failed to read file! \n");
			}
			LR.add(BOX);
		}
		
		return LR;
	}

	//ricerca dicotomica
	private boolean binarySearch(String temp) {
		int inizio=0;
		int fine = this.Dictionary.size();
		
		while (inizio!=fine) {
			int medio = inizio+(fine-inizio)/2;
			if (temp.compareToIgnoreCase(this.Dictionary.get(medio))==0) {
				return true;
			} else if (temp.compareToIgnoreCase(this.Dictionary.get(medio))>0) {
				inizio=medio+1;
			} else {
				fine = medio;
			}
			
		}
		
		return false;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Dictionary == null) ? 0 : Dictionary.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Model other = (Model) obj;
		if (Dictionary == null) {
			if (other.Dictionary != null)
				return false;
		} else if (!Dictionary.equals(other.Dictionary))
			return false;
		return true;
	}



}
