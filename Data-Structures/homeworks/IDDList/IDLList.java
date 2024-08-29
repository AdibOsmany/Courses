/*
 * @author Adib Osmany
 * @course CS 284 F
 * @pledge I pledge my honor that I have abided by the Stevens Honor System.
 */

import java.util.ArrayList;


public class IDLList<E> {
	
//	The Node Class helps to create a Node with any type of data. 
//	The Node class also points a node to two different nodes
	private class Node<E> {
		private E data;
		private Node<E> next;
		private Node<E> prev;

		//Node constructor
		public Node(E elem) {
		      this.data = elem;
		      this.next = null;
		      this.prev = null;
		   }
		//Node constructor
		public Node (E elem, Node<E> prev, Node<E> next) {
			this.data = elem;
		    this.next = next;
		    this.prev = prev;
		}
		
		//This function returns what data a Node is carrying in a string format
		public String toString() {
			
			return this.data.toString();
		}
		
	}

	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices; 
	private ArrayList<E> ind;
	
	//IDLList constructor
	public IDLList() {
		this.head=null;
		this.tail=null;
		this.size=0;
		indices=new ArrayList<Node<E>>();
		ind=new ArrayList<E>();
	}
	
	//The set function sets the head and tail of the double linked list.
	//It also sets the previous and next nodes of a new node, or a node 
	//that needs to be changed to match the current linked list. 
	private void set(int index) {
		this.size=indices.size();
		if (indices.isEmpty()) {
			this.head=null;
			this.tail=null;
		}
		else {
			if (index==0) {
				this.indices.get(index).prev= null;
				this.head=this.indices.get(index);
			}
			else {
				this.indices.get(index).prev=indices.get(index-1);
				this.indices.get(index-1).next=indices.get(index);
			}
			if (index==size-1) {
				this.indices.get(index).next=null;
				this.tail=this.indices.get(index);
			}
			else {
				this.indices.get(index).next=indices.get(index+1);
				this.indices.get(index+1).prev=indices.get(index);
			}
		}
		  
	}
		
	
	//The add operation adds a new node to the double linked list. 
	//The new node can be added anywhere within the list but can't 
	//be set anywhere outside the list, unless it becomes the 
	//new head or tail of the list. 
	
	public boolean add (int index, E elem)  {
		if ((index < 0 || index > size)) {
			  throw new IndexOutOfBoundsException(); 
		  }
		else {
			if (index==0) {
				return this.add(elem);
			}
			else if(index==this.size) {
				return this.append(elem);
			}
			else {
				Node<E> tis=new Node<E>(elem);
				indices.add(index, tis);
				ind.add(index,elem);
				this.set(index);
				return true;
			}
		}
	}
	
	
//	This add function adds a new node at the beginning 
//	of the double linked list, making it the new head of
//	the list. 
	
	
	public boolean add(E elem) {
		Node<E> hed=new Node<E>(elem);
		indices.add(0,hed);
		ind.add(0,elem);
		this.set(0);
		return true;
	}
	/*The append function takes a new node and places
	 * it at the end of the double linked list,
	 * making it the new tail of the list.
	 */
	
	public boolean append(E elem) {
		Node<E> tal=new Node<E>(elem);
		indices.add(tal);
		ind.add(elem);
		this.set(size);
		return true;
	}
	
	
	/*
	 * The get function gets the data from the double linked 
	 * list. The data that is received is determined by the
	 * given index.
	 */
	public E get (int index) {
		if ((index < 0 || index >= size)) {
			  throw new IndexOutOfBoundsException(); 
		  }
		else {
			return indices.get(index).data; 
		}
	}
	
	
	/*
	 * The getHead function gets the data from the first Node of
	 * the double linked list.
	 */
	public E getHead() {
		if (this.head == null) {
			return null;
		}
		return this.head.data;
	}
	
	/*
	 * The getLast function gets the data from the last Node
	 * of the double linked list. 
	 */
	public E getLast() {
		if (this.tail == null) {
			return null;
		}
		return this.tail.data;
	}
	
	/*
	 * The size function returns the size of the double linked list
	 */
	
	public int size() {
		return this.size;
	}
	
	/*
	 * The remove function removes the first node of the
	 * double linked list.
	 */
	public E remove() {
		if (ind.isEmpty()) {
			  throw new IllegalStateException(); 
		  }
		else {
			Node<E> r = indices.remove(0);
			ind.remove(0);
			this.set(0);
			return r.data;
		}
	}
	
	/*
	 * The removeLast function removes the last node
	 * of the double linked list.
	 */
	
	public E removeLast()  {
		if (ind.isEmpty()) {
			  throw new IllegalStateException(); 
		  }
		else {
			Node<E> rl = indices.remove(size-1);
			ind.remove(size-1);
			this.set(size-2);
			return rl.data;
		}
	}
	
	/*
	 * The removeAt function removes a node from the double
	 * linked list. The node that gets removed is determined by the 
	 * given index. 
	 */
	
	public E removeAt(int index) {
		if ((index < 0 || index > (size-1))) {
			  throw new IllegalStateException(); 
		  }
		else {
			if (index==0) {
				return this.remove();
			}
			else if(index==this.size-1) {
				return this.removeLast();
			}
			else {
				Node<E> ra = indices.remove(index);
				ind.remove(index);
				this.set(index);
				return ra.data;
			}
		}
	}
	
	/*
	 * This remove function checked to see if the 
	 * given element is in the double linked list.
	 * If it is, the entire node gets removed,
	 * if not, then nothing happens.
	 */
	
	public boolean remove(E elem) {
		if (ind.contains(elem)) {
			int b=ind.indexOf(elem);
			indices.remove(b);
			this.set(b);
		}
		return ind.remove(elem);
	}
	
	/*
	 * The toString function returns the indices list
	 * in string format. 
	 */

	public String toString() {
		return indices.toString();
	}
	
	public static void main(String[] arg) {
		IDLList<Integer> list=new IDLList<Integer>();
        list.append(0);
        list.add(1, 1); // Adding at the end
        list.add(1, 2); // Adding in the middle
		System.out.println(list);
		System.out.println(list.get(1));
//		for (int i=0; i<100;i++) {
//			t.add(i);
//		}
//		ArrayList<Integer> it=new ArrayList<Integer>();
//		it.add(5);
//		it.set(0, 6);
//		System.out.println(it.get(0));
		
	}

}
