import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.Locale;

public class Hangman{
    protected JFrame f;
    protected JTextField textInputSelectGame = new JTextField("");

    protected final int windowWidth = 900;
    protected final int windowHeight = 700;

    private final int menuButtonWidth=200;
    private final int menuButtonHeight=50;
    
    public Hangman() {
        // Tworzenie Okna gry
        f = new JFrame("Wisielec");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("JavaMan/icon.png");
        f.setIconImage(icon.getImage());
        f.pack();
        f.setSize(windowWidth, windowHeight);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setVisible(true);

        StartMenu();
    }
    public Hangman(JFrame f){
        this.f=f;
        StartMenu();
    }
    private void clearWindow(){
        f.getContentPane().removeAll();
        // f.removeAll();
        f.revalidate();
        f.repaint();
    }

    private void StartMenu(){
        clearWindow();
        JPanel menu = new JPanel();
        menu.setBounds((int) (windowWidth/2 - menuButtonWidth/2), (int) (windowHeight/2 - (12 * menuButtonHeight/2)), menuButtonWidth, (int)(9.5 * menuButtonHeight)); 
        // menu.setBackground(Color.CYAN);
        menu.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));


        JLabel logo = new JLabel();
        ImageIcon icon = new ImageIcon(new ImageIcon("JavaMan/icon.png").getImage().getScaledInstance(menuButtonWidth, 4*menuButtonHeight, Image.SCALE_DEFAULT));
        logo.setIcon(icon);
        

        JButton startSingle = new JButton("Single player");
        startSingle.setPreferredSize(new Dimension(menuButtonWidth, menuButtonHeight));
        startSingle.addActionListener(new GameListener(1));
        // startSingle.setBackground(Color.GREEN);

        JButton startMulti = new JButton("Two-player game");
        startMulti.setPreferredSize(new Dimension(menuButtonWidth, menuButtonHeight));
        startMulti.addActionListener(new GameListener(2));
        // startMulti.setBackground(Color.GREEN);

        JButton info = new JButton("Information");
        info.setPreferredSize(new Dimension(menuButtonWidth, menuButtonHeight));
        info.addActionListener(new InformationListener());
        // info.setBackground(Color.GREEN);

        JButton exit = new JButton("Exit");
        exit.setPreferredSize(new Dimension(menuButtonWidth, menuButtonHeight));
        exit.addActionListener(new CloseListener());
        // exit.setBackground(Color.GREEN);




        menu.add(logo);
        menu.add(startSingle);
        menu.add(startMulti);
        menu.add(info);
        menu.add(exit);


        f.add(menu);
        f.revalidate();
        f.repaint();
    }


    private void StartInfo(){
        clearWindow();
        JPanel info = new JPanel();
        info.setBounds(0, 0, windowWidth, windowHeight); 
        // info.setBackground(Color.CYAN);
        info.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        JLabel text = new JLabel();
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setVerticalAlignment(JLabel.CENTER);
        text.setLocale(new Locale("pl", "PL"));
        String textToJLabel ="Wisielec jest to gra jedno lub dwu-osobowa, w kt??rej komputer b??d?? u??ytkownik wymy??laj?? s??owo, kt??re gracz literka po literce \npr??buje zgadn???? s??owo kt??re jest rozwi??zaniem gry.\n Gracz jednak ma tylko 8 podej????, po kt??rych wykorzystaniu zostanie narysowany pe??en wisielec co jest r??wnozanczne z&nbsp;pora??k??.<br><br> Rozgrywka polega na podawaniu liter, kt??re je??li znajduj?? si?? w zgadywanym s??owie, to uzupe??ni?? si??, natomiast je??li nie to stracisz jedno z o??miu ??y??, a&nbsp;literka kt??ra nie znajduje si?? w s??owie zostanie wpisane na list?? b????dnych liter.";
        text.setText("<html><body><div style='text-align: center; width: 98%; margin-left: auto; margin-right: auto;'>" + textToJLabel + "</div></body></html>");
        text.setMinimumSize(new Dimension(windowWidth, (windowHeight-2*menuButtonHeight)));
        text.setPreferredSize(new Dimension(windowWidth, (windowHeight-2*menuButtonHeight)));
        // text.setMaximumSize(new Dimension(windowWidth, (windowHeight-menuButtonHeight)));

        JButton back = new JButton("Return");
        back.setPreferredSize(new Dimension(menuButtonWidth, menuButtonHeight));
        back.addActionListener(new MenuListener());
        // back.setBackground(Color.GREEN);

        info.add(text);
        info.add(back);

        f.add(info);
        f.revalidate();
        f.repaint();
    }

    private void SelectGame(int mode){
        clearWindow();

        if (mode==2){
            JPanel game2player = new JPanel();
            game2player.setBounds(windowWidth/2-800/2, windowHeight/2-300/2, 800, 300); 
            // info.setBackground(Color.CYAN);

            JPanel label = new JPanel(); 

                JLabel text = new JLabel();
                text.setHorizontalAlignment(JLabel.CENTER);
                text.setVerticalAlignment(JLabel.CENTER);
                text.setFont(new Font("Arial", Font.BOLD, 30));
                text.setLocale(new Locale("pl", "PL"));
                text.setText("Podaj s??owo do odgadni??cia przez drug?? osob??:");

            label.add(text);
            
            
            
            textInputSelectGame.setLocale(new Locale("pl","PL"));
            textInputSelectGame.setColumns(14);
            textInputSelectGame.setFont(new Font("Arial", Font.BOLD, 50));
            // textInputSelectGame.setBackground(new Color(230, 230, 230));
            textInputSelectGame.setBorder(BorderFactory.createRaisedBevelBorder());
            textInputSelectGame.setHorizontalAlignment(JTextField.CENTER);
            textInputSelectGame.addActionListener(new RunGameListener());

            EventQueue.invokeLater(new Runnable() {

                @Override
                  public void run() {
                    textInputSelectGame.grabFocus();
                    textInputSelectGame.requestFocus();
                  }
            });

            JPanel label2 = new JPanel(); 

                JLabel text2 = new JLabel();
                text2.setHorizontalAlignment(JLabel.CENTER);
                text2.setVerticalAlignment(JLabel.CENTER);
                text2.setFont(new Font("Arial", Font.BOLD, 16));
                text2.setLocale(new Locale("pl", "PL"));
                text2.setText("Po podaniu s??owa wci??nij ENTER");

            label2.add(text2);

            JPanel label3 = new JPanel(); 

                JLabel text3 = new JLabel();
                text3.setHorizontalAlignment(JLabel.CENTER);
                text3.setVerticalAlignment(JLabel.CENTER);
                text3.setFont(new Font("Arial", Font.BOLD, 16));
                text3.setLocale(new Locale("pl", "PL"));
                text3.setText("Tekst powinien si?? sk??ada?? z samych liter");

            label3.add(text3);


            game2player.add(label);
            game2player.add(textInputSelectGame);
            game2player.add(label2);
            game2player.add(label3);

            f.add(game2player);
        }else{
            Game game = new Game(f);
            game.RunGame();
        }

        f.revalidate();
        f.repaint();
    }

    
    private class CloseListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    private class MenuListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            StartMenu();
        }
    }
    private class InformationListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            StartInfo();
        }
    }

    private class GameListener implements ActionListener{
        private int mode;
        GameListener(int mode){
            this.mode=mode;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            SelectGame(mode);
        }
    }
    private class RunGameListener  implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = textInputSelectGame.getText().toUpperCase().replaceAll(" ", "").replaceAll("[0-9]","").replaceAll("[\\<\\>\\.\\,\\?\\'\\]\\[\\{\\}\\=\\+\\_\\-\\(\\)\\:\\;\\|\\/\\~\\`\\\"\\\\\\!\\@\\#\\$\\%\\^\\&\\*]","");
            if(text.length()>=1){
                clearWindow();
                Game game = new Game(f,text);
                game.RunGame();
                f.revalidate();
                f.repaint();
            }

            
        }
    }
}
