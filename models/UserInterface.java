import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * A graphical user interface for the calculator. No calculation is being
 * done here. This class is responsible just for putting up the display on 
 * screen. It then refers to the "CalcEngine" to do all the real work.
 * 
 * @author Ciara Power
 */
public class UserInterface
	implements ActionListener    // interface which listens for key presses by user
{
	private CalcEngine calc;
	private boolean showingAuthor;

    private JFrame frame;       // frame of calculator window
	private JTextField display;   // the white text box to display equation entered and answer computed 
	private JLabel status;   // label on calculator

	/**
	 * Create a user interface for a given calcEngine.
	 */
	public UserInterface(CalcEngine engine)
	{
		calc = engine;
		showingAuthor = true;
		makeFrame();    //call to method which creates the window
		frame.setVisible(true);    //display window created
		calc.setParent(frame);   // the calc engine parent field is set to the frame of the calculator window, which is the component
		                         // any popup dialog boxes in calc will show infront of (instead of middle of screen)
	}

	/**
	 * Make this interface visible again. (Has no effect if it is already
	 * visible.)
	 */
    public void setVisible(boolean visible)
	{
		frame.setVisible(visible);
	}

	/**
	 * Make the frame for the user interface.
	 */
	private void makeFrame()
	{
		frame = new JFrame(calc.getTitle());    // initialise frame 
		
		JPanel contentPane = (JPanel)frame.getContentPane();
		contentPane.setPreferredSize(new Dimension(400, 400));    //set sizes
		contentPane.setLayout(new BorderLayout(10, 10));
		contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));
		contentPane.setBackground(Color.WHITE);    // white background behind button panel on calculator

		Font font1 = new Font("Comic Sans MS", Font.BOLD, 24);    // font for display 
		display = new JTextField();
		display.setFont(font1);    // font used for display
		display.setPreferredSize( new Dimension( 200, 38 ) );   // display size
		contentPane.add(display, BorderLayout.NORTH);   // display placed at top of window

		JPanel buttonPanel = new JPanel(new GridLayout(5, 4));    // button panel grid 
		addButton(buttonPanel, "C");
		addButton(buttonPanel, "(");
		addButton(buttonPanel, ")");
		addButton(buttonPanel, "/");
			addButton(buttonPanel, "7");
			addButton(buttonPanel, "8");
			addButton(buttonPanel, "9");
			addButton(buttonPanel, "*");
			addButton(buttonPanel, "4");
			addButton(buttonPanel, "5");
			addButton(buttonPanel, "6");
			addButton(buttonPanel, "+");
			addButton(buttonPanel, "1");
			addButton(buttonPanel, "2");
			addButton(buttonPanel, "3");
			addButton(buttonPanel, "-");
			addButton(buttonPanel, "0");
			addButton(buttonPanel, ".");
			addButton(buttonPanel, "^");
			addButton(buttonPanel, "=");
		contentPane.add(buttonPanel, BorderLayout.CENTER);   //placed in middle

		status = new JLabel(calc.getAuthor());  
		contentPane.add(status, BorderLayout.SOUTH);   /// author displayed at bottom

		frame.pack();
		
	}

	/**
	 * Add a button to the button panel.
	 */
	private void addButton(Container panel, String buttonText)
	{
		JButton button = new JButton(buttonText);
		button.addActionListener(this);
		button.setBackground(new Color(214,135,253));    //color set to a purple tone
		button.setFont(new Font("Comic Sans MS", Font.BOLD, 20));   //font changed to comic sans ms , and size changed
		button.setForeground(Color.WHITE);  // the text appearing on each button set to white
		panel.add(button);
	}

	/**
	 * An interface action has been performed. Find out what it was and
	 * handle it.
	 */
	public void actionPerformed(ActionEvent event) 
	{
		String command = event.getActionCommand();

		if(command.equals("0") ||
		   command.equals("1") ||
		   command.equals("2") ||
		   command.equals("3") ||
		   command.equals("4") ||
		   command.equals("5") ||
		   command.equals("6") ||
		   command.equals("7") ||
		   command.equals("8") ||
		   command.equals("9"))
		{
			int number = Integer.parseInt(command);       // for each case of button press, a specific calc method is called
			calc.numberPressed(number);
		}
		else if(command.equals("+"))
			calc.plus();
		else if(command.equals("-"))
			calc.minus();
		else if(command.equals("="))
			calc.equals();
		else if(command.equals("C"))
			calc.clear();
		else if(command.equals("*"))
			calc.multiply();
		else if(command.equals("/"))
			calc.divide();
		else if(command.equals("^"))
			calc.powerOf();
		else if(command.equals("("))
			calc.openBracket();
		else if(command.equals(")"))
			calc.closeBracket();
		else if(command.equals("."))
			calc.decimalPoint();

		redisplay();  // display updated for each button press
	}

	/**
	 * Update the interface display to show the current value of the 
	 * calculator.
	 */
	private void redisplay()
	{
		display.setText("" + calc.getDisplayValue());    //display updated with display value field of calc object
	}

	/**
	 * Toggle the info display in the calculator's status area between the
	 * author and version information.
	 */
	private void showInfo()
	{
		if(showingAuthor)
			status.setText(calc.getVersion());
		else
			status.setText(calc.getAuthor());

		showingAuthor = !showingAuthor;
	}
}
