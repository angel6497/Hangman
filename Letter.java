//Letter class used in the Hangman class

public class Letter{
  private char value;
  private boolean isGuessed;

  //Constructor that assigns a value to the value attribute and set isGuessed as false
  public Letter(char value){
    this.value = value;
    isGuessed = false;
  }

  //Get method for the value attribute
  public char getValue(){
    return value;
  }

  //Set method for the isGuessed attribute
  public boolean getRevealed(){
    return isGuessed;
  }

  //Get method for the isGuessed attribute
  public void reveal(){
    isGuessed = true;
  }
}
