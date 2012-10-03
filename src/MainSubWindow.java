
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class MainSubWindow {
    private GroupSelection gs;
    private MySQL my;
    private JFrame frame;
    PrintStream out = System.out;
    
    public void setMYSQL(MySQL my) {
        this.my = my;
    }
    
    public void setGS(GroupSelection gs) {
        this.gs = gs;
    }
    
    public void launchWindow(int winNo) {
        out.println("winNo: " + winNo);
        switch(winNo) {
            case 0: // Join a new group
                join();
                break;
            case 1: // Create a new group
                create();
                break;
            case 2: // Edit Personal info
                edit();
                break;
        }
    }
    
    private void join() {
        out.println("start join frame");
        frame = new JFrame();
        //frame.setMinimumSize(new Dimension(50, 100));
        frame.setBounds(100,100,200,120);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        Container container = frame.getContentPane();
        JPanel c = new JPanel();
        JLabel groupID = new JLabel("Enter Group ID: ");
        final JTextField iDEntry = new JTextField();
        //iDEntry.setSize(100, 200);
        iDEntry.setColumns(10);
        JButton join = new JButton("Join Group");
        
        join.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
                out.println("triggered join final");
                if(!iDEntry.getText().equals("") ) {
                    out.println("definitely something there: " + iDEntry.getText() );
                    String collect = iDEntry.getText();
                    try {
                        /*GroupSelection.GroupAdded*/ TransferStuff ts = my.joinGroup(Runner.idNo,Integer.parseInt(collect) );
                        //System.out.println("GroupAdded: " + GroupSelection.GroupAdded);
                        if(ts != null) {
                            Runner.groupNames = ts.groupName;
                            Runner.groupIDs = ts.groupID;
                            GroupSelection.GroupChange = true;
                            gs.groupChanged();
                        }
                    } catch(Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        
        c.add(groupID);
        c.add(iDEntry);
        c.add(join);
        
        container.add(c);
        
        frame.setTitle("Join Group");
        frame.setVisible(true);
    }
    
    private void create() {
        frame = new JFrame();
        //frame.setMinimumSize(new Dimension(50, 100));
        frame.setBounds(100,100,200,120);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        Container container = frame.getContentPane();
        JPanel c = new JPanel();
        JLabel groupID = new JLabel("Enter Group Name: ");
        final JTextField iDEntry = new JTextField();
        //iDEntry.setSize(100, 200);
        iDEntry.setColumns(10);
        JButton join = new JButton("Create Group");
        
        join.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
                out.println("triggered group final");
                if(!iDEntry.getText().equals("") ) {
                    out.println("definitely something there: " + iDEntry.getText() );
                    String collect = iDEntry.getText();
                    try {
                        TransferStuff ts = my.createGroup(collect);
                        if(ts != null) {
                            Runner.groupNames = ts.groupName;
                            Runner.groupIDs = ts.groupID;
                            GroupSelection.GroupChange = true;
                            gs.groupChanged();
                        }
                    } catch(Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        
        c.add(groupID);
        c.add(iDEntry);
        c.add(join);
        
        container.add(c);
        
        frame.setTitle("Join Group");
        frame.setVisible(true);
    }
    
    private void edit() {
        String[] data = my.editQuery(Runner.idNo);
        frame = new JFrame();
        frame.setMinimumSize(new Dimension(450, 400));
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        
        /*JButton retScreen = new JButton();
        retScreen.setText("Return");
        retScreen.setBounds(0,5,80,20);
        panel.add(retScreen);

        JLabel lblID = new JLabel("ID No.");
        lblID.setBounds(51, 70, 92, 14);
        panel.add(lblID);

        JLabel lblFName = new JLabel("Name");
        lblFName.setBounds(51, 95, 92, 14);
        panel.add(lblFName);

        JLabel lblName = new JLabel("Username");
        lblName.setBounds(51, 120, 92, 14);
        panel.add(lblName);*/

        JLabel lblPassword = new JLabel("Password");
        //lblPassword.setBounds(51, 145, 92, 14);
        lblPassword.setBounds(51, 45, 92, 14);
        panel.add(lblPassword);

        JLabel lblEmail = new JLabel("Email");
        //lblEmail.setBounds(51, 170, 92, 14);
        lblEmail.setBounds(51, 70, 92, 14);
        panel.add(lblEmail);

        JLabel lblMobileNumber = new JLabel("Mobile Number");
        //lblMobileNumber.setBounds(51, 195, 92, 14);
        lblMobileNumber.setBounds(51, 95, 92, 14);
        panel.add(lblMobileNumber);

        JLabel lblHomeNumber = new JLabel("Home Number");
        //lblHomeNumber.setBounds(51, 220, 92, 14);
        lblHomeNumber.setBounds(51, 120, 92, 14);
        panel.add(lblHomeNumber);

        JLabel lblHomeAddress = new JLabel("Home Address");
        //lblHomeAddress.setBounds(51, 245, 92, 14);
        lblHomeAddress.setBounds(51, 145, 92, 14);
        panel.add(lblHomeAddress);
        
        /*final JTextField textField_5 = new JTextField();
        textField_5.setBounds(153, 67, 210, 20);
        panel.add(textField_5);
        textField_5.setColumns(10);
        
        final JTextField textField_6 = new JTextField();
        textField_6.setBounds(153, 92, 210, 20);
        panel.add(textField_6);
        textField_6.setColumns(10);
        
        final JTextField textField = new JTextField();
        textField.setBounds(153, 117, 210, 20);
        panel.add(textField);
        textField.setColumns(10);*/
        
        final JTextField textField_2 = new JTextField();
        //textField_2.setBounds(153, 167, 212, 20);
        textField_2.setBounds(153, 67, 212, 20);
        textField_2.setText(data[1] );
        panel.add(textField_2);
        textField_2.setColumns(10);
        
        final JTextField textField_3 = new JTextField();
        //textField_3.setBounds(153, 192, 86, 20);
        textField_3.setBounds(153, 92, 86, 20);
        textField_3.setText(data[2] );
        panel.add(textField_3);
        textField_3.setColumns(10);

        final JTextField textField_4 = new JTextField();
        //textField_4.setBounds(153, 217, 86, 20);
        textField_4.setBounds(153, 117, 86, 20);
        textField_4.setText(data[3] );
        panel.add(textField_4);
        textField_4.setColumns(10);

        final JPasswordField passwordField = new JPasswordField();
        //passwordField.setBounds(153, 145, 210, 14);
        passwordField.setText(data[0] );
        passwordField.setBounds(153, 45, 210, 14);
        panel.add(passwordField);

        final JEditorPane dtrpnBLB = new JEditorPane();
        //dtrpnBLB.setBounds(153, 245, 188, 118);
        dtrpnBLB.setText(data[4] );
        dtrpnBLB.setBounds(153, 145, 188, 118);
        panel.add(dtrpnBLB);

        JButton btnSaveChanges = new JButton("Save Changes");
        btnSaveChanges.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String p = "";
                char[] pass = passwordField.getPassword();
                for(int i = 0; i < pass.length; i++){
                    p = p + pass[i];
                }
                String em = textField_2.getText();
                String m = textField_3.getText();
                String h = textField_4.getText();
                String a = dtrpnBLB.getText();
                
                my.editInfo(p, em, m, h, a, Runner.idNo);
                frame.dispose();
                frame.setVisible(false);
            }
        });
        //btnSaveChanges.setBounds(153, 398, 117, 23);
        btnSaveChanges.setBounds(153, 298, 117, 23);
        panel.add(btnSaveChanges);

        /*JButton btnRetrieve = new JButton("Retrieve");
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String find = textField_5.getText();
                String get = my.registerQuery(Integer.parseInt(find) );
                if(get != null) {
                    textField_6.setText(get);
                    retrieved = true;
                }
                else {
                    out.println("Did not find that person");
                    retrieved = false;
                }
            }
        });
        btnRetrieve.setBounds(373, 66, 86, 23);
        panel.add(btnRetrieve);
        
        retScreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
                frame.dispose();
                frame.setVisible(false);
                //SOURCE: http://www.dreamincode.net/forums/topic/164349-clicking-a-button-to-open-a-new-jframe-and-then-close-the-recent-jfram/
                login();
            }
        });*/
        
        frame.setTitle("Edit Info");
        frame.setVisible(true);
    }
}
