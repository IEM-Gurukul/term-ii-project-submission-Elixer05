import javax.swing.*;
import java.awt.*;

public class Main extends JFrame 
{
private BookService bookService;
private BookTablePanel tablePanel;
private BookFormPanel formPanel;
private SearchPanel searchPanel;

public Main() 
{
bookService = new BookService();

setTitle("Library Collection Management System");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setSize(900, 600);
setLocationRelativeTo(null);
setLayout(new BorderLayout());


JLabel titleLabel = new JLabel("Library Collection Management System", JLabel.CENTER);
titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
add(titleLabel, BorderLayout.NORTH);


tablePanel = new BookTablePanel(bookService);
add(tablePanel, BorderLayout.CENTER);


formPanel = new BookFormPanel(bookService, tablePanel);
add(formPanel, BorderLayout.EAST);


searchPanel = new SearchPanel(bookService, tablePanel);
add(searchPanel, BorderLayout.SOUTH);

setVisible(true);
}

public static void main(String[] args) 
{
SwingUtilities.invokeLater(() -> new Main());
}
}
