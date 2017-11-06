package Table;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class BookModel extends AbstractTableModel {

    private List<Book> books = new ArrayList<>();

    public BookModel() {

        BooksFileReader task = new BooksFileReader(books);
        task.execute();
    }

    public Book getBook(int i){
        return books.get(i);
    }

    public void addBook(Book b){
       books.add(b);
       fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book cur=books.get(rowIndex);
        switch (columnIndex){
            case 0:
                return cur.getName();
            case 1:
                return cur.getAuthor().toString();
            case 2:
                return cur.getPublisher().toString();
            case 3:
                return cur.getPrice();
            case 4:
                return cur.getQty();
        }
        return null;
    }


    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Book name";
            case 1:
                return "Author";
            case 2:
                return "Publisher";
            case 3:
                return "Price";
            case 4:
                return "Count";
        }
        return "";
    }

    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Double.class;
            case 4:
                return Integer.class;
        }
        return Object.class;
    }

    public void deleteBook(int[] selectedRows) {
        int k=0;
        for (int i:
                selectedRows) {

            books.remove(i-k);
            k++;
            fireTableDataChanged();
        }
    }

    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return false;
    }


    public void save() {

        BooksFileWriter task = new BooksFileWriter(books);
        task.execute();


    }

    public void editBook(int row, Book book) {
        books.add(row,book);
    }
}
