import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Hangman{
    public static void main(String[] args)throws FileNotFoundException{
        Scanner in = new Scanner(new File("JavaMan/WORDS.txt"), "UTF-8");
    
        List<String> words = new ArrayList<>();
        while (in.hasNextLine()){
            words.add(in.nextLine());
        }

        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size()));

        // System.out.println(word);
        List<Character> guess = new ArrayList<>();
        printWord(word,guess);
        
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