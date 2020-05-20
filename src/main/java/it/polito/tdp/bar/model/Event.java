package it.polito.tdp.bar.model;

import java.time.LocalTime;

public class Event implements Comparable<Event>{
	
	public enum EventType{
		
		NEW_GROUP,END_DINNER;
	}

	private LocalTime time;
	private EventType type;
	private int numPersone;
	private Table tavolo;
		
	public Event(LocalTime time, EventType type) {
		super();
		this.time = time;
		this.type = type;
		this.numPersone =(int) (Math.random() * 10) + 1;
	}
	
	public LocalTime getTime() {
		return time;
	}
	
	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	public EventType getType() {
		return type;
	}
	
	public void setType(EventType type) {
		this.type = type;
	}
	
	public int getNumPersone() {
		return numPersone;
	}
	
	public void setNumPersone(int numPersone) {
		
		
		this.numPersone = numPersone;
	}

	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return this.time.compareTo(o.time);
	}
	
	public String toString() {
		
		return time + " " + type + " " + numPersone;
	}

	public void assegnaTavolo(Table t) {
		this.tavolo = t;
		
	}
	
	public Table getTable() {
		
		return tavolo;
	}
	
	
	
	
}
