import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.lang.model.type.ExecutableType;
import javax.swing.CellRendererPane;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Display extends JPanel implements ActionListener {

    private final int windowWidth = 900;
    private final int windowHeight = 700;

    private final int textInputWidth = 80;
    private final int textInputHeight = 80;

    private final int imageWidth = 350;
    private final int imageHeight = 350;

    public int amountOfFailure = 8;

    private JTextField textInput = new JTextField("");

    public Display() {
        this.setBackground(Color.white);
        // dim = renderer.getPreferredSize();
        // this.add(crp);
    }

    public void actionPerformed(ActionEvent e) {
        String pattern = "[A-ZĄĆĘŁŃÓŚŻŹ]{1}";
        if (textInput.getText().toUpperCase().matches(pattern)) {
            System.out.println(textInput.getText().toUpperCase() + ": pop");
        } else {
            System.out.println(textInput.getText().toUpperCase() + ": Nie pop");
        }
        textInput.setText("");
    }

    public void actionPerformed(KeyEvent e) {
        System.out.println(textInput.getText());
        textInput.setText("");
    }

    private List<ImageIcon> getHangManImagesList() {
        List<ImageIcon> images = new ArrayList<ImageIcon>();
        File folder = new File("JavaMan/HangManImages");
        int k = 0;

        for (File f : folder.listFiles()) {
            String fileName = f.toString();
            int index = fileName.lastIndexOf('.');
            if (index > 0) {
                String extension = fileName.substring(index + 1);
                if (f.isFile() && extension.equals("png")) {
                    images.add(new ImageIcon(f.getPath()));
                }
                k++;
            }
        }
        if (k == 0) {
            JOptionPane.showMessageDialog(null, "Brak obrazków", "Brak obrazków", JOptionPane.ERROR_MESSAGE);
        }
        return images;
    }

    private void display() {
        // Tworzenie Okna gry
        JFrame f = new JFrame("Wisielec");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("JavaMan/icon.png");
        f.setIconImage(icon.getImage());
        f.pack();
        f.setSize(windowWidth, windowHeight);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setVisible(true);

        // Tworzenie Text Inputu
        Locale locale = new Locale("pl", "PL");
        Font fontTextInput = new Font("Arial", Font.BOLD, 50);

        textInput.setBounds(windowWidth / 2 - textInputWidth / 2, windowHeight - (int) (0.25 * (windowHeight)),
                textInputWidth, textInputHeight);
        textInput.setLocale(locale);
        textInput.setColumns(2);
        textInput.setFont(fontTextInput);
        textInput.setBackground(new Color(255, 0, 0));
        textInput.setHorizontalAlignment(JTextField.CENTER);

        textInput.addActionListener(this);
        f.add(textInput);

        // Dodawanie obrazków wisielca
        List<ImageIcon> imageList = getHangManImagesList();
        int k = 0;
        for (ImageIcon im : imageList) {
            if (amountOfFailure == k) {
                JPanel jp = new JPanel();
                JLabel jl = new JLabel();
                jl.setIcon(im);
                jp.add(jl);
                jp.setBounds(windowWidth / 2 - imageWidth / 2, (int) (windowHeight * 0.05), imageWidth, imageHeight);
                f.add(jp);
            }
            k++;
        }

        // Tworzenie wyświetlania słowa
        JLabel jl = new JLabel();
        String guess = "________";
        guess = guess.toUpperCase();
        String correctedGuess = "";

        Font fontGuess = new Font("Arial", Font.BOLD, 60);

        for (int i = 0; i < guess.length(); i++) {
            if(i==guess.length()-1){
                correctedGuess += guess.charAt(i);
            }else{
                correctedGuess += guess.charAt(i) + "\t";
            }
        }
        correctedGuess = correctedGuess.replaceAll("\t", "  ");
        jl.setText(correctedGuess);
        jl.setFont(fontGuess);
        jl.setForeground(Color.BLACK);
        jl.setLocale(locale);
        jl.setHorizontalAlignment(JLabel.CENTER);
        jl.setVerticalAlignment(JLabel.BOTTOM);
        jl.setBounds(0, (int) (windowHeight * 0.70) - (int) (windowHeight * 0.10), windowWidth,
                (int) (windowHeight * 0.10));
        f.add(jl);

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
