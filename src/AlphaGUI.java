import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import javax.swing.AbstractAction;
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
    private MySQL my;
    public int rowCountAdd;
    public JPanel contentPane;
    public JTable dailySchedTable;
    public AlphaSQL sql;
    public JLabel dailySchedLabel;
    private JTable thisCalendar;
    private JTable nextCalendarTable;
    private JButton nextCheckButton;
    private JButton nextEditButton;
    private JButton thisEditButton;
    public FileReader read;
    public Scanner in;
    public PrintWriter pr;
    private JPanel thisPanel1;
    private JPanel thisPanel2;
    private JLabel thisDate;
    private JButton thisSave;
    private JLabel thisFree;
    private JTable nextSchedTable;
    private JScrollPane scrollPane_2;
    private JPanel thisMonth;
    private CardLayout sample;
    private JPanel nextPanel2;
    private JLabel nextDate;
    private JButton nextSave;
    private JLabel nextFree;
    private Calendar neo;
    private String month;
    private JTable thisSchedTable;
    private JScrollPane scrollPane;
    public JComboBox thisStart; 
    public JComboBox thisEnd;
    public String filename;
    private JTable thisCheckTable;
    private JTextField thisEventText;
	/**
	 * Launch the application.
	 */
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
        
    public void setMySQL(MySQL my) {
        this.my = my;
    }

    /**
     * Create the frame.
     */
    public AlphaGUI() {
        neo = new GregorianCalendar();						// instantiate a new calendar
        neo = Calendar.getInstance();						// get local time
        int currMonth = neo.get(neo.MONTH);
        System.out.println(currMonth);
        /*if(currMonth == 8)
        {
            month = "September";
        }
        */
        try {
            read = new FileReader("FT.txt");
            System.out.print("success2");	
            in = new Scanner(read);
            System.out.print("success");		
        }
        catch(Exception e)
        {	
            System.out.print("ERROR");		
        }
        switch(currMonth){
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
        rowCountAdd = 0;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 797, 602);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        sql = new AlphaSQL("pawnshop");

        final JTabbedPane MainTabs = new JTabbedPane(JTabbedPane.TOP);
        MainTabs.setFont(new Font("Calibri", MainTabs.getFont().getStyle(), MainTabs.getFont().getSize()));
        MainTabs.setBounds(0, 0, 781, 564);
        contentPane.add(MainTabs);

        JPanel Appraisal = new JPanel();
        MainTabs.addTab("Daily Schedule", null, Appraisal, null);
        Appraisal.setLayout(null);

        JScrollPane scheduleScrollPane = new JScrollPane();
        scheduleScrollPane.setBounds(0, 23, 776, 440);
        Appraisal.add(scheduleScrollPane);

        dailySchedTable = new JTable();							//table for the daily schedule
        dailySchedTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {

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
                "Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
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

        dailySchedLabel = new JLabel("Right Click to Highlight\r\n");
        dailySchedLabel.setForeground(UIManager.getColor("CheckBox.highlight"));
        dailySchedLabel.setBackground(UIManager.getColor("CheckBox.darkShadow"));
        dailySchedLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        dailySchedLabel.setBounds(0, 0, 776, 23);
        Appraisal.add(dailySchedLabel);

        //Populating the Table
        popDaily(dailySchedTable,in);

        JButton dailySchedSave = new JButton("Save Changes\r\n");
        dailySchedSave.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent arg0)
            {
                // Updating the DB
                editDaily(dailySchedTable, pr);
                /*String input = searchFieldA.getText();
                String choice = ChoiceBoxA.getSelectedItem().toString();
                System.out.println( input + " " + choice);
                TableFiller fill = sql.setTableAppraisal(choice,input);
                if(choice == "Description")
                {
                        SearchResultTitleA.setText("Search Result For " + input);
                }
                else
                {
                        SearchResultTitleA.setText("Search Result For " + input + " Risk Level");
                }
                if(fill != null)
                {
                        ResultTableA.setModel(fill);
                        ResultTableA.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                        ResultTableA.getColumnModel().getColumn(0).setHeaderValue("Item #");
                        ResultTableA.getColumnModel().getColumn(1).setHeaderValue("Category");
                        ResultTableA.getColumnModel().getColumn(2).setHeaderValue("Description");
                        ResultTableA.getColumnModel().getColumn(3).setHeaderValue("Risk Level");
                        ResultTableA.getColumnModel().getColumn(4).setHeaderValue("Amount");
                }
                */
            }
        });
        dailySchedSave.setFont(new Font("Calibri", Font.PLAIN, 11));
        dailySchedSave.setBounds(302, 485, 170, 28);
        Appraisal.add(dailySchedSave);

        thisMonth = new JPanel(new CardLayout(0,0));						//tab for This Month with a card layout
        MainTabs.addTab("This Month\r\n", null, thisMonth, null);
        MainTabs.setEnabledAt(1, true);

        thisPanel1 = new JPanel();											//first panel for card layout
        thisMonth.add(thisPanel1);
        thisPanel1.setLayout(null);

        JScrollPane thisCalendarScroll = new JScrollPane();
        thisCalendarScroll.setBounds(101, 5, 452, 266);
        thisPanel1.add(thisCalendarScroll);

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

        thisEditButton = new JButton("Edit");								//button for going to the 2nd panel of the card layout w/editing available
        thisEditButton.setBounds(563, 207, 89, 23);
        thisPanel1.add(thisEditButton);

        JButton thisCheckButton = new JButton("Check");
        thisCheckButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(thisCalendar.getValueAt(thisCalendar.getSelectedRow(),thisCalendar.getSelectedColumn()) != null)
                {
                    int day = (int)thisCalendar.getValueAt(thisCalendar.getSelectedRow(),thisCalendar.getSelectedColumn());
                    filename = (month + " " + day +".txt");
                    popCalendSched(thisCheckTable,in,filename,thisCalendar.getSelectedColumn());
                }
            }
        });
        thisCheckButton.setBounds(563, 247, 89, 23);
        thisPanel1.add(thisCheckButton);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(104, 307, 449, 218);
        thisPanel1.add(scrollPane_1);

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
        thisEditButton.addActionListener(new editAction());

        thisPanel2 = new JPanel();											//2nd panel of the card layout; responsible for getting the status of the date selected in the calendar
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
        //thisMonth.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{thisPanel1, thisPanel2}));


        JPanel nextMonth = new JPanel();
        MainTabs.addTab("Next Month", null, nextMonth, null);
        neo.add(neo.MONTH,1);
        nextMonth.setLayout(new CardLayout(0, 0));
        neo.getInstance();

        JPanel nextPanel1 = new JPanel();
        nextMonth.add(nextPanel1);

        JScrollPane nextCalendarScroll = new JScrollPane();
        nextPanel1.add(nextCalendarScroll);

        nextCalendarTable = new JTable();
        nextCalendarTable.setModel(new DefaultTableModel(
            new Object[][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
            },
            new String[] {
                "Sunday", "Monday", "Tuseday", "Wednesday", "Thursday", "Friday", "Saturday"
            }
        ));
        calendarPopulation(neo,nextCalendarTable);
        nextCalendarScroll.setViewportView(nextCalendarTable);
        nextCalendarTable.setRowHeight(40);
        nextCalendarTable.setColumnSelectionAllowed(true);
        nextCalendarTable.setCellSelectionEnabled(true);


        nextEditButton = new JButton("Edit");
        nextPanel1.add(nextEditButton);

        nextCheckButton = new JButton("Check");
        nextPanel1.add(nextCheckButton);

        nextPanel2 = new JPanel();
        nextPanel2.setLayout(null);
        nextMonth.add(nextPanel2, "name_1618371576471");

        nextDate = new JLabel("DATE:");
        nextDate.setBounds(0, 0, 333, 14);
        nextPanel2.add(nextDate);

        nextSave = new JButton("Save Changes\r\n");
        nextSave.setFont(new Font("Calibri", Font.PLAIN, 11));
        nextSave.setEnabled(false);
        nextSave.setBounds(291, 469, 170, 28);
        nextPanel2.add(nextSave);

        scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(213, 11, 427, 302);
        nextPanel2.add(scrollPane_2);

        nextSchedTable = new JTable();
        nextSchedTable.setModel(new DefaultTableModel(
            new Object[][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
            },
            new String[] {
                "Times", "Status"
            }
        ));


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
        scrollPane_2.setViewportView(nextSchedTable);
        nextSchedTable.setFillsViewportHeight(true);

        nextFree = new JLabel("The Free times are:");
        nextFree.setVerticalAlignment(SwingConstants.TOP);
        nextFree.setBounds(30, 357, 681, 91);
        nextPanel2.add(nextFree);
        nextCheckButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        nextEditButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
    }
    
    public void getMin(Scanner scan,JLabel label)
    {
        String init = label.getText();
        int min=100;
        while(scan.hasNextLine())
        {
                String day = scan.nextLine();
                String names = scan.nextLine();
                if(names.equals("") && min == 0)
                {
                        label.setText(label.getText() + "\n" + day);
                        }
                else if(names.equals(""))
                {
                        min = 0;
                        label.setText(init + "\n" + day);
                }
                else
                {	
                        int temp = 1;
                        for(int i = 0; i < names.length(); i++ )
                        {

                                if(names.charAt(i) == ',')
                                {
                                        temp++;
                                }
                        }
                        if (temp == min)
                        {
                                label.setText(label.getText() + "\n" + day);
                        }
                        else if(temp < min)
                        {
                                min = temp;
                                label.setText(init + "\n" + day);
                        }
                }
        }
        System.out.print("got here");
    }


    //populates the Calendar
    public void calendarPopulation(Calendar cal, JTable tabel)
    {
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


    private class SwingAction extends AbstractAction {
        public SwingAction() {
            putValue(NAME, "SwingAction");
            putValue(SHORT_DESCRIPTION, "Some short description");
        }
        public void actionPerformed(ActionEvent e) {
        }
    }


    private class editAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            if(thisCalendar.getValueAt(thisCalendar.getSelectedRow(),thisCalendar.getSelectedColumn()) != null)
            {
                int day = (int)thisCalendar.getValueAt(thisCalendar.getSelectedRow(),thisCalendar.getSelectedColumn());
                sample = (CardLayout) thisMonth.getLayout();
                sample.next(thisMonth);
                thisDate.setText("DATE: "  + month + " " + day);
                filename = (month + " " + day +".txt");
                popCalendSched(thisSchedTable,in,filename,thisCalendar.getSelectedColumn());
            }
        }
    }


    //saves the changes in the daily Sched
    public void editDaily(JTable table,PrintWriter print)
    {
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
    public void popDaily(JTable table, Scanner scan)
    {
        try {
            in = new Scanner(new FileReader("Daily Schedule.txt") ); // Load file
        }
        catch(Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
        }
        int limit = Integer.parseInt(in.nextLine() ); // read whole line, first line is number of classes
        // each row below: startTime endTime className 0(Mon) 1(Tue) 2(Wed) 3(Thu) 4(Fri) 5(Sat)
        for(int i = 0; i < limit; i++) {
            String[] temp = in.nextLine().split(" ");
            int begin = Integer.parseInt(temp[0] );
            int end = Integer.parseInt(temp[1] );
            String name = temp[2];
            int days = temp.length - 3;
            for(int j = 0; j < days; j++) {									//Old Code
                /*days[j] = Integer.parseInt(temp[j + 3] );
                System.out.println(days[j] );*/
                int day = Integer.parseInt(temp[j+3] ) + 1;
                for(int k = begin; k <= end; k++) {
                    dailySchedTable.setValueAt(name, k, day);
                }
            }
        }
        
        /*for( int i = 0; i < table.getRowCount(); i++)
        {
            String temp[] = in.nextLine().split(" ");
            for(int j = 1; j < table.getColumnCount(); j++)
            {
                if(temp[j-1] != "^^&*")
                {	
                    table.setValueAt(temp[j-1], i, j);
                }
                else
                {
                    table.setValueAt(" ", i, j);
                }
            }
        }*/
    }


    //saves the changes in the Schedule of the selected day of the Calendar
    public void editCalendSched(JTable table, PrintWriter print, String filename )
    {
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
    public void popCalendSched(JTable table, Scanner scan, String filename,int date)
    {
        try {
            in = new Scanner(new FileReader(filename) ); // Load file
            for(int i = 0; i < 28; i++) 
            {
                String temp = in.next();
                if(temp == " ")
                {
                    table.setValueAt(temp, i, 1);}
                else {
                    table.setValueAt("Free",i,1);
                }
            }
        }
        catch(Exception e) 
        {
            for(int i = 0; i <28; i++)
            {
                String temp = dailySchedTable.getValueAt(i,date).toString();
                if(temp == " ") {
                    table.setValueAt("Free",i,1);
                }
                else {
                    table.setValueAt(dailySchedTable.getValueAt(i, date), i, 1);
                }
            }
            System.out.println("Exception");
            e.printStackTrace();
        }
    }


    //sets an Appointment with the name inputted on the text field, and the time selected with the combo box
    public void setAppointment(JTable table, JComboBox start, JComboBox end, JTextField event)
    {
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
    public void mark(JTable table)
    {
        int[]index = table.getSelectedRows();
        for(int i = 0; i < index.length;i++)
        {
            table.setValueAt("Busy",index[i],1);
        }
    }


    //sets the selected Rows to Free
    public void free(JTable table)
    {
        int[]index = table.getSelectedRows();
        for(int i = 0; i < index.length;i++)
        {
            table.setValueAt("Free",index[i],1);
        }
    }
}