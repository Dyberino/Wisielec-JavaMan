import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.FileNotFoundException;
import java.text.Collator;
import java.util.Scanner;
import java.util.Locale;

public class Hangman {
    public static void main(String[] args) throws FileNotFoundException {
        Locale locale = new Locale("pl", "PL");
        Scanner in = new Scanner(new File("JavaMan/WORDS.txt"), "UTF-8");
        Collator col = Collator.getInstance(locale);
        col.setStrength(Collator.PRIMARY);
        Scanner keyboard = new Scanner(System.in);

        List<String> words = new ArrayList<>();
        while (in.hasNextLine()) {
            words.add(in.nextLine());
        }

        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size()));

        System.out.println(word);
        List<Character> guess = new ArrayList<>();
        printWord(word, guess);

        Boolean loop = true;
        int errors = 0;
        while (loop) {
            switch (errors) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;

            }

            if (errors >= 8) {
                System.out.println("Lose");
                break;
            }

            printWord(word, guess);
            if (!playerGuess(keyboard, word, guess)) {
                errors++;
                System.out.println("Frajer jestes i dzban");
            }

            if (printWord(word, guess)) {
                System.out.println("Win");
                break;
            }

        }

    }

    private static boolean printWord(String word, List<Character> guess) {
        int counter = 0;

        for (int i = 0; i < word.length(); i++) {
            if (guess.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                counter++;
            } else {
                System.out.print("_ ");
            }
        }
        return (word.length() == counter);
    }

    private static boolean playerGuess(Scanner keyboard, String word, List<Character> guess) {
        System.out.println("Podaj literę: ");
        String letterGuess = keyboard.nextLine();
        // while(letterGuess.length()>1){
        // try {
        // System.out.println("Podaj literę: ");
        // letterGuess = keyboard.nextLine();
        // if (letterGuess.length() > 1)
        // throw new Exception("Wpisz tylko 1 literę");

        // } catch (Exception e) {
        // System.out.println(e.getMessage());
        // }
        // }
        //
        guess.add(letterGuess.charAt(0));

        return word.contains(letterGuess);
    }
}