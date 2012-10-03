import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.*;
public class AlphaSQL
{
	String dbName;
	Connection connection;
	Statement statement;
	ResultSet result;
	String queryString;
	public AlphaSQL(String db)
	{
		/*dbName = db;
		String connectionURL ="jdbc:mysql://localhost:3306/"+db;
		statement = null;
		try
		{
		System.out.println("Started..");*/
		//Class.forName("com.mysql.jdbc.Driver").newInstance();
		/*Class.forName("com.mysql.jdbc.Driver").newInstance();
		System.out.println("1");
		connection = DriverManager.getConnection(connectionURL, "root", "root");
		System.out.println("2");
		statement = connection.createStatement();
		System.out.println("connected");
		}
		catch(Exception ex)
		{
			System.out.println("Error in Initialization");
			
		}
		*/
		
	}
	
	public String createAccount(String lastName,String mi,String  firstName, String blk, String lot, String streetName,String villageName,String city,String postalCode,int month, int day, int year, String telNum, String celNum)
	{
		String ret;
		String address = "Blk no. " + blk + " Lot no. " + lot + " " + streetName + " st. " + villageName + " village ";
		//int telNumFinal = clean(telNum);
		//int celNumFinal = clean(celNum);
		System.out.println(telNum + " " + celNum + " " + address);
		int telNumFinal = Integer.parseInt(telNum);
		int celNumFinal = Integer.parseInt(celNum);
		Date bday = new Date(year,month,day);
		try
		{
			statement.executeUpdate("INSERT INTO customer (given_name, middle_initial, last_name, address, city, postal_code, cellphone_no, landline, birthdate) VALUES (" + "'" + firstName+ "' ,'" + mi + "' ,'" + lastName+ "' ,'" + address +"' ,'" + city +"' ,'" + postalCode + "' ," + celNumFinal + " ," + telNumFinal + " ,'" + bday + "')");
			
		}
		catch( Exception e)
		{
			String s ="Error in Insertion";
			return s;
		}
		ret = "insertion successful";
		
		return ret;
	
	}
	
	public int clean(String n)
	{
		for(int i =0; i < n.length();i++)
		{
			if(n.charAt(i)>= 30 && n.charAt(i)<= 39)
			{
				
				continue;
			}
			else
			{
				char c = 0;
				n.replace(n.charAt(i),c);
			
			}
		
		}
		
		return Integer.parseInt(n);
	
	}
	
	public ArrayList<ArrayList<String>> searchName(String choice, String input)
	{
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		ArrayList<String> customerID = new ArrayList<String>();
		ArrayList<String> lastName = new ArrayList<String>();
		ArrayList<String> middleInitial = new ArrayList<String>();
		ArrayList<String> firstName = new ArrayList<String>();
		ArrayList<String> add = new ArrayList<String>();
		ArrayList<String> telNum = new ArrayList<String>();
		ArrayList<String> celNum = new ArrayList<String>();
		try
		{
			if(choice.equals("ID"))
			{
				result = statement.executeQuery("Select * from customer where customer_ID = "+ input );
				if( result != null)
				{
					while( result.next())
					{
						customerID.add(result.getString("customer_ID"));
						lastName.add(result.getString("last_name"));
						middleInitial.add(result.getString("middle_initial"));
						firstName.add(result.getString("given_name"));
						add.add(result.getString("address"));
						telNum.add(result.getString("landline"));
						celNum.add(result.getString("cellphone_no"));
					}
				}
				
			}
			else if(choice.equals("Last Name"))
			{
				result = statement.executeQuery("Select * from customer where last_name = '" + input+"'");
				if( result != null)
				{
					while( result.next())
					{
						customerID.add(result.getString("customer_ID"));
						lastName.add(result.getString("last_name"));
						middleInitial.add(result.getString("middle_initial"));
						firstName.add(result.getString("given_name"));
						add.add(result.getString("address"));
						telNum.add(result.getString("landline"));
						celNum.add(result.getString("cellphone_no"));
					}
				}
			}
			else if ( choice.equals("M.I."))
			{
				result = statement.executeQuery("Select * from customer where middle_initial = '" + input+"'");
				if( result != null)
				{
					while( result.next())
					{
						customerID.add(result.getString("customer_ID"));
						lastName.add(result.getString("last_name"));
						middleInitial.add(result.getString("middle_initial"));
						firstName.add(result.getString("given_name"));
						add.add(result.getString("address"));
						telNum.add(result.getString("landline"));
						celNum.add(result.getString("cellphone_no"));
					}
				}
			}
			else
			{
				result = statement.executeQuery("Select * from customer where given_name = '" + input+"'");
				if( result != null)
				{
					while( result.next())
					{
						customerID.add(result.getString("customer_ID"));
						lastName.add(result.getString("last_name"));
						middleInitial.add(result.getString("middle_initial"));
						firstName.add(result.getString("given_name"));
						add.add(result.getString("address"));
						telNum.add(result.getString("landline"));
						celNum.add(result.getString("cellphone_no"));
					}
				}
				
			}
			output.add(customerID);
			output.add(lastName);
			output.add(middleInitial);
			output.add(firstName);
			output.add(add);
			output.add(telNum);
			output.add(celNum);
			
			for(int i = 0; i < output.get(0).size();i++)
			{
				System.out.println( choice+ " " + output.get(0).get(i) + " " + output.get(1).get(i) + " " + output.get(2).get(i) + " " + output.get(3).get(i));
				
			}
			
			return output;
			
		}
		catch( Exception e)
		{
			String s ="Error in Query";
			System.out.println(s);
			e.printStackTrace();
		}
		
		return output;
	}
		
