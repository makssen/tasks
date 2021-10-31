import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Mac;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.security.Key;
import java.security.InvalidKeyException;
import java.io.UnsupportedEncodingException;

public class KeyGenerator {
	private int computerMove;
	private Key key;
	byte[] bytes;
	byte[] hmacData;

	public KeyGenerator(String[] moves) throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException{
		SecureRandom random = new SecureRandom();
		computerMove = random.nextInt(moves.length);
		bytes = new byte[16];
		random.nextBytes(bytes);
		key = new SecretKeySpec(bytes, "HMACSHA256");
		Mac hmac = Mac.getInstance("HMACSHA256");
		hmac.init(key);
		hmacData = hmac.doFinal(moves[computerMove].getBytes("utf-8"));
	}

	public void showHMAC() {
		System.out.println("HMAC: " + bytesToHex(hmacData));
	}

	public void showKey() {
		System.out.println("HMAC key: " + bytesToHex(bytes));
	}

	public int getMove() {
		return computerMove;
	}

	public String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
}
