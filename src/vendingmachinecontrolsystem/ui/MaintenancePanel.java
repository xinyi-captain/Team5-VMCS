/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachinecontrolsystem.ui;

import vendingmachinecontrolsystem.controller.MaintainerController;
import vendingmachinecontrolsystem.factory.PropertiesFactory;
import vendingmachinecontrolsystem.model.Coin;
import vendingmachinecontrolsystem.model.Drink;
import vendingmachinecontrolsystem.util.CurrencyHelper;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Dannel
 */
public class MaintenancePanel extends javax.swing.JFrame {

    /**
     * Creates new form MaintenancePanel
     */
    private static final String VALID_PASSWORD = "Valid Password";
    private static final String INVALID_PASSWORD = "Invalid Password";

    private PropertiesFactory propertiesFactory;
    private String PASSWORD="123";
    private boolean isLocked = true;

    public MaintenancePanel() {
    	propertiesFactory = new PropertiesFactory();
    	PASSWORD = propertiesFactory.getProperty(PropertiesFactory.MACHINE)
    			.getProperty("password");
        initComponents();
        initTextFieldListner();
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        coinPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        drinkPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();

        setTitle("VMCS - Maintainer Panel");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Maintainer Panel");

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Password:  ");
        jPanel1.add(jLabel2, new java.awt.GridBagConstraints());

        jPasswordField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPasswordField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPasswordField1.setPreferredSize(new java.awt.Dimension(120, 23));
        jPanel1.add(jPasswordField1, new java.awt.GridBagConstraints());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(java.awt.Color.green);
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setPreferredSize(new java.awt.Dimension(120, 17));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 10;
        jPanel2.add(jLabel3, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("          ");
        jPanel2.add(jLabel5, new java.awt.GridBagConstraints());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(java.awt.Color.red);
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setPreferredSize(new java.awt.Dimension(120, 17));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 10;
        jPanel2.add(jLabel6, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Quantity of Coins Available:");

        coinPanel.setLayout(new java.awt.GridLayout(0, 2));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Quantity of Drinks Available:");

        drinkPanel.setLayout(new java.awt.GridLayout(0, 2));

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Brand Price:");
        jPanel5.add(jLabel8, new java.awt.GridBagConstraints());

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(10, 0));
        jPanel5.add(jLabel9, new java.awt.GridBagConstraints());

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel5.add(jTextField1, new java.awt.GridBagConstraints());

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Show Total Cash Held");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton1, new java.awt.GridBagConstraints());

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(20, 0));
        jPanel6.add(jLabel10, new java.awt.GridBagConstraints());

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel6.add(jTextField2, new java.awt.GridBagConstraints());

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Press to Collect All Cash");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton2, new java.awt.GridBagConstraints());

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Collect Cash:");
        jPanel8.add(jLabel11, new java.awt.GridBagConstraints());

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(10, 0));
        jPanel8.add(jLabel12, new java.awt.GridBagConstraints());

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("0 c");
        jTextField3.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel8.add(jTextField3, new java.awt.GridBagConstraints());

