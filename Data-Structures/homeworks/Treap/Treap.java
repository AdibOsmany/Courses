
/*
 * @author Adib Osmany
 * @course CS 284 F
 * @pledge I pledge my honor that I have abided by the Stevens Honor System.
 */

import java.util.Random;
import java.util.Stack;

public class Treap<E> {


	/*
	 * 
	 * The Node class contains data type E, a priority number, and a pointer to a left and right node
	 * 
	 */
	private static class Node<E>{
		public E data; // key for the search
		public int priority; // random heap priority
		public Node <E > left;
		public Node <E > right;

		
		/*
		 * The Node constructor sets all the variables. 
		 */
		public Node (E data , int priority ) {
			if(data==null) {
				throw new IllegalArgumentException("Data cannot be null");
			}
			else {
			this.data=data;
			this.priority=priority;
			this.left=null;
			this.right=null;
			}
		}

/*
 * The rotateRight method rotates a part of a tree to the right. 
 */
		public Node <E> rotateRight(){
			if(this.left!=null) {
				Node<E> temp=this.left;
				if(temp.right!=null) {
					this.left=temp.right;
					temp.right=this;
					return temp;
				}
				else {
					this.left=null;
					temp.right=this;
					return temp;
				}
			}
			return this;


		}
		
		
		/*
		 * The rotateLeft method rotates a part of a tree to the left. 
		 */
		public Node <E> rotateLeft(){
			if(this.right!=null) {
				Node<E> temp=this.right;
				if(temp.left!=null) {
					this.right=temp.left;
					temp.left=this;
					return temp;
				}
				else {
					this.right=null;
					temp.left=this;
					return temp;
				}
			}
			return this;

		}

		/*
		 * The toString function returns a string representation of the node. 
		 */
		public String toString() {
			return "(key="+data+", priority="+priority+")";
		}


	}


	private Random priorityGenerator ;

	private Node <E > root ;

	private Stack<Node<E>> lis=null;

/*
 * The Treap constructor sets root to null, and generators random priority numbers.
 */
	public Treap () {
		root=null;
		priorityGenerator=new Random();

	}

	
	/*
	 * The Treap constructor sets root to null, and generators random priority numbers using seed.
	 */
	public Treap ( long seed ) {
		root=null;
		priorityGenerator=new Random(seed);

	}

/*
 * The addleaf function adds a node to the very end of the treap. This is a helper function to the add functions. 
 */
	private void addleaf(Node<E> k) {
		Node<E> x = root;
		boolean y=true;

		lis=new Stack<Node<E>>();
		String kd=k.data.toString();
		while (y) {
			lis.push(x);
			String xd=x.data.toString();
			if(kd.compareTo(xd)<0) {
				if(x.left==null) {
					x.left=k;
					y=false;
				}
				else {
					x=x.left;
				}
			}
			else {
				if(x.right==null) {
					x.right=k;
					y=false;
				}
				else {
					x=x.right;
				}
			}
		}

	}

/*
 * The add functions adds a node to the treap and places it into its right spot It also gives the node a random priority. 
 */
	public boolean add (E key ) {
		return add(key,priorityGenerator.nextInt() );

	}

