import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.io.*;
import java.net.*;

public class HangmanPart3 {
	
	public static void main(String [] args) throws Exception {
		
		//part 3 get array from website
		String URLString = "https://cs.nyu.edu/~odeh/resources/python/animals.txt";
		String[] animals = new String [74];
		int i = 0;
		try {
			URL url = new URL(URLString);
			Scanner input = new Scanner(url.openStream());
			while (input.hasNext()) {
				String line = input.nextLine();
				animals[i] = line;
				i += 1;
			}
		}
		catch (Exception e){
			System.exit(0);
		}
		

		
		//pull random word from array from files
		String random = (randomIndex(animals));
		System.out.println("Let's begin!");
		
		
		
		String[] list = spots(random).split("");
		String list2 = Arrays.toString(list);
		printHangman(hangman(0));
		System.out.println(list2);
		
		
		guesses(random, list);
	}
	
	
	//Pick a random number 1-10 for the word
	public static String randomIndex(String array[]) {
		Random rand = new Random();
		int randIndex = rand.nextInt(array.length);
		String randWord = array[randIndex];
		System.out.println(randWord);
		return randWord;
		
	
		
	}
	
	
	//create stick figure
	public static String[] hangman(int lives) {
	    String[] hangman = {" 0","\n      /","|","\\","\n      /"," \\"};

	    // Remove elements from the end of the array based on lives used
	    for (int i = 0; i < lives; i++) {
	        if (hangman.length > 0) {
	            hangman = Arrays.copyOf(hangman, hangman.length - 1);
	        }
	    }

	    return hangman;
	}

	public static void printHangman(String[] hangman) {
	    System.out.println(" _______");
	    System.out.println("|      |");
	    System.out.print("|     ");

	    for (int i = 0; i < hangman.length; i++) {
	        System.out.print(hangman[i]);
	    }
	    System.out.println();
	}
	
	
		
		
		
	
	
	
	//set "_" for word
	public static String spots(String wordArray2) {
		
		String spot = "_";
		for (int i = 0; i < wordArray2.length()-1; i++) {
			spot += "_";
		}
		return spot;
	}
	
	
	
	
	//create guesses
	public static void guesses(String random, String[] list) {
		
		
		//number of attempts
		int z = 0;
		int counter = 0;
		String guesses = ("");
		
		String [] currentLife = hangman(z);

		
		while (z < 6) {
			Scanner in = new Scanner(System.in);
			System.out.println("");
			System.out.println("Guess a letter");
			String guess = in.nextLine();
		
			int index = random.indexOf(guess);
			
			//failure case wrong guesses
			if (index == -1) {
				System.out.println("Wrong guess!");
				z++;
				currentLife = hangman(z);
				printHangman(currentLife);

				System.out.println(Arrays.toString(list));
				continue;
			}
			
			while(index >= 0) {
				if (guesses.contains(guess)) {
					System.out.println("You have already guessed this!");
					break;
				}
			   list[index] = guess;
			   index = random.indexOf(guess, index+1);
			   counter++;
			   
			}
			guesses += guess;
			
			
			printHangman(currentLife);
			

			System.out.println(Arrays.toString(list));
			if (counter == (list.length)){
				System.out.println("You win!");
				System.out.println("You used " + z + " lives!");
				in.close();
				return;
			}
		}
		System.out.println("You ran out of lives!");
		System.out.println("The word was " + random);
	}
}
