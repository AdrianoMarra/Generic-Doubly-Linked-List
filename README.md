# Generic-Doubly-Linked-List
This is a simple "generic doubly linked list" implementation in Java. (For academic purposes)

This code is just a sample of how to create a generic doubly linked list from scratch in Java, 
I used a class "Node" to wrap the nodes and their next and previous nodes pointers. In the real world you could just 
use the LinkedList class from the Java API which of course is much more complete, however, in the academic world it is 
frequently requested for students to create data structures from scratch without using the Java API, therefore 
(as the description says) this code is just for academic purposes... 
You also could easily use the GenericDoublyLinkedList class to create Stacks and Queuees, in case you want to create 
a stack you can use only the add(T item) and deleteLast() methods, and to create a queuee the add(T item) and deleteFirst() methods.

Ps: A class Employee and a main() method was created just to test the methods of the doubly linked list class, however, 
as GenericDoublyLinkedList is a generic class you can use it to store any kind of object you like.
