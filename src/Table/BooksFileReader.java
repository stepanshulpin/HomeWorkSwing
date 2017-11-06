package Table;

import javax.swing.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BooksFileReader extends SwingWorker<List<Book>,Void> {

    private List<Book> books;

    public BooksFileReader(List<Book> books) {
        this.books=books;
    }

    @Override
    protected List<Book> doInBackground() throws Exception {
        int i =0;
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
        return books;
    }
}
