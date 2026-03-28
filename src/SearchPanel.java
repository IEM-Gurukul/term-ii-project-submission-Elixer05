import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SearchPanel extends JPanel 
{
private BookService bookService;
private BookTablePanel tablePanel;

private JTextField searchField;
private JButton searchBtn, resetBtn;

public SearchPanel(BookService bookService, BookTablePanel tablePanel) 
{
this.bookService = bookService;
this.tablePanel = tablePanel;

setLayout(new FlowLayout(FlowLayout.LEFT));
setBorder(BorderFactory.createTitledBorder("Search"));

searchField = new JTextField(20);
searchBtn   = new JButton("Search");
resetBtn    = new JButton("Reset");

add(new JLabel("Search by Title / Author / ISBN:"));
add(searchField);
add(searchBtn);
add(resetBtn);

searchBtn.addActionListener(e -> searchBooks());
resetBtn.addActionListener(e -> resetSearch());
}

private void searchBooks() 
{
String keyword = searchField.getText().trim();
if (keyword.isEmpty()) 
{
JOptionPane.showMessageDialog(this, "Please enter a search keyword.", "Error", JOptionPane.ERROR_MESSAGE);
return;
}
List<Book> results = bookService.searchBooks(keyword);
if (results.isEmpty()) 
{
JOptionPane.showMessageDialog(this, "No books found.", "Info", JOptionPane.INFORMATION_MESSAGE);
}
tablePanel.refreshTable(results);
}

private void resetSearch() 
{
searchField.setText("");
tablePanel.refreshTable();
}
}