        jPanel9.setLayout(new java.awt.GridBagLayout());

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("Press Here When Finished");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton3, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(coinPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(drinkPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(coinPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(drinkPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
		setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        MaintainerController.get().showTotalCashHeld();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jTextField3.setText(jTextField2.getText());
        jTextField2.setText("0 c");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        isLocked = true;
        lock();
    }//GEN-LAST:event_jButton3ActionPerformed

    public void lock() {
        resetPassword();
        jLabel4.setVisible(false);
        coinPanel.setVisible(false);
        jLabel7.setVisible(false);
        drinkPanel.setVisible(false);
        jPanel5.setVisible(false);
        jPanel6.setVisible(false);
        jPanel7.setVisible(false);
        jPanel8.setVisible(false);
        jPanel9.setVisible(false);
    }

    public void unlock() {
        resetPassword();
        jLabel4.setVisible(true);
        coinPanel.setVisible(true);
        jLabel7.setVisible(true);
        drinkPanel.setVisible(true);
        jPanel5.setVisible(true);
        jPanel6.setVisible(true);
        jPanel7.setVisible(true);
        jPanel8.setVisible(true);
        jPanel9.setVisible(true);
    }

    public void resetPassword() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jPasswordField1.setText(null);
                jPasswordField1.setText(null);
                if(isLocked)
                    jLabel3.setText(null);
                jLabel6.setText(null);
            }
        });
    }

    public void showTotalCashHeld(double total) {
        jTextField2.setText(CurrencyHelper.toCoins(total));
        jTextField3.setText("0 c");
    }
    
    public String getEnterPassword() {
    	String password = new String(jPasswordField1.getPassword());
    	return password;
    }

    private void checkPassword() {
        String password = new String(jPasswordField1.getPassword());
        if(password.isEmpty() || password == null){
          resetPassword();  
        } else if (password.equals(PASSWORD)) {
            validPassword();
        } else {
            invalidPassword();
        }
    }

    public void validPassword() {
        jLabel3.setText(VALID_PASSWORD);
        jLabel6.setText(null);
        if (isLocked) {
            isLocked = false;
            unlock();
        }
    }

    public void invalidPassword() {
        jLabel3.setText(null);
        jLabel6.setText(INVALID_PASSWORD);
    }

    private void initTextFieldListner() {
        jPasswordField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
            	MaintainerController.get().checkPassword();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            	MaintainerController.get().checkPassword();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
            	MaintainerController.get().checkPassword();
            }
        });
    }
    
    public void addNewCoins(Coin coin) {
    	if (!coin.getName().equalsIgnoreCase("Invalid")) {
            JTextField jTextField = new JTextField();
            jTextField.setEditable(false);
            jTextField.setBackground(new java.awt.Color(0, 0, 0));
            jTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jTextField.setForeground(new java.awt.Color(255, 255, 255));
            jTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            jTextField.setText(String.valueOf(coin.getQuantity()));
            jTextField.setVisible(false);

            JButton jButton = new JButton();
            jButton.setFont(new java.awt.Font("Tahoma", 0, 14));
            jButton.setText(coin.getName());
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    coinButton(jTextField);
                }
            });
            coinPanel.add(jButton);
            coinPanel.add(jTextField);
        }
	}

	public void addNewDrink(Drink drink) {
		JTextField jTextField = new JTextField();
        jTextField.setEditable(false);
        jTextField.setBackground(new java.awt.Color(0, 0, 0));
        jTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField.setForeground(new java.awt.Color(255, 255, 255));
        jTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField.setText(String.valueOf(drink.getQuantity()));
        jTextField.setVisible(false);

        JButton jButton = new JButton();
        jButton.setFont(new java.awt.Font("Tahoma", 0, 14));
        jButton.setText(drink.getName().replace("_", " ").toUpperCase());
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                drinkButton(jTextField, String.valueOf(drink.getValue()));
            }
        });

        drinkPanel.add(jButton);
        drinkPanel.add(jTextField);
	}

    private void drinkButton(JTextField jTextField, String price) {
        jTextField1.setText(price);
        Component[] comp = drinkPanel.getComponents();
        for (int i = 0; i < comp.length; i++) {
            if (comp[i] instanceof JTextField) {
                JTextField jtf = (JTextField) comp[i];
                if (jtf.equals(jTextField)) {
                    if (jtf.isVisible()) {
                        jtf.setVisible(false);
                    } else {
                        jtf.setVisible(true);
                    }
                } else {
                    jtf.setVisible(false);
                }
            }
        }
    }

    private void coinButton(JTextField jTextField) {
        Component[] comp = coinPanel.getComponents();
        for (int i = 0; i < comp.length; i++) {
            if (comp[i] instanceof JTextField) {
                JTextField jtf = (JTextField) comp[i];
                if (jtf.equals(jTextField)) {
                    if (jtf.isVisible()) {
                        jtf.setVisible(false);
                    } else {
                        jtf.setVisible(true);
                    }
                } else {
                    jtf.setVisible(false);
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel coinPanel;
    private javax.swing.JPanel drinkPanel;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
