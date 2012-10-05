import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
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
    private String[] classSched;
    
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
        MainTabs.setFont(new Font("Calibri", MainTabs.getFont().getStyle(), MainTabs.getFont().getSize()));
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
        System.out.println(currMonth);
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                "Time", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
            }
        ) {
            Class[] columnTypes = new Class[] {
                String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
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
        Appraisal.add(dailySchedSave);
    }
    
    // Builds the calendar tab
    public void buildCalendar(JTabbedPane MainTabs) {
        // Build Tab
        thisMonth = new JPanel(new CardLayout(0,0)); //tab for This Month with a card layout
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
                "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
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
        thisEditButton = new JButton("Edit"); //button for going to the 2nd panel of the card layout w/editing available
        thisEditButton.setBounds(563, 207, 89, 23);
        thisPanel1.add(thisEditButton);
        
        // Adds a check button to display the schedule for the day without editing
        JButton thisCheckButton = new JButton("Check");
        thisCheckButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(thisCalendar.getValueAt(thisCalendar.getSelectedRow(),thisCalendar.getSelectedColumn()) != null) {
                    //int day = (Integer)thisCalendar.getValueAt(thisCalendar.getSelectedRow(),thisCalendar.getSelectedColumn());
                    //filename = (month + " " + day +".txt");
                    //popCalendSched(thisCheckTable,in,filename,thisCalendar.getSelectedColumn());
                    popCalendSched(thisCheckTable,thisCalendar.getSelectedColumn() );
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
        thisPanel2 = new JPanel(); //2nd panel of the card layout; responsible for getting the status of the date selected in the calendar
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
		
        thisSave = new JButton("Save Changes\r\n");							//button for saving changes
        thisSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editCalendSched(thisSchedTable, pr, filename);
            }
        });
        thisSave.setFont(new Font("Calibri", Font.PLAIN, 11));
        thisSave.setBounds(284, 444, 170, 28);
        thisPanel2.add(thisSave);	
        thisFree = new JLabel("The Free times are:");
        thisFree.setVerticalAlignment(SwingConstants.TOP);
        thisFree.setBounds(40, 170, 111, 91);
        thisPanel2.add(thisFree);
		
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
        thisStart.setModel(new DefaultComboBoxModel(new String[] {"7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30"}));
        thisStart.setBounds(65, 366, 61, 20);
        thisPanel2.add(thisStart);

        thisEnd = new JComboBox();
        thisEnd.setModel(new DefaultComboBoxModel(new String[] {"7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00"}));
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
            if(thisCalendar.getValueAt(thisCalendar.getSelectedRow(),thisCalendar.getSelectedColumn()) != null)
            {
                int day = (Integer)thisCalendar.getValueAt(thisCalendar.getSelectedRow(),thisCalendar.getSelectedColumn());
                sample = (CardLayout) thisMonth.getLayout();
                sample.next(thisMonth);
                thisDate.setText("DATE: "  + month + " " + day);
                filename = (month + " " + day +".txt");
                //popCalendSched(thisSchedTable,in,filename,thisCalendar.getSelectedColumn());
                popCalendSched(thisSchedTable,thisCalendar.getSelectedColumn() );
            }
        }
    }

    //saves the changes in the daily Sched
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
	
    //populates the dailySched with the times in the text file
    public void popDaily(JTable table) {
        //String[] dailyClasses = my.
        if(classSched != null) {
            for(int i = 0; i < classSched.length; i++) {
                String[] temp = classSched[i].split(" ");
                for(int k = 0; k < temp.length; k++) {
                    System.out.print(temp[k] + " ");
                }
                System.out.println();
                int begin = Integer.parseInt(temp[0] );
                int end = Integer.parseInt(temp[1] );
                System.out.println("begin: " + begin + ", end: " + end);
                String name = temp[2];
                int days = temp.length - 3;
                for(int j = 0; j < days; j++) {
                    int day = Integer.parseInt(temp[j+3] ) + 1;
                    System.out.println("Day: " + day);
                    for(int k = begin; k <= end; k++) {
                        dailySchedTable.setValueAt(name, k, day);
                    }
                }
            }
        }
        
        //<editor-fold defaultstate="collapsed" desc="Old Println Style">
        /*try {
         * Scanner reading = new Scanner(new FileReader("Daily Schedule.txt") ); // Load file
         * int limit = Integer.parseInt(reading.nextLine() ); // read whole line, first line is number of classes
         * // each row below: startTime endTime className 0(Mon) 1(Tue) 2(Wed) 3(Thu) 4(Fri) 5(Sat)
         * for(int i = 0; i < limit; i++) {
         *  String[] temp = reading.nextLine().split(" ");
         *  int begin = Integer.parseInt(temp[0] );
         *  int end = Integer.parseInt(temp[1] );
         *  String name = temp[2];
         *  int days = temp.length - 3;
         *      for(int j = 0; j < days; j++) {
         *          int day = Integer.parseInt(temp[j+3] ) + 1;
         *          for(int k = begin; k <= end; k++) {
         *              dailySchedTable.setValueAt(name, k, day);
         *          }
         *      }
         * }
         * }
         * catch(Exception e) {
         * System.out.println("Exception");
         * e.printStackTrace();
         * }*/
        //</editor-fold>
    }
			
    //saves the changes in the Schedule of the selected day of the Calendar
    public void editCalendSched(JTable table, PrintWriter print, String filename ) {
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

    //populates the Schedule of the Selected day on the Calendar
    public void popCalendSched(JTable table, int day) {
        for(int i = 0; i < 28; i++) {
            table.setValueAt(dailySchedTable.getValueAt(i,day+1),i,1);
        }
    }
    //<editor-fold defaultstate="collapsed" desc="Old popCalendSched method">
    /*public void popCalendSched(JTable table, Scanner scan, String filename,int date) {
        try {
            in = new Scanner(new FileReader(filename) ); // Load file
            for(int i = 0; i < 28; i++) 
            {
                String temp = in.next();
                table.setValueAt(temp, i, 1);
            }
        }
        catch(Exception e) 
        {
            for(int i = 0; i <28; i++)
            {
                table.setValueAt(dailySchedTable.getValueAt(i, date), i, 1);
            }
            System.out.println("Exception");
            e.printStackTrace();
        }
    }*/
    //</editor-fold>

    //sets an Appointment with the name inputted on the text field, and the time selected with the combo box
    public void setAppointment(JTable table, JComboBox start, JComboBox end, JTextField event) {
        int go = 1;
        for(int j = start.getSelectedIndex(); j <= end.getSelectedIndex(); j++)
        {
            if(table.getValueAt(j, 1).toString() != "Free" )
                go = 0;
        }
        if(go == 1)
        {
            for(int i = start.getSelectedIndex(); i <= end.getSelectedIndex(); i++)
            {
                System.out.print(i + " " + end.getSelectedIndex());
                table.setValueAt(event.getText(), i, 1);
            }
        }
    }

    //sets the selected rows to busy
    public void mark(JTable table) {
        int[]index = table.getSelectedRows();
        for(int i = 0; i < index.length;i++)
        {
            table.setValueAt("Busy",index[i],1);
        }
    }

    //sets the selected Rows to Free
    public void free(JTable table) {
        int[]index = table.getSelectedRows();
        for(int i = 0; i < index.length;i++)
        {
                table.setValueAt("Free",index[i],1);
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
        for(int i = 0; i < 7; i++) { // sets the top and bottom of the calendar to null
                                     // allows repaint to occur with new values
            for(int j = 0; j < 6; j++) {
                thisCalendar.setValueAt(null, j, i);
            }
        }
        calendarPopulation(neo, thisCalendar);
        return (month + " " + neo.get(neo.YEAR) );
    }
}