	/*
	 * The add functions adds a node to the treap and places it into its right spot. 
	 */
	public boolean add (E key , int priority ) {
		Node<E> k=new Node<E>(key,priority);
		if(find(k.data)) {
			return false;
		}
		else if(root==null){
			root=k;
			return true;
		}
		else {
			addleaf(k);
			int j=lis.size();
			for(int i=0; i<j;i++) {
				if(k.priority>lis.peek().priority) {
					if(k.data.toString().compareTo(lis.peek().data.toString())<0) {
						lis.pop().rotateRight();
						if(lis.isEmpty()==false) {
							if(k.data.toString().compareTo(lis.peek().data.toString())<0) {
								lis.peek().left=k;
							}
							else {
								lis.peek().right=k;
							}
						}
						else {
							root=k;
						}
					}
					else {
						lis.pop().rotateLeft();
						if(lis.isEmpty()==false) {
							if(k.data.toString().compareTo(lis.peek().data.toString())<0) {
								lis.peek().left=k;
							}
							else {
								lis.peek().right=k;
							}
						}
						else {
							root=k;
						}
					}
				}
				else {
					break;
				}
			}

			return true;
		}
	}

	
	/*
	 * The delete function deletes a specific node from the treap. It also fixes the trees order. 
	 */
	public boolean delete ( E key ) {

		if(find(key)==false) {

			return false;
		}
		else {
			if((lis.size()==1) && (root.left==null) && (root.right==null)) {
				root=null;
				return true;
			}
			Node<E> k=lis.pop();
			Node<E> f;
			if(lis.isEmpty()) {
				Node<E> c;
				if((k.left!=null) &&(k.right==null)) {
					c=k.rotateRight();
					root=c;
				}
				else if ((k.left==null) &&(k.right!=null)){
					c=k.rotateLeft();
					root=c;
				}
				else {
					if(k.right.priority>k.left.priority) {
						c=k.rotateLeft();
						root=c;
					}
					else {
						c=k.rotateRight();
						root=c;
					}
				}
				f=c;
			}
			else {
				f=lis.pop();
			}
			while(true) {
				if((k.left!=null) &&(k.right==null)) {
					if(k.data.toString().compareTo(f.data.toString())<0) {
						Node<E> t=k.rotateRight();
						f.left=t;
						f=t;

					}
					else {
						Node<E> t=k.rotateRight();
						f.right=t;
						f=t;
					}

				}
				else if((k.left==null) &&(k.right!=null)) {
					if(k.data.toString().compareTo(f.data.toString())<0) {
						Node<E> t=k.rotateLeft();
						f.left=t;
						f=t;
					}
					else {
						Node<E> t=k.rotateLeft();
						f.right=t;
						f=t;
					}
				}
				else if((k.left==null) &&(k.right==null)) {
					if(k.data.toString().compareTo(f.data.toString())<0) {
						f.left=null;
					}
					else {
						f.right=null;
					}
					return true;
				}
				else {
					if(k.right.priority>k.left.priority) {
						if(k.data.toString().compareTo(f.data.toString())<0) {
							Node<E> t=k.rotateLeft();
							f.left=t;
							f=t;
						}
						else {
							Node<E> t=k.rotateLeft();
							f.right=t;
							f=t;
						}
					}
					else {
						if(k.data.toString().compareTo(f.data.toString())<0) {
							Node<E> t=k.rotateRight();
							f.left=t;
							f=t;

						}
						else {
							Node<E> t=k.rotateRight();
							f.right=t;
							f=t;
						}
					}
				}
			}
		}
	}

	
	/*
	 * The find function checks for a specific node in the treap. If it is found, it returns true.
	 */
	private boolean find ( Node <E > root , E key ) {
		if(root==null) {
			return false;
		}
		else {
			Node<E> x=root;
			lis=new Stack<Node<E>>();
			while(true) {
				lis.push(x);
				if(key.toString().compareTo(x.data.toString())<0) {
					if(x.left!=null) {
						x=x.left;
					}
					else {
						return false;
					}
				}
				else if(key.toString().compareTo(x.data.toString())>0) {
					if(x.right!=null) {
						x=x.right;
					}
					else {
						return false;
					}
				}
				else {
					return true;
				}
			}
		}
	}

	/*
	 * The find function checks for a specific node in the treap. If it is found, it returns true.
	 */
	public boolean find (E key ) {
		return find(root, key);
	}

/*
 * The space function is a help function to tsh. This function creates a given number of spaces. 
 */
	private String space(int h) {
		String s="";
		for(int i=0; i<h; i++) {
			s=s+"  ";
		}
		return s;
	}

/*
 * The tsh function is a helper function to toString. This function uses recursion to create a string representation of the treap. 
 */
	private String tsh(Node<E> t, int h) {
		String s="";
		s=s+space(h)+t.toString()+"\n";
		if(t.left!=null) {
			s=s+tsh(t.left,h+1);
		}
		else {
			s=s+space(h+1)+"null\n";
		}
		if(t.right!=null) {
			s=s+tsh(t.right,h+1);
		}
		else {
			s=s+space(h+1)+"null\n";
		}
		return s;
	}


/*
 * The toString function returns a string representation of the treap using the helper function tsh(). 
 */
	public String toString () {
		if(root==null) {
			return "null";
		}
		else {
			return tsh(root,0);
		}

	}



}
