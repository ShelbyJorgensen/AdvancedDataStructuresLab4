package LabAssignment4;

import java.util.Random;

public class Customer implements Comparable<Customer> {
	
	/*
	 * Customer class, holds data about each customer that visits the bank
	 * Contains a default constructor, as well as getters and setters for all data fields
	 */
	
	private int priority;
	private int processTime;
	private int waitTime;
	
	public Customer() {
		Random rn = new Random();
		// Generates a random priority from 1-10
		this.priority = rn.nextInt(10) + 1;
		// Generate a random process time from 5-20
		this.processTime = rn.nextInt(16) + 5;
		waitTime = 0;
	}
	
	protected void setPriority(int newPriority) {
		this.priority = newPriority;
	}
	
	protected void setProcessTime(int newProcessTime) {
		this.processTime = newProcessTime;
	}
	
	protected void setWaitTime(int newWaitingTime) {
		this.waitTime = newWaitingTime;
	}
	
	public int getPriority() {
		return this.priority;
	}
	
	public int getProcessTime() {
		return this.processTime;
	}
	
	public int getWaitTime() {
		return this.waitTime;
	}
	
	@Override
	public int compareTo(Customer o) {
		return this.priority - o.priority;
	}
}
