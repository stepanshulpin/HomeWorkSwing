package Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Error extends JDialog {

    private JLabel label;
    private JButton btn = new JButton("OK");

    public Error(JDialog parent, String msg) {

        super(parent,"Error",true);
        label = new JLabel(msg);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                Error.this.dispose();
            }
        });
        add(label, BorderLayout.CENTER);
        add(btn, BorderLayout.SOUTH);
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
}
