/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachinecontrolsystem.ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import vendingmachinecontrolsystem.controller.MachineryController;
import vendingmachinecontrolsystem.controller.MaintainerController;
import vendingmachinecontrolsystem.model.Coin;
import vendingmachinecontrolsystem.model.Drink;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.text.NumberFormat;
import java.util.HashMap;

/**
 *
 * @author Dannel
 */
public class MachineryPanel extends javax.swing.JFrame {

	private boolean isLocked = true;
	private HashMap<String,JTextField> drinkQtyTextField;
	private HashMap<String,JTextField> coinQtyTextField;

	/**
	 * Creates new form MachineryPanel
	 */
	public MachineryPanel() {
		initComponents();
		drinkQtyTextField=new HashMap<>();
		coinQtyTextField=new HashMap<>();
//		doorStateCheckBox.setSelected(isLocked);
	}

	public void addNewCoins(Coin coin) {
		if (!coin.getName().equalsIgnoreCase("Invalid")) {
			JLabel coinLabel = new JLabel();
			coinLabel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
			coinLabel.setFont(new java.awt.Font("Tahoma", 0, 14));
			coinLabel.setText(coin.getName());




			JTextField coinQty=quantityUI(coin.getQuantity());

			coinPanel.add(coinLabel);
			coinPanel.add(coinQty);
			coinQty.addActionListener(actionEvent -> {

				int qty=Integer.valueOf(coinQty.getText());
				if(qty>=0&&qty<=40){
					coin.setQuantity(Integer.valueOf(coinQty.getText()));

				}else{
					JOptionPane.showMessageDialog(this,
							"Quantity must be between 0 and 40");

				}
			});
			coinQtyTextField.put(coin.getName(),coinQty);

		}
	}



	public void addNewDrink(final  Drink drink) {
		JLabel drinkLabel = new JLabel();
		drinkLabel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		drinkLabel.setFont(new java.awt.Font("Tahoma", 0, 14));
		drinkLabel.setText(drink.getName().replace("_", " ").toUpperCase());

		JTextField drinkQty=quantityUI(drink.getQuantity());

		drinkQty.addActionListener(actionEvent -> {
			int qty=Integer.valueOf(drinkQty.getText());
			if(qty>=0&&qty<=20){
				drink.setQuantity(Integer.valueOf(drinkQty.getText()));

			}else{
				JOptionPane.showMessageDialog(this,
						"Quantity must be between 0 and 20");
			}
		});

		drinkPanel.add(drinkLabel);
		drinkPanel.add(drinkQty);
		drinkQtyTextField.put(drink.getName(),drinkQty);

	}

	private JFormattedTextField quantityUI(int quantity) {
		NumberFormat longFormat = NumberFormat.getIntegerInstance();

		NumberFormatter numberFormatter = new NumberFormatter(longFormat);
		numberFormatter.setValueClass(Long.class); //optional, ensures you will always get a long value
		numberFormatter.setAllowsInvalid(false); //this is the key!!
		numberFormatter.setMinimum(0l); //Optional


		JFormattedTextField quantityTextField = new JFormattedTextField(numberFormatter);
		quantityTextField.setEnabled(false);

		quantityTextField.setBackground(new java.awt.Color(0, 0, 0));
		quantityTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		quantityTextField.setForeground(java.awt.Color.YELLOW);
		quantityTextField.setHorizontalAlignment(JTextField.CENTER);
		quantityTextField.setText(String.valueOf(quantity));
		quantityTextField.setDisabledTextColor(java.awt.Color.YELLOW);

		return quantityTextField;
	}

	public void updateDrinkUI(Drink drink){
		drinkQtyTextField.get(drink.getName()).setText(String.valueOf(drink.getQuantity()));
	}
	public void updateCoinUI(Coin coin){
		if(coinQtyTextField.get(coin.getName())!=null){
			coinQtyTextField.get(coin.getName()).setText(String.valueOf(coin.getQuantity()));
		}

	}


