import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BookTablePanel extends JPanel 
{
private JTable table;
private DefaultTableModel tableModel;
private BookService bookService;

private String[] columns = {"ID", "Title", "Author", "Genre", "ISBN", "Quantity", "Price", "Available"};

public BookTablePanel(BookService bookService) 
{
this.bookService = bookService;
setLayout(new BorderLayout());

tableModel = new DefaultTableModel(columns, 0) 
{
@Override
public boolean isCellEditable(int row, int column) 
{
    return false;
}
};

table = new JTable(tableModel);
table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
table.setRowHeight(25);
table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

JScrollPane scrollPane = new JScrollPane(table);
add(scrollPane, BorderLayout.CENTER);

refreshTable();
}

public void refreshTable() {
tableModel.setRowCount(0);
List<Book> books = bookService.getAllBooks();
for (Book b : books) {
tableModel.addRow(new Object[]{
b.getId(),
b.getTitle(),
b.getAuthor(),
b.getGenre(),
b.getIsbn(),
b.getQuantity(),
String.format("%.2f", b.getPrice()),
b.isAvailable() ? "Yes" : "No"
});
    }
}

public void refreshTable(List<Book> books) {
tableModel.setRowCount(0);
for (Book b : books) {
tableModel.addRow(new Object[]{
b.getId(),
b.getTitle(),
b.getAuthor(),
b.getGenre(),
b.getIsbn(),
b.getQuantity(),
String.format("%.2f", b.getPrice()),
b.isAvailable() ? "Yes" : "No"
});
}
}

public int getSelectedBookId() {
int row = table.getSelectedRow();
if (row == -1) return -1;
return (int) tableModel.getValueAt(row, 0);
}
}