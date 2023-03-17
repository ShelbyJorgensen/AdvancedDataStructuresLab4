package LabAssignment4;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Simulation {
	
	private static final DecimalFormat df = new DecimalFormat("0.000");
	
	/*
	 * Method to initialize the number of tellers currently working in the bank
	 */
	public int getTellers(int counter, int tellers) {
		if(counter == 0) {
			System.out.println("Welcome to the Bank Simulation!");
			System.out.print("Please enter a number of Tellers: ");
			try (Scanner input = new Scanner(System.in)) {
				tellers = input.nextInt();
				return tellers;
			}
		}
		return tellers;
	}
	
	/*
	 * Method to randomly generate, then add customers to the queue
	 * Those already in the queue have their priority increased with each new customer
	 */
	public Heap<Customer> addCustomers(Heap<Customer> customerQueue) {
		// Randomly create customers and add them to the queue; update the priority of those already in the queue
    	Random rn = new Random();
    	int makeCustomer = rn.nextInt(10) + 1;
    	if(makeCustomer <= 5) {
    		Customer newCustomer = new Customer();
    		System.out.println("New Customer has arrived with the priority " + newCustomer.getPriority()
								+ "\nand transaction time: " + newCustomer.getProcessTime());
    		Iterator<Customer> itr = customerQueue.iterator();
    		while(itr.hasNext()) {
    			Customer temp = itr.next();
    			temp.setPriority(temp.getPriority() + 1);
    		}
    		customerQueue.insert(newCustomer);
    	}
		return customerQueue;
	}
	
	/*
	 * Method to update the wait time of customers still in the queue waiting to meet with tellers
	 */
	public Heap<Customer> updateWaitTime(Heap<Customer> customerQueue) {
		Iterator<Customer> itr = customerQueue.iterator();
    	while(itr.hasNext()) {
    		Customer temp = itr.next();
    		int currentWait = temp.getWaitTime();
    		temp.setWaitTime(currentWait + 1);
    	}
    	return customerQueue;
	}
	/*
	 * Method to update the process time and wait time of each customer currently with a teller
	 */
	public ArrayList<Customer> updateProcessAndWait(ArrayList<Customer> withTeller) {
		for(int i = 0; i < withTeller.size(); i++) {
    		Customer temp = withTeller.get(i);
    		int inProcess = temp.getProcessTime();
    		temp.setProcessTime(inProcess - 1);
    		int waiting = temp.getWaitTime();
    		temp.setWaitTime(waiting + 1);
		}
		return withTeller;
	}
	
	/*
	 * Method to find and return the index location of any customer that is finished being processed
	 */
	public int findFinishedCusotmer(ArrayList<Customer> withTeller) {
		for(int i = 0; i < withTeller.size(); i++) {
			Customer temp = withTeller.get(i);
    		if(temp.getProcessTime() == 0) {
    			return i;
    		}
		}
		return -1;
	}
	
	/*
	 * Remove all finished customers from withTeller list, and update the processed customer count
	 */
	public Customer processFinishedCustomer(ArrayList<Customer> withTeller, int customerCount, int doneAtTeller) {
        Customer temp = withTeller.get(doneAtTeller);
        withTeller.remove(doneAtTeller);
        System.out.println("Customer Leaves, customer waited for " + temp.getWaitTime() +" units of time.");
		return temp;
	}
	
	/*
	 * Method to get wait time stats from processed customer
	 */
	public double getCustomerStats(Customer temp, double totalWaitTime, int customerCount) {
		totalWaitTime += temp.getWaitTime();
        System.out.println("Customer's serviced: " + customerCount + "...Average waiting time: " + df.format(totalWaitTime / customerCount));
        return totalWaitTime;
	}
}
