/*
 ********************************************************************************************************************
 * Project: comp3095_Team_Sky_Assignment1
 * Assignment: assignment 1
 * Author(s): Leila Jalali Abyaneh - Sogol Ganji Haghighi
 * Student Number: 101088286 - 100902745
 * Date: October/04/2018
 * Description: The helper class for the project
 ********************************************************************************************************************
*/
package helper;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {
	
	private Pattern emailPattern;
	private Pattern passwordPattern;
	private Pattern characters;
	private Matcher matcher;
	
	
	private static final String EMAIL_PATTERN = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
	private static final String CHARACTER_PATTERN = "^[a-zA-Z]+$";
	private static final String PASSWORD_PATTERN = "";
	
	public Helper() {
		
		emailPattern = Pattern.compile(EMAIL_PATTERN);
		characters = Pattern.compile(CHARACTER_PATTERN);
		passwordPattern = Pattern.compile(PASSWORD_PATTERN);
	}
	
	public boolean validateCharacter(final String hex) {
		matcher = characters.matcher(hex);
		return matcher.matches();
		
	}
	
	public boolean validateEmail(final String hex) {
		matcher = emailPattern.matcher(hex);
		return matcher.matches();
		
	}
	
	public boolean validatePassword(final String hex) {
		matcher = passwordPattern.matcher(hex);
		return matcher.matches();
	}
	
	public static boolean isEmty(String str) {
		if (str == "" || str == null) {
			return false;
		}else {
			return true;
		}
	}

}
