/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oopgroup3.e_exam_client;

import com.oopgroup3.e_exam_client.Interfaces.BuildPanelInterface;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Kyle
 */
public class E_Exam_Client_GUI extends javax.swing.JFrame {

    private final CardLayout cardLayoutManager;
    private boolean  doonce = true;
    /**
     * Creates new form EExam_GUI
     */
    public E_Exam_Client_GUI() {
        initComponents();
        
        //cardlayout_container.setLayout(cardLayoutManager);
        
        cardLayoutManager = (CardLayout)cardlayout_container.getLayout();
        
        cardlayout_container.add(login_panel, "login");
        cardlayout_container.add(register_panel, "register");
        cardlayout_container.add(student_panel, "student");
        cardlayout_container.add(teacher_panel, "teacher");
        cardlayout_container.add(exam_panel, "exam");
        
        
        cardLayoutManager.show(cardlayout_container, "login");
         
        pack();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        cardlayout_container = new javax.swing.JPanel();
        login_panel = new javax.swing.JPanel();
        username_lbl = new javax.swing.JLabel();
        password_lbl = new javax.swing.JLabel();
        login_btn = new javax.swing.JButton();
        cancel_login_btn = new javax.swing.JButton();
        username_txtField = new javax.swing.JTextField();
        password_txtField = new javax.swing.JPasswordField();
        gotoRegisterPanel = new javax.swing.JButton();
        gotoStudentPanel = new javax.swing.JButton();
        gotoTeacherPanel = new javax.swing.JButton();
        register_panel = new javax.swing.JPanel();
        registerUsername_lbl = new javax.swing.JLabel();
        registerPassword_lbl = new javax.swing.JLabel();
        registerConfirmPassword_lbl = new javax.swing.JLabel();
        register_btn = new javax.swing.JButton();
        register_cancel_btn = new javax.swing.JButton();
        registerUsername_txtField = new javax.swing.JTextField();
        registerPassword_txtField = new javax.swing.JPasswordField();
        registerPasswordConfirm_txtField = new javax.swing.JPasswordField();
        backToLogin = new javax.swing.JButton();
        user_type_comboBox = new javax.swing.JComboBox<>();
        user_type_lbl = new javax.swing.JLabel();
        student_panel = new javax.swing.JPanel();
        studentBackToLogin = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        teacher_panel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        teacherBackToLogin = new javax.swing.JButton();
        exam_panel = new javax.swing.JPanel();
        examBackToLogin = new javax.swing.JButton();
        examScrollPane = new javax.swing.JScrollPane();
        examFormContainer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cardlayout_container.setLayout(new java.awt.CardLayout());

        login_panel.setLayout(new java.awt.GridBagLayout());

        username_lbl.setText("Username :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 60, 0, 0);
        login_panel.add(username_lbl, gridBagConstraints);

        password_lbl.setText("Password :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 62, 0, 0);
        login_panel.add(password_lbl, gridBagConstraints);

        login_btn.setText("Login");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 60, 0, 90);
        login_panel.add(login_btn, gridBagConstraints);

        cancel_login_btn.setText("Cancel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 65, 0, 88);
        login_panel.add(cancel_login_btn, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 90);
        login_panel.add(username_txtField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 90);
        login_panel.add(password_txtField, gridBagConstraints);

        gotoRegisterPanel.setText("To Register Panel");
        gotoRegisterPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gotoRegisterPanelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        login_panel.add(gotoRegisterPanel, gridBagConstraints);

        gotoStudentPanel.setText("To Student Panel");
        gotoStudentPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gotoStudentPanelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        login_panel.add(gotoStudentPanel, gridBagConstraints);

        gotoTeacherPanel.setText("To Teacher Panel");
        gotoTeacherPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gotoTeacherPanelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        login_panel.add(gotoTeacherPanel, gridBagConstraints);

        cardlayout_container.add(login_panel, "card2");

        register_panel.setLayout(new java.awt.GridBagLayout());

        registerUsername_lbl.setText("Username :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        register_panel.add(registerUsername_lbl, gridBagConstraints);

        registerPassword_lbl.setText("Password :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        register_panel.add(registerPassword_lbl, gridBagConstraints);

        registerConfirmPassword_lbl.setText("Confirm Password :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        register_panel.add(registerConfirmPassword_lbl, gridBagConstraints);

        register_btn.setText("Register");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        register_panel.add(register_btn, gridBagConstraints);

        register_cancel_btn.setText("Cancel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        register_panel.add(register_cancel_btn, gridBagConstraints);

        registerUsername_txtField.setText("jTextField1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        register_panel.add(registerUsername_txtField, gridBagConstraints);

        registerPassword_txtField.setText("jPasswordField1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        register_panel.add(registerPassword_txtField, gridBagConstraints);

        registerPasswordConfirm_txtField.setText("jPasswordField2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        register_panel.add(registerPasswordConfirm_txtField, gridBagConstraints);

        backToLogin.setText("Back To Login");
        backToLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToLoginActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        register_panel.add(backToLogin, gridBagConstraints);

        user_type_comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Student", "Teacher" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        register_panel.add(user_type_comboBox, gridBagConstraints);

        user_type_lbl.setText("User Type:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        register_panel.add(user_type_lbl, gridBagConstraints);

        cardlayout_container.add(register_panel, "card3");

        student_panel.setLayout(new java.awt.GridBagLayout());

        studentBackToLogin.setText("Back To Login");
        studentBackToLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentBackToLoginActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        student_panel.add(studentBackToLogin, gridBagConstraints);

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.weighty = 0.25;
        student_panel.add(jScrollPane1, gridBagConstraints);

        jLabel1.setText("Welcome to E-Exam");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        student_panel.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Username");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        student_panel.add(jLabel2, gridBagConstraints);

        jButton6.setText("Take Exam");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        student_panel.add(jButton6, gridBagConstraints);

        cardlayout_container.add(student_panel, "card4");

        teacher_panel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        teacher_panel.add(jTabbedPane1, gridBagConstraints);

        teacherBackToLogin.setText("Back To Login");
        teacherBackToLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherBackToLoginActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        teacher_panel.add(teacherBackToLogin, gridBagConstraints);

        cardlayout_container.add(teacher_panel, "card5");

        exam_panel.setLayout(new java.awt.GridBagLayout());

        examBackToLogin.setText("Back To Login");
        examBackToLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                examBackToLoginActionPerformed(evt);
            }
        });
        exam_panel.add(examBackToLogin, new java.awt.GridBagConstraints());

        examFormContainer.setPreferredSize(new java.awt.Dimension(366, 222));
        examFormContainer.setLayout(new java.awt.GridLayout(0, 1));
        examScrollPane.setViewportView(examFormContainer);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        exam_panel.add(examScrollPane, gridBagConstraints);

        cardlayout_container.add(exam_panel, "card6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cardlayout_container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cardlayout_container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void gotoRegisterPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gotoRegisterPanelActionPerformed
        cardLayoutManager.show(cardlayout_container, "register");
    }//GEN-LAST:event_gotoRegisterPanelActionPerformed

    private void backToLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToLoginActionPerformed
        cardLayoutManager.show(cardlayout_container, "login");
    }//GEN-LAST:event_backToLoginActionPerformed

    private void studentBackToLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentBackToLoginActionPerformed
        cardLayoutManager.show(cardlayout_container, "login");
    }//GEN-LAST:event_studentBackToLoginActionPerformed

    private void gotoStudentPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gotoStudentPanelActionPerformed
        cardLayoutManager.show(cardlayout_container, "student");
    }//GEN-LAST:event_gotoStudentPanelActionPerformed

    private void teacherBackToLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherBackToLoginActionPerformed
        cardLayoutManager.show(cardlayout_container, "login");
    }//GEN-LAST:event_teacherBackToLoginActionPerformed

    private void gotoTeacherPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gotoTeacherPanelActionPerformed
        cardLayoutManager.show(cardlayout_container, "teacher");
    }//GEN-LAST:event_gotoTeacherPanelActionPerformed

    private void examBackToLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_examBackToLoginActionPerformed
        cardLayoutManager.show(cardlayout_container, "login");
    }//GEN-LAST:event_examBackToLoginActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        

        GridBagConstraints gbc = new GridBagConstraints();
        
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                if(doonce)
                {
                    ExamQuestion examQuestion = new ExamQuestion(1, 1, "Do fish climb trees", "", "", "", "");
                    BuildPanelInterface bpi;
                    for(int row = 0; row < 4; row++)
                    {
                        gbc.gridy = row;
                        //gbc.weightx = 0.5;
                        //gbc.weighty = 0.5;
                        bpi = new ExamQuestionTrueFalseControl(1, 1, getExamFormContainer());
                        //examFormContainer.add(bpi.buildEditableExamPanel(), gbc);
                        examFormContainer.add(bpi.buildExamPanel(examQuestion));
                        

                    }
                    revalidate();
                    doonce = false;
                    cardLayoutManager.show(cardlayout_container, "exam");
                }
            }
        });
                
    }//GEN-LAST:event_jButton6ActionPerformed

    
    public JButton getLoginButton()
    {
       return this.login_btn;
    }
    
    public JTextField getUsernameTextField()
    {
        return this.username_txtField;
    }
    
    /*public JPasswordField getPasswordTextField()
    {
        return this.password_txtField;
        //return String.valueOf(this.password_txtField.getPassword());
    }*/
    
    public CardLayout getCardLayoutManager()
    {
        return this.cardLayoutManager;
    }
    
    public JPanel getCardContainer()
    {
        return this.cardlayout_container;
    }
    
    public JButton getCancel_login_btn() {
        return cancel_login_btn;
    }

    
    public JPasswordField getPassword_txtField() {
        return password_txtField;
    }

    public JPasswordField getRegisterPasswordConfirm_txtField() {
        return registerPasswordConfirm_txtField;
    }

    public JPasswordField getRegisterPassword_txtField() {
        return registerPassword_txtField;
    }

    public JTextField getRegisterUsername_txtField() {
        return registerUsername_txtField;
    }

    public JButton getRegister_cancel_btn() {
        return register_cancel_btn;
    }

    public JTextField getUsername_txtField() {
        return username_txtField;
    }
    
     public JButton getRegister_btn() {
        return register_btn;
    }   
     
     public JComboBox<String> getUser_type_comboBox() {
        return user_type_comboBox;
    }    
     
    /**
     *
     * @return JPanel
     */
    public JPanel getExamFormContainer() {
        return examFormContainer;
    }
     
     
    
    /**
     * @param args the command line arguments
     */
  /*  public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
/*        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(E_Exam_Client_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(E_Exam_Client_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(E_Exam_Client_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(E_Exam_Client_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        /*
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new E_Exam_Client_GUI().setVisible(true);
            }
        }); 
    }*/





     
     


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backToLogin;
    private javax.swing.JButton cancel_login_btn;
    private javax.swing.JPanel cardlayout_container;
    private javax.swing.JButton examBackToLogin;
    private javax.swing.JPanel examFormContainer;
    private javax.swing.JScrollPane examScrollPane;
    private javax.swing.JPanel exam_panel;
    private javax.swing.JButton gotoRegisterPanel;
    private javax.swing.JButton gotoStudentPanel;
    private javax.swing.JButton gotoTeacherPanel;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton login_btn;
    private javax.swing.JPanel login_panel;
    private javax.swing.JLabel password_lbl;
    private javax.swing.JPasswordField password_txtField;
    private javax.swing.JLabel registerConfirmPassword_lbl;
    private javax.swing.JPasswordField registerPasswordConfirm_txtField;
    private javax.swing.JLabel registerPassword_lbl;
    private javax.swing.JPasswordField registerPassword_txtField;
    private javax.swing.JLabel registerUsername_lbl;
    private javax.swing.JTextField registerUsername_txtField;
    private javax.swing.JButton register_btn;
    private javax.swing.JButton register_cancel_btn;
    private javax.swing.JPanel register_panel;
    private javax.swing.JButton studentBackToLogin;
    private javax.swing.JPanel student_panel;
    private javax.swing.JButton teacherBackToLogin;
    private javax.swing.JPanel teacher_panel;
    private javax.swing.JComboBox<String> user_type_comboBox;
    private javax.swing.JLabel user_type_lbl;
    private javax.swing.JLabel username_lbl;
    private javax.swing.JTextField username_txtField;
    // End of variables declaration//GEN-END:variables
}
