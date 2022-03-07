// https://sunghs.tistory.com/119

package encryption;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

// AES128 : 128 Byte(String 16 letters)
public class Aes128 {
	// based for encrypt & decrypt
	private static final Charset ENCODING_TYPE = StandardCharsets.UTF_8;
	private static final String INSTANCE_TYPE = "AES/CBC/PKCS5Padding";
	private SecretKeySpec secretKeySpec;
	private Cipher cipher;
	private IvParameterSpec ivParameterSpec;
	
	public Aes128(final String key) {
		validation(key);
		try {
			byte[] keyBytes = key.getBytes(ENCODING_TYPE);
			secretKeySpec = new SecretKeySpec(keyBytes, "AES");
				/*
						reference URL : https://docs.oracle.com/javase/7/docs/api/javax/crypto/spec/SecretKeySpec.html
					
					This class specifies a secret key in a provider-independent fashion.
					It can be used to construct a SecretKey from a byte array, without having to go through a (provider-based) SecretKeyFactory.
		
					This class is only useful for raw secret keys that can be represented as a byte array and have no key parameters associated with them, e.g., DES or Triple DES keys.
					
					!!) use : SecretKeySpec(byte[] key, String algorithm)
						Constructs a secret key from the given byte array.
						
				 * */
			cipher = Cipher.getInstance(INSTANCE_TYPE);
				/*
				  		reference URL : https://docs.oracle.com/javase/7/docs/api/javax/crypto/Cipher.html
				 
				 	This class provides the functionality of a cryptographic cipher for encryption and decryption. It forms the core of the Java Cryptographic Extension (JCE) framework.
					In order to create a Cipher object, the application calls the Cipher's getInstance method, and passes the name of the requested transformation to it.
					Optionally, the name of a provider may be specified.
		
					A transformation is a string that describes the operation (or set of operations) to be performed on the given input, to produce some output.
					A transformation always includes the name of a cryptographic algorithm (e.g., AES), and may be followed by a feedback mode and padding scheme.
					
				 	!!) use : Cipher c = Cipher.getInstance(( TYPE ) : "AES/CBC/PKCS5Padding");
				 	
				 * */
			ivParameterSpec = new IvParameterSpec(keyBytes);
				/*
						reference URL : https://docs.oracle.com/javase/8/docs/api/javax/crypto/spec/IvParameterSpec.html
					
					This class specifies an initialization vector (IV). Examples which use IVs are ciphers in feedback mode, e.g., DES in CBC mode and RSA ciphers with OAEP encoding operation.
					
					!!) use : IvParameterSpec(byte[] iv)
						Creates an IvParameterSpec object using the bytes in iv as the IV.
						
				 * */
		} catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public String encrypt(final String str) throws Exception{
		// Cipher.ENCRYPT_MODE : Constant used to initialize cipher to encryption mode
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
		// init(int opmode, Key key, AlgorithmParameterSpec params)
			// Initializes this cipher with a key and a set of algorithm parameters.
		byte[] encrypted = cipher.doFinal(str.getBytes(ENCODING_TYPE));
		// doFinal(byte[] input)
			// Encrypts or decrypts data in a single-part operation, or finishes a multiple-part operation.
		return new String(Base64.getEncoder().encode(encrypted), ENCODING_TYPE);
	}
	
	public String decrypt(final String str) throws Exception{
		// Cipher.DECRYPT_MODE : Constant used to initialize cipher to decryption mode.
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
		byte[] decoded = Base64.getDecoder().decode(str.getBytes(ENCODING_TYPE));
		return new String(cipher.doFinal(decoded), ENCODING_TYPE);
	}
	
	// Count Key's letters Method
	// key must be have 16 letters
	private void validation(final String key) {
		Optional.ofNullable(key)
		.filter(Predicate.not(String::isBlank))
		.filter(Predicate.not(s -> s.length() != 16))
		.orElseThrow(IllegalArgumentException::new);
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		// Aes128(key : Encryption & Decryption Symmetric Key)
		Aes128 aes128 = new Aes128("passwordadmin128");
		String enc = aes128.encrypt("chanryeol");
		String dec = aes128.decrypt(enc);
		System.out.println(enc);
		System.out.println(dec);
		sc.close();
	}
}
