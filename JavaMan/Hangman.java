import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.FileNotFoundException;
import java.text.Collator;
import java.util.Scanner;
import java.util.Locale;
public class Hangman{
    public static void main(String[] args)throws FileNotFoundException{
        Locale locale = new Locale("pl","PL");
        Scanner in = new Scanner(new File("JavaMan/WORDS.txt"),"UTF-8");
        Collator col = Collator.getInstance(locale);
        col.setStrength(Collator.PRIMARY);
        Scanner keyboard = new Scanner(System.in); 

        List<String> words = new ArrayList<>();
        while (in.hasNextLine()){
            words.add(in.nextLine());
        }

        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size()));

        // System.out.println(word);
        List<Character> guess = new ArrayList<>();
        printWord(word,guess);

        System.out.println("Podaj literę: ");
        String letterGuess = keyboard.nextLine();
            try {
                if (letterGuess.length() > 1)
                    throw new Exception("Wpisz tylko 1 literę");
                    System.out.println("Podaj literę: ");
                    letterGuess = keyboard.nextLine();
            } catch (Exception e) {
                System.out.println("Blad" + e);
            }
        }
        
    private static void printWord(String word,List<Character>guess){

        for (int i=0;i<word.length();i++){
            if(guess.contains(word.charAt(i))){
                System.out.print(word.charAt(i));
            }
            else{
                System.out.print("_ ");
            }
    }
    }
}