	public TableFiller setTableAppraisal(String choice, String input)
	{
		try
		{
			if(choice == "Description")
			{
				System.out.println("start");
				result = statement.executeQuery("SELECT item_no AS 'Item #', category AS 'Category',description AS 'Description',risk_name AS 'Risk Level',loan_amount AS "
						+ "'Amount' FROM pawned_item p JOIN loan l ON p.ticket_no = l.ticket_no "
                        +"JOIN item_category cat ON  p.category_ID = cat.category_ID "
                        +"JOIN risk_level ris ON p.risk_ID = ris.risk_ID " 
                        +"WHERE description LIKE '%" + input + "%'" );
				System.out.println("end");
			}
			else if(choice == "Risk Level")
			{
				result = statement.executeQuery("SELECT item_no AS 'Item #', category AS 'Category',description AS 'Description',risk_name AS 'Risk Level',loan_amount AS "
						+ "'Amount' FROM pawned_item p JOIN loan l ON  p.ticket_no = l.ticket_no "
                        +"JOIN item_category cat ON p.category_ID = cat.category_ID "
                        +"JOIN risk_level ris ON p.risk_ID = ris.risk_ID " 
                        +"WHERE risk_name = '" + input +"'");
			}
			else if(choice == "Item#")
			{
				result = statement.executeQuery("SELECT item_no AS 'Item #', category AS 'Category',description AS 'Description',risk_name AS 'Risk Level',loan_amount AS "
						+ "'Amount' FROM pawned_item p JOIN loan l ON  p.ticket_no = l.ticket_no "
                        +"JOIN item_category cat ON p.category_ID = cat.category_ID "
                        +"JOIN risk_level ris ON p.risk_ID = ris.risk_ID " 
                        +"WHERE item_no = " + input);
			}
			else if(choice == "Category ID")
			{
				result = statement.executeQuery("SELECT item_no AS 'Item #', category AS 'Category',description AS 'Description',risk_name AS 'Risk Level',loan_amount AS "
						+ "'Amount' FROM pawned_item p JOIN loan l ON  p.ticket_no = l.ticket_no "
                        +"JOIN item_category cat ON p.category_ID = cat.category_ID "
                        +"JOIN risk_level ris ON p.risk_ID = ris.risk_ID " 
                        +"WHERE cat.category_ID = " + input);
			}
			else if(choice == "Price")
			{
				result = statement.executeQuery("SELECT item_no AS 'Item #', category AS 'Category',description AS 'Description',risk_name AS 'Risk Level',loan_amount AS "
						+ "'Amount' FROM pawned_item p JOIN loan l ON  p.ticket_no = l.ticket_no "
                        +"JOIN item_category cat ON p.category_ID = cat.category_ID "
                        +"JOIN risk_level ris ON p.risk_ID = ris.risk_ID " 
                        +"WHERE pi.loan_amount = " + input);
			}
			TableFiller fill = new TableFiller(result);
			return fill;
		}
		catch(Exception e)
		{return null;
		}
	}
	
