import java.util.Arrays;

/**
 * My implementation of a Stack object
 * 
 * @author  Ciara Power
 * @version 1.0
 */

public class MyStack {
	private String[] stackArray;
	private int stackSize;
	private int topOfStack=-1;
	private boolean debug=true;

	MyStack(int size){
		stackSize=size;
		stackArray=new String[size];
		Arrays.fill(stackArray, "-1");  
	}
	
	public void push(String input){
		if (topOfStack+1< stackSize){  //if space left in stack
			topOfStack++;   // top of stack moves up
			stackArray[topOfStack]=input;
		}
		else{
			if(debug)
				System.out.println("Sorry but the stack is full");
		}
		if(debug){
			System.out.println("-----------------");
			System.out.println("PUSH "+input+" was added to the stack");
			displayStack();
			System.out.println("-----------------");
		}
	}

	public String pop(){
		if (topOfStack>=0){  //stack isnt empty
			if(debug){
				System.out.println("-----------------");
				System.out.println("POP "+stackArray[topOfStack]+" Was removed from the stack");
			}
			String top=stackArray[topOfStack];
			stackArray[topOfStack]="-1";  //element reset to -1
			topOfStack--; //top of stack moves down
			if(debug){
				displayStack();
				System.out.println("-----------------");
			}
			return top;
		}
		else{
			if (debug){
				System.out.println("-----------------");
				displayStack();
				System.out.println("Sorry but the stack is empty");
				System.out.println("-----------------");
			}
			return "-1";
		}
	}

	public String peek(){ 
		if(topOfStack>=0){  //stack isnt empty
			if(debug){
				System.out.println("-----------------");
				System.out.println("PEEK " + stackArray[topOfStack]+ " Is at the top of the stack");
				displayStack();
				System.out.println("-----------------");
			}
			return stackArray[topOfStack];
		}
		else{
			return "-1";
		}
	}

	private void displayStack() {
		if(debug){
			for (int i=0;i<stackSize;i++){
				System.out.println(i+": "+ stackArray[i]);
			}
		}
	}

	public void popAll(){
		for (int i=topOfStack;i>=0;i--){  //pop all elements on stack
			pop();
		}
	}

	/*public static void main (String[] args){   // used to inspect stack implementation initially
	Stack stack= new Stack(10);
	   stack.push("10");
	   stack.push("15");
	   stack.peek();
	   stack.pop();
	   stack.popAll();

   }
	 */
}
