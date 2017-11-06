package Table;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class BooksFileWriter extends SwingWorker<Void,Void> {

    List<Book> books;

    public BooksFileWriter(List<Book> books) {
        this.books=books;
    }

    @Override
    protected Void doInBackground() {
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



        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
