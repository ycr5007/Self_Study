package encryption;

public class ConvertAscii {
	
	static char[] charMessage;
	public static String encryption(String message, int key) {
		charMessage = message.toCharArray();
		for(int i = 0; i < message.length(); i++) {
			charMessage[i] += key;
		}
		return String.valueOf(charMessage);
	}
	
	public static String decryption(String message, int key) {
		charMessage = message.toCharArray();
		for(int i = 0; i < message.length(); i++) {
			charMessage[i] -= key;
		}
		return String.valueOf(charMessage);
	}
	
}
