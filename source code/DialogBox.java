
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Provides a class used for GUI of the Cinema Booking System.
 * Creates the Dialog box with multiple input fields.
 * The function getInput returns all the user input as a list.
 *
 *
 * @author Fariha Nazmul
 * @version 01.11.10
 */
public class DialogBox {
    
    private JTextField text1;
    private JTextField text2;
    private JTextField text3;
    private JTextField text4;
    private JTextField text5;
    private JDialog dialogBox;
    private ArrayList<String> allInput;

    /**
     * Constructor - initialises the Dialog Box.
     */
    public DialogBox(JFrame parent, final CommandWord command,
            String[] dialogLabels) {

        JLabel label1, label2, label3, label4, label5;       
        JPanel panel, p;

        allInput = new ArrayList<String>();
        final int numOfElements = dialogLabels.length;
        panel = new JPanel(new GridLayout(numOfElements + 1, 2, 0, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        label1 = new JLabel(dialogLabels[0]);
        label2 = new JLabel(dialogLabels[1]);
        text1 = new JTextField(20);
        text2 = new JTextField(20);
        p = new JPanel(new FlowLayout(FlowLayout.CENTER) );
        p.add(label1);
        panel.add(p);
        panel.add(text1);

        p = new JPanel(new FlowLayout(FlowLayout.CENTER) );
        p.add(label2);
        panel.add(p);
        panel.add(text2);
        if (numOfElements == 3) {
            label3 = new JLabel(dialogLabels[2]);
            text3 = new JTextField(20);

            p = new JPanel(new FlowLayout(FlowLayout.CENTER) );
            p.add(label3);
            panel.add(p);
            panel.add(text3);

        } else if (numOfElements == 5) {
            label3 = new JLabel(dialogLabels[2]);
            label4 = new JLabel(dialogLabels[3]);
            label5 = new JLabel(dialogLabels[4]);
            text3 = new JTextField(20);
            text4 = new JTextField(20);
            text5 = new JTextField(20);


            p = new JPanel(new FlowLayout(FlowLayout.CENTER) );
            p.add(label3);
            panel.add(p);
            panel.add(text3);
            p = new JPanel(new FlowLayout(FlowLayout.CENTER) );
            p.add(label4);
            panel.add(p);
            panel.add(text4);
            p = new JPanel(new FlowLayout(FlowLayout.CENTER) );
            p.add(label5);
            panel.add(p);
            panel.add(text5);
        }

        JButton OKButton = new JButton("OK");
        JButton CancelButton = new JButton("Cancel");
        dialogBox = new JDialog(parent);

        JPanel buttonPanel1 = new JPanel();
        buttonPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel1.add(OKButton);
        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));        
        buttonPanel2.add(CancelButton);

        panel.add(buttonPanel1);
        panel.add(buttonPanel2);

        dialogBox.getContentPane().add(panel, BorderLayout.CENTER);
        dialogBox.setTitle(command.toString().toUpperCase());
        dialogBox.setModal(true);
        dialogBox.pack();
        dialogBox.setPreferredSize(new Dimension(200, 250));

        OKButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {

                allInput.add(text1.getText());
                allInput.add(text2.getText());
                if (numOfElements == 3) {
                    if (command == CommandWord.ADD_FILM) {
                        StringTokenizer days =
                                new StringTokenizer(text3.getText(), ", ");
                        while (days.hasMoreTokens()) {
                            allInput.add(days.nextToken());
                        }
                    } else {
                        allInput.add(text3.getText());
                    }
                } else if (numOfElements == 5) {
                    allInput.add(text3.getText());
                    allInput.add(text4.getText());
                    allInput.add(text5.getText());
                }
                dialogBox.dispose();
            }
        });

        CancelButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                dialogBox.dispose();
            }
        });

        // place the frame at the center of the screen and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        dialogBox.setLocation(d.width / 2 - dialogBox.getWidth() / 2,
                d.height / 2 - dialogBox.getHeight() / 2);

        dialogBox.setVisible(true);
    }


    /**
     * Creates a list of all the user inputs.
     */
    public ArrayList<String> getInput() {
        return allInput;
    }
}