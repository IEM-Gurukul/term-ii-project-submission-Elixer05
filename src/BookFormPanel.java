import javax.swing.*;
import java.awt.*;

public class BookFormPanel extends JPanel 
{
private BookService bookService;
private BookTablePanel tablePanel;

private JTextField titleField, authorField, genreField, isbnField, quantityField, priceField;
private JButton addBtn, updateBtn, deleteBtn, borrowBtn, returnBtn, clearBtn;

public BookFormPanel(BookService bookService, BookTablePanel tablePanel) 
{
this.bookService = bookService;
this.tablePanel = tablePanel;

setLayout(new GridBagLayout());
setBorder(BorderFactory.createTitledBorder("Book Details"));
setPreferredSize(new Dimension(280, 400));

GridBagConstraints gbc = new GridBagConstraints();
gbc.insets = new Insets(5, 5, 5, 5);
gbc.fill = GridBagConstraints.HORIZONTAL;

titleField    = new JTextField(15);
authorField   = new JTextField(15);
genreField    = new JTextField(15);
isbnField     = new JTextField(15);
quantityField = new JTextField(15);
priceField    = new JTextField(15);

String[] labels = {"Title:", "Author:", "Genre:", "ISBN:", "Quantity:", "Price:"};
JTextField[] fields = {titleField, authorField, genreField, isbnField, quantityField, priceField};

for (int i = 0; i < labels.length; i++)
{
gbc.gridx = 0; gbc.gridy = i;
add(new JLabel(labels[i]), gbc);
gbc.gridx = 1;
add(fields[i], gbc);
}

addBtn    = new JButton("Add");
updateBtn = new JButton("Update");
deleteBtn = new JButton("Delete");
borrowBtn = new JButton("Borrow");
returnBtn = new JButton("Return");
clearBtn  = new JButton("Clear");

JPanel btnPanel = new JPanel(new GridLayout(3, 2, 5, 5));
btnPanel.add(addBtn);
btnPanel.add(updateBtn);
btnPanel.add(deleteBtn);
btnPanel.add(borrowBtn);
btnPanel.add(returnBtn);
btnPanel.add(clearBtn);

gbc.gridx = 0; gbc.gridy = labels.length;
gbc.gridwidth = 2;
add(btnPanel, gbc);

// Button Actions
addBtn.addActionListener(e -> addBook());
updateBtn.addActionListener(e -> updateBook());
deleteBtn.addActionListener(e -> deleteBook());
borrowBtn.addActionListener(e -> borrowBook());
returnBtn.addActionListener(e -> returnBook());
clearBtn.addActionListener(e -> clearFields());
}

private void addBook()
 {
try 
{
Book book = new Book(
0,
titleField.getText(),
authorField.getText(),
genreField.getText(),
isbnField.getText(),
Integer.parseInt(quantityField.getText()),
Double.parseDouble(priceField.getText())
);
bookService.addBook(book);
tablePanel.refreshTable();
clearFields();
JOptionPane.showMessageDialog(this, "Book added successfully.");
} catch (IllegalArgumentException e) {
JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
}
}

private void updateBook()
 {
int id = tablePanel.getSelectedBookId();
if (id == -1) {
JOptionPane.showMessageDialog(this, "Please select a book to update.", "Error", JOptionPane.ERROR_MESSAGE);
return;
}
try 
{
Book book = new Book(
id,
titleField.getText(),
authorField.getText(),
genreField.getText(),
isbnField.getText(),
Integer.parseInt(quantityField.getText()),
Double.parseDouble(priceField.getText())
);
bookService.updateBook(book);
tablePanel.refreshTable();
clearFields();
JOptionPane.showMessageDialog(this, "Book updated successfully.");
} catch (IllegalArgumentException e) {
JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
}
}

private void deleteBook()
{
int id = tablePanel.getSelectedBookId();
if (id == -1) {
JOptionPane.showMessageDialog(this, "Please select a book to delete.", "Error", JOptionPane.ERROR_MESSAGE);
return;
}
int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this book?", "Confirm", JOptionPane.YES_NO_OPTION);
if (confirm == JOptionPane.YES_OPTION) {
bookService.deleteBook(id);
tablePanel.refreshTable();
clearFields();
JOptionPane.showMessageDialog(this, "Book deleted successfully.");
}
}

private void borrowBook() 
{
int id = tablePanel.getSelectedBookId();
if (id == -1) {
JOptionPane.showMessageDialog(this, "Please select a book to borrow.", "Error", JOptionPane.ERROR_MESSAGE);
return;
}
try
 {
bookService.borrowBook(id);
tablePanel.refreshTable();
JOptionPane.showMessageDialog(this, "Book borrowed successfully.");
} catch (IllegalStateException e) {
JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
}
}

private void returnBook() 
{
int id = tablePanel.getSelectedBookId();
if (id == -1) {
JOptionPane.showMessageDialog(this, "Please select a book to return.", "Error", JOptionPane.ERROR_MESSAGE);
return;
}
bookService.returnBook(id);
tablePanel.refreshTable();
JOptionPane.showMessageDialog(this, "Book returned successfully.");
}

private void clearFields() 
{
titleField.setText("");
authorField.setText("");
genreField.setText("");
isbnField.setText("");
quantityField.setText("");
priceField.setText("");
}
}