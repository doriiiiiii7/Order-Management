package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
/**
 * @author Tirsogoiu Dorina-Mihaela, grupa 302210
 * @since 18.04.2021
 */
public  class TableView extends JFrame {

    /**
     * Constructorul creaza interfata utilizata in vederea afisarii tabelelor din baza de date
     * @param data      - pentru tabel; contine datele sub forma unei matrice de obiecte
     * @param header    - pentru tabel; contine numele coloanelor sub forma unui vector
     * @param title     - pentru setarea titlului ferestrei
     */
    public TableView(Object[][] data, Object[] header, String title) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setTitle(title);
        setBackground(new Color(255, 240, 245));
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBackground(new Color(255, 240, 245));
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JTable table = new JTable(data, header);
        table.getTableHeader().setBackground(new Color(255, 182, 193));
        table.setBackground(new Color(255, 240, 245));
        scrollPane.setViewportView(table);
    }
}

