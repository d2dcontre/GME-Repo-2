import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class JoinGroup extends JPanel{
	JPanel leftPanel,rightPanel;
	JPanel rightPanelGrid,rightPanelLGrid;
	public JList leftList,rightList;
	JButton joinButt;
	String groupName;
	public static LinkedList<String> firstList;
	public static LinkedList<String> secondList;
	public JoinGroup(){
		setLayout(new GridLayout(1,2));
			leftPanel = new JPanel(new BorderLayout());
				leftList = new JList(firstList.toArray());
				leftList.setSelectedIndex(0);
				leftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				groupName = leftList.getSelectedValue().toString();
				leftList.addListSelectionListener(new ListSelectionListener(){
					public void valueChanged(ListSelectionEvent e){
						try{
							groupName = leftList.getSelectedValue().toString();
							//Use the groupName for the SELECT query.
							//Get the Student Names from there and add it to the other list.
							//Add a loop here to add stuff to the second list
							rightList.setListData(secondList.toArray()); //This line updates the student members of the group
							//Basically it's like what you did for the first screen
						}
						catch(NullPointerException ae){}
						}
					});
			leftPanel.add(leftList,BorderLayout.CENTER);
			rightPanel = new JPanel(new GridLayout(2,1));
				rightPanelLGrid = new JPanel(new FlowLayout());
				rightList = new JList(secondList.toArray());
				rightList.setSelectedIndex(0);
				rightList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				joinButt = new JButton("Join Group");
				
			rightPanel.add(rightList);	
			rightPanel.add(joinButt);
		add(leftPanel);
		add(rightPanel);
		
	}
	public void actionCreator(){
		class joinGroup implements ActionListener{
			public void actionPerformed(ActionEvent ae){
				//use groupName which automatically has the group name selected in the list
				//to insert(join the group)
				//actions go here
				//from the way jaudric explained it to me, after this uploads to the DB,
				//the original list should update automatically.
				
			}
		}
		joinButt.addActionListener(new joinGroup());
	}
}