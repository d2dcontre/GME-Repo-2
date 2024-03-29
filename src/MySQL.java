import java.io.PrintStream;
import java.sql.*;

public class MySQL {
    
    private PrintStream out = System.out;
    private Connection con;
    private ResultSet rs;
    private PreparedStatement s;
    private TransferStuff ts;
    private int rowRet;
    
    public MySQL() {
        out.println("DB Start!");

        con = null;
        try { // Connects to the DB
            out.println("DB set up try");
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/newtest", "root", "root");
        } catch(Exception e) {
            e.getMessage();
        }
    }
    
    // Designed to safely close the DB connection upon exit
    public void close() {
        try {
            if(con != null) {
                con.close();}
            if(rs != null) {
                rs.close(); }
            out.println("closed");
        } catch (SQLException ex) {
            out.println("closed error");
        }
    }
    
    // Non-important stream closing for safety
    public void microClose() {
        try {
            if(s != null)
                s.close();
            if(rs != null)
                rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    // Prepares the statement to be queried
    /* NOTE: for the queries, the each index of the string array represents
     * each '?' in the prepared statement, in the order that the question
     * marks appear. 
     * E.g. select * from groupdata where ? = ?;
     * String arr[] = {"name","jimmy"};
     */
    private void prepare(String[] arr, String query, boolean isQuery) {
        try {
            s = con.prepareStatement(query);
            for(int i = 0; i < arr.length; i++) {
                s.setString(i+1, arr[i] );
            }
            if(isQuery)
                rs = s.executeQuery();
            else
                rowRet = s.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    // Handles the login query that checks if a user already exists
    public TransferStuff loginQuery(String user, String pass) {
        ts = null;
        String query = "select id from UserData where user = ? and pass = ?"; // Queries ID of valid user
        try {
            String[] arr = {user,pass};
            prepare(arr, query, true);
            if (rs.next() ) { // get the user id
                ts = new TransferStuff();
                ts.id = rs.getInt("id");
                System.out.println("Welcome to the Program!");
                microClose();
                groupQuery(ts.id);
                groupPopulate();
            } else {
                System.out.println("Incorrect username and/or password.");
            }
        } catch (SQLException sQLException) {
            System.out.println(sQLException.getMessage() );
        }
        microClose();
        return ts;
    }
    
    // Queries groupMembers
    public void groupQuery(int idNo) {
        String query = "select GroupData.GroupID,GroupData.GroupName from GroupData " // Queries GroupID and GroupNames of groups valid user belongs to
        + "join GroupPerson on GroupPerson.GroupID = GroupData.GroupID "
        + "join UserData on GroupPerson.UserID = UserData.id where id = ?";
        String[] arr = {"" + idNo};
        prepare(arr, query, true);
    }
    
    // http://stackoverflow.com/questions/9993972/populating-an-array-from-resultset-sql-results
    public void groupPopulate() {
        int rows = getRowCount(rs);
        System.out.println("No. Rows of Result Set: " + rows);
        try {
            if (rows > 0) {
                ts.groupID = new int[rows];
                ts.groupName = new String[rows];
                rs.next();
                for (int i = 0; i < rows; i++) {
                    ts.groupID[i] = rs.getInt("GroupID");
                    ts.groupName[i] = rs.getString("GroupName");
                    rs.next(); // necessary to move pointer
                }
            }
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
    }
    
    // Returns the name of the student who has the ID No
    public String registerQuery(int userID) {
        String ret = null;
        
        String queryQ = "select name from IdData where id = ?";
        try{
            String arr[] = {""+userID};
            prepare(arr, queryQ, true);
            int rowCount = getRowCount(rs);
            if(rowCount > 0) {
                rs.next();
                ret = rs.getString("name");
            } else {
                System.out.println("No person of that ID");
            }
        }
        catch(Exception e){
                System.out.println("Error");
                e.printStackTrace();
                System.exit(0);
        }
        microClose();
        return ret;
    }
    
    // Checks if a username already exists
    public boolean registerCheck(String username) {
        boolean check = false;
        String query = "SELECT user FROM UserData WHERE user = ?";
        String[] arr = {username};
        try {
            prepare(arr, query, true);
            if(rs.next() ) {
                check = true;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return check;
    }
    
    // SOURCE: http://www.coderanch.com/t/303346/JDBC/databases/find-number-rows-resultset
    public static int getRowCount(ResultSet set) {
        int rowCount = 1;
        try {
            int currentRow = set.getRow();            // Get current row  
            rowCount = set.last() ? set.getRow() : 0; // Determine number of rows  
            if (currentRow == 0) // If there was no current row  
            {
                set.beforeFirst();                     // We want next() to go to first row  
            } else // If there WAS a current row  
            {
                set.absolute(currentRow);              // Restore it  
            }
        } catch (SQLException sQLException) {
            sQLException.getMessage();
            sQLException.printStackTrace();
        }
        return rowCount;
    }
    
    // Registers a new user
    public int registerExecute(String id, String user, String pass, String email, String mobile,String home, String address) {
        rowRet = 0;
        String updateQ = "insert into UserData(id,user,pass,email,mobnum,homenum,address)"
                             + "values (?, ?, ?, ?, ?, ?, ?)";
        String[] arr = {id,user,pass,email,mobile,home,address};
        prepare(arr, updateQ, false);
        if(rowRet == 0)
            System.out.println("No Update Made, error");
        else
            System.out.println(rowRet + " rows modified");
        microClose();
        return rowRet;
    }
    
    public int[] memberIDQuery(int groupid) {
        int[] ret = null;
        String query = "select IdData.id from IdData " +
        "join GroupPerson on GroupPerson.UserID = IdData.id " +
        "join GroupData on GroupData.GroupID = GroupPerson.GroupID " +
        "where GroupData.GroupID = ?";
        String arr[] = {""+groupid};
        prepare(arr, query, true);
        int rowCount = getRowCount(rs);
        try {
            if(rowCount > 0) {
                out.println("Members found and being stored");
                ret = new int[rowCount];
                int i = 0;
                while(rs.next() ) {
                    ret[i] = rs.getInt("id");
                    i++;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    
    // Returns all of the members of a particular group
    public String[] memberQuery(int groupid) {
        String[] gMembers = null;
        String query = "select IdData.name from IdData " +
        "join GroupPerson on GroupPerson.UserID = IdData.id " +
        "join GroupData on GroupData.GroupID = GroupPerson.GroupID " +
        "where GroupData.GroupID = ?";
        out.println("GroupID: " + groupid);
        try {
            out.println("Running member list query");
            String arr[] = {""+groupid};
            prepare(arr, query, true);
            int rowCount = getRowCount(rs);
            if(rowCount > 0) {
                out.println("Members found and being stored");
                gMembers = new String[rowCount];
                int i = 0;
                while(rs.next() ) {
                    gMembers[i] = rs.getString("name");
                    i++;
                }
            }
        } catch(Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        microClose();
        return gMembers;
    }
    
    // Connects a user to a new group
    public TransferStuff joinGroup(int userid, int groupid) {
        System.out.println(groupid);
        String query = "insert into GroupPerson(UserID,GroupID) values (?, ?)";
        ts = null;
        try {
            String arr[] = {""+userid,""+groupid};
            prepare(arr, query, false);
            if(rowRet == 0)
                out.println("No update made. Error.");
            else {
                out.println(rowRet + " rows modified.");
                ts = new TransferStuff();
                groupQuery(userid);
                groupPopulate();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        microClose();
        return ts;
    }
    
    public boolean joinCheck(String gid, String userid) {
        boolean check = false;
        String query = "SELECT GroupID FROM GroupPerson WHERE GroupID = ? AND UserID = ?";
        String[] arr = {gid,userid};
        try {
            prepare(arr, query, true);
            if(rs.next() )
                check = true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        microClose();
        return check;
    }
    
    // creates a group and connects the user to the group
    public TransferStuff createGroup(String name) {
        String query = "insert into GroupData(GroupName) values (?)";
        try {
            String arr[] = {name};
            prepare(arr, query, false); // Create group
            if(rowRet == 0)
                out.println("No update made. Error.");
            else {
                out.println(rowRet + " rows modified.");
                query = "select GroupID from GroupData where GroupName = ? order by GroupID desc";
                prepare(arr, query, true); // Get new GroupID
                if(rs.next() ) {
                    int gid = rs.getInt(1);
                    String[] arr2 = {""+Runner.idNo,""+gid};
                    query = "insert into GroupPerson(UserID,GroupID) values (?,?)";
                    prepare(arr2, query, false); // Create connection to new group
                    if(rowRet > 0) {
                        groupQuery(Runner.idNo);
                        groupPopulate();
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        microClose();
        return ts;
    }
    
    public void editInfo(String password, String email, String mobile, String landline, String address, int id) {
        String update = "UPDATE UserData SET pass = ?, email = ?, mobnum = ?, homenum = ?, address = ? WHERE UserData.id = ?";
        String arr[] = {password,email,mobile,landline,address,""+id};
        prepare(arr, update, false);
        microClose();
    }
    
    public String[] editQuery(int idNo) {
        String ret[] = null;
        String query = "select pass, email, mobnum, homenum, address from UserData where id = ?";
        String arr[] = {""+idNo};
        prepare(arr, query, true);
        try {
            int rowCount = getRowCount(rs);
            if (rowCount > 0) {
                rs.next();
                ret = new String[5];
                for(int col = 1; col <= 5; col++) {
                    ret[col - 1] = rs.getString(col);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        microClose();
        return ret;
    }
    
    public String[] dailyQuery(int id) {
        String arr[] = null; // Int-start Int-end String-name daysOfWeek{"1 3 5"}
        String query = "SELECT classStart, classEnd, className, dayOfWeek FROM UserSched WHERE UserID = ?";
        String temp[] = {""+id};
        prepare(temp, query, true);
        try {
            int rowCount = getRowCount(rs);
            out.println("DQ rowCount: " + rowCount);
            if (rowCount > 0) {
                arr = new String[rowCount];
                rs.next();
                for(int j = 0; j < rowCount; j++) {
                    arr[j] = rs.getInt(1) + " " + rs.getInt(2) + " "
                            + rs.getString(3) + " " + rs.getString(4);
                    out.println("dq arr[j]: " + arr[j] );
                    rs.next();
                }
            } else {
            }
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
        microClose();
        return arr;
    }
    
    public String[][] dailyQuery2(int id) {
        String arr[][] = null; // Int-start Int-end String-name daysOfWeek{"1 3 5"}
        String query = "SELECT classStart, classEnd, className, dayOfWeek FROM UserSched WHERE UserID = ?";
        String temp[] = {""+id};
        prepare(temp, query, true);
        try {
            int rowCount = getRowCount(rs);
            out.println("DQ rowCount: " + rowCount);
            if (rowCount > 0) {
                arr = new String[rowCount][4];
                rs.next();
                for(int j = 0; j < rowCount; j++) {
                    /*arr[j] = rs.getInt(1) + " " + rs.getInt(2) + " "
                            + rs.getString(3) + " " + rs.getString(4);*/
                    for(int i = 0; i < 4; i++) {
                        if(i < 2)
                            arr[j][i] = "" + rs.getInt(i+1);
                        else
                            arr[j][i] = "" + rs.getString(i+1);
                    }
                            
                    //out.println("dq arr[j]: " + arr[j] );
                    rs.next();
                }
            } else {
            }
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
        microClose();
        return arr;
    }
    
    public String[][] dayQuery(int id, int month, int day, int year) {
        System.out.println(id + " " + month + " " + day + " " + year);
        String[][] ret = null;
        String query = "SELECT dayOfMon, month, year, timeBlock, free" 
                + " FROM EventData WHERE UserID = ? AND month = ?" 
                + " AND dayOfMon = ? AND year = ? ORDER BY timeBlock";
        String arr[] = {""+id,""+month,""+day,""+year};
        prepare(arr, query, true);
        try {
            int rowCount = getRowCount(rs);
            if(rowCount > 0) {
                ret = new String[rowCount][5];
                rs.next();
                for(int j = 0; j < rowCount; j++) {
                    for(int i = 1; i <= 5; i++) {
                        if (i < 5) {
                            ret[j][i - 1] = "" + rs.getInt(i);
                        } else {
                            ret[j][i - 1] = "" + rs.getBoolean(i);
                        }
                    }
                    rs.next();
                }
            }
            else {
                out.println("dayQuery: No result");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        microClose();
        return ret;
    }
    
    public String[][] appQuery(int month, int day, int year, int groupid) {
        String[][] ret = null;
        String query = "SELECT dayOfMon, month, year, name, timeStart, noBlocks FROM "
                + "AppData WHERE month = ? AND dayOfMon = ? AND year = ? AND GroupID = ? "
                + "ORDER BY timeStart";
        String arr[] = {""+month,""+day,""+year,""+groupid};
        prepare(arr, query, true);
        try {
            int rowCount = getRowCount(rs);
            if(rowCount > 0) {
                ret = new String[rowCount][6];
                rs.next();
                for(int i = 0; i < rowCount; i++) {
                    for(int j = 1; j <= 6; j++) {
                        if(j != 4)
                            ret[i][j-1] = "" + rs.getInt(j);
                        else
                            ret[i][j-1] = rs.getString(j);
                    }
                    rs.next();
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        microClose();
        return ret;
    }
    
    public int appUpdate(int dayOfMon, int month, int year, String name,
            int timeStart, int noBlocks, int GroupID) {
        rowRet = -1;
        String update = "INSERT INTO AppData(dayOfMon,month,year,name,timeStart,noBlocks,GroupID) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";
        String arr[] = {""+dayOfMon,""+month,""+year,name,""+timeStart,""+noBlocks,""+GroupID};
        prepare(arr, update, false);
        return rowRet;
    }
    
    public void markBusyFree(int day, int month, int year, int block, boolean free, int userid) {
        String update = "INSERT INTO EventData(dayOfMon,month,year,timeBlock,free,UserID) "
                + "VALUES(?, ?, ?, ?, ?, ?)";
        int freeC = -1;
        if(free)
            freeC = 1;
        else
            freeC = 0;
        String arr[] = {""+day,""+month,""+year,""+block,""+freeC,""+userid};
        prepare(arr, update, false);
    }
    
    public void dailyUpdate(int start, int end, String name, String dayOfWeek, int UserID) {
        String update = "insert into UserSched(classStart,classEnd,className,dayOfWeek,UserID) "
        + "values (?, ?, ?, ?, ?)";
        String arr[] = {""+start,""+end, name, dayOfWeek, ""+UserID};
        prepare(arr, update, false);
    }
}