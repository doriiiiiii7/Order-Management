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
public class AuxiliarView extends JFrame {

    private JPanel contentPane;
    private JLabel nameLabel;
    private JTextField idField;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField emailField;
    private ButtonGroup group;
    private JRadioButton insertButton;
    private JRadioButton updateButton;
    private JRadioButton deleteButton;
    private JRadioButton viewAllButton;
    private JButton proceedButton;

    /**
     * Constructorul creaza interfata grafica prin intermediul careia se selecteaza operatia dorita
     * si se introduc datele necesare realizarii respectivei operatii
     * @param titleName - numele ferestrei si titlul
     * @param col1      - numele celei de-a doua coloane din tabel
     * @param col2      - numele celei de-a treia coloane din tabel
     * @param col3      - numele celei de-a patra coloane din tabel
     */
    public AuxiliarView(String titleName, String col1, String col2, String col3) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        contentPane = new JPanel();
            contentPane.setBackground(new Color(255, 240, 245));
            contentPane.setForeground(new Color(255, 240, 245));
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setTitle(titleName);
            contentPane.setLayout(null);

        insertButton = new JRadioButton("INSERT");
            insertButton.setHorizontalAlignment(SwingConstants.LEFT);
            insertButton.setBackground(new Color(255, 240, 245));
            insertButton.setBounds(20, 60, 100, 25);
            contentPane.add(insertButton);

        updateButton = new JRadioButton("UPDATE");
            updateButton.setHorizontalAlignment(SwingConstants.LEFT);
            updateButton.setBackground(new Color(255, 240, 245));
            updateButton.setBounds(20, 100, 100, 25);
            contentPane.add(updateButton);

        deleteButton = new JRadioButton("DELETE");
            deleteButton.setHorizontalAlignment(SwingConstants.LEFT);
            deleteButton.setBackground(new Color(255, 240, 245));
            deleteButton.setBounds(20, 140, 100, 25);
            contentPane.add(deleteButton);

        viewAllButton = new JRadioButton("VIEW ALL");
            viewAllButton.setHorizontalAlignment(SwingConstants.LEFT);
            viewAllButton.setBackground(new Color(255, 240, 245));
            viewAllButton.setBounds(20, 180, 100, 25);
            contentPane.add(viewAllButton);

        ButtonGroup group = new ButtonGroup();
        group.add(insertButton);
        group.add(updateButton);
        group.add(deleteButton);
        group.add(viewAllButton);

        JLabel titleLabel = new JLabel(titleName);
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
            titleLabel.setBounds(120, 10, 110, 25);
            contentPane.add(titleLabel);

        JLabel idLabel = new JLabel("id:");
            idLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            idLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
            idLabel.setBounds(150, 60, 70, 20);
            contentPane.add(idLabel);

        idField = new JTextField();
            idField.setBounds(225, 60, 120, 20);
            contentPane.add(idField);
            idField.setColumns(10);

        nameLabel = new JLabel(col1);
            nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
            nameLabel.setBounds(150, 100, 70, 20);
            contentPane.add(nameLabel);

        nameField = new JTextField();
            nameField.setColumns(10);
            nameField.setBounds(225, 100, 120, 20);
            contentPane.add(nameField);

        JLabel lblAddress = new JLabel(col2);
            lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
            lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
            lblAddress.setBounds(150, 140, 70, 20);
            contentPane.add(lblAddress);

        addressField = new JTextField();
            addressField.setColumns(10);
            addressField.setBounds(225, 140, 120, 20);
            contentPane.add(addressField);

        JLabel emailLabel = new JLabel(col3);
            emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
            emailLabel.setBounds(150, 180, 70, 20);
            contentPane.add(emailLabel);

        emailField = new JTextField();
            emailField.setColumns(10);
            emailField.setBounds(225, 180, 120, 20);
            contentPane.add(emailField);

        proceedButton = new JButton("PROCEED");
            proceedButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
            proceedButton.setBounds(120, 220, 110, 25);
            contentPane.add(proceedButton);
    }
    /**
     * Metoda care adauga un Action Listener pe radioButton-ul de Insert din interfata secundara
     * @param actionListener
     */
    public void addInsertButtonListener(ActionListener actionListener){
        insertButton.addActionListener(actionListener);
    }
    /**
     * Metoda care adauga un Action Listener pe radioButton-ul de Update din interfata secundara
     * @param actionListener
     */
    public void addUpdateButtonListener(ActionListener actionListener){
        updateButton.addActionListener(actionListener);
    }
    /**
     * Metoda care adauga un Action Listener pe radioButton-ul de Delete din interfata secundara
     * @param actionListener
     */
    public void addDeleteButtonListener(ActionListener actionListener){
        deleteButton.addActionListener(actionListener);
    }
    /**
     * Metoda care adauga un Action Listener pe radioButton-ul de View All din interfata secundara
     * @param actionListener
     */
    public void addViewAllButtonListener(ActionListener actionListener){
        viewAllButton.addActionListener(actionListener);
    }
    /**
     * Metoda care adauga un Action Listener pe butonul de Proceed din interfata secundara
     * @param actionListener
     */
    public void addProceedButtonListener(ActionListener actionListener){
        proceedButton.addActionListener(actionListener);
    }

    /**
     * Metoda afiseaza un mesaj de eroare pe ecran
     * @param message   - mesajul de eroare ce urmeaza a fi afisat
     */
    public void printError(String message){
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Metoda afiseaza un mesaj informativ pe ecran
     * @param message   - mesajul ce urmeaza a fi afisat
     */
    public void printMessage(String message){
        JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public JTextField getIdField() {
        return idField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getAddressField() {
        return addressField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JRadioButton getInsertButton() {
        return insertButton;
    }

    public JRadioButton getUpdateButton() {
        return updateButton;
    }

    public JRadioButton getDeleteButton() {
        return deleteButton;
    }

    public JRadioButton getViewAllButton() {
        return viewAllButton;
    }





}
