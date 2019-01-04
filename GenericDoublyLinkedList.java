import java.util.Objects;

public class GenericDoublyLinkedList<T> {

	Node header;

	GenericDoublyLinkedList() {
		header = new Node(null, null, null);
	}

	/**
	 * Add before the first item in the list
	 */
	public Node addFirst(T item) {
		Node n = new Node(header.next, header, item);
		if (header.next != null) {
			header.next.previous = n;
		}
		header.next = n;
		return n;
	}

	/**
	 * Add after the last item in the list
	 */
	public Node add(T item) {
		Node end = getNode(size() - 1);
		Node next = new Node(null, end, item);
		end.next = next;
		return next;
	}

	/**
	 * Add anywhere in the list, after the index of position index
	 */
	public Node add(T item, int index) {
		if (index >= size() || index < 0) {
			throw new IndexOutOfBoundsException("pos = " + index + " does not exist");
		}

		Node nodeIns = null;

		if (item != null) {
			Node node = header;

			if (node.next != null) {
				for (int i = 0; i <= index; i++) {
					node = node.next;
				}
			}
			
			nodeIns = new Node(node.next, node, item);
			
			if (node.next != null) {
				node.next.previous = nodeIns;
			}
			node.next = nodeIns;
		}
		return nodeIns;
	}

	/**
	 * Delete the first item of the list
	 */
	public Node deleteFirst() {
		Node temp = getNode(0);
		if (temp != null && temp.next != null) {
			temp.next.previous = header;
			header.next = temp.next;
		} else {
			header.next = null;
		}
		
		return temp;
	}

	/**
	 * Delete the last item of the list
	 */
	public Node deleteLast() {
		Node temp = getNode(size() - 1);
		Node newLast = getNode(size() - 2);
		newLast.next = null;
		return temp;
	}

	/**
	 * Delete object at specified index
	 */
	public boolean delete(int index) {
		if (index >= size() || index < 0) {
			throw new IndexOutOfBoundsException("pos = " + index + " does not exist");
		}
		Node toBeRemoved = getNode(index);
		if (toBeRemoved == null)
			return false;
		Node previous = toBeRemoved.previous;
		Node next = toBeRemoved.next;

		previous.next = next;
		if (next != null) {
			next.previous = toBeRemoved.previous;
		}
		toBeRemoved = null;
		return true;
	}

	/**
	 * Delete by specifying object -- removes first occurrence of a string
	 */
	public boolean delete(T item) {
		int index = find(item);
		if (index == -1)
			return false;
		return delete(index);

	}

	/**
	 * Get the specific node we are looking for at the position index
	 */
	private Node getNode(int index) {
		if (index > size())
			throw new IndexOutOfBoundsException();
		Node node = header;
		for (int i = 0; i <= index; ++i) {
			node = node.next;
		}

		return node;
	}

	/**
	 * Get the value of the node we are looking for at the position index
	 */
	public String get(int index) {
		Node node = getNode(index);
		return (node != null) ? node.value.toString() : null;
	}

	/**
	 * Returns the index of the String s, if found; -1 otherwise
	 */
	public int find(T item) {
		if (item == null)
			return -1;
		Node currentNode = header;
		int i = -1;
		while (currentNode.next != null) {
			++i;
			currentNode = currentNode.next;
			if (item.equals(currentNode.value))
				return i;
		}
		return -1;
	}

	/**
	 * Returns the size of the list
	 */
	public int size() {
		int count = 0;
		Node node = header.next;
		while (node != null) {
			++count;
			node = node.next;
		}
		return count;
	}

	/**
	 * Returns true if the list is empty, false otherwise
	 */
	public boolean isEmpty() {
		boolean isEmpty = (size() > 0) ? false : true;
		return isEmpty;
	}

	/**
	 * Prints out the list in an array format
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		Node next = header.next;
		while (next != null) {
			sb.append(next.value + ", ");
			next = next.next;
		}
		String ret = sb.toString();
		if (ret.length() > 1) {
			ret = ret.substring(0, ret.length() - 2);
		}
		return (ret + "]");
	}

	/**
	 * The class Node wraps the nodes for the doubly linked list. Its constructor
	 * receives pointers to next and previous nodes, as well as, an object to which
	 * will be the node's value.
	 */

	class Node {
		T value;
		Node next;
		Node previous;

		Node() {
		}

		Node(Node next, Node previous, T value) {
			this.next = next;
			this.previous = previous;
			this.value = value;
		}

		public String toString() {
			return value + "";
		}
	}

	/**
	 * The class Employee is just to show that we can store any kind of object
	 * inside the list, it could be objects of any other thing like String,
	 * Integer...
	 */
	public static class Employee {

		String name;
		Integer age;
		String position;

		Employee(String name, Integer age, String position) {
			this.name = name;
			this.age = age;
			this.position = position;
		}

		@Override
		public String toString() {
			return this.name;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Employee other = (Employee) obj;
			return Objects.equals(age, other.age) && Objects.equals(name, other.name)
					&& Objects.equals(position, other.position);
		}
	}

	public static void main(String[] args) {

		GenericDoublyLinkedList<Employee> list = new GenericDoublyLinkedList<Employee>();
		list.add(new Employee("Eployee_01", 28, "software developer"));
		list.add(new Employee("Eployee_02", 25, "designer"));
		list.add(new Employee("Eployee_03", 25, "product manager"));
		list.add(new Employee("Eployee_04", 25, "designer"), 0);
		list.addFirst(new Employee("Eployee_05", 26, "software developer"));

		System.out.println(list);
		list.deleteFirst();
		System.out.println(list);
		list.deleteLast();
		System.out.println(list);
		
		Employee employee = new Employee("Eployee_02", 25, "designer");
		list.delete(employee);
		System.out.println(list);
		
		list.delete(1);
		System.out.println(list);
		
		list.deleteFirst();
		System.out.println(list);
	}
}
