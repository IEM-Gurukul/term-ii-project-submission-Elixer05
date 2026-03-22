import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookDAOImp implements BookDAO
 {
    private static final String FILE_PATH = "books.dat";
    private List<Book> books;

    public BookDAOImp() 
    {
        books = loadFromFile();
    }

    @Override
    public void add(Book book) 
    {
        book.setId(generateId());
        books.add(book);
        saveToFile();
    }

    @Override
    public void update(Book book) 
    {
        for (int i = 0; i < books.size(); i++)
             {
            if (books.get(i).getId() == book.getId()) 
                {
                books.set(i, book);
                break;
            }
        }
        saveToFile();
    }

    @Override
    public void delete(int id)
     {
        books.removeIf(b -> b.getId() == id);
        saveToFile();
    }

    @Override
    public Book findById(int id) 
    {
        return books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Book> findAll()
     {
        return new ArrayList<>(books);
    }

    @Override
    public List<Book> search(String keyword)
     {
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(keyword.toLowerCase())
                        || b.getAuthor().toLowerCase().contains(keyword.toLowerCase())
                        || b.getIsbn().contains(keyword))
                .collect(Collectors.toList());
    }

    private void saveToFile()
     {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FILE_PATH)))
                 {
            oos.writeObject(books);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private List<Book> loadFromFile() 
    {
        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FILE_PATH)))
                 {
            return (List<Book>) ois.readObject();
        } 
        catch (IOException | ClassNotFoundException e)
         {
            return new ArrayList<>();
        }
    }

    private int generateId()
     {
        return books.stream()
                .mapToInt(Book::getId)
                .max()
                .orElse(0) + 1;
    }
}
