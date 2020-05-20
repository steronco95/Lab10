package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Model {
	
	private Table tav10 = new Table(10,2);
	private Table tav8 = new Table(8,4);
	private Table tav6 = new Table(6,4);
	private Table tav4 = new Table(4,5);
	private int numClienti;
	private int numInsoddisfatti;
	private int numSoddifatti;
	
	public void creaSimulazione() {
		
		List<Table> tavoli = new ArrayList<>();
		
		tavoli.add(tav10);
		tavoli.add(tav6);
		tavoli.add(tav4);
		tavoli.add(tav8);
		
		Simulator s = new Simulator();
		
		s.setSimulazione(tavoli);
		s.run();
		
		this.numClienti = s.getClienti();
		this.numInsoddisfatti = s.getInsoddisfatti();
		this.numSoddifatti = s.getSoddisfatti();
	}
	
	public int getClienti() {
		return this.numClienti;
	}
	
	public int getSoddisfatti() {
		return this.numSoddifatti;
	}
	public int getInsoddisfatti() {
		return this.numInsoddisfatti;
	}
	

}
