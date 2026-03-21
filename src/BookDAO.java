import java.util.List;

public interface BookDAO 
{
    void add(Book book);
    void update(Book book);
    void delete(int id);
    Book findById(int id);
    List<Book> findAll();
    List<Book> search(String keyword);
}
