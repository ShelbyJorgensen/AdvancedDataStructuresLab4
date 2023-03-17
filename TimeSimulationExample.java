package LabAssignment4;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class TimeSimulationExample {
    
	public static Heap<Customer> customerQueue = new Heap<Customer>();
	public static ArrayList<Customer> withTeller = new ArrayList<Customer>();
	public static Simulation sim = new Simulation();
    static int counter = 0;
    static int customerCount = 0;
    static int tellers = 0;
    static double totalWaitTime;
    
    public static void main(String[] args) {
        Timer timer = new Timer(100, new TimerListener());
        timer.start();
        while(true){
        }
    }
    private static class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Set up the start of the simulation
            tellers = sim.getTellers(counter, tellers);
            counter++;
            methodCall();
            System.out.println("Tick "+counter);
            System.out.println("**********************************************");
        }
        private void methodCall() {
        	
        	// Add customers to the queue when randomly generated
        	customerQueue = sim.addCustomers(customerQueue);
        	
        	// If a customer is with a teller, check if they have been processed and can be removed
        	withTeller = sim.updateProcessAndWait(withTeller);
        	
        	// Find the index location of customers with finished processed times
        	int doneAtTeller = sim.findFinishedCusotmer(withTeller);
        	
        	// Remove all finished customers from tellers, restors number of tellers, and update customer stats
        	while(doneAtTeller != -1) {
        		Customer temp = sim.processFinishedCustomer(withTeller, customerCount, doneAtTeller);
        		customerCount++;
        		totalWaitTime = sim.getCustomerStats(temp, totalWaitTime, customerCount);
        		tellers++;
        		// Once 200 customers are processed, exit the program
        		if(customerCount == 200) {
                	System.exit(0);
                }
        		// Check to ensure all customers remaining with tellers are processed
        		doneAtTeller = sim.findFinishedCusotmer(withTeller);
        	}
        	
        	// Pull from the top of the queue and pair customers with any available tellers
        	Iterator<Customer> itr = customerQueue.iterator();
        	while(itr.hasNext()) {
        		if(tellers != 0) {
            		withTeller.add(itr.next());
            		customerQueue.deleteMaximum();
            		tellers--;
        		} else {
        			break;
        		}
        	}
        	
        	// Update the wait time of all customers in the queue
        	customerQueue = sim.updateWaitTime(customerQueue);
        }
    }
}
