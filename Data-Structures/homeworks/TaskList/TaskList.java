/*
 * @author Adib Osmany
 * @pledge I pledge my honor that I have abided by the Stevens Honor System.
 */
import java.util.Iterator;


public class TaskList<E> {

	private ListQueue<E> all;

	private ListQueue<E> completed;

	private ListQueue<E> active;
	private int LOW_PRIORITY = Integer.MAX_VALUE;
	private int HIGH_PRIORITY = 1;

	/*
	 * TaskList constructor
	 */
	public TaskList() {
		all= new ListQueue();
		completed= new ListQueue();
		active= new ListQueue();
	}

	
	/*
	 * The createtask function adds an element to the end of the all and active list. 
	 */
	boolean createTask(E item) {
		if(item.equals(null)) {
			return false;
		}
		else {
			all.addRear(item);
			active.addRear(item);
			return true;
		}
	}

	/*
	 * The createtask function adds an element to the all and active list. Where the element is placed is determined by the priority. 
	 */
	boolean createTask(E item, int priority) {
		if(item.equals(null)) {
			return false;
		}
		else {
			all.offer(item, priority);
			active.offer(item, priority);
			return true;
		}
	}
	

	/*
	 * The printTopThreeTasks function prints the top three elements in the active list. 
	 */
	public void printTopThreeTasks(){
		int index=1;
		Iterator<E> ti=active.iterator();
		while(ti.hasNext() && index<=3) {
			System.out.println(index +". " + ti.next());
			index++;
		}
		if(active.getSize()<=3) {
		System.out.println(index +". "+ active.getRear().getData());
		}
	}

	/*
	 * The showActiveTasks function prints all the elements in the active list, in order. 
	 */
	public void showActiveTasks() {
		printTasks(active);
	}

	/*
	 * The showAllTasks function prints all the elements in the all list, in order. 
	 */
	public void showAllTasks() {
		printTasks(all);
	}
	
	/*
	 * The showCompletedTasks function prints all the elements in the completed list, in order. 
	 */
	public void showCompletedTasks() {
		printTasks(completed);
	}

	
	/*
	 * The printTasks is a helper function that prints all the elements in queue.  
	 */
	private void printTasks(ListQueue<E> queue) {
		int index=1;
		Iterator<E> ti=queue.iterator();
		while(ti.hasNext()) {
			System.out.println(index +". " + ti.next());
			index++;
		}
		System.out.println(index +". "+ queue.getRear().getData());
	}

	/*
	 * The crossOffMostUrgent function gets rid of the top element in the active list, and adds it to the completed list. 
	 */
	public boolean crossOffMostUrgent() {
		if(active.getSize()==0) {
			System.out.println("Error: there are no items in the To-Do List to cross off!");
			return false;
		}
		else {
			completed.addRear(active.getFront().getData());
			return  active.remove(active.getFront());

		}

	}

	/*
	 * The crossOffTask function gets rid of the element that correlates to the given task number. That element is added to the completed list. 
	 */
	public boolean crossOffTask(int taskNumber) {
		if (taskNumber>active.getSize() || taskNumber<1){
			return false;
		}
		else {
			completed.addRear(active.getCer(taskNumber).getData());
			return active.remove(active.getCer(taskNumber));
		}

	}


	/*
	 * The getAll function returns the all ListQueue
	 */
	public ListQueue<E> getAll() {
		return all;
	}


	/*
	 * The getCompleted function returns the completed ListQueue
	 */
	public ListQueue<E> getCompleted() {
		return completed;
	}


	/*
	 * The getActive function returns the active ListQueue
	 */
	public ListQueue<E> getActive() {
		return active;
	}


}
