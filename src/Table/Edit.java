package Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Edit extends JDialog {

    private JPanel rootPanel = new JPanel(new BorderLayout());
    private JTextArea text0 = new JTextArea();
    private JTextArea text1 = new JTextArea();
    private JTextArea text2 = new JTextArea();
    private JTextArea text3 = new JTextArea();
    private JTextArea text4 = new JTextArea();
    private JTextArea text5 = new JTextArea();
    private JTextArea text6 = new JTextArea();
    private JTextArea text7 = new JTextArea();
    private JButton btn = new JButton("OK");
    private JFrame par;
    public Edit(JFrame parent, Book book) {
        super(parent, "Edit", true);
        par=parent;
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Edit.this.dispose();
            }
        });
        this.getContentPane().add(rootPanel);
        JPanel grid = new JPanel(new GridLayout(2, 8, 1, 1));
        grid.add(new JLabel("Book name:"));
        grid.add(new JLabel("Author name:"));
        grid.add(new JLabel("Email:"));
        grid.add(new JLabel("Gender:"));
        grid.add(new JLabel("Publisher name:"));
        grid.add(new JLabel("Date:"));
        grid.add(new JLabel("Price:"));
        grid.add(new JLabel("Quantity:"));
        text0.setText(book.getName());
        text1.setText(book.getAuthor().getName());
        text2.setText(book.getAuthor().getEmail());
        text3.setText(book.getAuthor().getGender().toString());
        text4.setText(book.getPublisher().getName());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        text5.setText(formatter.format(book.getPublisher().getDate()));
        text6.setText(String.valueOf(book.getPrice()));
        text7.setText(String.valueOf(book.getQty()));
        grid.add(text0);
        grid.add(text1);
        grid.add(text2);
        grid.add(text3);
        grid.add(text4);
        grid.add(text5);
        grid.add(text6);
        grid.add(text7);
        rootPanel.add(grid, BorderLayout.CENTER);
        rootPanel.add(btn, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(parent);
    }
    public Book getBook() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            if(Double.valueOf(text6.getText())<0) throw new NumberFormatException();
            if(Integer.valueOf(text7.getText())<0) throw new NumberFormatException();
            return new Book(text0.getText(),
                    new Author(text1.getText(), text2.getText(), Gender.valueOf(text3.getText())),
                    new Publisher(text4.getText(), formatter.parse(text5.getText())),
                    Double.valueOf(text6.getText()), Integer.valueOf(text7.getText()));
        }
        catch (ParseException ex)
        {
            System.out.println("Here");
            Error er = new Error(par,"Incorrect date");
            er.setVisible(true);
        }
        catch (NumberFormatException ex)
        {
            Error er = new Error(par,"Incorrect price or quantity");
            er.setVisible(true);
        }
        catch (IllegalArgumentException ex)
        {
            Error er = new Error(par,"Incorrect gender");
            er.setVisible(true);
        }
        return null;
    }

}
