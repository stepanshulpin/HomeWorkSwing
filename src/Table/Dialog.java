package Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Dialog extends JDialog {

    protected JPanel rootPanel = new JPanel(new BorderLayout());
    protected JTextArea textBookName = new JTextArea();
    protected JTextArea textAuthorName = new JTextArea();
    protected JTextArea textAuthorEmail = new JTextArea();
    protected JTextArea textAuthorGender = new JTextArea();
    protected JTextArea textPublisherName = new JTextArea();
    protected JTextArea textPublisherDate = new JTextArea();
    protected JTextArea textBookPrice = new JTextArea();
    protected JTextArea textBookQty = new JTextArea();
    protected JButton btnOk = new JButton("OK");
    public Dialog(JFrame parent, String title) {
        super(parent, title, true);
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                boolean canDispose=true;

                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    if(Double.valueOf(textBookPrice.getText())<0) throw new NumberFormatException();
                    if(Integer.valueOf(textBookQty.getText())<0) throw new NumberFormatException();
                    formatter.parse(textPublisherDate.getText());
                    Gender.valueOf(textAuthorGender.getText());
                }
                catch (ParseException ex)
                {
                    canDispose=false;
                    Error er = new Error(Dialog.this,"Incorrect date");
                    er.setVisible(true);
                }
                catch (NumberFormatException ex)
                {
                    canDispose=false;
                    Error er = new Error(Dialog.this,"Incorrect price or quantity");
                    er.setVisible(true);
                }
                catch (IllegalArgumentException ex)
                {
                    canDispose=false;
                    Error er = new Error(Dialog.this,"Incorrect gender");
                    er.setVisible(true);
                }


                if(canDispose) {
                    Dialog.this.dispose();
                }
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
        grid.add(textBookName);
        grid.add(textAuthorName);
        grid.add(textAuthorEmail);
        grid.add(textAuthorGender);
        grid.add(textPublisherName);
        grid.add(textPublisherDate);
        grid.add(textBookPrice);
        grid.add(textBookQty);
        rootPanel.add(grid, BorderLayout.CENTER);
        rootPanel.add(btnOk, BorderLayout.SOUTH);
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
    public Book getBook() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return new Book(textBookName.getText(),
                    new Author(textAuthorName.getText(), textAuthorEmail.getText(), Gender.valueOf(textAuthorGender.getText())),
                    new Publisher(textPublisherName.getText(), formatter.parse(textPublisherDate.getText())),
                    Double.valueOf(textBookPrice.getText()), Integer.valueOf(textBookQty.getText()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }





}
