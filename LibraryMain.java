import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static java.lang.System.exit;

class LibraryMain {
    public static void main(String args[]) throws IOException {

        //create file for database
        File file = new File("books.txt");
        if(!file.exists()) {
            file.createNewFile();
        }

        //create frame
        JFrame frame = new JFrame("Βιβλιοθήκη");
        frame.setBounds(100, 100, 500, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //create buttons
        JButton view = new JButton("Προβολή όλων των βιβλίων");
        JButton search = new JButton("Αναζήτηση βιβλίου");
        JButton insert = new JButton("Εισαγωγή βιβλίου");
        JButton exit = new JButton("Έξοδος");

        //create horizontal layout
        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup()
                        //create group of buttons
                        .addGroup(groupLayout.createSequentialGroup()
                                //align horizontally and add buttons
                                .addGap(150)
                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(view)
                                        .addComponent(insert)
                                        .addComponent(search)
                                        .addComponent(exit)))
        );

        //create vertical layout
        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        //align vertically and add buttons
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(25)
                                .addComponent(view)
                                .addGap(25)
                                .addComponent(insert)
                                .addGap(25)
                                .addComponent(search)
                                .addGap(25)
                                .addComponent(exit))
        );

        frame.getContentPane().setLayout(groupLayout);
        frame.setVisible(true);

        //button listeners
        exit.addActionListener(new ActionListener() {   //exits program when exit button pressed
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        exit(0);
                    }
                });
            }
        });

        insert.addActionListener(new ActionListener() { //opens insertJFrame in new window
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        insertJFrame inst = null;
                        try {
                            inst = new insertJFrame("Εισαγωγή βιβλίου");
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        inst.setLocationRelativeTo(null);
                        inst.setVisible(true);
                    }
                });
            }
        });

        search.addActionListener(new ActionListener() { //opens searchJFrame in new window
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        searchJFrame inst = null;
                        inst = new searchJFrame("Αναζήτηση βιβλίου");
                        inst.setLocationRelativeTo(null);
                        inst.setVisible(true);
                    }
                });
            }
        });

        view.addActionListener(new ActionListener() { //opens PreviewJFrame in new window
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        PreviewJFrame inst = null;
                        inst = new PreviewJFrame("Προβολή βιβλίων");
                        inst.setLocationRelativeTo(null);
                        inst.setVisible(true);
                    }
                });
            }
        });
    }
}
