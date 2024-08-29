/*
 * @author Adib Osmany
 * @pledge I pledge my honor that I have abided by the Stevens Honor System.
 */
import java.util.Scanner;


public class TestTaskList<E> {
	private TaskList<E> toDoList;
	Scanner scan;

	public static void main(String[] args) {
		
		TestTaskList<String> t= new TestTaskList<String>();
		t.printMenu();


	}

	/*
	 * The print menu function prints a menu of options for the user to select
	 */
	public void printMenu() {
		toDoList=new TaskList<E>();
		int input = 0;
		System.out.println("~~~ TO-DO List Program, created by truly yours ~~~");
		while(input!=8) {
			if (toDoList.getActive().getSize()==0) {
				System.out.println("==> Currently there are NO items in the To-Do List");
			}
			else {
				System.out.println("Current TO-DO List:");
				System.out.println("-------------------");
				toDoList.showActiveTasks();
			}
			System.out.println("To add a new task without priority information, press 1.");
			System.out.println("To add a new task with a priority information, press 2.");
			System.out.println("To cross off the task at the top of the list, press 3.");
			System.out.println("To cross off a certain task in the list, press 4.");
			System.out.println("To see the top 3 highest priority tasks, press 5.");
			System.out.println("To see the completed tasks, press 6.");
			System.out.println("To see the all tasks that has been completed or still active, press 7.");
			System.out.println("To quit the program, press 8.");
			scan=new Scanner(System.in);
			if(scan.hasNextInt()) {
				input=scan.nextInt();
				if(input>8 || input<1) {
					System.out.println("ERROR! Please enter a number between 1 and 8 (included).");
				}
				else {
					this.processMenuItem(input);
				}
			}
			else {
				System.out.println("ERROR! Please enter a number between 1 and 8 (included).");
			}
		}
	}

	
	/*
	 * The processmenuitem function takes the input from the user and processes it based on their selection.
	 */

	private boolean processMenuItem(int menuItem) {
		E task;
		int userSelection = 0;
		if(menuItem==1) {
			System.out.println("Please enter the task description:");
			scan=new Scanner(System.in);
			task=(E) scan.nextLine();
			toDoList.createTask(task);
			System.out.println("Successfully entered the task to the to-do list!");
		}
		else if(menuItem==2){
			System.out.println("Please enter the task description:");
			scan=new Scanner(System.in);
			task=(E) scan.nextLine();
			System.out.println("Please enter a priority number (1 indicates highest priority, increasing numbers show lower priority) :");
			boolean p=true;
			while(p) {
				scan=new Scanner(System.in);
				if(scan.hasNextInt()) {
					userSelection=scan.nextInt();
					if(userSelection<1) {
						System.out.println("Please enter a priority number greater than 0");
					}
					else {
						toDoList.createTask(task,userSelection);
						System.out.println("Successfully entered the task to the to-do list!");
						p=false;
					}
				}
				else {
					System.out.println("Please enter a priority number greater than 0");
				}
			}
		}
		else if(menuItem==3){
			if(toDoList.getActive().getFront()==null) {
				toDoList.crossOffMostUrgent();
			}
			else {
				String dt=toDoList.getActive().getFront().getData().toString();
				boolean m3=toDoList.crossOffMostUrgent();
				if (m3) {
					System.out.println("Task is completed and removed from the list: "+dt);
					System.out.println("Successfully removed the most urgent task/top of the list task!");
				}
			}
		}
		else if(menuItem==4){

			if (toDoList.getActive().getSize()==0) {
				System.out.println("Error: there are no items in the To-Do List to cross off!");
			}
			else {
				System.out.println("Please enter the task number you would like to cross off the list :");

				scan=new Scanner(System.in);
				if(scan.hasNextInt()) {
					userSelection=scan.nextInt();
					boolean m4=toDoList.crossOffTask(userSelection);
					if(m4) {
						int pe=toDoList.getCompleted().getSize();
						String ep=toDoList.getCompleted().getCer(pe).getData().toString();
						System.out.println("Task number "+userSelection+" is removed: "+ep);
						System.out.println("Successfully removed the task number: "+userSelection);
					}
					else {
						System.out.println("Unsuccessful operation! Please try again!");
					}
				}
				else {
					System.out.println("Unsuccessful operation! Please try again!");
				}
			}


		}
		else if(menuItem==5){
			if (toDoList.getActive().getSize()==0) {
				System.out.println("There are NO items in the To-Do List");
			}
			else {
				System.out.println("Top 3 highest priority tasks:");
				System.out.println("------------------------------");
				System.out.println("Printing Top Three Tasks...");
				toDoList.printTopThreeTasks();
			}
		}
		else if(menuItem==6){
			if (toDoList.getCompleted().getSize()==0) {
				System.out.println("==> Currently there are NO items in the Completed Tasks List");
			}
			else {
				System.out.println("Completed Tasks:");
				System.out.println("----------------");
				toDoList.showCompletedTasks();
			}
		}
		else if(menuItem==7){
			if (toDoList.getAll().getSize()==0) {
				System.out.println("==> Currently there are NO items in the To-Do and Completed tasks List");
			}
			else {
				System.out.println("All of the Tasks - Both completed and active:");
				System.out.println("---------------------------------------------");
				toDoList.showAllTasks();
			}
		}
		else if(menuItem==8){
			System.out.println();
			System.out.println("Process finished with exit code 0");
		}
		return true;
	}

}




