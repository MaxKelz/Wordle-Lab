import java.util.Scanner;
public class Wordle
{
    static Scanner input = new Scanner(System.in);
    final static String WORDLE_OF_THE_DAY = "MOVIE";
    static String[] solution = new String[] {"_", "_", "_", "_", "_"};
    static final String[] ALPHABET = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", 
                                            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    static String[] userAlphabet = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", 
                                            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    public static void main(String[] args)
    {
        int count = 0;
        while(count < 6)
        {
            count++;
            String userGuess = guess(count); //Gets user guess
            char[] guessAsChar = guessToChar(userGuess); //Converts userGuess to a char array
            evaluateGuess(guessAsChar); //Evaluates the guess, filling in solution/modifying userAlphabet.
            printInfo(solution, userAlphabet); //Prints the solution and userAlphabet
            System.out.println("===============================");
            if(checkSolution()) //Checks if the solution is completed
            {
                break;
            }
        }
        if(checkSolution()) //If completed
        {
            System.out.println("Congrats! You completed the Wordle!");
        } else {
            System.out.println("Sorry, but you ran out of guesses.");
        }
    }
    
    
    public static String guess(int guessNum)
    {
        System.out.println("Guess Number " + guessNum + ": "); //Tells user the guess number they are on
        String userInput = input.nextLine();
        while(userInput.length() != 5)
        {
            System.out.println("Error: Guess must be five letters, try again.");
            userInput = input.nextLine();
        }
        return userInput; //Returns the guess of the user
    }
    

    public static char[] guessToChar(String guess)
    {
        char[] temp = new char[guess.length()];
        for(int i = 0; i < guess.length(); i++)
        {
            temp[i] = guess.charAt(i);
        }
        return temp;
    }
    
    

    public static void evaluateGuess(char[] guess)
    {
        for(int i = 0; i < guess.length; i++)
        {
            String character = String.valueOf(guess[i]).toUpperCase();
            if(character.equals(WORDLE_OF_THE_DAY.substring(i,i+1))) //If character is found at the correct index of the solution.
            {
                solution[i] = character; //Sets the solution at index i to the character
            } else {
                int index = findIndexInAlphabet(character); //Finds the index in the alphabet of a character
                if(WORDLE_OF_THE_DAY.contains(character)) //If the character is found in the solution, but not at the correct index.
                {
                    userAlphabet[index] = character.toLowerCase(); //Sets the letter in the alphabet to lower case.
                } else { //If the character is not in the solution
                    userAlphabet[index] = " "; //Sets the letter in the alphabet to blank.
                }
            }
        }
    }
    
    

    public static int findIndexInAlphabet(String key)
    {
        for(int i = 0; i < ALPHABET.length; i++)
        {
            if(ALPHABET[i].equals(key))
            {
                return i;
            }
        }
        return -1;
    }
    
    

    public static void printInfo(String[] solution, String[] alphabet)
    {
        System.out.print("Current Solution: ");
        for(String e : solution)
        {
            System.out.print(e + " ");
        }
        System.out.println("");
        System.out.print("Remaining letters: ");
        for(int i = 0; i < alphabet.length; i++)
        {
            System.out.print(alphabet[i] + " ");
            if(i == 14) //Add extra spaces so the output is more condensed
            {
                System.out.println("");
                System.out.print("\t \t \t");
            }
        }
        System.out.println("");
    }
    
    

    public static boolean checkSolution()
    {
        for(int i = 0; i < solution.length; i++)
        {
            if(!solution[i].equals(WORDLE_OF_THE_DAY.substring(i, i+1)))
            {
                return false;
            }
        }
        return true;
    }
}
