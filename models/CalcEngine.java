
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The main part of the calculator doing the calculations.
 * 
 * @author  Ciara Power
 * @version 1.0
 */
public class CalcEngine
{
	private boolean debug=true;   // DEBUG (true to debug and see program steps on console, false to show no debug print outs)
	
	String displayValue;   // global field for the calculator display 
	private boolean lastOperator;    // global field set to true if the last element entered on calculator was an operator
	private boolean decimalPointInNum; // global field set to true if the numerical value being entered already has a decimal point (one per numeric value)
									   // e.g cant have 2.34.23
	private int openBracketCount;   // count of how many opening brackets entered in the equation
	private int closeBracketCount;  // count of how many closing brackets entered in the equation
	private JFrame parent;     // the parent component calculator window ,dialog boxes in calcEngine will show within this frame space

	/*
	 * Method used to set the parent frame , called from userInterface object
	 */
	public void setParent(JFrame parent) {
		this.parent = parent;
	}

	/**
	 * Create a CalcEngine instance. Initialize its state so that it is ready 
	 * for use.
	 */
	public CalcEngine()
	{
		openBracketCount=0;    // none entered yet
		closeBracketCount=0;     // none entered yet
		decimalPointInNum=false;     // none entered yet
		lastOperator=false;      // none entered yet
		displayValue="";   // empty display
		
		if (debug){
			System.out.println("Initial: " + displayValue);
		}
	}

	/**
	 * Return the value that should currently be displayed on the calculator
	 * display.
	 */
	public String getDisplayValue()
	{
		return(displayValue);
	}

	/**
	 * A number button was pressed. 
	 * The number value of the button is given as a parameter.
	 */
	public void numberPressed(int number)
	{
		if(!displayValue.endsWith(".")){     // if a "." was not just entered before this number , there is currently no decimal point in numeric value
			decimalPointInNum=false;         // set to false as no decimal point (as of yet)
		}
		displayValue +=""+ number;   // added to display
		lastOperator=false;    // number was added,not an operator, so lastOperator set to false
		
		if (debug){
			System.out.println(number +" added as number: "+displayValue);
		}
	}

	/**
	 * The '+' button was pressed. 
	 */
	public void plus()
	{
		if(!lastOperator && !displayValue.equals("")){  // if an operator was not just entered, and the display isnt empty (cant begin equation with operator)
			displayValue += " + ";  //operator added with spaces either side to distinguish equation elements 
			lastOperator=true;   // + is operator
			
			if (debug){
				System.out.println("+ added as addition operator: "+displayValue);
			}
		}
		else{   // if + added after another operator, or as start of equation
			JOptionPane.showMessageDialog(parent,"Please enter a valid equation \n>> Two operators cannot be entered consecutively <<\n"
					+ ">> An equation cannot start with an operator <<", "ERROR", 0);
		}
	}

	/**
	 * The '-' button was pressed.
	 */
	public void minus()
	{
		if((displayValue.equals("") || lastOperator) && !displayValue.endsWith("-")){ // if display empty or the last element entered was an operator, and the last element entered wasnt a negation "-" 
			displayValue += "-";   // added as negation, no spaces so it will be included with following number element
			
			if (debug){
				System.out.println("- added as negation: "+displayValue);
			}
		}
		else{   // otherwise, the "-"  entered is taken as a subtraction sign
			if(!lastOperator){   // last element entered wasnt operator
				displayValue += " - ";  // add subtraction sign with spaces to distinguish elements 
				lastOperator=true;   // minus is an operator
				
				if (debug){
					System.out.println("- added as minus operator: "+displayValue);
				}
			}
			else{
				JOptionPane.showMessageDialog(parent, "Please enter a valid equation \n>> Two operators cannot be entered consecutively <<\n>> An equation cannot start with an operator <<", "ERROR", 0);
			}
		}
	}

	/**
	 * The '*' button was pressed.
	 */
	public void multiply()
	{
		if(!lastOperator && !displayValue.equals("")){  // if an operator was not just entered, and the display isnt empty (cant begin equation with operator)
			displayValue += " * ";   //added with spaces
			lastOperator=true;   //* is operator
			
			if (debug){
				System.out.println("* added: "+displayValue);
			}
		}
		else{
			JOptionPane.showMessageDialog(parent, "Please enter a valid equation \n>> Two operators cannot be entered consecutively <<\n>> An equation cannot start with an operator <<", "ERROR", 0);
		}
	}

