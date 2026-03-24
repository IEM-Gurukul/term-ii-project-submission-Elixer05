import java.util.List;
import java.util.stream.Collectors;

public class BookService {
private final BookDAO dao;

public BookService() 
{
this.dao = new BookDAOImp();
}

public void addBook(Book book)
    {
if (book.getTitle() == null || book.getTitle().isBlank())
    throw new IllegalArgumentException("Title is required");
if (book.getAuthor() == null || book.getAuthor().isBlank())
    throw new IllegalArgumentException("Author is required");
if (book.getQuantity() < 0)
    throw new IllegalArgumentException("Quantity cannot be negative");
if (book.getPrice() < 0)
    throw new IllegalArgumentException("Price cannot be negative");
dao.add(book);
}

public void updateBook(Book book)
{
if (book.getTitle() == null || book.getTitle().isBlank())
    throw new IllegalArgumentException("Title is required");
if (book.getAuthor() == null || book.getAuthor().isBlank())
    throw new IllegalArgumentException("Author is required");
if (book.getQuantity() < 0)
    throw new IllegalArgumentException("Quantity cannot be negative");
if (book.getPrice() < 0)
    throw new IllegalArgumentException("Price cannot be negative");
dao.update(book);
}

public void deleteBook(int id)
{
dao.delete(id);
}

public Book getBookById(int id)
{
return dao.findById(id);
}

public List<Book> getAllBooks()
{
return dao.findAll();
}

public List<Book> searchBooks(String keyword) 
{
return dao.search(keyword);
}

public void borrowBook(int id)
{
Book book = dao.findById(id);
if (book == null)
    throw new IllegalArgumentException("Book not found");
if (book.getQuantity() <= 0)
    throw new IllegalStateException("No copies available to borrow");
book.setQuantity(book.getQuantity() - 1);
book.setAvailable(book.getQuantity() > 0);
dao.update(book);
}

public void returnBook(int id)
    {
    Book book = dao.findById(id);
    if (book == null)
        throw new IllegalArgumentException("Book not found");
    book.setQuantity(book.getQuantity() + 1);
    book.setAvailable(true);
    dao.update(book);
}

public List<Book> getLowStockBooks(int threshold) 
{
    return dao.findAll().stream()
            .filter(b -> b.getQuantity() <= threshold)
            .collect(Collectors.toList());
}
}