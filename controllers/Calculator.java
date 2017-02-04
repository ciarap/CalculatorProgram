
/**
 * The main class of a simple calculator. 
 * 
 * @author Ciara Power
 */
public class Calculator
{
	private CalcEngine engine;
	private UserInterface gui;

	/**
	 * Create a new calculator and show it.
	 */
	public Calculator()
	{
		engine = new CalcEngine();
		gui = new UserInterface(engine);
	}

	/**
	 * In case the window was closed, show it again.
	 */
	public void show()
	{
		gui.setVisible(true);
	}

	public static void main(String[] args)
	{
		Calculator cTest = new Calculator();
		while(true);   // runs in endless loop
	}
}