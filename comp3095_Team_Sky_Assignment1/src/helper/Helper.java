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


import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

	private Pattern emailPattern;
	private Pattern passwordPattern;
	private Pattern characters;
	private Matcher matcher;
	
	 final static String emailUsername = "";
	    final static String emailPassword = "";
	
	
	private static final String EMAIL_PATTERN = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
	private static final String CHARACTER_PATTERN = "^[a-zA-Z]+$";
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$%]).{6,12})";

	public static final String url = "https://www.google.com/recaptcha/api/siteverify";
	public static final String secret = "6LeAhHMUAAAAAJSqPwpdaXpC00KcDUlO5azyC_Sz";
	private final static String USER_AGENT = "Mozilla/5.0";

	
	public Helper() {
		
		emailPattern = Pattern.compile(EMAIL_PATTERN);
		characters = Pattern.compile(CHARACTER_PATTERN);
		passwordPattern = Pattern.compile(PASSWORD_PATTERN);
	}
	 public static String getBaseUrl(HttpServletRequest request) {
	        String scheme = request.getScheme() + "://";
	        String serverName = request.getServerName();
	        String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
	        String contextPath = request.getContextPath();
	        return scheme + serverName + serverPort + contextPath;
	    }
	    public static boolean sendEmail(String receiverEmail, String subject, String messageContent){
	        Properties props = new Properties();
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");

	        Session session = Session.getInstance(props,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(emailUsername, emailPassword);
	                    }
	                });

	        try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(emailUsername));
	            message.setRecipients(Message.RecipientType.TO,
	                    InternetAddress.parse(receiverEmail));
	            message.setSubject(subject);
	            message.setText(messageContent);

	            Transport.send(message);

	            return true;

	        } catch (MessagingException e) {
	            System.err.println(e.getMessage());
	        }
	        return false;

	    }
	 public static boolean verifyRecaptcha(String gRecaptchaResponse) throws IOException {
	        if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
	            return false;
	        }

	        try {
	            URL obj = new URL(url);
	            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

	            // add reuqest header
	            con.setRequestMethod("POST");
	            con.setRequestProperty("User-Agent", USER_AGENT);
	            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

	            String postParams = "secret=" + secret + "&response="
	                    + gRecaptchaResponse;

	            // Send post request
	            con.setDoOutput(true);
	            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	            wr.writeBytes(postParams);
	            wr.flush();
	            wr.close();

	            int responseCode = con.getResponseCode();
	            System.out.println("\nSending 'POST' request to URL : " + url);
	            System.out.println("Post parameters : " + postParams);
	            System.out.println("Response Code : " + responseCode);

	            BufferedReader in = new BufferedReader(new InputStreamReader(
	                    con.getInputStream()));
	            String inputLine;
	            StringBuffer response = new StringBuffer();

	            while ((inputLine = in.readLine()) != null) {
	                response.append(inputLine);
	            }
	            in.close();

	            // print result
	            System.out.println(response.toString());

	            //parse JSON response and return 'success' value
	            JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
	            JsonObject jsonObject = jsonReader.readObject();
	            jsonReader.close();

	            return jsonObject.getBoolean("success");
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	public boolean validateCharacter(final String hex) {
		matcher = characters.matcher(hex);
		return matcher.matches();
		
	}
	
	public boolean validateEmail(final String hex) {
		matcher = emailPattern.matcher(hex);
		return matcher.matches();
		
	}
	
	 public String validatePassword(final String hex) {
	        String errorStr = "";
	        if (hex.length() < 6 || hex.length() > 12) errorStr += "The password must between 6-12 characters!<br/>";
	        matcher=passwordPattern.matcher(hex);
	        if (!matcher.matches())
	            errorStr+="Password must have at least 1 special character and 1 uppercase character!<br/>";
	        return errorStr;
	    }
	
	  public static boolean isNotEmty(String str) {
	        if (str == "" || str == null) {
	            return false;
	        } else {
	            return true;
	        }
	    }

}