	/**
	 * The '/' button was pressed.
	 */
	public void divide()
	{
		if(!lastOperator && !displayValue.equals("")){ // if an operator was not just entered, and the display isnt empty (cant begin equation with operator)
			displayValue += " / ";  //with spaces
			lastOperator=true;  // is operator
			
			if (debug){
				System.out.println("/ added: "+displayValue);
			}
		}
		else{
			JOptionPane.showMessageDialog(parent, "Please enter a valid equation \n>> Two operators cannot be entered consecutively <<\n>> An equation cannot start with an operator <<", "ERROR", 0);
		}
	}
	
	/**
	 * The '^' button was pressed.
	 */
	public void powerOf()
	{
		if(!lastOperator && !displayValue.equals("")){ // if an operator was not just entered, and the display isnt empty (cant begin equation with operator)
			displayValue += " ^ ";   //with spaces
			lastOperator=true;   // is operator
			
			if (debug){
				System.out.println("^ added: "+displayValue);
			}
		}
		else{
			JOptionPane.showMessageDialog(parent, "Please enter a valid equation \n>> Two operators cannot be entered consecutively <<\n>> An equation cannot start with an operator <<", "ERROR", 0);
		}
	}
	
	/**
	 * The '(' button was pressed.
	 */
	public void openBracket()
	{
		if(lastOperator || displayValue.equals("")){ // if the display is empty, or last element entered was an operator 
			displayValue += "( ";  // space only after bracket
			openBracketCount++;  // open bracket count is incremented
			lastOperator=true;  // bracket is operator in this case (dont want a case such as " ( * 4 +5 ) " occuring where operator is entered immediately after open bracket
			
			if (debug){
				System.out.println("( added: "+displayValue);
			}
		}
		else{
			JOptionPane.showMessageDialog(parent, "Please enter a valid equation \n>> An operator must be placed between a number and an open bracket << ", "ERROR", 0);
		}
	}
	
	/**
	 * The ')' button was pressed.
	 */
	public void closeBracket()
	{
		if(!lastOperator && openBracketCount>closeBracketCount ){  // if the equation in bracket didnt end with operator,
																	// and there is more opening brackets than closing 
																	//(cant add closing bracket if it doesnt match with an opening bracket)
			displayValue += " )";   //space on left 
			closeBracketCount++;  //closing bracket count incremented
			lastOperator=false;   // not included as operator, as an operator must be added next e.g " ( 2 + 2 ) * 3 " 
			
			if (debug){
				System.out.println(") added: "+displayValue);
			}
		}
		else{
			JOptionPane.showMessageDialog(parent, "Please enter a valid equation \n>> A closing bracket cannot be preceeded by an operator <<\n>> You cannot enter more closing brackets than there are opening brackets <<","ERROR", 0);
		}
	}

	/**
	 * The '.' button was pressed.
	 */
	public void decimalPoint()
	{
		if(!lastOperator && !decimalPointInNum){  // if last element entered wasnt operator, and there is not already a decimal point in this numeric value
			displayValue += "."; //added with no space so included with surrounding digits
			decimalPointInNum=true;  // the current numeric value being entered now has a decimal point 
			
			if (debug){
				System.out.println(". added: "+displayValue);
			}
		}
		else{
			JOptionPane.showMessageDialog(parent, "Please enter a valid equation \n>> A decimal point can only be entered after a number <<\n>> Only one decimal point can be entered per numeric value <<","ERROR", 0);
		}
	}

