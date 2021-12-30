import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.Locale;

import javax.swing.CellRendererPane;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Display extends JPanel implements ActionListener{

        private final int windowWidth = 900;
        private final int windowHeight = 700;

        private final int textInputWidth = 100;
        private final int textInputHeight = 100;


        JTextField textInput = new JTextField("");
        
       
    
        public Display() {
            this.setBackground(Color.white);
            // dim = renderer.getPreferredSize();
            // this.add(crp);
        }
    

        public void actionPerformed(ActionEvent e) { 
            System.out.println(textInput.getText());
            textInput.setText("");
        }

        public void actionPerformed(KeyEvent e) { 
            System.out.println(textInput.getText());
            textInput.setText("");
        }
    
        private void display() {
            Locale locale = new Locale("pl", "PL");
            Font font = new Font("Arial", Font.BOLD, 60);
            
            textInput.setBounds(windowWidth/2-textInputWidth/2,windowHeight-(int)(0.25*(windowHeight)), textInputWidth, textInputHeight);
            textInput.setLocale(locale);
            textInput.setColumns(2);
            textInput.setFont(font);
            textInput.setBackground(new Color(255, 0, 0));
            textInput.setHorizontalAlignment(JTextField.CENTER);


            textInput.addActionListener(this);


            JFrame f = new JFrame("Wisielec");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ImageIcon img = new ImageIcon("JavaMan/icon.png");
            f.setIconImage(img.getImage());
            f.pack();
            f.setSize(windowWidth, windowHeight);
            f.setResizable(false);
            f.setLocationRelativeTo(null);

            f.add(textInput);
            f.setLayout(null);
            f.setVisible(true);
        }
    
        public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
    
                @Override
                public void run() {
                    new Display().display();
                }
            });
        }
}
