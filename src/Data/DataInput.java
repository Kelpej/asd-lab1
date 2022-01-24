package Data;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public final class DataInput {

	private static void writeText(String wr){
		if (wr == null)
			System.out.print("Введіть дані: ");
		else 
			System.out.print(wr);
	}

	public static int checkInt(int input, int low, int high) {
		while (input <= low || input > high) {
			input = getInt("Try again: ");
		}

		return input;
	}

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

	public static Long getLong() throws IOException{
		String s = getString("Enter a long value: ");
		Long value = Long.valueOf(s);

		return value;
	}
	
	public static char getChar(String text) throws IOException {
		return getString(text).charAt(0);
	}
	
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
	
	public static String getString(String text) throws IOException {
		writeText(text);
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();

		return s;
	}
}