	public TableFiller setTableLC(String choice, String input)
	{
		try
		{
			if(choice == "ID")
			{
				System.out.println("start");
				result = statement.executeQuery("select customer_ID AS 'ID',last_name AS 'Last Name',middle_initial AS 'M.I.',"
						+"given_name AS 'First Name',address AS 'Address',cellphone_no AS 'Cel#',"
						+"landline AS 'Tel#' from customer where customer_ID = " + input );
				System.out.println("end");
			}
			else if(choice == "Last Name")
			{
				result = statement.executeQuery("select customer_ID AS 'ID',last_name AS 'Last Name',middle_initial AS 'M.I.',"
						+"given_name AS 'First Name',address AS 'Address',cellphone_no AS 'Cel#',"
						+"landline AS 'Tel#' from customer where last_name = '" + input + "'");
			}
			else if(choice == "M.I.")
			{
				result = statement.executeQuery("select customer_ID AS 'ID',last_name AS 'Last Name',middle_initial AS 'M.I.',"
						+"given_name AS 'First Name',address AS 'Address',cellphone_no AS 'Cel#',"
						+"landline AS 'Tel#' from customer where middle_initial = '" + input + "'");
			}
			else if(choice == "First Name")
			{
				result = statement.executeQuery("select customer_ID AS 'ID',last_name AS 'Last Name',middle_initial AS 'M.I.',"
						+"given_name AS 'First Name',address AS 'Address',cellphone_no AS 'Cel#',"
						+"landline AS 'Tel#' from customer where given_name = '" + input + "'");
			}
			TableFiller fill = new TableFiller(result);
			return fill;
		}
		catch(Exception e)
		{return null;
		}
	}
	
	public TableFiller setTableViewPawn(String choice, String input)
	{
		try
		{
			if(choice == "Active Pawns")
			{
				System.out.println("start");
				result = statement.executeQuery("select l.ticket_no AS 'Ticket#',pawn_date AS 'Pawn Date', DATE_ADD(pawn_date,INTERVAL 1 month) AS 'Due Date',"
				+ "COUNT(item_no) AS '# of Items', (SUM(loan_amount*(interest_rate/100) + loan_amount)) AS 'Total Loan'," 
				+ "(SUM((loan_amount*(interest_rate/100)) + loan_amount)*(service_charge_rate/100)) AS 'Service Charge',"
				+ "(SUM(loan_amount*(interest_rate/100) + loan_amount)) +"
				+ "(SUM((loan_amount*(interest_rate/100)) + loan_amount)*(service_charge_rate/100))"
				+ " AS 'Amount Due', status AS 'Status' FROM customer c "
				+ "join loan l on c.customer_ID = l.customer_ID "
				+ "join pawned_item pi on pi.ticket_no = l.ticket_no "
				+ "join risk_level ris on pi.risk_ID = ris.risk_ID "
				+ "where (status = 'A' or 'P') AND c.customer_id = " + input + " group by l.ticket_no");
				System.out.println("end");
			}
			else if(choice == "History")
			{
				System.out.println("start");
				result = statement.executeQuery("select l.ticket_no AS 'Ticket#',pawn_date AS 'Pawn Date', DATE_ADD(pawn_date,INTERVAL 1 month) AS 'Due Date',"
						+ "COUNT(item_no) AS '# of Items', (SUM(loan_amount*(interest_rate/100) + loan_amount)) AS 'Total Loan'," 
						+ "(SUM((loan_amount*(interest_rate/100)) + loan_amount)*(service_charge_rate/100)) AS 'Service Charge',"
						+ "(SUM(loan_amount*(interest_rate/100) + loan_amount)) +"
						+ "(SUM((loan_amount*(interest_rate/100)) + loan_amount)*(service_charge_rate/100))"
						+ " AS 'Amount Due', status AS 'Status' FROM customer c "
						+ "join loan l on c.customer_ID = l.customer_ID "
						+ "join pawned_item pi on pi.ticket_no = l.ticket_no "
						+ "join risk_level ris on pi.risk_ID = ris.risk_ID "
						+ "where c.customer_id = "+ input + " group by l.ticket_no");
				System.out.println("end");
			}
			TableFiller fill = new TableFiller(result);
			return fill;
		}
		catch(Exception e)
		{return null;
		}
	}
	
