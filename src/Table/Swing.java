package Table;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

public class Swing extends JFrame {
    public Swing() {
        super("CommandButtons");
        setSize(350, 250);
        setLocation(150, 100);
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        BookModel m=new BookModel();
        JTable table=new JTable(m);
        JScrollPane jScrollPane=new JScrollPane(table);
        add(jScrollPane);
        JButton btn1= new JButton("Add");
        JButton btn2= new JButton("Delete");
        JButton btn3= new JButton("Save");

        table.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 2)
                {
                    Point p = e.getPoint();
                    int row = table.rowAtPoint(p);
                    int[] rows = {row};
                    Edit edit = new Edit(Swing.this,m.getBook(row));
                    edit.setVisible (true);
                    Book book = edit.getBook();
                    if(book!=null) {
                        m.deleteBook(rows);
                        m.addBook(edit.getBook());
                    }
                    //int column = table.columnAtPoint(p);
                    System.out.println("Double click on " + row + " row");
                }
            }
        });

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.save();
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.deleteBook(table.getSelectedRows());
        }
        });

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dialog dialog = new Dialog(Swing.this);
                dialog.setVisible (true);
                Book book = dialog.getBook();
                if(book!=null)
                    m.addBook(dialog.getBook());
            }
        });
        JPanel grid = new JPanel(new GridLayout(1,3,1,1));
        grid.add(btn1);
        grid.add(btn2);
        grid.add(btn3);
        JPanel flow = new JPanel(new FlowLayout(
                FlowLayout.RIGHT ));
        flow.add(grid);
        add(flow, BorderLayout.SOUTH );
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Swing();
            }
        });
    }
}


