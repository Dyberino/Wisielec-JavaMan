import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.lang.model.type.ExecutableType;
import javax.swing.BorderFactory;
import javax.swing.CellRendererPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class Game{
    private final int windowWidth = 900;
    private final int windowHeight = 700;

    private final int textInputWidth = 80;
    private final int textInputHeight = 80;

    private final int imageWidth = 350;
    private final int imageHeight = 350;

    private final int menuButtonWidth=200;
    private final int menuButtonHeight=50;

    private int amountOfFailure = 0;
    private String textToGuess="";
    private String hiddenTextToGuess="";

    private JFrame f = new JFrame();
    private JTextField textInput = new JTextField("");
    private JPanel jpBadCharacter = new JPanel();
    private JLabel jlText = new JLabel();
    private JLabel jlImage = new JLabel();
    private JPanel jpImage = new JPanel();

    GameEngine engine;

    public Game(JFrame f){
        this.f=f;
    }
    public Game(JFrame f, String text){
        this.f=f;
        this.textToGuess=text.toUpperCase();
    }


    public JFrame RunGame() {
        engine = new GameEngine();
        amountOfFailure=0;
        
        if(textToGuess==""){
            textToGuess=engine.getWordToGuess();
        }else{
            engine.wordToGuess=textToGuess;
        }
        System.out.println(textToGuess);
        hiddenTextToGuess=engine.getHiddenWordToGuess(textToGuess,engine.guessesLetter);






        // Tworzenie Text Inputu
        Locale locale = new Locale("pl", "PL");
        Font fontTextInput = new Font("Arial", Font.BOLD, 50);

        textInput.setBounds(windowWidth / 2 - textInputWidth / 2, windowHeight - (int) (0.25 * (windowHeight)), textInputWidth, textInputHeight);
        textInput.setLocale(locale);
        textInput.setColumns(2);
        textInput.setFont(fontTextInput);
        textInput.setBackground(new Color(230, 230, 230));
        textInput.setBorder(BorderFactory.createRaisedBevelBorder());
        textInput.setHorizontalAlignment(JTextField.CENTER);
        textInput.addKeyListener(new KeyEvents());
        textInput.addActionListener(new checkLetterListener());
        f.add(textInput);
        

        // Dodawanie obrazków wisielca
            jlImage.setIcon(getActualHangmanImage());
            jpImage.add(jlImage);
            jpImage.setBounds(windowWidth / 2 - imageWidth / 2, (int) (windowHeight * 0.05), imageWidth, imageHeight);
        f.add(jpImage);
            

        // Tworzenie wyświetlania słowa
        
        jlText.setText(hiddenTextToGuess);
        jlText.setFont(new Font("Arial", Font.BOLD, 60));
        jlText.setForeground(Color.BLACK);
        jlText.setLocale(locale);
        jlText.setHorizontalAlignment(JLabel.CENTER);
        jlText.setVerticalAlignment(JLabel.BOTTOM);
        jlText.setBounds(0, (int) (windowHeight * 0.70) - (int) (windowHeight * 0.10), windowWidth,
                (int) (windowHeight * 0.10));
        f.add(jlText);

        // Tworzenie słowa błędne literki
        JLabel jlBadCharacter = new JLabel();

        jlBadCharacter.setText("Błędne literki");
        jlBadCharacter.setFont(new Font("Arial", Font.BOLD, 20));
        jlBadCharacter.setForeground(Color.BLACK);
        jlBadCharacter.setLocale(locale);
        jlBadCharacter.setHorizontalAlignment(JLabel.CENTER);
        jlBadCharacter.setVerticalAlignment(JLabel.BOTTOM);
        jlBadCharacter.setBounds((int) (windowWidth*0.72), 10, 200, 20);
        f.add(jlBadCharacter);

        // Tworzenie pola błędne literki
        jpBadCharacter.setFont(new Font("Arial", Font.BOLD, 20));
        jpBadCharacter.setForeground(Color.BLACK);
        jpBadCharacter.setLocale(locale);
        jpBadCharacter.setBounds((int) (windowWidth*0.72), 35, 200, 400);
        f.add(jpBadCharacter);

        EventQueue.invokeLater(new Runnable() {

            @Override
              public void run() {
                textInput.grabFocus();
                textInput.requestFocus();
              }
         });

        return f;
    }


















    private void endGame(int mode){
        EventQueue.invokeLater(new Runnable() {

            @Override
              public void run() {
                f.requestFocus();
              }
        });

        JPanel endingPanelMain = new JPanel();
        endingPanelMain.setFont(new Font("Arial", Font.BOLD, 20));
        endingPanelMain.setForeground(Color.BLACK);
        endingPanelMain.setLocale(new Locale("pl","PL"));
        // endingPanelMain.setBackground(Color.BLUE);
        endingPanelMain.setBounds(windowWidth/2-(menuButtonWidth+100)/2, windowHeight-200, menuButtonWidth+100, 200);




        JLabel endingLabel = new JLabel();

        if(mode==1){
            endingLabel.setText("Wygrana");
            endingLabel.setForeground(new Color(70, 143, 46));
        } 
        if(mode==0){
            endingLabel.setText("Przegrana");
            endingLabel.setForeground(new Color(207, 28, 28));
        } 

        endingLabel.setLocale(new Locale("pl","PL"));
        endingLabel.setHorizontalAlignment(JLabel.CENTER);
        endingLabel.setVerticalAlignment(JLabel.CENTER);
        endingLabel.setFont(new Font("Arial", Font.BOLD, 60));
        Border border = endingLabel.getBorder();
        Border margin = new EmptyBorder(0,0,20,0);
        endingLabel.setBorder(new CompoundBorder(border, margin));


        

        JButton backToMenu = new JButton("Back to manu");
        backToMenu.setPreferredSize(new Dimension(menuButtonWidth, menuButtonHeight));
        backToMenu.addActionListener(new BackToMenuListener());



        endingPanelMain.add(endingLabel);
        endingPanelMain.add(backToMenu);

        textInput.setVisible(false);
        f.add(endingPanelMain);
        f.revalidate();
        f.repaint();
    }








    private ImageIcon getActualHangmanImage(){
        List<ImageIcon> imageList = getHangManImagesList();
        int k = 0;
        for (ImageIcon im : imageList) {
            if (amountOfFailure == k) {
                return im;
            }
            k++;
        }
        return new ImageIcon();
    }
    private void changeActualImage(){
        jlImage.setIcon(getActualHangmanImage());
        jlImage.revalidate();
        jlImage.repaint();
        jpImage.revalidate();
        jpImage.repaint();
    }



    private void addToBadCharacter(Character c){
        JLabel jlBadCharacter = new JLabel();
        jlBadCharacter.setText(textInput.getText().toUpperCase());
        jlBadCharacter.setFont(new Font("Arial", Font.BOLD, 20));
        jlBadCharacter.setForeground(Color.gray);
        jlBadCharacter.setLocale(new Locale("pl", "PL"));
        jlBadCharacter.setHorizontalAlignment(JLabel.CENTER);
        jlBadCharacter.setVerticalAlignment(JLabel.BOTTOM);

        jpBadCharacter.add(jlBadCharacter);
        jpBadCharacter.revalidate();
        jpBadCharacter.repaint();
        changeActualImage();
    }
    private void addToCorrectCharacter(Character c){
        engine.guessesLetter.add(c);
        hiddenTextToGuess=engine.getHiddenWordToGuess(textToGuess,engine.guessesLetter);
        jlText.setText(hiddenTextToGuess);
        System.out.println(textToGuess);
        System.out.println(hiddenTextToGuess);
        jlText.revalidate();
        jlText.repaint();
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

    private void haveItMoreThanOneLetter(){
        if(textInput.getText().length() > 1){
            textInput.setText("" + textInput.getText().charAt(0));
        }
        // System.out.println(textInput.getText().length());
    }

    private class checkLetterListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String pattern = "[A-ZĄĆĘŁŃÓŚŻŹ]{1}";
            Color color = new Color(230, 230, 230);

            if (textInput.getText().toUpperCase().matches(pattern)) {
                System.out.println(textInput.getText().toUpperCase() + ": pop");
                if(engine.isLetterInWordToGuess(textInput.getText().toUpperCase().charAt(0))){
                    System.out.println("Jest w tekscie");
                    addToCorrectCharacter(textInput.getText().toUpperCase().charAt(0));
                    textInput.setBackground(new Color(0,255,0));
                    setTimeout(() -> textInput.setBackground(color), 2000);
                    if(engine.isGoodEnding(hiddenTextToGuess)){
                        endGame(1);
                    }
                }else{
                    System.out.println("Nie jest w tekscie");
                    amountOfFailure++;
                    if(engine.isBadEnding(amountOfFailure)){
                        endGame(0);
                    }
                    addToBadCharacter(textInput.getText().toUpperCase().charAt(0));
                    textInput.setBackground(new Color(255,0,0));
                    setTimeout(() -> textInput.setBackground(color), 2000);
                    
                }
            } else {
                System.out.println(textInput.getText().toUpperCase() + ": Nie pop");
                
                textInput.setBackground(new Color(255,0,0));
                setTimeout(() -> textInput.setBackground(color), 2000);

            }
            textInput.setText("");
            System.out.println("ilosc porazek: "+amountOfFailure);
            f.revalidate();
            f.repaint();
        }
    }

    
    private class KeyEvents implements KeyListener{
        public void keyPressed(KeyEvent e) {
            // System.out.println("presed");
        }public void keyTyped(KeyEvent e) {
            // System.out.println("typed");
        }
        public void keyReleased(KeyEvent e) {
            // System.out.println("released");
            haveItMoreThanOneLetter();
        }
    }   

    private static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }
    private class BackToMenuListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            new Hangman(f);
        }
    }
}
