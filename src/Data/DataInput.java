package Data;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public final class DataInput {

	/**
	 * Writes text or premade massage
	 * @param wr
	 */
	private static void writeText(String wr){
		if (wr == null)
			System.out.print("Введіть дані: ");
		else 
			System.out.print(wr);
	}
/**
 * отримує число в визначених межах 
 * @param input
 * @param low
 * @param high
 * @return input if in range [low, high)
 */
	public static int checkInt(int input, int low, int high) {
		while (input <= low || input > high) {
			input = getInt("Try again: ");
		}

		return input;
	}
/**
 *  зчитує початкові файли 
 * @param file
 * @return text from file
 * @throws FileNotFoundException
 */
	public static String[] readFile(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);

		ArrayList<String> ar = new ArrayList<String>();

		while(scanner.hasNextLine()) {
//            System.out.println(scanner.nextLine());
			ar.add(scanner.nextLine());
		}

		Object[] arr = ar.toArray();
		String[] res = new String[arr.length];

		for (int i = 0; i < arr.length; i++) {
			res[i] = (String) arr[i];
		}

		return res;
	}

	/**
	 * отримує символ від користувача 
	 * @param text
	 * @return char
	 * @throws IOException
	 */
	public static char getChar(String text) throws IOException {
		return getString(text).charAt(0);
	}
	
	/**
	 * отримує число від користувача 
	 * @param text
	 * @return int value
	 */
	public static Integer getInt(String text){
		writeText(text);
		String s = "";

		try {
			s = getString("");
		} catch (IOException e) {
			e.printStackTrace();
		}

		Integer value = Integer.valueOf(s);

		return value;
	}
	
	/**
	 * отримує стрічку від користувача 
	 * @param text
	 * @return text
	 * @throws IOException
	 */
	public static String getString(String text) throws IOException {
		writeText(text);
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();

		return s;
	}
}