	public void AddPawn(String ID,Object[][] data )
	{
		String query = "";
	    String query2 = "";
	    String ticketno = "6";
	    String category = "";
	    String riskID = "";
		for(int i = 0; i < data[4].length; i++)
		{
			if(data[0].toString() == "Jewelry")
			{
				category = "1";
			}
			else if(data[0].toString() == "Electronics")
			{
				category = "2";
			}
			else
			{
				category = "3";
			}
			if(data[3].toString() == "1.5")
			{
				riskID = "1";
			}
			else if(data[3].toString() == "3.5")
			{
				riskID = "2";
			}
			else if(data[3].toString() == "8.75")
			{
				riskID = "2";
			}
			else
			{
				riskID = "3";
			}
			query = query + "(curdate(), " + data[3][i].toString() + " , 'A', " + ticketno + " , null, " + ID + ") \n" ;
			query2 = query2 + "(" + data[2][i].toString() + ", " + data[1][i].toString() + ", " + ticketno + ", " + category + ", " + riskID + ") \n";
		}
		try
		{
			statement.executeUpdate("INSERT INTO loan(pawn_date, service_charge_rate, status, original_ticket_no, previous_ticket_no, customer_id) VALUES " + query);
			statement.executeUpdate("INSERT INTO pawned_item(loan_amount, description, ticket_no, category_ID, risk_ID) VALUES " + query2);
		}
		catch(Exception e)
		{}
	}
	
	public TableFiller getInventory(String choice)
	{
		try
		{
			if(choice.equals("Expired"))
			{
			System.out.println("start");
			result = statement.executeQuery("select pi.item_no, pi.category_ID,pi.description,ADDDATE( l.pawn_date, INTERVAL 1 MONTH), pi.loan_amount,r.interest_rate from pawned_item pi "
					+"join loan l on pi.ticket_no = l.ticket_no join risk_level r on pi.risk_ID = r.risk_ID "
					+ "where l.status = 'E'"); 
				System.out.println("end");
			}
			else if(choice.equals("Active"))
			{
				System.out.println("start");
				result = statement.executeQuery("select pi.item_no, pi.category_ID,pi.description,ADDDATE( l.pawn_date, INTERVAL 1 MONTH), pi.loan_amount,r.interest_rate from pawned_item pi "
						+"join loan l on pi.ticket_no = l.ticket_no join risk_level r on pi.risk_ID = r.risk_ID "
						+ "where l.status = 'A'");
					System.out.println("end");
			}
			else if(choice.equals("Renewed"))
			{
				System.out.println("start");
				result = statement.executeQuery("select pi.item_no, pi.category_ID,pi.description,ADDDATE( l.pawn_date, INTERVAL 1 MONTH), pi.loan_amount,r.interest_rate from pawned_item pi "
						+"join loan l on pi.ticket_no = l.ticket_no join risk_level r on pi.risk_ID = r.risk_ID "
						+ "where l.status = 'R'");
					System.out.println("end");
			}
			TableFiller fill = new TableFiller(result);
			return fill;
		}
		catch(Exception e)
		{return null;
		}
	}
	
	public Object [] renewPawnTicket(String input)
	{
		Object [] out = new Object [8];
		try
		{
			System.out.println("start");
			result = statement.executeQuery("select l.ticket_no,concat(c.given_name ,\" \", c.middle_initial,\" \",  c.last_name) AS 'Name', concat(c.address, \" \",c.city) AS 'Address', c.landline, " +
					"c.cellphone_no,l.pawn_date, adddate(l.pawn_date,INTERVAL '1' MONTH) AS 'Due Date',curdate() AS 'Payment Date'" +
					"from loan l " +
					"join customer c on l.customer_ID = c.customer_ID where l.ticket_no = " + input);
			System.out.println("dito");
			while(result.next())
			{
				out[0] = result.getObject("ticket_no");
				out[1] = result.getObject("Name");
				out[2] = result.getObject("Address");
				out[3] = result.getObject("Landline");
				out[4] = result.getObject("cellphone_no");
				out[5] = result.getDate("pawn_date");
				out[6] = result.getDate("Due Date");
				out[7] = result.getDate("Payment Date");
				System.out.println(out[0].toString());
			}
			System.out.println("end");
			return out;
		}
		catch(Exception e)
		{return null;
		}		
	}
	
	public TableFiller renewPawnItems(String input)
	{
		try
		{
			System.out.println("start");
			result = statement.executeQuery("select category, pi.description,pi.loan_amount,interest_rate,loan_amount*(interest_rate/100) " +
					"from pawned_item pi " +
					"join loan l on pi.ticket_no = l.ticket_no " +
					"join item_category ic on pi.category_id = ic.category_id " +
					"join risk_level ris on pi.risk_ID = ris.risk_ID where pi.ticket_no = " + input);
			System.out.println("end");
			TableFiller fill = new TableFiller(result);
			return fill;
		}
		catch(Exception e)
		{return null;
		}
	}
	
