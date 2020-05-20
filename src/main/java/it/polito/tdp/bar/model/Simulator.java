package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.bar.model.Event.EventType;

public class Simulator {

	private PriorityQueue <Event> coda = new PriorityQueue<>();
	private List<Table> tavoli = new ArrayList<>();
	private int numClienti;
	private int numInsoddisfatti;
	private int numSoddifatti;
	private final LocalTime oraApertura = LocalTime.of(6, 00);
	private final LocalTime oraChiusura = LocalTime.of(18, 00);
	private double tolleranza = Math.random();
	

	public void setSimulazione(List<Table> tavoli) {
		
		this.tavoli = tavoli;
		
	}

	public void run() {
		
		this.numClienti = this.numInsoddisfatti = this.numSoddifatti = 0;
		
		this.coda.clear();
		
		LocalTime oraArrivoCliente = this.oraApertura;
		
		do {
			Event e = new Event(oraArrivoCliente,EventType.NEW_GROUP);
			this.coda.add(e);
			oraArrivoCliente = oraArrivoCliente.plus(Duration.of((int) (Math.random() * 10), ChronoUnit.MINUTES));
		}while(oraArrivoCliente.isBefore(oraChiusura));
		
		
		while(!coda.isEmpty()) {
			Event e = coda.poll();
			System.out.println(e);
			processEvent(e);
		}
		
	}

	private void processEvent(Event e) {
		
		switch(e.getType()) {
		
		case NEW_GROUP:
			
			int numeroPersone = (int) e.getNumPersone();
			//ordino i tavoli dal piu piccolo al piu grande, cosi sono sicuro che venga assegnato il tavolo piu piccolo disponibile
			Collections.sort(tavoli);
			
			for(Table t : tavoli ) {
				if(t.getGrandezza() >= numeroPersone && t.getGrandezza()< 2*numeroPersone && t.getQuantita()>0) {
					t.decrementaDisponibili();
					this.numSoddifatti++;
					this.numClienti++;
					
					int num = (int) (Math.random() * 60) + 60;
					Duration permanenza = Duration.of(num, ChronoUnit.MINUTES);
					
					Event nuovo = new Event(e.getTime().plus(permanenza),EventType.END_DINNER);
					nuovo.assegnaTavolo(t);
					this.coda.add(nuovo);
					
				}else if(generaInsoddisfatto() <= tolleranza) {
					this.numSoddifatti++;
					this.numClienti ++;
				}else {
					this.numClienti++;
					this.numInsoddisfatti++;
				}
			}
			
		break;
		
		case END_DINNER:
			
			e.getTable().aumentaDisponibili();
			break;
		
		}
		
			
		
	}

	private double generaInsoddisfatto() {
		
		return Math.random();
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
