// Youtube 대멀쌤 - "java 자바 암호화 복호화 메소드 호출 코딩"

package encryption;

import java.util.Scanner;

public class ExeAscii {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\rEncryption & Decryption Program");
		System.out.println("\rSelectMenu : 1.Encryption 2.Decryption");
		int menu = sc.nextInt();
		System.out.println("\rWrite Your Message.");
		String message = sc.next();
		System.out.println("\rWrite Your Key");
		int key = sc.nextInt();
		sc.close();
		if(menu == 1) {
			String plusMessage = ConvertAscii.encryption(message, key);
			System.out.println("Your Message : " + message);
			System.out.println("Encryption Message : " + plusMessage);
		}
		if(menu == 2) {
			String minusMessage = ConvertAscii.decryption(message, key);
			System.out.println("Your Message : " + message);
			System.out.println("Decryption Message : " + minusMessage);
		}
		System.out.println(ConvertAscii.encryption("korea", 1));
		System.out.println(ConvertAscii.decryption("lpsfb", 1));
		
	}
}
