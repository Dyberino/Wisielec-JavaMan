import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.CellRendererPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Display extends JPanel {

        private static final int N = 8;
        private static final String s = "<html>";
        private JLabel renderer = new JLabel(s);
        private CellRendererPane crp = new CellRendererPane();
        private Dimension dim;
        
       
    
        public Display() {
            this.setBackground(Color.white);
            dim = renderer.getPreferredSize();
            this.add(crp);
            
        }
    
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int i = 0; i < 1; i++) {
                renderer.setForeground(Color.getHSBColor((float) i / N, 1, 1));
                crp.paintComponent(g, renderer, this,
                    i * dim.width, i * dim.height, dim.width, dim.height);
            }
        }
    
        private void display() {
            JTextField textInput = new JTextField("TextInput");
            textInput.setBounds(50,100, 200,30);


            JFrame f = new JFrame("Wisielec");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setIconImage(image);
            f.add(this);
            f.add(textInput);
            f.pack();
            f.setSize(700, 700);
            f.setResizable(false);
            f.setLocationRelativeTo(null);
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
