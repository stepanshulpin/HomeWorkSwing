package Table;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class BookModel extends AbstractTableModel {

    private List<Book> books = new ArrayList<>();

    public BookModel() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            File file = new File("library.txt");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            while (line != null) {
                StringTokenizer stok = new StringTokenizer(line, "#");
                try {
                    books.add(new Book(stok.nextToken(),
                            new Author(stok.nextToken(), stok.nextToken(), Gender.valueOf(stok.nextToken())),
                            new Publisher(stok.nextToken(), formatter.parse(stok.nextToken())),
                            Double.valueOf(stok.nextToken()), Integer.valueOf(stok.nextToken())));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        for (int i:
                selectedRows) {

            books.remove(i);
            fireTableDataChanged();
        }
    }

    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return false;
    }


    public void save() {

        File fileSafe = new File("library.txt");
        FileWriter fr = null;

        try {
            fr = new FileWriter(fileSafe);
            for (Book book:books) {

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(book.getName()).append("#");
                stringBuilder.append(book.getAuthor().getName()).append("#");
                stringBuilder.append(book.getAuthor().getEmail()).append("#");
                stringBuilder.append(book.getAuthor().getGender().toString()).append("#");
                stringBuilder.append(book.getPublisher().getName()).append("#");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                stringBuilder.append(formatter.format(book.getPublisher().getDate())).append("#");
                stringBuilder.append(book.getPrice()).append("#");
                stringBuilder.append(book.getQty());

                String data = stringBuilder.toString();

                fr.write(data);
                fr.write('\n');

            }

            fr.write('E');


        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