	/**
	 * The '=' button was pressed.
	 */
	public void equals()
	{
		if(!lastOperator && openBracketCount==closeBracketCount && !displayValue.equals("")){ // if equation doesnt end with operator, and brackets balanced, and equation display isnt empty
			String postfix=infixToPostfix(displayValue);   // pass the infix string entered to method which returns postfix string
			if (debug){
				System.out.println("----- CONVERSIONS -----");
				System.out.println("Infix: "+displayValue+"\nPostfix: "+postfix);
			}
			displayValue=evaluateEquation(postfix);  // pass postfix string to evaluate method, returning a solution string for the display 
			if (debug){
				System.out.println("Equation Solved: "+displayValue);
			}
		}
		else{
			JOptionPane.showMessageDialog(parent, "Please enter a valid equation \n>> The equation cannot end with an operator <<\n>> "
					+ "The equation must have balanced brackets <<\n>> The equation cannot be blank <<","ERROR", 0);
		}

	}

	/*
	 * returns string postfix conversion of the infix parameter
	 */
	public String infixToPostfix(String equation){
		if (debug){
			System.out.println("----- INFIX TO POSTFIX COMMENCEMENT -----");
		}
		MyStack stack=new MyStack(10);  // create a stack for infix to postfix conversion
		String delims = "[ ]";  // the string will be divided in relation to spaces 
		String postfix="";   // empty postfix string , later will be returned as solution 
		String[] equationTokens = equation.split(delims);  // the equation passed through is split by spaces
		for (int tokenNo=0;tokenNo<equationTokens.length;tokenNo++){  // iterate through each token in the equation
			if (debug){
				System.out.println("Token "+tokenNo+": " +equationTokens[tokenNo]);
			}
			if(!isOperator(equationTokens[tokenNo])){  // if token is not an operator ( calls method to return boolean)
				postfix+=equationTokens[tokenNo]+" ";  // token added to postfix string with space 
				if (debug){
					System.out.println(equationTokens[tokenNo]+" added to postfix: "+postfix);
				}
			}
			else{
				if(equationTokens[tokenNo].equals("(")){  // if open bracket, add to stack 
					stack.push("(");
				}
				else if(equationTokens[tokenNo].equals(")")){ 
					while(!stack.peek().equals("(")){  // pop stack elements until open bracket pushed previously is reached, indicating bracket equation end
						postfix+=stack.pop()+" ";  // added to string , bracket equations have highest priority 
						if (debug){
							System.out.println(equationTokens[tokenNo]+" added to postfix: "+postfix);
						}
					}
					stack.pop(); // pop the open bracket off stack, but this is not added to postfix
				}
				else if(!stack.peek().equals("-1")){   //if stack isnt empty 
					while(priority(equationTokens[tokenNo])< priority(stack.peek()) ){   //check priorities , if current token is of less priority,pop the higher priority off stack
						postfix+=stack.pop()+" ";
						if (debug){
							System.out.println("Postfix updated with popped stack element: "+postfix);
						}
					}
					stack.push(equationTokens[tokenNo]);  // if token priority higher, add to stack
				}
				else{
					stack.push(equationTokens[tokenNo]); //stack empty so add token to stack
				}

			}
		}

		while(!stack.peek().equals("-1")){  // after every token in equation has been inspected , pop all remaining on stack and add to postfix
			postfix+=stack.pop()+" ";
			if (debug){
				System.out.println("Postfix updated with popped stack element (END OF INPUT EQUATION STRING): "+postfix);
			}
		}
		return postfix;
	}

	/*
	 * returns int value indicating priority of string entered as parameter
	 */

	public int priority(String string) {   // returns priority of the token 
		if (string.equals("^")){  
			if (debug){
				System.out.println("Priority returned for "+string);
			}
			return 2;
		}
		else if (string.equals("*") || string.equals("/")){   // both same priority
			if (debug){
				System.out.println("Priority returned for "+string);
			}
			return 1;
		}
		else {
			if (debug){
				System.out.println("Priority returned for "+string);
			}
			return 0;
		}
	}

