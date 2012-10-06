
import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class GroupSelection extends javax.swing.JFrame {
    private MySQL my;
    private String[] selectMembers;
    public static int GroupAdded = -1;
    public static boolean GroupChange = false;
    private boolean selected = false;
    public static int groupSelected;
    
    /**
     * Creates new form GroupSelection
     */
    public GroupSelection() {
        initComponents();
        this.setVisible(true);
    }
    
    public GroupSelection(MySQL my) {
        this.my = my;
        initComponents();
        this.setVisible(true);
        this.setTitle("Select a Group");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        groupList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        groupMemberList = new javax.swing.JList();
        selectGroup = new javax.swing.JButton();
        JoinGroup = new javax.swing.JButton();
        createGroup = new javax.swing.JButton();
        GroupNo = new javax.swing.JLabel();
        GroupName = new java.awt.Label();
        editInfo = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        groupList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = Runner.groupNames;//String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() {
                if(strings != null)
                return strings.length;
                else
                return 1;
            }
            public Object getElementAt(int i) {
                if(strings != null)
                return strings[i];
                else
                return "No groups joined";
            }
        }
    );
    ListSelectionModel ls = groupList.getSelectionModel();
    ls.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    groupList.addListSelectionListener(new ListSelectionListener() {
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting() || Runner.groupIDs == null)
            return;
            JList j = (JList) e.getSource();
            int selectedRow = j.getSelectedIndex();
            int query = Runner.groupIDs[selectedRow];
            groupSelected = query;
            selectMembers = my.memberQuery(query);
            if(selectMembers != null) {
                System.out.println("Starting refresh of member list");
                GroupName.setText(Runner.groupNames[selectedRow]);
                GroupNo.setText(""+Runner.groupIDs[selectedRow]);
                groupMemberList.setModel(new javax.swing.AbstractListModel() {
                    String[] strings = selectMembers;//String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
                    public int getSize() {
                        if(strings != null)
                        return strings.length;
                        else
                        return 1;
                    }
                    public Object getElementAt(int i) {
                        if(strings != null)
                        return strings[i];
                        else
                        return "No Members";
                    }
                });
                selected = true;
                jScrollPane1.validate();
            }
            else {
                selected = false;
            }
        }
    } );
    jScrollPane1.setViewportView(groupList);

    groupMemberList.setModel(new javax.swing.AbstractListModel() {
        String[] strings = selectMembers;//String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
        public int getSize() {
            if(strings != null)
            return strings.length;
            else
            return 1;
        }
        public Object getElementAt(int i) {
            if(strings != null)
            return strings[i];
            else
            return "No Members";
        }
    });
    jScrollPane2.setViewportView(groupMemberList);

    selectGroup.setText("Select group");
    selectGroup.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            selectGroupActionPerformed(evt);
        }
    });

    JoinGroup.setText("Join group");
    JoinGroup.setComponentPopupMenu(jPopupMenu1);
    JoinGroup.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            JoinGroupActionPerformed(evt);
        }
    });

    createGroup.setText("Create group");
    createGroup.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            createGroupActionPerformed(evt);
        }
    });

    GroupNo.setText("Group No.");

    GroupName.setText("Group Name");

    editInfo.setText("Edit Info");
    editInfo.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            editInfoActionPerformed(evt);
        }
    });

    jButton1.setText("Refresh");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addComponent(jButton1)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(GroupName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(selectGroup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(createGroup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(JoinGroup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(GroupNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(GroupName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(GroupNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(selectGroup)
                        .addComponent(JoinGroup))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(editInfo)
                        .addComponent(createGroup))
                    .addContainerGap())
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1))))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editInfoActionPerformed
        // TODO add your handling code here:
        MainSubWindow msw = new MainSubWindow();
        msw.setMYSQL(my);
        msw.setGS(this);
        msw.launchWindow(2);
    }//GEN-LAST:event_editInfoActionPerformed

    private void JoinGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JoinGroupActionPerformed
        // TODO add your handling code here:
        //JoinGroup jg = new JoinGroup();
        System.out.println("joingroup triggered");
        MainSubWindow msw = new MainSubWindow();
        msw.setMYSQL(my);
        msw.setGS(this);
        msw.launchWindow(0);
        System.out.println("reached line after launch");
    }//GEN-LAST:event_JoinGroupActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        this.setVisible(false);
        GroupSelection gs = new GroupSelection(my);        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void selectGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectGroupActionPerformed
        // TODO add your handling code here:
        //GroupNo.getText();
        if(selected) {
            AlphaGUI ag = new AlphaGUI(my);
            ag.setVisible(true);
            ag.groupID = Integer.parseInt(GroupNo.getText() );
        }
        else {
            StartGUI.showMessage("Select a Group First!");
        }
    }//GEN-LAST:event_selectGroupActionPerformed

    private void createGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createGroupActionPerformed
        // TODO add your handling code here:
        MainSubWindow msw = new MainSubWindow();
        msw.setMYSQL(my);
        msw.setGS(this);
        msw.launchWindow(1);
    }//GEN-LAST:event_createGroupActionPerformed

    public void groupChanged() {
        System.out.println("GroupChange triggered: " + GroupChange);
        if(GroupChange) {
            //groupList = new JList();
            groupList.setModel(new javax.swing.AbstractListModel() {
                String[] strings = Runner.groupNames;//String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
                public int getSize() {
                    if(strings != null)
                        return strings.length;
                    else
                        return 1;
                }
                public Object getElementAt(int i) {
                    if(strings != null)
                        return strings[i];
                    else
                        return "No groups joined";
                }
            });
            ListSelectionModel ls = groupList.getSelectionModel();
            ls.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            groupList.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    if (e.getValueIsAdjusting() || Runner.groupIDs == null)
                    return;
                    JList j = (JList) e.getSource();
                    int selectedRow = j.getSelectedIndex();
                    int query = Runner.groupIDs[selectedRow];
                    /*String[] members*/ selectMembers = my.memberQuery(query);
                    if(selectMembers != null) {
                        System.out.println("Starting refresh of member list");
                        GroupName.setText(Runner.groupNames[selectedRow]);
                        GroupNo.setText(""+Runner.groupIDs[selectedRow]);
                        //groupMemberList = new javax.swing.JList();
                        groupMemberList.setModel(new javax.swing.AbstractListModel() {
                            String[] strings = selectMembers;//String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
                            public int getSize() {
                                if(strings != null)
                                return strings.length;
                                else
                                return 1;
                            }
                            public Object getElementAt(int i) {
                                if(strings != null)
                                return strings[i];
                                else
                                return "No Members";
                            }
                        });
                        jScrollPane1.validate();
                    }
                }
                /*Object o = j.getModel().getElementAt(selectedRow);
                String selectedItem = o.toString();
                System.out.println("Selected from " + e.getFirstIndex() + " to " + e.getLastIndex());
                System.out.println("Selected Row: " + selectedItem);*/
            }
            );
            jScrollPane1.validate();
            jScrollPane2.validate();
            /*groupList.validate();
            jScrollPane1.repaint();
            jScrollPane2.repaint();
            jScrollPane1.validate();
            jScrollPane2.validate();
            groupList.repaint();
            this.repaint();
            this.validate();*/
            System.out.println("model changed");
            /*groupList.invalidate();
            groupList.validate();*/
            /*this.getContentPane().validate();
            this.getContentPane().repaint();*/
            /*  groupList = new JList(new javax.swing.AbstractListModel() {
                String[] strings = Runner.groupNames;//String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
                public int getSize() {
                    if(strings != null)
                    return strings.length;
                    else
                    return 1;
                }
                public Object getElementAt(int i) {
                    if(strings != null)
                    return strings[i];
                    else
                    return "No groups joined";
                }
            });*/
            GroupChange = !GroupChange;
        }
    }
    
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(GroupSelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GroupSelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GroupSelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GroupSelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new GroupSelection().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Label GroupName;
    private javax.swing.JLabel GroupNo;
    private javax.swing.JButton JoinGroup;
    private javax.swing.JButton createGroup;
    private javax.swing.JButton editInfo;
    private javax.swing.JList groupList;
    private javax.swing.JList groupMemberList;
    private javax.swing.JButton jButton1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton selectGroup;
    // End of variables declaration//GEN-END:variables
}
