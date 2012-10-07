import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Stack;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
public class AlphaGUI extends JFrame {
    public MySQL my;
    public int rowCountAdd;
    public JPanel contentPane;
    public JTable dailySchedTable;
    public AlphaSQL sql;
    public JLabel dailySchedLabel;
    private JTable thisCalendar;
    private JButton thisEditButton;
    public PrintWriter pr;
    private JPanel thisPanel1;
    private JPanel thisPanel2;
    private JLabel thisDate;
    private JButton thisSave;
    private JLabel thisFree;
    private JPanel thisMonth;
    private CardLayout sample;
    private Calendar neo;
    private String month;
    private JTable thisSchedTable;
    private JScrollPane scrollPane;
    public JComboBox thisStart; 
    public JComboBox thisEnd;
    public String filename;
    private JTable thisCheckTable;
    private JTextField thisEventText;
    private int year;
    private int currMonth;
    
    private JLabel thisLabel;
    private JButton nextMonth;
    private JButton btnPreviousMonth;
    public int groupID;
    private String[] classSched;
    private String[][] eventDay;
    private String[][] appDay;
    private JTextArea freelist;
    
    //<editor-fold defaultstate="collapsed" desc="void main">
    /*public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AlphaGUI frame = new AlphaGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
    //</editor-fold>
    
    // Constructs the Calendar GUI
    public AlphaGUI(MySQL my) {
        this.my = my;
        
        classSched = my.dailyQuery(Runner.idNo);
        
        createCalendar();
        
        rowCountAdd = 0;
        sql = new AlphaSQL("pawnshop");
        
        modifyFrame();

        final JTabbedPane MainTabs = new JTabbedPane(JTabbedPane.TOP);
        MainTabs.setFont(new Font("Calibri", MainTabs.getFont().getStyle(), 
                MainTabs.getFont().getSize()));
        MainTabs.setBounds(0, 0, 781, 564);
        contentPane.add(MainTabs);
        
        // Builds daily schedule
        buildDaily(MainTabs);

        // Builds month calendar display
        buildCalendar(MainTabs);
        
        // Builds edit screen
        buildEdit();
    }

    // Sets up calendar-related things
    public void createCalendar() {
        neo = new GregorianCalendar();	// instantiate a new calendar
        neo.setLenient(true); // changes date overflow to equivalent month
        neo = Calendar.getInstance();	// get local time
        currMonth = neo.get(neo.MONTH);
        //System.out.println(currMonth);
        switch(currMonth) {
        case 0:
            month = "January";
            break;
        case 1:
            month = "February";
            break;
        case 2:
            month = "March";
            break;
        case 3:
            month = "April";
            break;
        case 4:
            month = "May";
            break;
        case 5:
            month = "June";
            break;
        case 6:
            month = "July";
            break;
        case 7:
            month = "August";
            break;
        case 8:
            month = "September";
            break;
        case 9:
            month = "October";
            break;
        case 10:
            month = "November";
            break;
        case 11:
            month = "December";
            break;
        }
        year = neo.get(neo.YEAR);
    }
    
    // Handles content additions to the main frame
    public void modifyFrame() {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 797, 602);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
    }
    
    // Build daily schedule tab
    public void buildDaily(JTabbedPane MainTabs) {
        // Build panel to hold tab content
        JPanel Appraisal = new JPanel();
        MainTabs.addTab("Daily Schedule", null, Appraisal, null);
        Appraisal.setLayout(null);
        
        // Add a scroll pane to the tab
        JScrollPane scheduleScrollPane = new JScrollPane();
        scheduleScrollPane.setBounds(0, 23, 776, 440);
        Appraisal.add(scheduleScrollPane);
        
        // Build the Daily Sched JTable
        dailySchedTable = new JTable();
        dailySchedTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                //
            }
        });
        dailySchedTable.setModel(new DefaultTableModel(
            new Object[][] {
                {"7:00-7:30", " ", " ", " ", " ", " ", " ", " "},
                {"7:30-8:00", " ", " ", " ", " ", " ", " ", " "},
                {"8:00-8:30", "  ", " ", " ", " ", " ", " ", " "},
                {"8:30-9:00", " ", " ", " ", " ", " ", " ", " "},
                {"9:00-9:30", " ", " ", " ", " ", " ", " ", " "},
                {"9:30-10:00", " ", " ", " ", " ", " ", " ", " "},
                {"10:00-10:30", " ", " ", " ", " ", " ", " ", " "},
                {"10:30-11:00", " ", " ", " ", " ", " ", " ", " "},
                {"11:00-11:30", " ", " ", " ", " ", " ", " ", " "},
                {"11:30-12:00", " ", " ", " ", " ", " ", " ", " "},
                {"12:00-12:30", " ", " ", " ", " ", " ", " ", " "},
                {"12:30-1:00", " ", " ", " ", " ", " ", " ", " "},
                {"1:00-1:30", " ", " ", " ", " ", " ", " ", " "},
                {"1:30-2:00", " ", " ", " ", " ", " ", " ", " "},
                {"2:00-2:30", " ", " ", " ", " ", " ", " ", " "},
                {"2:30-3:00", " ", " ", " ", " ", " ", " ", " "},
                {"3:00-3:30", " ", " ", " ", " ", " ", " ", " "},
                {"3:30-4:00", " ", " ", " ", " ", " ", " ", " "},
                {"4:00-4:30", " ", " ", " ", " ", " ", " ", " "},
                {"4:30-5:00", " ", " ", " ", " ", " ", " ", " "},
                {"5:00-5:30", " ", " ", " ", " ", " ", " ", " "},
                {"5:30-6:00", " ", " ", " ", " ", " ", " ", " "},
                {"6:00-6:30", " ", " ", " ", " ", " ", " ", " "},
                {"6:30-7:00", " ", " ", " ", " ", " ", " ", " "},
                {"7:00-7:30", " ", " ", " ", " ", " ", " ", " "},
                {"7:30-8:00", " ", " ", " ", " ", " ", " ", " "},
                {"8:00-8:30", " ", " ", " ", " ", " ", " ", " "},
                {"8:30-9:00", " ", " ", " ", " ", " ", " ", " "},
            },
            new String[] {
                "Time", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", 
                "Friday", "Saturday"
            }
        ) {
            Class[] columnTypes = new Class[] {
                String.class, String.class, String.class, String.class, 
                String.class, String.class, String.class, String.class
            };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        dailySchedTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        dailySchedTable.getColumnModel().getColumn(3).setPreferredWidth(97);
        dailySchedTable.getColumnModel().getColumn(4).setPreferredWidth(104);
        dailySchedTable.setFillsViewportHeight(true);
        scheduleScrollPane.setViewportView(dailySchedTable);
        
        // Build daily sched label
        dailySchedLabel = new JLabel("Right Click to Highlight\r\n");
        dailySchedLabel.setForeground(UIManager.getColor("CheckBox.highlight"));
        dailySchedLabel.setBackground(UIManager.getColor("CheckBox.darkShadow"));
        dailySchedLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        dailySchedLabel.setBounds(0, 0, 776, 23);
        Appraisal.add(dailySchedLabel);

        // Populating the Table
        popDaily(dailySchedTable);
        
        // Add saving functionality
        JButton dailySchedSave = new JButton("Save Changes\r\n");
        dailySchedSave.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                // Updating the DB
                editDaily(dailySchedTable, pr);
            }
        });
        dailySchedSave.setFont(new Font("Calibri", Font.PLAIN, 11));
        dailySchedSave.setBounds(302, 485, 170, 28);
        //Appraisal.add(dailySchedSave);
    }
    
    // Builds the calendar tab
    public void buildCalendar(JTabbedPane MainTabs) {
        // Build Tab
        thisMonth = new JPanel(new CardLayout(0,0)); //tab for This Month with 
                                                     //a card layout
        MainTabs.addTab("Calendar\r\n", null, thisMonth, null);
        MainTabs.setEnabledAt(1, true);
        
        // Build panel to be placed in tab
        thisPanel1 = new JPanel();											//first panel for card layout
        thisMonth.add(thisPanel1);
        thisPanel1.setLayout(null);
        
        // Add a scroll pane to the panel
        JScrollPane thisCalendarScroll = new JScrollPane();
        thisCalendarScroll.setBounds(101, 5, 452, 266);
        thisPanel1.add(thisCalendarScroll);
        
        // Builds the calendar that shows the days in a month
        thisCalendar = new JTable();										//calendar for the current month
        thisCalendarScroll.setViewportView(thisCalendar);
        thisCalendar.setColumnSelectionAllowed(true);
        thisCalendar.setCellSelectionEnabled(true);
        thisCalendar.setModel(new DefaultTableModel(
            new Object[][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
            },
            new String[] {
                "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"
                    , "Saturday"
            }
        ));
        thisCalendar.getColumnModel().getColumn(0).setPreferredWidth(150);
        thisCalendar.getColumnModel().getColumn(1).setPreferredWidth(150);
        thisCalendar.getColumnModel().getColumn(2).setPreferredWidth(150);
        thisCalendar.getColumnModel().getColumn(3).setPreferredWidth(150);
        thisCalendar.getColumnModel().getColumn(4).setPreferredWidth(150);
        thisCalendar.getColumnModel().getColumn(5).setPreferredWidth(150);
        thisCalendar.getColumnModel().getColumn(6).setPreferredWidth(150);
        calendarPopulation(neo,thisCalendar);
        thisCalendar.setRowHeight(40);
        
        // Adds an edit button that will allow the user to edit a day's schedule
        thisEditButton = new JButton("Edit"); //button for going to the 2nd 
                                 //panel of the card layout w/editing available
        thisEditButton.setBounds(563, 207, 89, 23);
        thisPanel1.add(thisEditButton);
        
        // Adds a check button to display the schedule for the day without editing
        JButton thisCheckButton = new JButton("Check");
        thisCheckButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int selectRow = thisCalendar.getSelectedRow();
                int selectColumn = thisCalendar.getSelectedColumn();
                if (selectRow > -1 && selectColumn > -1) {
                    if (thisCalendar.getValueAt(selectRow, selectColumn) != null) {
                        popCalendSched(thisCheckTable, thisCalendar.getSelectedColumn());
                    }
                    else {
                        StartGUI.showMessage("Please select a valid (with a number) day!");
                    }
                } else {
                    StartGUI.showMessage("Please select a day!");
                }
            }
        });
        thisCheckButton.setBounds(563, 247, 89, 23);
        thisPanel1.add(thisCheckButton);
        
        // Adds a second scrollpane to hold the check table for the events in a day
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(104, 307, 449, 218);
        thisPanel1.add(scrollPane_1);
        
        // Creates the check table
        thisCheckTable = new JTable();
        scrollPane_1.setViewportView(thisCheckTable);
        thisCheckTable.setModel(new DefaultTableModel(
            new Object[][] {
                {"7:00-7:30", null},
                {"7:30-8:00", null},
                {"8:00-8:30", null},
                {"8:30-9:00", null},
                {"9:00-9:30", null},
                {"9:30-10:00", null},
                {"10:00-10:30", null},
                {"10:30-11:00", null},
                {"11:00-11:30", null},
                {"11:30-12:00", null},
                {"12:00-12:30", null},
                {"12:30-13:00", null},
                {"13:00-13:30", null},
                {"13:30-14:00", null},
                {"14:00-14:30", null},
                {"14:30-15:00", null},
                {"15:00-15:30", null},
                {"15:30-16:00", null},
                {"16:00-16:30", null},
                {"16:30-17:00", null},
                {"17:00-17:30", null},
                {"17:30-18:00", null},
                {"18:00-18:30", null},
                {"18:30-19:00", null},
                {"19:00-19:30", null},
                {"19:30-20:00", null},
                {"20:00-20:30", null},
                {"20:30-21:00", null},
            },
            new String[] {
                "Time", "Status"
            }
        ));
        
        // Creates a button that allows you to change months +1
        nextMonth = new JButton("Next Month");
        nextMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thisLabel.setText(setTitle(neo,1) );
            }
        });
        nextMonth.setBounds(563,60,89,23);
        thisPanel1.add(nextMonth);
        
        // Creates a button that allows you to change months -1
        btnPreviousMonth = new JButton("Previous Month");
        btnPreviousMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thisLabel.setText(setTitle(neo,-1) );
            }
        });
        btnPreviousMonth.setBounds(563,100,118,23);
        thisPanel1.add(btnPreviousMonth);
        
        // Adds a new label that displays the month and the year of the current table
        thisLabel = new JLabel("");
        thisLabel.setBounds(563, 12, 118, 23);
        thisPanel1.add(thisLabel);
        thisLabel.setText(month + " " + year);
        
        // Adds the listener to the edit button that triggers the edit screen
        thisEditButton.addActionListener(new editAction());
    }
    
    // Builds edit screen
    public void buildEdit() {
        thisPanel2 = new JPanel(); //2nd panel of the card layout; responsible 
                // for getting the status of the date selected in the calendar
        thisPanel2.setLayout(null);
        thisMonth.add(thisPanel2);

        thisDate = new JLabel("DATE:");
        thisDate.setBounds(0, 0, 333, 14);
        thisPanel2.add(thisDate);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(195, 25, 445, 220);
        thisPanel2.add(scrollPane);

        thisSchedTable = new JTable();
        scrollPane.setViewportView(thisSchedTable);
        thisSchedTable.setModel(new DefaultTableModel(
            new Object[][] {
                {"7:00-7:30", null},
                {"7:30-8:00", null},
                {"8:00-8:30", null},
                {"8:30-9:00", null},
                {"9:00-9:30", null},
                {"9:30-10:00", null},
                {"10:00-10:30", null},
                {"10:30-11:00", null},
                {"11:00-11:30", null},
                {"11:30-12:00", null},
                {"12:00-12:30", null},
                {"12:30-13:00", null},
                {"13:00-13:30", null},
                {"13:30-14:00", null},
                {"14:00-14:30", null},
                {"14:30-15:00", null},
                {"15:00-15:30", null},
                {"15:00-16:00", null},
                {"16:00-16:30", null},
                {"16:30-17:00", null},
                {"17:00-17:30", null},
                {"17:30-18:00", null},
                {"18:00-18:30", null},
                {"18:30-19:00", null},
                {"19:00-19:30", null},
                {"19:30-20:00", null},
                {"20:00-20:30", null},
                {"20:30-21:00", null},
            },
            new String[] {
                "Times", "Status"
            }
        ) {
            boolean[] columnEditables = new boolean[] {
                true, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
		
        thisSave = new JButton("Save Changes\r\n"); //button for saving changes
        thisSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editCalendSched(thisSchedTable, pr, filename);
            }
        });
        thisSave.setFont(new Font("Calibri", Font.PLAIN, 11));
        thisSave.setBounds(284, 444, 170, 28);
        //thisPanel2.add(thisSave);	
        thisFree = new JLabel("The Free times are:");
        thisFree.setVerticalAlignment(SwingConstants.TOP);
        thisFree.setBounds(40, 40, 111, 91);
        thisPanel2.add(thisFree);
        
        freelist = new JTextArea();
        JScrollPane temp = new JScrollPane();
        temp.setViewportView(freelist);
        temp.setBounds(40,80,140,180);
        thisPanel2.add(temp);
		
        JButton thisRetBtn = new JButton("Return\r\n");
        thisRetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                sample = (CardLayout) thisMonth.getLayout();
                sample.previous(thisMonth);
            }
        });
        thisRetBtn.setBounds(549, 443, 170, 28);
        thisPanel2.add(thisRetBtn);
        
        JButton setAppoint = new JButton("Set Appointment");
        setAppoint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                setAppointment(thisSchedTable, thisStart, thisEnd, thisEventText);
            }
        });
        setAppoint.setBounds(65, 444, 141, 26);
        thisPanel2.add(setAppoint);

        JLabel lblStart = new JLabel("Start");
        lblStart.setBounds(65, 341, 46, 14);
        thisPanel2.add(lblStart);

        JLabel lblEnd = new JLabel("End");
        lblEnd.setBounds(145, 341, 46, 14);
        thisPanel2.add(lblEnd);

        thisStart = new JComboBox();
        thisStart.setModel(new DefaultComboBoxModel(
            new String[] {"7:00", "7:30", "8:00", "8:30", "9:00", "9:30", 
                "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", 
                "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", 
                "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", 
                "20:30"
            })
        );
        thisStart.setBounds(65, 366, 61, 20);
        thisPanel2.add(thisStart);

        thisEnd = new JComboBox();
        thisEnd.setModel(new DefaultComboBoxModel(
            new String[] {"7:30", "8:00", "8:30", "9:00", "9:30", "10:00", 
                "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", 
                "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", 
                "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", 
                "21:00"})
            );
        thisEnd.setBounds(145, 366, 61, 20);
        thisPanel2.add(thisEnd);

        JButton thisBusyBtn = new JButton("Mark Busy");
        thisBusyBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mark(thisSchedTable);
            }
        });
        thisBusyBtn.setBounds(284, 372, 170, 28);
        thisPanel2.add(thisBusyBtn);

        thisEventText = new JTextField();
        thisEventText.setBounds(65, 397, 141, 28);
        thisPanel2.add(thisEventText);
        thisEventText.setColumns(10);

        JButton thisFreeBtn = new JButton("Mark Free");
        thisFreeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                free(thisSchedTable);
            }
        });
        thisFreeBtn.setBounds(549, 374, 170, 28);
        thisPanel2.add(thisFreeBtn);
    }
    
    //<editor-fold defaultstate="collapsed" desc="getMin(Scanner scan, JLabel label)">
    /*public void getMin(Scanner scan,JLabel label) {
        String init = label.getText();
        int min=100;
        while(scan.hasNextLine()) {
            String day = scan.nextLine();
            String names = scan.nextLine();
            if(names.equals("") && min == 0) {
                label.setText(label.getText() + "\n" + day);
            }
            else if(names.equals("")) {
                min = 0;
                label.setText(init + "\n" + day);
            }
            else {	
                int temp = 1;
                for(int i = 0; i < names.length(); i++ ) {
                    if(names.charAt(i) == ',') {
                        temp++;
                    }
                }
                if (temp == min) {
                    label.setText(label.getText() + "\n" + day);
                }
                else if(temp < min) {
                    min = temp;
                    label.setText(init + "\n" + day);
                }
            }
        }
        System.out.print("got here");
    }*/
    //</editor-fold>
	
