# AdvancedDataStructuresLab4
This project was created for my CS302 Advanced Data Structures class at Central Washington University. In this lab, we were asked to create a phone book with a working GUI. To complete this, I used the built in GUI builder that is part of the Eclipse IDE. Below is the provided outline for this project:

We have a system, such as a bank, where customers arrive and wait in line until a teller is available. We are
interested in how long on average a customer has to wait. Using this information, the bank officers can
determine how many tellers are needed to ensure reasonably smooth service. A simulation consists of
processing events. Initialize the number of tellers before the start of the simulation, this should be a user
input.

**Implementation**
• The simulation should be driven by a ticking timer (example in the lab directory on Canvas). A tick is a
quantum unit set by the simulation, say 1000 milliseconds.
• The vents are generated by the passage of time (ticks). Event parameters should be generated according
to a random variable.
• At any point, the next event that can occur is either the next customer arrives, with a given priority and a
given transaction time, or one of the customers at a teller leaves. Note, several events may occur during
the same tick. Namely, a customer arrives and one or more of the customers at a teller leave.
• If the event is an arrival, a customer is created with an initial priority and a need to spend a certain
number of ticks at the teller to complete his/her transaction (processTime).
o Both the initial priority and the length of transaction time are assigned randomly at the time of
customer generation.
o The priority should be generated in the range of 1-10.
o The transaction time should be generated in the range of 5-20.
o Add that customer to a heap priority queue based on the customers assinged priority.
• After each new arrival, increment the priorities of the already existent customers by a constant value, say
one.
• If the event is a departure, processing includes gathering statistics for the departing customer
(specifically, waiting time).
• Print out each tick and any events that occur during that tick. If a customer leaves, print the current
aevrage waiting time for customers.

You should use a Max-Heap as the underlying data structure for the customer queue. Heap.java is provided
for you on Canvas. Define a class Customer which has the fields processTime and priority. In order to
compare Customer objects, you need to override the compareTo method in the Customer class. Build a class
Simulation, which coordinates each arrival and each departure. The timer should be used to automatically
adnavce your simulation. A sample Timer example can be found on Canvas in the lab directory.