	public Object [] renewPawnPayment(String input)
	{
		//insert query here
		Object [] out = new Object [4];
		try
		{
			System.out.println("start");
			result = statement.executeQuery("select SUM(pi.loan_amount) AS 'Total Loan', l.service_charge_rate AS 'Service Charge', " +
					"SUM(pi.loan_amount*(l.service_charge_rate/100)) + pi.loan_amount AS 'Total Amount Due'" +
					" from loan l join pawned_item pi on l.ticket_no = pi.ticket_no where l.customer_ID = " + input + " group by l.ticket_no" );
			System.out.println("dito");
			while(result.next())
			{
				out[0] = result.getObject("Total Loan");
				out[1] = result.getObject("Service Charge");
				out[2] = result.getObject("Total Amount Due");
				//out[3] = payment;
				System.out.println(out[0].toString());
			}
			System.out.println("end");
			return out;
		}
		catch(Exception e)
		{return null;
		}		
	}
	
	public void Payment(String ID,String payment)
	{
		try
		{
			statement.executeUpdate("UPDATE loan l set status = 'R' WHERE l.ticket_no = " + ID );
			statement.executeUpdate("INSERT INTO payment (payment_date,amount_paid,ticket_no) VALUES (curdate()," + payment +".00," + ID +")");
		} 
		catch(Exception e)
		{}
	}
	
	public void renewPayment(String ID)
	{
		try
		{
			statement.executeUpdate("UPDATE loan l set status = 'R' WHERE l.ticket_no = " + ID );
		}
		catch(Exception e)
		{}
	}

	public Object [] getReceipt(String input)
	{
		Object [] out = new Object [13];
		try
		{
			System.out.println("start");
			result = statement.executeQuery(" select l.ticket_no, " +
					"concat(c.given_name, \" \" , c.middle_initial, \" \" , c.last_name) as 'Customer', " +
					"concat(c.address, \" \" , c.city) as 'Address', " +
					"c.cellphone_no, c.landline, l.pawn_date, ADDDATE( pawn_date, INTERVAL 1 MONTH) as 'Due Date', " +
					"p.payment_date, Count(pi.ticket_no) '# of Items', Sum(pi.loan_amount) 'Total Loan', " +
					"Sum(pi.loan_amount)*(l.service_charge_rate/100) AS 'Service Charge', " +
					"sum(pi.loan_amount)*((l.service_charge_rate/100)+1) AS 'Total Amount', p.amount_paid from loan l " +
					"join customer c on l.customer_id = c.customer_id join pawned_item pi on l.ticket_no = pi.ticket_no " +
					"join payment p on l.ticket_no = p.ticket_no where l.ticket_no = " + input);
			System.out.println("dito");
			while(result.next())
			{
				out[0] = result.getObject("ticket_no");
				out[1] = result.getObject("Customer");
				out[2] = result.getObject("Address");
				out[3] = result.getObject("cellphone_no");
				out[4] = result.getObject("landline");
				out[5] = result.getObject("pawn_date");
				out[6] = result.getObject("Due Date");
				out[7] = result.getObject("payment_date");
				out[8] = result.getObject("# Of Items");
				out[9] = result.getObject("Total Loan");
				out[10] = result.getObject("Service Charge");
				out[11] = result.getObject("Total Amount");
				out[12] = result.getObject("amount_paid");
				System.out.println(out[0].toString());
			}
			System.out.println("end");
			return out;
		}
		catch(Exception e)
		{return null;
		}		
	}
	
}

class TableFiller extends AbstractTableModel
{
	public ResultSet rs;
	public int rows;
	public int columns;
	public static final long serialVersionUID = 1241414123;
	public ArrayList data = new ArrayList();
	public TableFiller(ResultSet a)throws Exception
	{
		SetRS(a);
	}
	
	public void SetRS(ResultSet a)throws Exception
	{
		rs = a;
		ResultSetMetaData metaData = a.getMetaData();
		rows= 0;
		columns = metaData.getColumnCount();
		while(a.next())
		{
			Object [] row = new Object[columns];
			for(int i =0;i<columns;i++)
			{
				row[i] = a.getObject(i+1);
			}
			data.add(row);
			rows++;
		}
	}
	public int getColumnCount()
	{
		return columns;
	}
	public int getRowCount()
	{
		return rows;
	}
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		Object [] row = (Object[]) data.get(rowIndex);
		return row[columnIndex];
	}
}