package it.polito.tdp.bar.model;

public class Table implements Comparable <Table> {

	private int grandezza;
	private int quantita;
	
	public Table(int i, int j) {

		this.grandezza = i;
		this.quantita = j;
			
	}

	public int getGrandezza() {
		return grandezza;
	}

	public void setGrandezza(int grandezza) {
		this.grandezza = grandezza;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public void decrementaDisponibili() {
		
		this.quantita--;
		
	}

	public void aumentaDisponibili() {
		this.quantita++;
		
	}

	@Override
	public int compareTo(Table o) {
		
		return this.grandezza - o.grandezza;
	}
	
	

}
