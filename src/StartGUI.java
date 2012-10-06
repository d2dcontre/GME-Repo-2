import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

/*
 * This class handles the construction and functions of the Login and Register Screens
 */
public class StartGUI {

    private MySQL my; // A class containing all of the queries necessary
    private JFrame frame; // The single frame that is used by both the login and register screen
    PrintStream out = System.out; // Used for debugging purposes, rapid prints
    private boolean retrieved = false; // Used to indicate if a person has been retrieved
                                       // in the register screen, meaning the process can continue
    
    // Initializes the class by assigning a MySQL class containing all of the queries necessary.
    public StartGUI(MySQL my) {
        System.out.println("GUI constructor");
        this.my = my;
        login();
    }
    
    // Used to show error messages
    // String message contains the message you want to send
    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    /*
     * The first screen shown to the user. Here, the user logs in using his/her User Name.
     * The user can also access the register screen to create an account from here.
     */
    public void login() {
        // Construct the frame
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Construct the panel
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        
        // Constructs the User Name text field
        final JTextField textField = new JTextField();
        textField.setBounds(164, 88, 182, 20);
        panel.add(textField);
        textField.setColumns(10);
        
        // Constructs the Password Field
        final JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(164, 119, 182, 20);
        panel.add(passwordField);
        
        // Constructs the User Name Label
        JLabel lblNewLabel = new JLabel("Username");
        lblNewLabel.setBounds(77, 91, 77, 14);
        panel.add(lblNewLabel);
        
        // Constructs the Password Label
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(77, 122, 77, 14);
        panel.add(lblPassword);
        
        // Constructs the login button
        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(164, 180, 89, 23);
        panel.add(btnLogin);
        
        JButton btnRegister = new JButton("Register");
        //btnRegister.setAction(action_1);
        btnLogin.addActionListener(null);
        btnRegister.setBounds(257, 180, 89, 23);
        panel.add(btnRegister);
        
        // Adds a listener to the Login Button
        // Retrieves the user name and password from their respective fields
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = textField.getText();
                String p = "";
                char[] pass = passwordField.getPassword();
                for(int i = 0; i < pass.length; i++){
                    p = p + pass[i];
                }
                out.println("user: " + user + ", pass: " + p);
                if(!user.equals("") && !p.equals("") ) {
                    TransferStuff ts = my.loginQuery(user, p); // Used to contain the data received from a query
                    if(ts != null) {
                        Runner.groupIDs = ts.groupID; // Sets the groups the user is a member of
                        Runner.groupNames = ts.groupName; // Sets the names of the groups the user is a member of
                        Runner.idNo = ts.id; // Stores the ID number of the user in memory

                        out.println("No issues with setting values");
                        System.out.print("GroupID ");
                        Runner.printer(Runner.groupIDs);
                        System.out.print("GroupNames ");
                        Runner.printer(Runner.groupNames);
                        out.println("ID: " + Runner.idNo);

                        // Closes the frame
                        frame.dispose();
                        frame.setVisible(false);
                        
                        // Launches the group selection screen
                        GroupSelection gs = new GroupSelection(my);
                    }
                    else
                        showMessage("Incorrect User Name and/or Password");
                }
                else
                    showMessage("No User Name and/or Password");
            } 
        });
        
        // Attaches a listener to the register button to launch the register screen
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*WindowEvent wev = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);*/
                //SOURCE: http://stackoverflow.com/questions/1234912/how-to-programmatically-close-a-jframe
                
                frame.dispose();
                frame.setVisible(false);
                // SOURCE: http://www.dreamincode.net/forums/topic/164349-clicking-a-button-to-open-a-new-jframe-and-then-close-the-recent-jfram/
                
                register();
            }
        });
        
        // Finalizes the frame setup and makes it visible
        frame.setTitle("Welcome - Login");
        frame.setVisible(true);
    }
    
    public void register() {
        // Initializes Register screen
        frame = new JFrame();
        frame.setMinimumSize(new Dimension(500, 500));
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        
        // Sets up JPanel
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        // Sets up the labels
        JLabel lblID = new JLabel("ID No.");
        lblID.setBounds(51, 70, 92, 14);
        panel.add(lblID);

        JLabel lblFName = new JLabel("Name");
        lblFName.setBounds(51, 95, 92, 14);
        panel.add(lblFName);

        JLabel lblName = new JLabel("Username");
        lblName.setBounds(51, 120, 92, 14);
        panel.add(lblName);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(51, 145, 92, 14);
        panel.add(lblPassword);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(51, 170, 92, 14);
        panel.add(lblEmail);

        JLabel lblMobileNumber = new JLabel("Mobile Number");
        lblMobileNumber.setBounds(51, 195, 92, 14);
        panel.add(lblMobileNumber);

        JLabel lblHomeNumber = new JLabel("Home Number");
        lblHomeNumber.setBounds(51, 220, 92, 14);
        panel.add(lblHomeNumber);

        JLabel lblHomeAddress = new JLabel("Home Address");
        lblHomeAddress.setBounds(51, 245, 92, 14);
        panel.add(lblHomeAddress);
        
        // Initializes the textfields
        // ID No.
        final JTextField textField_5 = new JTextField();
        textField_5.setBounds(153, 67, 210, 20);
        panel.add(textField_5);
        textField_5.setColumns(10);
        
        // Name
        final JTextField textField_6 = new JTextField();
        textField_6.setEditable(false);
        textField_6.setBounds(153, 92, 210, 20);
        panel.add(textField_6);
        textField_6.setColumns(10);
        
        // Username
        final JTextField textField = new JTextField();
        textField.setBounds(153, 117, 210, 20);
        panel.add(textField);
        textField.setColumns(10);
        
        // Password
        final JTextField textField_2 = new JTextField();
        textField_2.setBounds(153, 167, 212, 20);
        panel.add(textField_2);
        textField_2.setColumns(10);
        
        // Email
        final JTextField textField_3 = new JTextField();
        textField_3.setBounds(153, 192, 86, 20);
        panel.add(textField_3);
        textField_3.setColumns(10);

        // Mobile No.
        final JTextField textField_4 = new JTextField();
        textField_4.setBounds(153, 217, 86, 20);
        panel.add(textField_4);
        textField_4.setColumns(10);

        // Landline
        final JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(153, 145, 210, 14);
        panel.add(passwordField);

        // Home address
        final JEditorPane dtrpnBLB = new JEditorPane();
        dtrpnBLB.setBounds(153, 245, 188, 118);
        panel.add(dtrpnBLB);

        // Initializes the save changes button
        JButton btnSaveChanges = new JButton("Register");
        btnSaveChanges.setBounds(153, 398, 117, 23);
        panel.add(btnSaveChanges);
        // Adds the listener
        btnSaveChanges.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textField_5.getText(); // Grabs ID
                String u = textField.getText(); // Grabs username
                boolean uCheck = regExCheck(u, "[a-zA-Z0-9_-]+"); // checks for valid username
                                                                  //   (at least one alphanumeric character)
                String p = ""; // Grabs password
                char[] pass = passwordField.getPassword(); // Converts password in field
                for(int i = 0; i < pass.length; i++){      //    to readable text
                    p = p + pass[i];
                }
                boolean pCheck = regExCheck(p, ".+"); // Checks for valid password (at least 1 char)
                String em = textField_2.getText(); // Grabs email
                boolean emCheck = regExCheck(em, "[a-zA-Z0-9_-]+@(([a-zA-Z0-9_-])\\.?)+\\.[a-z]{2,3}");
                                                   // Checks for valid email (using the format Alpha#@alpha#.alpha)
                String m = textField_3.getText(); // Grabs mobile numer
                boolean mCheck = regExCheck(m, "0[0-9]{10,10}"); // Checks for mobile with 11 digits
                String h = textField_4.getText(); // Grabs landline
                boolean hCheck = regExCheck(h, "[0-9]{7,7}"); // checks for landline with 7 digits
                String a = dtrpnBLB.getText(); // Grabs address
                boolean aCheck = regExCheck(a, ".+"); // checks that address has at least one letter
                
                boolean taken = my.registerCheck(u);
                
                if(retrieved) { // Will only trigger if a valid student is retrieved by the retrieve button
                    if(uCheck && pCheck && emCheck && mCheck && hCheck && aCheck && !taken) { // checks for errors
                        int execUp = my.registerExecute(id, u, p, em, m, h, a); // Executes update
                        if(execUp > 0) { // Checks if any lines have been updated
                            JOptionPane.showMessageDialog(null, "Thank you for registering!\nPlease login using your new User Name and Password!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                            frame.dispose();
                            frame.setVisible(false);
                            //SOURCE: http://www.dreamincode.net/forums/topic/164349-clicking-a-button-to-open-a-new-jframe-and-then-close-the-recent-jfram/
                            login(); // Fires a message and returns you to the login screen
                        }
                        else { // MySQL error
                            System.out.println("Nothing modified. Error. regExec value is: " + execUp);
                        }
                    }
                    else { // list all errors individually
                        String mess = "Errors are: \n";
                        if(!uCheck)
                            mess = mess + "User Name must not be empty and must be alpha numerical;\n";
                        if(!pCheck)
                            mess = mess + "Password must be at least any one character;\n";
                        if(!emCheck)
                            mess = mess + "Email must fit the standard format e.g. abc@def.com;\n";
                        if(!mCheck)
                            mess = mess + "Mobile phone must be 11 digits long;\n";
                        if(!hCheck)
                            mess = mess + "Home phone must be 7 digits long;\n";
                        if(!aCheck)
                            mess = mess + "Address must not be empty;\n";
                        if(taken)
                            mess = mess + "Your username is taken.";
                        if(mess.lastIndexOf(";\n") == mess.length() - 2)
                            mess = mess.substring(0,mess.lastIndexOf(";\n") ) + ".";
                        showMessage(mess);
                    }
                }
                else // User has not tried to retrieve his name from the database
                    showMessage("Please check if you are in the database first!\nType your ID No." + 
                            " above and click \"Retrieve\" to get your name!");
            }
        });

        // initializes the retrieve button
        JButton btnRetrieve = new JButton("Retrieve");
        btnRetrieve.setBounds(373, 66, 86, 23);
        panel.add(btnRetrieve);
        // adds the listener
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String find = textField_5.getText(); // grabs the id number
                boolean findCheck = regExCheck(find, "[0-9]{6,6}"); // checks for valid 6 digit ID
                if(findCheck) { // checks for errors
                    String get = my.registerQuery(Integer.parseInt(find) );
                    if(get != null) { // checks to see if any ID is retrieved
                        textField_6.setText(get); // set the field to the suer
                        retrieved = true; // set retrieved to true
                    }
                    else { // display invalid user error
                        showMessage("We couldn't find you.\nAre you sure that's your ID Number?");
                        out.println("Did not find that person");
                        retrieved = false; // set retrieved to false
                    }
                }
                else { // display error
                    showMessage("Please enter an ID Number at least 6 digits long!");
                }
            }
        });
        
        // Sets up the return button--takes you back to login screen
        JButton retScreen = new JButton();
        retScreen.setText("Return");
        retScreen.setBounds(0,5,80,20);
        panel.add(retScreen);
        // Adds the listener
        retScreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // closes the window
                frame.setVisible(false);
                //SOURCE: http://www.dreamincode.net/forums/topic/164349-clicking-a-button-to-open-a-new-jframe-and-then-close-the-recent-jfram/
                login(); // returns to login
            }
        });
        
        // finalizes setup
        frame.setTitle("Register Now - Groups Made Easy");
        frame.setVisible(true);
    }
    
    // method used to check validity
    public static boolean regExCheck(String s, String regex) {
        boolean found = false;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        found = m.find();
        return found;
    }
}