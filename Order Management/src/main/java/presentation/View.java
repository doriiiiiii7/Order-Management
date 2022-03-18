package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
/**
 * @author Tirsogoiu Dorina-Mihaela, grupa 302210
 * @since 18.04.2021
 */
public class View extends JFrame {
    private JPanel contentPane;
    private JButton clientsButton;
    private JButton productsButton;
    private JButton ordersButton;

    /**
     * Constructorul creaza interfata grafica principala, cu cele trei butoane ce vor deschide
     * ferestrele secundare, in vederea modificarii datelor din tabele
     */
    public View() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 150);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 240, 245));
        contentPane.setForeground(new Color(255, 240, 245));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setTitle("Order Management");
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Order Management");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        titleLabel.setBounds(150, 20, 150, 20);
        contentPane.add(titleLabel);

        clientsButton = new JButton("Clients");
        clientsButton.setBackground(new Color(255, 182, 193));
        clientsButton.setForeground(new Color(0, 0, 0));
        clientsButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        clientsButton.setBounds(30, 70, 90, 25);
        contentPane.add(clientsButton);

        productsButton = new JButton("Products");
        productsButton.setBackground(new Color(255, 182, 193));
        productsButton.setForeground(new Color(0, 0, 0));
        productsButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        productsButton.setBounds(170, 70, 90, 25);
        contentPane.add(productsButton);

        ordersButton = new JButton("Orders");
        ordersButton.setBackground(new Color(255, 182, 193));
        ordersButton.setForeground(new Color(0, 0, 0));
        ordersButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
        ordersButton.setBounds(310, 70, 90, 25);
        contentPane.add(ordersButton);
    }

    /**
     * Metoda care adauga un Action Listener pe butonul de Clients din interfata principala
     * @param actionListener
     */
    public void addClientsButtonListener(ActionListener actionListener){
        clientsButton.addActionListener(actionListener);
    }

    /**
     * Metoda care adauga un Action Listener pe butonul de Products din interfata principala
     * @param actionListener
     */
    public void addProductsButtonListener(ActionListener actionListener){
        productsButton.addActionListener(actionListener);
    }

    /**
     * Metoda care adauga un Action Listener pe butonul de Orders din interfata principala
     * @param actionListener
     */
    public void addOrdersButtonListener(ActionListener actionListener){
        ordersButton.addActionListener(actionListener);
    }

}

