import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PalindromeMain {
	
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	static int beginYear;
	static int endYear;

	public static void main(String[] args) {
		inputDate();
		LocalDate beginDate = LocalDate.of(beginYear, 1, 1);
		LocalDate endDate = LocalDate.of(endYear, 12, 31);
		System.out.println("\nThe following Table shows all Palindrome Dates between "+beginDate+
				" and "+endDate+":\n");
		ArrayList<LocalDate> datesPal = checkDatesForPalindrome(beginDate, endDate);
		outputDatesPal(datesPal);
	}
	private static void inputDate() {
		boolean ok;
		int year = 0;
		do {
			Scanner sc = new Scanner(System.in);
			ok = true;
			System.out.print("Begin Year: ");
			try {
				year = sc.nextInt();
				if(String.valueOf(year).length() != 4) {
					System.out.println("The year must contain 4 digits!");
					ok = false;
				}
			} catch (InputMismatchException e) {
				System.out.println("Please enter a number!");
				ok = false;
			}
		} while (!ok);
		beginYear = year;
		do {
			ok = true;
			System.out.print("End Year: ");
			try {
				Scanner sc = new Scanner(System.in);
				year = sc.nextInt();
				if(String.valueOf(year).length() != 4) {
					System.out.println("The year must contain 4 digits!");
					ok = false;
				}
				if (year < beginYear) {
					System.out.println("The End Year mustn't be smaller than the Begin Year! ("+beginYear+")");
					ok = false;
				}
			} catch (InputMismatchException e) {
				System.out.println("Please enter a number!");
				ok = false;
			}
		} while (!ok);
		endYear = year;
	}
	private static boolean isPal(String s) {
		String rs = reverseString(s, "",  s.length());
		//System.out.println("Original-String: " + s);
		//System.out.println("Reversed-String: " + rs);
		return s.equalsIgnoreCase(rs);
	}
	//recursive Method to reverse a String (String, ReversedString, Length)
	private static String reverseString(String s, String rs, int n) {
		n--;
		if (n < 0) {
			return rs;
		}
		rs = rs + s.charAt(n);
		return reverseString(s, rs, n);
	}
	private static ArrayList<LocalDate> checkDatesForPalindrome(LocalDate beginDate, LocalDate endDate) {
		ArrayList<LocalDate> datesPal = new ArrayList<LocalDate>();
		LocalDate currentDate = beginDate.minusDays(1); //-1 Days: make sure first beginDate is also checked
		do {
			currentDate = currentDate.plusDays(1);
			String currentDateString = formatter.format(currentDate);
			if (isPal(currentDateString)) {
				datesPal.add(currentDate);
			}
		} while (currentDate.compareTo(endDate) != 0); //compareTo returns 0 if the dates are equal
		return datesPal;
	}
	private static void outputDatesPal(ArrayList<LocalDate> datesPal) {
		System.out.println("    Date    |  Formatted Date  | Reversed Formatted Date");
		System.out.println("------------+------------------+------------------------");
		for (int i = 0; i < datesPal.size(); i++) {
			String dateString = formatter.format(datesPal.get(i));
			String revDateString = reverseString(dateString, "", dateString.length());
			System.out.print(" " + datesPal.get(i) + " |     ");
			System.out.print(dateString + "     |        ");
			System.out.println(revDateString);
		}
	}
}
