package Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Edit extends Dialog {

    public Edit(JFrame parent, Book book) {
        super(parent,"Edit");
        textBookName.setText(book.getName());
        textAuthorName.setText(book.getAuthor().getName());
        textAuthorEmail.setText(book.getAuthor().getEmail());
        textAuthorGender.setText(book.getAuthor().getGender().toString());
        textPublisherName.setText(book.getPublisher().getName());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        textPublisherDate.setText(formatter.format(book.getPublisher().getDate()));
        textBookPrice.setText(String.valueOf(book.getPrice()));
        textBookQty.setText(String.valueOf(book.getQty()));
    }

}
