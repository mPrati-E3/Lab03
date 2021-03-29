package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class Model {
	
	Set<String> Dictionary = new LinkedHashSet<String>();
	
	private boolean LoadFromFile (String Lingua) {
		
		String FileName;
		final String PATH = "";
		
		if (Lingua.equals("Italiano")) {
			FileName=PATH+"Italian.txt";
		} else {
			FileName=PATH+"English.txt";
		}
		
		try {
			
			FileReader FR = new FileReader(FileName);
			BufferedReader BR = new BufferedReader(FR);
			String WORD = BR.readLine();
			
			while (WORD !=null) {
				
				System.out.println(WORD);
				this.Dictionary.add(WORD);
				
				WORD = BR.readLine();
			}
			
			BR.close();
			
		} catch (IOException e) {
			return false;	
		}
		return true;
	}

	public String ControllaTesto(String text, String Lingua) {
		
		String S = "";
		
		if (this.LoadFromFile(Lingua)) {
			System.out.println(this.Dictionary);
			
			return S;	
		} else {
			if (Lingua.equals("Italiano")) {
				return "Il caricamento del file non è andato a buon fine! \n";
			} else {
				return "Failed to read file! \n";
			}
		}
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