    // Populates the Calendar
    public void calendarPopulation(Calendar cal, JTable tabel) {
        cal.set(cal.DATE, 1);
        int dayz = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        int currentDayz = cal.get(GregorianCalendar.DAY_OF_WEEK);
        for (int i=1; i<=dayz; i++)
        {
            int row = new Integer((i+currentDayz-2)/7);
            int column  =  (i+ currentDayz-2)%7;
            tabel.setValueAt(i, row, column);
        }
        Calendar.getInstance();
    }

    // Handles actions triggered by clicking the edit button
    private class editAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectRow = thisCalendar.getSelectedRow();
            int selectColumn = thisCalendar.getSelectedColumn();
            if (selectRow > -1 && selectColumn > -1) {
                if(thisCalendar.getValueAt(selectRow, selectColumn) != null)
                {
                    int day = (Integer)thisCalendar.getValueAt(
                        thisCalendar.getSelectedRow(),
                        thisCalendar.getSelectedColumn()
                    );
                    sample = (CardLayout) thisMonth.getLayout();
                    sample.next(thisMonth);
                    thisDate.setText("DATE: "  + month + " " + day);
                    filename = (month + " " + day +".txt");
                    popCalendSched(thisSchedTable,thisCalendar.getSelectedColumn());
                    bigCheck();
                }
                else {
                    StartGUI.showMessage("Please select a valid (with a number) day!");
                }
            } else {
                StartGUI.showMessage("Please select a day!");
            }
        }
    }
    
    // saves the changes in the daily Sched
    public void editDaily(JTable table,PrintWriter print) {
        try
        {
            print = new PrintWriter("Daily Schedule.txt");
        }
        catch(Exception e)
        {
        }	
        for( int i = 0; i < table.getRowCount(); i++)
        {
            for(int j = 1; j < table.getColumnCount(); j++)
            {
                System.out.print(table.getValueAt(i, j).toString());
                if(table.getValueAt(i, j).toString() != " ")
                {
                    print.write(table.getValueAt(i, j).toString()+ " ");
                }
                else
                {
                    print.write("^^&* ");
                }
            }
            print.write("\n");
            System.out.print("\n");
        }
        print.flush();
    }
	
    // populates the dailySched with the times in the text file
    public void popDaily(JTable table) {
        if(classSched != null) {
            for(int i = 0; i < classSched.length; i++) {
                String[] temp = classSched[i].split(" ");
                /*for(int k = 0; k < temp.length; k++) {
                    System.out.print(temp[k] + " ");
                }*/
                //System.out.println();
                int begin = Integer.parseInt(temp[0] );
                int end = Integer.parseInt(temp[1] );
                //System.out.println("begin: " + begin + ", end: " + end);
                String name = temp[2];
                int days = temp.length - 3;
                for(int j = 0; j < days; j++) {
                    int day = Integer.parseInt(temp[j+3] ) + 1;
                    //System.out.println("Day: " + day);
                    for(int k = begin; k <= end; k++) {
                        dailySchedTable.setValueAt(name, k, day);
                    }
                }
            }
        }
    }
			
    // saves the changes in the Schedule of the selected day of the Calendar
    public void editCalendSched(JTable table,PrintWriter print,String filename) {
        try
        {
            print = new PrintWriter(filename);
        }
        catch(Exception e)
        {}
        for( int i = 0; i < table.getRowCount(); i++)
        {
            print.write(table.getValueAt(i, 1).toString());
            System.out.println(table.getValueAt(i, 1).toString());
            print.write("\n");
        }
        print.flush();
    }

    // populates the Schedule of the Selected day on the Calendar
    public void popCalendSched(JTable table, int day) {
        // Daily schedule write
        for(int i = 0; i < 28; i++) {
            table.setValueAt(dailySchedTable.getValueAt(i,day+1),i,1);
        }
        
        // Events write
        loadDay( ( (Integer) thisCalendar.getValueAt(thisCalendar.getSelectedRow(),
            thisCalendar.getSelectedColumn() ) ).intValue(),
            neo.get(neo.MONTH),neo.get(neo.YEAR) );
        if(eventDay != null) {
            for(int i = 0; i < eventDay.length; i++) {
                String free = ""; 
                if(Boolean.parseBoolean(eventDay[i][4] ) )
                    free = "Free";
                else
                    free = "Busy";
                table.setValueAt(free,Integer.parseInt(eventDay[i][3]), 1);
            }
        }
        
        // Group events write
        appDay = my.appQuery(neo.get(neo.MONTH), ( (Integer) thisCalendar.getValueAt(thisCalendar.getSelectedRow(),
            thisCalendar.getSelectedColumn() ) ).intValue(), neo.get(neo.YEAR), groupID);
        if(appDay != null) {
            //System.out.println("appDay length: " + appDay.length);
            for(int i = 0; i < appDay.length; i++) {
                int run = Integer.parseInt(appDay[i][5] );
                //System.out.println("run: " + run);
                for(int j = 0; j < run; j++) {
                    //System.out.println("appDay: " + appDay[i][4]);
                    table.setValueAt(appDay[i][3], Integer.parseInt(appDay[i][4] )+j, 1);
                }
            }
        }
    }
    
    public void bigCheck() {
        int[] memberID = my.memberIDQuery(groupID);
        /*for(int i = 0; i < memberID.length; i++)
            System.out.print(memberID[i] + " ");
        System.out.println();*/
        //ArrayList<String[][]>[] al = new ArrayList<String[][]>[memberID.length];
        ArrayList[] al = new ArrayList[memberID.length];
        for(int i = 0; i < memberID.length; i++) {
            al[i] = new ArrayList<String[][]>();
            al[i].add(my.dailyQuery2(memberID[i]) );
            al[i].add(my.dayQuery(memberID[i], neo.get(neo.MONTH), 
                ( (Integer) 
                    thisCalendar.getValueAt(thisCalendar.getSelectedRow(),
                    thisCalendar.getSelectedColumn() ) 
                ).intValue(), neo.get(neo.YEAR) 
            ) );
            al[i].add(my.appQuery(neo.get(neo.MONTH), ( (Integer) 
                    thisCalendar.getValueAt(thisCalendar.getSelectedRow(),
                    thisCalendar.getSelectedColumn() ) 
                ).intValue(), neo.get(neo.YEAR), groupID) );
        }
        
        String[][] magic = new String[28][101];
        for(int i = 0; i < magic.length; i++) {
            magic[i][0] = i+"";
        }
        for(int i = 0; i < al.length; i++) { // loop through members
            System.out.println("i: " + i);
            for(int j = 0; j < al[i].size(); j++) { // loop through array list
                System.out.println("j: " + j);
                String[][] ngye = ((String[][]) al[i].get(j) );
                if(ngye == null)
                    continue;
                if(j == 0) { // if daily sched
                    for(int k = 0; k < ngye.length; k++) { // loops through class list
                        String daily = ngye[k][3];
                        if(daily.contains("" + thisCalendar.getSelectedColumn() ) ) {
                            int start = Integer.parseInt(ngye[k][0] );
                            int end = Integer.parseInt(ngye[k][1] );
                            for(int h = start; h <= end; h++) {
                                for(int g = 1; g < magic[h].length; g++) {
                                    if(magic[h][g] == null) {
                                        magic[h][g] = "" + memberID[i];
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                else if(j == 1) { // personal events
                    for(int k = 0; k < ngye.length; k++) { // loops through event list
                        int time = Integer.parseInt(ngye[k][3] );
                        System.out.println("time: " + time + ", " + ngye[k][4] );
                        if(ngye[k][4].equalsIgnoreCase("true") ) {
                            for(int h = 1; h < magic[time].length; h++) {
                                //magic[time][h] = null;
                                if(magic[time][h] == null || magic[time][h].equals(""+memberID[i] ) ) {
                                    magic[time][h] = null;
                                    break;
                                }
                            }
                        }
                        else {
                            for(int h = 1; h < magic[time].length; h++) {
                                if(magic[time][h] == null) {
                                    magic[time][h] = "" + memberID[i];
                                    System.out.println("pe, magic: " + magic[time][h]);
                                    break;
                                }
                            }
                        }
                    }
                }
                else {
                    for(int k = 0; k < ngye.length; k++) {
                        int time = Integer.parseInt(ngye[k][4] );
                        int noBlocks = Integer.parseInt(ngye[k][5] );
                        for(int h = 0; h < noBlocks; h++) {
                            for(int g = 1; g < magic[time+h].length; g++) {
                                if(magic[time+h][g] == null) {
                                    magic[time+h][g] = "" + memberID[i];
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        /*for(int i = 0; i < magic.length; i++) {
            for(int j = 0; j < magic[i].length; j++)
                System.out.print(magic[i][j] + " ");
            System.out.println();
        }*/
        String[][] opList = new String[28][101];
        
        AlgorithmFinal algo = new AlgorithmFinal();
        Stack<String[]> output = algo.Sorter(magic);
        /*for(int i=0; i<28;i++){
            String[] temp = output.pop();
            for(int ii=0;ii<temp.length;ii++){
                System.out.print(temp[ii]+" ");		
            }
            System.out.println();
        }*/
        String hold = "";
        for(int i = 0; i < 28; i++) {
            String[] temp = output.pop();
            switch(Integer.parseInt(temp[0] ) ) {
                case 0:
                    hold = hold + "\n7:00";
                    break;
                case 1:
                    hold = hold + "\n7:30";
                    break;
                case 2:
                    hold = hold + "\n8:00";
                    break;
                case 3:
                    hold = hold + "\n8:30";
                    break;
                case 4:
                    hold = hold + "\n9:00";
                    break;
                case 5:
                    hold = hold + "\n9:30";
                    break;
                case 6:
                    hold = hold + "\n10:00";
                    break;
                case 7:
                    hold = hold + "\n10:30";
                    break;
                case 8:
                    hold = hold + "\n11:00";
                    break;
                case 9:
                    hold = hold + "\n11:30";
                    break;
                case 10:
                    hold = hold + "\n12:00";
                    break;
                case 11:
                    hold = hold + "\n12:30";
                    break;
                case 12:
                    hold = hold + "\n13:00";
                    break;
                case 13:
                    hold = hold + "\n13:30";
                    break;
                case 14:
                    hold = hold + "\n14:00";
                    break;
                case 15:
                    hold = hold + "\n14:30";
                    break;
                case 16:
                    hold = hold + "\n15:00";
                    break;
                case 17:
                    hold = hold + "\n15:30";
                    break;
                case 18:
                    hold = hold + "\n16:00";
                    break;
                case 19:
                    hold = hold + "\n16:30";
                    break;
                case 20:
                    hold = hold + "\n17:00";
                    break;
                case 21:
                    hold = hold + "\n17:30";
                    break;
                case 22:
                    hold = hold + "\n18:00";
                    break;
                case 23:
                    hold = hold + "\n18:30";
                    break;
                case 24:
                    hold = hold + "\n19:00";
                    break;
                case 25:
                    hold = hold + "\n19:30";
                    break;
                case 26:
                    hold = hold + "\n20:00";
                    break;
                case 27:
                    hold = hold + "\n20:30";
                    break;
            }
            hold = hold + " - " + (temp.length-1) + " busy";
        }
        hold = hold.substring(hold.indexOf("\n")+1);
        freelist.setText(hold);
        freelist.setCaretPosition(0);
    }
    
    // Simply loads all of the personal events for the day
    public void loadDay(int day, int month, int year) {
        eventDay = my.dayQuery(Runner.idNo, month, day, year);
    }
    
    // sets an Appointment with the name inputted on the text field, 
    // and the time selected with the combo box
    public void setAppointment(JTable table, JComboBox start, JComboBox end, JTextField event) {
        int go = 1;
        int begin = start.getSelectedIndex();
        int finish = end.getSelectedIndex();
        for(int j = begin; j <= finish; j++)
        {
            //System.out.println("setAppoint j, 1: \"" + table.getValueAt(j, 1).toString() + "\"");
            if(!table.getValueAt(j, 1).toString().equals("Free") 
                    && !table.getValueAt(j, 1).toString().equals(" ") )
                go = 0;
        }
        if(go == 1)
        {
            for(int i = begin; i <= finish; i++)
            {
                //System.out.print(i + " " + end.getSelectedIndex());
                table.setValueAt(event.getText(), i, 1);
            }
            my.appUpdate( 
                ( (Integer) 
                    thisCalendar.getValueAt(thisCalendar.getSelectedRow(),
                    thisCalendar.getSelectedColumn() ) 
                ).intValue(), 
                neo.get(neo.MONTH), neo.get(neo.YEAR), event.getText(), begin, 
                finish-begin + 1, groupID
            );
        }
    }

    // sets the selected rows to busy
    public void mark(JTable table) {
        int[]index = table.getSelectedRows();
        for(int i = 0; i < index.length;i++)
        {
            table.setValueAt("Busy",index[i],1);
            my.markBusyFree( ( (Integer) thisCalendar.getValueAt(thisCalendar.getSelectedRow(),
            thisCalendar.getSelectedColumn() ) ).intValue(), neo.get(neo.MONTH), neo.get(neo.YEAR), index[i], false, Runner.idNo);
        }
    }

    // sets the selected Rows to Free
    public void free(JTable table) {
        int[]index = table.getSelectedRows();
        for(int i = 0; i < index.length;i++)
        {
            table.setValueAt("Free",index[i],1);
            my.markBusyFree( ( (Integer) thisCalendar.getValueAt(thisCalendar.getSelectedRow(),
            thisCalendar.getSelectedColumn() ) ).intValue(), neo.get(neo.MONTH), neo.get(neo.YEAR), index[i], true, Runner.idNo);
        }
    }
    
    // sets the title for the label
    public String setTitle(Calendar calend, int select) {
        neo.add(neo.MONTH,select);
        int tempCurrMonth = neo.get(neo.MONTH);
        switch(tempCurrMonth) {
            case 0:
            month = "January";
            break;
        case 1:
            month = "February";
            break;
        case 2:
            month = "March";
            break;
        case 3:
            month = "April";
            break;
        case 4:
            month = "May";
            break;
        case 5:
            month = "June";
            break;
        case 6:
            month = "July";
            break;
        case 7:
            month = "August";
            break;
        case 8:
            month = "September";
            break;
        case 9:
            month = "October";
            break;
        case 10:
            month = "November";
            break;
        case 11:
            month = "December";
            break;
        }
        for(int i = 0; i < 7; i++) { // sets the top and bottom of the calendar 
                                     // to null allows repaint to occur with 
                                     // new values
            for(int j = 0; j < 6; j++) {
                thisCalendar.setValueAt(null, j, i);
            }
        }
        calendarPopulation(neo, thisCalendar);
        return (month + " " + neo.get(neo.YEAR) );
    }
}
