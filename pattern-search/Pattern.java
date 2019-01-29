import java.util.Scanner;

public class Pattern {
	public static void main(String[] args) {
		System.out.println("\nWelcome to Pattern game!");
		
		Scanner in = new Scanner(System.in);
		
		int numberOfWords = 0;
		System.out.println("\nHow many words will you write? ");
		numberOfWords = in.nextInt();
		
		String[] words = new String[numberOfWords];
		for (int i=0; i < numberOfWords; i++) {
			System.out.println("Word " + (i+1) + ": ");
			words[i] = in.next();
		}
		
		char pattern = '\u0000';
		System.out.println("Enter a letter that you want to count at each word: ");
		pattern = Character.toLowerCase(in.next().toCharArray()[0]);
		
		System.out.println("\nLetter '" + pattern + "' appears:");
		for (String word : words) {
			int n = word.length();
			int count = 0;
			for (int i = 0; i < n; i++) {
				if (pattern == Character.toLowerCase(word.charAt(i)))
					count++;
			}
			System.out.println(count + " time(s) in '" + word + "'");
		}
	}
}