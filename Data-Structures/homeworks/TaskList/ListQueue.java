/*
 * @author Adib Osmany
 * @pledge I pledge my honor that I have abided by the Stevens Honor System.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListQueue<E> {

	private Node<E> front;

	private int size;

	private Iter it;


	private int LOW_PRIORITY = Integer.MAX_VALUE;


	/*
	 * Node Class
	 */
	public class Node<E>{
		private E data;
		private Node<E> next;
		private int priority;

		/*
		 * Node constructor
		 */
		public Node(E dataItem) {
			data=dataItem;
			next=null;
			priority=LOW_PRIORITY; 
		}

		/*
		 * Node constructor
		 */
		public Node(E dataItem, int priority) {
			data=dataItem;
			next=null;
			this.priority= priority; 
		}

		/*
		 * Node constructor
		 */
		public Node(E dataItem, Node<E> next, int priority) {
			data=dataItem;
			this.priority= priority; 
			this.next=next;
		}


		/*
		 * The getData method returns the data stored in the node. 
		 */
		public E getData() {
			return data;
		}

		/*
		 * The getNext method returns the next node.
		 */
		public Node<E> getNext(){
			return next;
		}
	}

	private class Iter implements Iterator<E>{
		private Node<E> next=front;

		/*
		 * The hadNext function checks to see if a node points to another node
		 */
		public boolean hasNext() {
			if (next.getNext()!=null) {
				return true;
			}
			else {
				return false;
			}
		}

		/*
		 * The next function returns the data of the next node attribute, and sets the next node attribute to the next node.
		 */
		public E next() {
			Node<E> pos=next;
			if(hasNext()) {
				next=next.getNext();
				return pos.getData();
			}
			else {
				throw new NoSuchElementException();
			}

		}


		/*
		 * The remove function throws an UnsupportedOperationException error
		 */
		public void remove() {
			throw new UnsupportedOperationException();
		}


	}


	/*
	 * ListQueue constructor
	 */
	public ListQueue() {
		front=null;

		size=0;
	}

	/*
	 * ListQueue constructor
	 */
	public ListQueue(Node<E> first) {
		front=first;

		size=1;
	}

	/*
	 * The getFront function returns the node at the beginning of the linked list.
	 */
	public Node<E> getFront() {
		return front;
	}

	/*
	 * The setFront function sets the node for the beginning of the linked list.
	 */
	public void setFront(Node<E> front) {
		this.front = front;
	}

	/*
	 * The getSize function returns the size of the linked list.
	 */
	public int getSize() {
		return size;
	}

	/*
	 * The peek function returns the data of the front node of the linked list. 
	 */
	public E peek() {
		return front.getData();
	}

	/*
	 * The offer function places a node in the linked list in any order. 
	 */
	public boolean offer (E item, int priority) {
		if(item.equals(null)) {
			throw new NullPointerException();
		}
		else {
			Node<E> hold=new Node<E>(item,priority);

			if(size==0) {
				this.setFront(hold);
				size++;
			}
			else if(priority==LOW_PRIORITY ) {
				Node<E> cur=front;
				for(int i=1; i<this.getSize(); i++) {
					cur=cur.getNext();
				}
				cur.next=hold;
				size++;

			}
			else {
				Node<E> cur=front;
				if(hold.priority<cur.priority) {
					this.setFront(hold);
					front.next=cur;
					size++;
				}
				else {
					for(int i=1;i<getSize();i++) {
						if(hold.priority<cur.getNext().priority) {
							hold.next=cur.getNext();
							cur.next=hold;
							size++;
							break;
						}
						else if(i==size-1) {
							cur.next=hold;
							size++;
							break;
						}
						cur=cur.getNext();
					}
				}
			}
			return true;
		}
	}



	/*
	 * The addRear function creates a node using the item given and adds that node to the end of the linked list.
	 */
	public boolean addRear (E item) {
		if(item.equals(null)) {
			throw new NullPointerException();
		}
		else {
			return this.offer(item, LOW_PRIORITY);
			
		}
	}

	/*
	 * The poll function returns the data of the front of the linked list, and also removes the front of the linked list from the list itself. 
	 */
	public E poll() {
		if(front.equals(null)) {
			throw new NullPointerException();
		}
		else {
			Node<E> keep=front;
			remove(front);
			return keep.getData();
		}
	}


	/*
	 * The remove function removes a specific node from the linked list. 
	 */
	public boolean remove (Node<E> toBeRemoved) {
		boolean elemHere=false;
		Node<E> cur=front;
		if((cur.getData().equals(toBeRemoved.getData())) && (cur.priority==toBeRemoved.priority)){
			if(size==1) {
				this.setFront(null);
				size--;
			}
			else {
				this.setFront(cur.getNext());
				size--;
			}
			elemHere=true;
		}
		else {
			for (int i=1;i<getSize();i++) {
				if((cur.getNext().getData().equals(toBeRemoved.getData()) && (cur.getNext().priority==toBeRemoved.priority))){
					if(i==size-1) {
						cur.next=null;
					}
					else {
						cur.next=cur.getNext().getNext();
					}
					size--;
					elemHere=true;
					break;

				}
				cur=cur.getNext();
			}
		}
		return elemHere;
	}


	/*
	 * The iterator function uses the Iter class to iterate through all the nodes in the linked list. 
	 */
	public Iterator<E> iterator(){
		it=new Iter();
		return it;

	}
	
	
	/*
	 * The getRear function returns the last node of the linked list.
	 */
	public Node<E> getRear() {
		Node<E> cur=front;
		for(int i=1; i<this.getSize(); i++) {
			cur=cur.getNext();
		}
		return cur;
	}
	
	/*
	 * The getCer function returns a node based on its location num in the linked list. 
	 */
	public Node<E> getCer(int num) {
		Node<E> cur=front;
		for(int i=1; i<num; i++) {
			cur=cur.getNext();
		}
		return cur;
	}

}