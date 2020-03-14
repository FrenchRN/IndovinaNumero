package it.polito.tdp.indovinanumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {
	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco;
	private Set<Integer> tentativi;
	
	public Model() {
		this.tentativiFatti=0;
		this.inGioco=false;
	}
	
	public void nuovaPartita() {
		this.segreto = (int)(Math.random() * NMAX) + 1;
    	this.tentativiFatti = 0;
    	this.inGioco = true;
    	this.tentativi=new HashSet<Integer>();
	}
	
	public int tentativo(int tentativo) {
		if(!inGioco) {
			throw new IllegalStateException("La partita è già terminata");
		}
		if(!tentativoValido(tentativo)) {
			throw new InvalidParameterException("Devi inserire un numero che non hai ancora utilizzato tra 1 e "+NMAX+"\n");
		}
		
		//il tentativo è valido
		this.tentativiFatti ++;
		
		this.tentativi.add(tentativo);
		
		if(this.tentativiFatti==TMAX) {
			this.inGioco=false;
		}
		
		if(tentativo==this.segreto) {
			this.inGioco=false;
			return 0;
		}
		
		if(tentativo<this.segreto) {
			return -1;
		} else return 1;
		
		
	}
	private boolean tentativoValido(int tentativo) {
		if(tentativo<1 || tentativo>NMAX) return false;
		else {
			if(this.tentativi.contains(tentativo)) {
				return false;
			}
			else return true;
		}
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public int getTMAX() {
		return TMAX;
	}

	
	
}
