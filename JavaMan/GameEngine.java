import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.FileNotFoundException;
import java.text.Collator;
import java.util.Scanner;
import java.util.Locale;
import java.lang.String;

public class GameEngine {

    protected List<Character> guessesLetter = new ArrayList<>();
    protected String wordToGuess;


    public GameEngine(){}

    protected String getWordToGuess(){
        try{
            Scanner in = new Scanner(new File("JavaMan/WORDS.txt"), "UTF-8");
            String word;
        
            List<String> words = new ArrayList<>();
            while (in.hasNextLine()) {
                words.add(in.nextLine());
            }

            Random rand = new Random();
            word = words.get(rand.nextInt(words.size()));

            wordToGuess=word.toUpperCase();
            return wordToGuess;

        }catch(FileNotFoundException e){
            System.exit(0);
        }
        return "";
    }

    protected String getHiddenWordToGuess(String word, List<Character> foundLetter) {
        String hiddenWord = "";
        for (int i = 0; i < word.length(); i++) {
            if(i==word.length()-1){
                if(foundLetter.contains(word.charAt(i))){
                    hiddenWord += word.charAt(i);
                }else{
                    hiddenWord += "_";
                }
            }else{
                if(foundLetter.contains(word.charAt(i))){
                    hiddenWord += word.charAt(i) + " ";
                }else{
                    hiddenWord += "_" + " ";
                }
            }
        }
        return hiddenWord;
    }

    protected boolean isLetterInWordToGuess(Character c){
        if(wordToGuess.indexOf(c)!=-1){
            return true;
        }else{
            return false;
        }
    }

    protected boolean isBadEnding(int amountOfFailure){
        if(amountOfFailure>=8){
            return true;
        }
        return false;
    }
    protected boolean isGoodEnding(String hiddenWordToGuess){
        if(hiddenWordToGuess.contains("_")==false){
            return true;
        }
        return false;
    }
}