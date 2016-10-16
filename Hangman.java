//Hangman class that plays a full game of hang man using Letter instances

//Imports Scanner to get input from the user
import java.util.Scanner;

public class Hangman{
  //letters and guesses attributes are initialized to a reasonable size
  private Letter[] letters = new Letter[15];
  private char[] guesses = new char[15];
  private boolean gameOver;
  /*Both maxNumGuesses and numGuessesMade are declared as static so their value remains
  constant in the playGame method as it is changed in the guess method*/
  private static int maxNumGuesses;
  private static int numGuessesMade;

  //Constructor that sets and initial value for the letters attribute and a default value of 8 for the maxNumGuesses
  public Hangman(String word){
    word = word.toUpperCase();
    for(int i = 0; i < word.length(); i++){
    letters[i] = new Letter(word.charAt(i));
    }
    maxNumGuesses = 8;
  }

  //Overloaded constructor that sets initial value for the letters attribute and another for the maxNumGuesses
  public Hangman(String word, int maxNumGuesses){
    word = word.toUpperCase();
    for(int i = 0; i < word.length(); i++){
    letters[i] = new Letter(word.charAt(i));
    }
    this.maxNumGuesses = maxNumGuesses;
  }

  //This method runs an entire game of hangman
  public void playGame(){
    gameOver = false;
    Scanner guess = new Scanner(System.in);
    //The game runs until the gameOver variable is set to true
    while(!gameOver){
      displayBoard();
      System.out.println("What is your next guess?");
      char answer = guess.next().charAt(0);
      if(!guess(answer)){
        System.out.println("Invalid guess. Try again");
      }
      else{
        //When any of this two situations are met the gameOver boolean is set to true and the game ends
        if (maxNumGuesses == numGuessesMade || isGuessed(letters)){
          gameOver = true;
        }
      }
    }
    //This if statement gives a final result to the player depending on whether the word was completely guessed or not
    if(isGuessed(letters)){
      System.out.println("You got the word!");
    }
    else{
      System.out.println("You're out of tries!");
    }
  }

  //This method evaluate if a guess is valid or invalid and correct or incorrect
  public boolean guess(char c){
    boolean isThere = false;
    //When the guess is invalid the method ends and returns false
    if (!Character.isLetter(c) || isCharThere(guesses, c)){
      return false;
    }
    else{
      //If the guess is valid and correct, all the letters that are equal to the guess are revealed
      c = Character.toUpperCase(c);
      for(int i = 0; i < countLetters(letters); i++){
        if(letters[i].getValue() == c){
          letters[i].reveal();
          isThere = true;
        }
      }
      //If the guess is valid but incorrect, the guess is added to the guessed array, and the numGuessesMade variable is increased by one
      if(!isThere){
        guesses[findIndex(guesses)] = c;
        numGuessesMade++;
      }
    }
    //In any of the last two cases the method returns true
    return true;
  }

  //This method prints the incorrect guesses made, the revealed letters in the mystery word so far, and the number of guesses remaining
  //Incorrect guesses made
  public void displayBoard(){
    System.out.print("You have made the following guesses: ");
    for(int i = 0; i < findIndex(guesses); i++){
      System.out.print(guesses[i] + " ");
    }
    //Guessed letters and missing letters(shown with a "_")
    System.out.println();
    System.out.print("Word so far: ");
    for(int i = 0; i < countLetters(letters);i++){
      if(letters[i].getRevealed()){
        System.out.print(letters[i].getValue() + " ");
      }
      else{
        System.out.print("_ ");
      }
    }
    //Guesses remaining
    System.out.println();
    System.out.print("You have " + (maxNumGuesses - numGuessesMade) + " remaining.");
    System.out.println();
  }

  //Method that checks whether a char is in a char array
  private boolean isCharThere(char[] list, char c){
    c = Character.toUpperCase(c);
    for(int i = 0; i < list.length ; i++){
      if(list[i] == c){
        return true;
      }
    }
    return false;
  }

  //Method that counts the amount of chars that aren't null in a char array
  private int findIndex(char[] list){
    int count = 0;
    for(int i = 0; i < list.length; i++){
      if(list[i] != '\0'){
        count++;
      }
    }
    return count;
  }

  //Method that counts the amount of values that aren't null in a Letter array
  private int countLetters(Letter[] list){
    int count = 0;
    for(int i = 0; i < list.length; i++){
      if(list[i] != null){
        count++;
      }
    }
    return count;
  }

  //Method that checks if a whole word has been guessed
  private boolean isGuessed(Letter[] list){
    for(int i = 0; i < countLetters(list); i++){
      if(!list[i].getRevealed()){
        return false;
      }
    }
    return true;
  }

  //Main method that runs a full game of hangman
  public static void main(String[] args){
    Hangman game = new Hangman("dinosaurs");
    game.playGame();
  }

}
