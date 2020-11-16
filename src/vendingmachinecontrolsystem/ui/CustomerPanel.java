/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachinecontrolsystem.ui;

import vendingmachinecontrolsystem.util.CurrencyHelper;
import vendingmachinecontrolsystem.model.Drink;
import vendingmachinecontrolsystem.model.Stock;
import vendingmachinecontrolsystem.model.Coin;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Dannel
 */
public class CustomerPanel extends javax.swing.JFrame implements Observer {

    /**
     * Creates new form CustomerPanel
     */
    private static final String NOT_IN_STOCK_TEXT = "Not in Stock";
    private static final String NO_COINS_TEXT = "0 c";
    private static final String NO_CAN_TEXT = "NO CAN";

    private List<Stock> COIN_STOCKS;
    private List<Stock> DRINK_STOCKS;
    private Drink SELECTED_DRINK;

    private static volatile CustomerPanel customerPanel;

    public static CustomerPanel get() {
        if (customerPanel == null) {
            synchronized (CustomerPanel.class) {
                if (customerPanel == null) {
                    customerPanel = new CustomerPanel();
                }
            }
        }
        return customerPanel;
    }

    private CustomerPanel() {
        if (customerPanel != null) {
            throw new RuntimeException("Use get() method to get the single instance of this class.");
        }
        initComponents();
    }

