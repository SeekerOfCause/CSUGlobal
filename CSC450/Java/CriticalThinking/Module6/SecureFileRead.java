package CriticalThinking.Module6;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class SecureFileRead {
    private static final String SECRET_KEY = "MySecretKey12345"; // Replace with your secret key
    private static final String FILE_PROPERTY = "secure.file.path";

    public static void main(String[] args) {
        System.setProperty(FILE_PROPERTY, "securefile.txt"); // System property for the file path, local file used for example
        System.setProperty("system.file.env", "developeomnt");
        
        if (userPermissions() && systemPermissions()) {
			try {
				String filePath = System.getProperty(FILE_PROPERTY);
				if (filePath != null) {
					readData(filePath);
				} else {
					System.out.println("File path not specified. Set the '" + FILE_PROPERTY + "' system property.");
				}
			} catch (IOException e) {
				System.out.println("Error reading data: " + e.getMessage());
			} catch (GeneralSecurityException e) {
				System.out.println("Security error: " + e.getMessage());
        }
        }
    }

    private static boolean systemPermissions() {
		// Create logic to verify system permissions to view file data
    	
    	if (System.getProperty("system.file.env") == "developeomnt") {
    		return true;
    	}
		return false;
	}

	private static boolean userPermissions() {
		//Create logic to verify user permissions to view file data
		if (System.getProperty("system.file.env") == "developeomnt") {
    		return true;
    	}
		return true;
	}

	static void readData(String filePath) throws IOException, GeneralSecurityException {
        // Decrypt the file
        byte[] decryptedBytes = decryptFile(filePath);

        // Decrypt and process the data
        String decryptedData = new String(decryptedBytes, StandardCharsets.UTF_8);
        System.out.println("Decrypted data: " + decryptedData.trim());
    }

    static byte[] decryptFile(String filePath) throws IOException, GeneralSecurityException {
        Cipher cipher = createCipher(Cipher.DECRYPT_MODE);
        FileInputStream fileInputStream = new FileInputStream(filePath);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        CipherOutputStream cipherOutputStream = new CipherOutputStream(byteArrayOutputStream, cipher);

        byte[] buffer = new byte[8192]; // Increased buffer size for better performance
        int bytesRead;
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            cipherOutputStream.write(buffer, 0, bytesRead);
        }

        cipherOutputStream.close();
        fileInputStream.close();

        return byteArrayOutputStream.toByteArray();
    }

    static Cipher createCipher(int cipherMode) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(cipherMode, secretKeySpec);
        return cipher;
    }
}