	public void changeTextFieldState(boolean state){
		coinQtyTextField.values().forEach(v->{
			System.out.println("Coin ui update");
			changeTextFieldState(state, v);

		});
		drinkQtyTextField.values().forEach(textField->{
			System.out.println("Drink ui update");

			changeTextFieldState(state, textField);
		});
	}

	private void changeTextFieldState(boolean state, JTextField v) {
		if(state){
			v.setEnabled(false);

			v.setBackground(new Color(0, 0, 0));
			v.setFont(new Font("Tahoma", 0, 14)); // NOI18N
			v.setForeground(Color.YELLOW);
		}else{
			v.setEnabled(true);

			v.setBackground(new Color(255, 255, 255));
			v.setFont(new Font("Tahoma", 0, 14)); // NOI18N
			v.setForeground(Color.BLACK);
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		header = new javax.swing.JLabel();
		coinQtyLabel = new javax.swing.JLabel();
		coinPanel = new javax.swing.JPanel();
		drinkQtyLabel = new javax.swing.JLabel();
		drinkPanel = new javax.swing.JPanel();
		doorStateCheckBox = new javax.swing.JCheckBox();

		setTitle("VMCS - Machinery Panel");

		header.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		header.setText("Machinery Panel");

		coinQtyLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		coinQtyLabel.setText("Quantity of Coins Available:");

		coinPanel.setLayout(new java.awt.GridLayout(0, 2));

		drinkQtyLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		drinkQtyLabel.setText("Quantity of Drinks Available:");

		drinkPanel.setLayout(new java.awt.GridLayout(0, 2));

		doorStateCheckBox.setText("Door Locked");
		doorStateCheckBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		doorStateCheckBox.setSelected(true);
		doorStateCheckBox.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				jCheckBox1ItemStateChanged(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
						.addComponent(coinPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(drinkPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(coinQtyLabel).addComponent(drinkQtyLabel))
								.addGap(0, 0, Short.MAX_VALUE))
						.addComponent(doorStateCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(header).addGap(18, 18, 18)
						.addComponent(coinQtyLabel).addGap(18, 18, 18)
						.addComponent(coinPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 6, Short.MAX_VALUE)
						.addGap(18, 18, 18).addComponent(drinkQtyLabel).addGap(18, 18, 18)
						.addComponent(drinkPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
						.addGap(18, 18, 18).addComponent(doorStateCheckBox)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		pack();
		setLocationRelativeTo(null);
	}// </editor-fold>//GEN-END:initComponents

	private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_jCheckBox1ItemStateChanged
		// TODO add your handling code here:
		if(doorStateCheckBox.isSelected()&&!isLocked){
			MachineryController.get().lockDoor();
		}else{
			MachineryController.get().unLockDoor();

		}
		isLocked = !isLocked;

//		if(evt.getStateChange()== ItemEvent.SELECTED){
//			MachineryController.get().lockDoor();
//		}else{
//			MachineryController.get().unLockDoor();
//
//		}
//		if(isLocked){
//			MachineryController.get().lockDoor();
//
//		}else{
//			MachineryController.get().lockDoor();
//
//		}
		System.out.println("isLocked: " + isLocked);

	}// GEN-LAST:event_jCheckBox1ItemStateChanged

	public void updateDoorLockState(boolean isLocked){
//		doorStateCheckBox.setSelected(b);
		if(isLocked){
			if(!doorStateCheckBox.isSelected()){
				doorStateCheckBox.setSelected(true);

			}
		}else{
			if(doorStateCheckBox.isSelected()){
				doorStateCheckBox.setSelected(false);

			}
		}
	}
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JCheckBox doorStateCheckBox;
	private javax.swing.JLabel header;
	private javax.swing.JLabel coinQtyLabel;
	private javax.swing.JLabel drinkQtyLabel;
	private javax.swing.JPanel coinPanel;
	private javax.swing.JPanel drinkPanel;
	// End of variables declaration//GEN-END:variables
}