    public void setCoinStocks(List<Stock> coinList) {
        COIN_STOCKS = coinList;
        Iterator iterator = COIN_STOCKS.iterator();
        while (iterator.hasNext()) {
            Coin coin = (Coin) iterator.next();

            JButton jButton = new JButton();
            jButton.setFont(new java.awt.Font("Tahoma", 0, 14));
            jButton.setText(coin.getName());
            jButton.setEnabled(false);
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    coinEvent(coin);
                }
            });
            coinPanel.add(jButton);
        }
    }

    private void coinEvent(Coin coin) {
        if (coin.getValue() == 0) {
            invalidCoin();
        } else {
            validCoin(coin);
        }
    }

    private void validCoin(Coin coin) {
        invalidCoinLabel.setText(null);
        noChangeLabel.setText(null);
        double amount = coin.getValue();
        amount += CurrencyHelper.coinsToAmount(insertedAmountTf.getText());
        coin.setQuantity(coin.getQuantity() + 1);
        insertedAmountTf.setText(CurrencyHelper.toCoins(amount));
        if (amount >= SELECTED_DRINK.getValue()) {
            dispenseDrink();
        }
    }

    private void invalidCoin() {
        invalidCoinLabel.setText("Invalid Coin");
        //noChangeLabel.setText("Insufficient Change Available");
    }

    private void enableCoinButtons() {
        Component[] comp = coinPanel.getComponents();
        for (int i = 0; i < comp.length; i++) {
            if (comp[i] instanceof JButton) {
                ((JButton) comp[i]).setEnabled(true);
            }
        }
        comp = drinkPanel.getComponents();
        for (int i = 0; i < comp.length; i++) {
            if (comp[i] instanceof JPanel) {
                Component[] drinkComp = ((JPanel) comp[i]).getComponents();
                for (int j = 0; j < drinkComp.length; j++) {
                    if (drinkComp[j] instanceof JButton) {
                        ((JButton) drinkComp[j]).setEnabled(false);
                    }
                }
            }
        }
    }

    private void disableCoinButtons() {
        Component[] comp = coinPanel.getComponents();
        for (int i = 0; i < comp.length; i++) {
            if (comp[i] instanceof JButton) {
                ((JButton) comp[i]).setEnabled(false);
            }
        }
        comp = drinkPanel.getComponents();
        for (int i = 0; i < comp.length; i++) {
            if (comp[i] instanceof JPanel) {
                Component[] drinkComp = ((JPanel) comp[i]).getComponents();
                for (int j = 0; j < drinkComp.length; j++) {
                    if (drinkComp[j] instanceof JButton) {
                        ((JButton) drinkComp[j]).setEnabled(true);
                    }
                }
            }
        }
    }

    public void setDrinkStocks(List<Stock> drinkList) {
        DRINK_STOCKS = drinkList;
        Iterator iterator = DRINK_STOCKS.iterator();
        while (iterator.hasNext()) {
            Drink drink = (Drink) iterator.next();

            JPanel jPanel = new JPanel();
            jPanel.setLayout(new GridLayout());

            JButton jButton = new JButton();
            jButton.setFont(new java.awt.Font("Tahoma", 0, 14));
            jButton.setText(drink.getName());
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    drinkEvent(drink);
                }
            });

            JTextField jTextField = new JTextField();
            jTextField.setText(CurrencyHelper.toCoins(drink.getValue()));
            jTextField.setEditable(false);
            jTextField.setBackground(new java.awt.Color(0, 0, 0));
            jTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jTextField.setForeground(new java.awt.Color(255, 255, 255));
            jTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

            JLabel jLabel = new JLabel();
            jLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            jLabel.setForeground(java.awt.Color.red);
            jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            if (drink.getQuantity() == 0) {
                jLabel.setText(NOT_IN_STOCK_TEXT);
                jLabel.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.red));
                jButton.setEnabled(false);
            }

            jPanel.add(jButton);
            jPanel.add(jTextField);
            jPanel.add(jLabel);
            drinkPanel.add(jPanel);
        }
    }

    private void drinkEvent(Drink drink) {
        SELECTED_DRINK = drink;
        selectedDrinkLabel.setText(SELECTED_DRINK.getName());
        System.out.println(SELECTED_DRINK.toString());
        enableCoinButtons();
        coinCollectTf.setText(NO_COINS_TEXT);
    }

    private void dispenseDrink() {
        selectedDrinkLabel.setText(null);
        collectCanTf.setText(SELECTED_DRINK.getName());
        calculateChange();
        new Timer().schedule(
                new TimerTask() {
            @Override
            public void run() {
                resetState();
            }
        }, 1000);
    }

    private void calculateChange() {
        double insertedAmount = CurrencyHelper.coinsToAmount(insertedAmountTf.getText());
        double lowestDenominator = COIN_STOCKS.get(0).getValue();
        for (int i = 0; i < COIN_STOCKS.size(); i++) {
            Coin coin = (Coin) COIN_STOCKS.get(i);
            if (coin.getQuantity() > 0) {
                if (insertedAmount >= coin.getValue()) {
                    insertedAmount = CurrencyHelper.subtract(insertedAmount, coin.getValue());
                }
            }
            if (insertedAmount <= 0 || insertedAmount < lowestDenominator) {
                break;
            }
            if (i == COIN_STOCKS.size() - 1) {
                i = -1;
            }
        }
        System.out.println("change remaining: " + insertedAmount);
    }

    private void resetState() {
        insertedAmountTf.setText(NO_COINS_TEXT);
        coinCollectTf.setText(NO_COINS_TEXT);
        collectCanTf.setText(NO_CAN_TEXT);
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
        jLabel2 = new javax.swing.JLabel();
        invalidCoinLabel = new javax.swing.JLabel();
        coinPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        insertedAmountTf = new javax.swing.JTextField();
        drinkPanel = new javax.swing.JPanel();
        noChangeLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        terminateButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        coinCollectTf = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        collectCanTf = new javax.swing.JTextField();
        selectedDrinkLabel = new javax.swing.JLabel();

        setTitle("VMCS - Customer Panel");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Soft Drink Dispenser");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Enter Coins Here");

        invalidCoinLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        invalidCoinLabel.setForeground(java.awt.Color.red);
        invalidCoinLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        invalidCoinLabel.setPreferredSize(new java.awt.Dimension(0, 20));

        coinPanel.setMinimumSize(new java.awt.Dimension(50, 50));
        coinPanel.setLayout(new java.awt.GridLayout(1, 0));

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Total Amount of Money Inserted: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel2.add(jLabel4, gridBagConstraints);

        insertedAmountTf.setEditable(false);
        insertedAmountTf.setBackground(new java.awt.Color(0, 0, 0));
        insertedAmountTf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        insertedAmountTf.setForeground(new java.awt.Color(255, 255, 255));
        insertedAmountTf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        insertedAmountTf.setText("0 c");
        insertedAmountTf.setPreferredSize(new java.awt.Dimension(100, 27));
        jPanel2.add(insertedAmountTf, new java.awt.GridBagConstraints());

        drinkPanel.setPreferredSize(new java.awt.Dimension(100, 100));
        drinkPanel.setLayout(new java.awt.GridLayout(0, 1));

        noChangeLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        noChangeLabel.setForeground(java.awt.Color.red);
        noChangeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noChangeLabel.setPreferredSize(new java.awt.Dimension(0, 20));

        terminateButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        terminateButton.setText("Terminate and Return Cash");
        terminateButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        terminateButton.setPreferredSize(new java.awt.Dimension(201, 30));
        terminateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminateButtonActionPerformed(evt);
            }
        });
        jPanel4.add(terminateButton);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Collect Coins:      ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel5.add(jLabel6, gridBagConstraints);

        coinCollectTf.setEditable(false);
        coinCollectTf.setBackground(new java.awt.Color(0, 0, 0));
        coinCollectTf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        coinCollectTf.setForeground(new java.awt.Color(255, 255, 255));
        coinCollectTf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        coinCollectTf.setText("0 c");
        coinCollectTf.setPreferredSize(new java.awt.Dimension(100, 27));
        jPanel5.add(coinCollectTf, new java.awt.GridBagConstraints());

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Collect Can Here: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel6.add(jLabel7, gridBagConstraints);

        collectCanTf.setEditable(false);
        collectCanTf.setBackground(new java.awt.Color(0, 0, 0));
        collectCanTf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        collectCanTf.setForeground(new java.awt.Color(255, 255, 255));
        collectCanTf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        collectCanTf.setText("NO CAN");
        collectCanTf.setPreferredSize(new java.awt.Dimension(100, 27));
        jPanel6.add(collectCanTf, new java.awt.GridBagConstraints());

        selectedDrinkLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        selectedDrinkLabel.setForeground(java.awt.Color.red);
        selectedDrinkLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        selectedDrinkLabel.setPreferredSize(new java.awt.Dimension(0, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(invalidCoinLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(coinPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                    .addComponent(drinkPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noChangeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(selectedDrinkLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(coinPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(invalidCoinLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(selectedDrinkLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(drinkPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(noChangeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void terminateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terminateButtonActionPerformed
        // TODO add your handling code here:
        disableCoinButtons();
        SELECTED_DRINK = null;
        selectedDrinkLabel.setText(null);
        coinCollectTf.setText(insertedAmountTf.getText());
        insertedAmountTf.setText(NO_COINS_TEXT);
    }//GEN-LAST:event_terminateButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField coinCollectTf;
    private javax.swing.JPanel coinPanel;
    private javax.swing.JTextField collectCanTf;
    private javax.swing.JPanel drinkPanel;
    private javax.swing.JTextField insertedAmountTf;
    private javax.swing.JLabel invalidCoinLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel noChangeLabel;
    private javax.swing.JLabel selectedDrinkLabel;
    private javax.swing.JButton terminateButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Coin) {
            Coin coin = (Coin) o;
            System.out.println(coin.toString());
        } else if (o instanceof Drink) {
            Drink drink = (Drink) o;
            System.out.println(drink.toString());
        }
    }
}
