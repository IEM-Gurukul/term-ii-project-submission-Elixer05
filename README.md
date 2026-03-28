[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/pG3gvzt-)
# PCCCS495 – Term II Project

## Project Title
Library Collection Management System

## Problem Statement (max 150 words)
Libraries managing their book collections manually or through unstructured 
digital methods face significant challenges in maintaining accurate and 
up-to-date records. Librarians often struggle with tracking book availability, 
monitoring stock levels, and recording borrowing and returning transactions 
efficiently. The absence of a structured system leads to data inconsistencies, 
difficulty in locating specific books, and the inability to identify low-stock 
titles that require restocking.

## Target User
- School and college librarians managing daily book transactions
- Library assistants tracking borrowing and returning of books
- Small community or organization libraries without enterprise software
- CS/IT students learning DAO Pattern and File Persistence in Java

## Core Features
- Add, edit, and delete books from the collection
- Borrow a book — decreases available quantity by 1
- Return a book — increases available quantity by 1
- Prevents borrowing when no copies are available
- Search books by title, author, or ISBN
- Filter books by genre or availability
- Low stock alert for books with copies at or below threshold
- Auto-saves data to books.dat after every operation
- Auto-loads data on application startup — no data loss on close
- Input validation with error messages for invalid entries
- Confirmation dialogs before delete and borrow operations

---

## OOP Concepts Used

- **Abstraction:** `BookDAO` interface abstracts all data access operations. 
The UI and Service layers only know what operations exist, not how they 
are implemented.

- **Encapsulation:** `Book.java` keeps all fields private with public 
getters and setters. Each layer hides its internal logic from other layers.

- **Polymorphism:** `BookDAO dao = new BookDAOImpl()` — the variable is 
declared as the interface type while the object is the concrete 
implementation, allowing storage to be swapped without changing other layers.

- **Inheritance:** UI classes extend Swing components — `MainFrame extends 
JFrame`, `BookTablePanel extends JPanel`, enabling custom behavior on top 
of built-in Swing functionality.

- **Exception Handling:** Handles FileNotFoundException on first launch, 
ClassNotFoundException during deserialization, and business rule violations 
such as borrowing when no copies are available or entering negative quantity.

- **Collections:** `List<Book>` and `ArrayList` store all books in memory. 
Java Streams are used for searching, filtering, and low stock queries across 
the collection.

- **Design Pattern:** DAO Pattern is the central design pattern — isolating 
all file read and write operations inside `BookDAOImpl`, keeping the UI and 
Service layers independent of the data source.

---

## Proposed Architecture Description
The system follows a 4-layer architecture based on the DAO Pattern where 
each layer has a single responsibility and communicates only with the layer 
directly below it.

**UI Layer** — Contains all Swing components: `MainFrame`, `BookTablePanel`, 
`BookFormPanel`, and `SearchPanel`. Handles user input and display only. 
Never directly accesses files or data.

**Service Layer** — `BookService` sits between the UI and DAO. Validates 
all input and enforces business rules such as preventing borrows when no 
copies are available before passing data down.

**DAO Layer** — `BookDAO` interface defines all data operations. 
`BookDAOImpl` implements them, managing the in-memory `List<Book>` and 
handling all file read and write operations.

**Persistence Layer** — A binary file `books.dat` stored on disk. Data is 
written using `ObjectOutputStream` and read back using `ObjectInputStream` 
on every application launch.
```
UI Layer
   ↓ calls
Service Layer   ← validates and applies business rules
   ↓ calls
DAO Layer       ← performs CRUD on in-memory list
   ↓ reads/writes
books.dat       ← persists data between sessions
```
---

## How to Run
### Prerequisites
- JDK 17 or above installed
- Any Java IDE (IntelliJ IDEA, VS Code with Java Extension Pack)

### Steps
1. Clone the repository
```
   git clone https://github.com/IEM-Gurukul/term-ii-project-submission-Elixer05.git
```

2. Open the project in your IDE

3. Navigate to the `src` folder

4. Compile all Java files
```
   cd src
   javac *.java
```

5. Run the application
```
   java Main