	/*
	 * returns string solution to postfix equation
	 */
	public String evaluateEquation(String postfix){
		if (debug){
			System.out.println("----- EVALUATE EQUATION COMMENCEMENT -----");
		}
		String delims = "[ ]";   //split postfix by spaces 
		String[] equationTokens = postfix.split(delims);
		double element1;  //element variables used in each section of equation 
		double element2;
		MyStack equationStack=new MyStack(10); //new local stack created for postfix evaluation
		for (int i=0;i<equationTokens.length;i++){   //go through each token
			if (debug){
				System.out.println("Token "+i+" : "+equationTokens[i] );
			}
			if(isOperator(equationTokens[i])){   //token is an operator
				switch(equationTokens[i]){  
				case "*":
					element1= Double.parseDouble(equationStack.pop());   // pop off previous two numeric values on stack 
					element2= Double.parseDouble(equationStack.pop());
					if (debug){
						System.out.println("MULTIPLY >> "+element2+" by "+element1);
					}
					equationStack.push(""+(element2*element1));  // push product of elements onto stack

					break;
				case "/":
					element1= Double.parseDouble(equationStack.pop());  // pop previous two elements off stack
					element2= Double.parseDouble(equationStack.pop());
					if (debug){
						System.out.println("DIVIDE >> "+element2+" by "+element1);
					}
					equationStack.push(""+(element2/element1));   // divide second element by first element and push onto stack

					break;
				case "-":
					element1= Double.parseDouble(equationStack.pop()); // pop previous two elements off stack
					element2= Double.parseDouble(equationStack.pop());
					if (debug){
						System.out.println("SUBTRACT >> "+element1+" from "+element2);
					}
					equationStack.push(""+(element2-element1));  // push result of subtraction onto stack

					break;

				case "+":
					element1= Double.parseDouble(equationStack.pop());  // pop previous two elements off stack
					element2= Double.parseDouble(equationStack.pop());
					if (debug){
						System.out.println("ADD >> "+element2+" with "+element1);
					}
					equationStack.push(""+(element2+element1));  // push addition result onto stack

					break;
				case "^":
					element1= Double.parseDouble(equationStack.pop());  // pop previous two elements off stack
					element2= Double.parseDouble(equationStack.pop());
					double product=1.0;  // initialised at 1 ,because 1*anything=anything, if 0 then result of loop below would be 0 
					if(element1<0){ // if a number is to a power of a negative number
						for(int power=1;power<=element1*-1;power++){  // negative power and positive power multiply same amount of times, so convert the element to positive for "power" to increment successfully 
							product*=element2;   
						}
						if (debug){
							System.out.println("NEGATIVE POWER OF >> "+element2+" to the power of "+element1);
						}
						equationStack.push(""+1/product);   // a number to a negative power is the same as the inverse , e.g 2^-2  == 1/(2^2) 

					}
					else{   //if power is positive 
						for(int power=1;power<=element1;power++){  //iterate until wanted power is reached
							product*=element2;
						}
						if (debug){
							System.out.println("POSITIVE POWER OF >> "+element2+" to the power of "+element1);
						}
						equationStack.push(""+product); // push product result onto stack
					}
					break;
				}	
			}
			else{   // if token is non operator 
				equationStack.push(equationTokens[i]);
			}

		}
		return equationStack.pop();  // at the end of evaluation, only solution is left on the stack, return this value
	}

	/*
	 * returns boolean indicating if a string is an operator or not
	 */
	public boolean isOperator(String element){   // method to test if string is an operator
		switch(element){
		case "*":
		case "-":
		case "+":
		case "/":
		case "^":
		case "(":
		case ")":
		{
			if (debug){
				System.out.println("Operator  >> "+element);
			}
			return true;
		}

		default:
		{
			if (debug){
				System.out.println("Non Operator  >> "+element);
			}
			return false;
		}
		}
	}

	/**
	 * The 'C' (clear) button was pressed.
	 */
	public void clear()
	{
		if (debug){
			System.out.println("Cleared Equation  >> "+displayValue);
		}
		openBracketCount=0;    //all global fields reset
		closeBracketCount=0;
		decimalPointInNum=false;
		lastOperator=false;
		displayValue = "";

	}

	/**
	 * Return the title of this calculation engine.
	 */
	public String getTitle()
	{
		return("My Calculator");
	}

	/**
	 * Return the author of this engine. This string is displayed as it is,
	 * so it should say something like "Written by H. Simpson".
	 */
	public String getAuthor()
	{
		return("Written by Ciara Power");
	}

	/**
	 * Return the version number of this engine. This string is displayed as 
	 * it is, so it should say something like "Version 1.1".
	 */
	public String getVersion()
	{
		return("Ver. 1.0");
	}

}