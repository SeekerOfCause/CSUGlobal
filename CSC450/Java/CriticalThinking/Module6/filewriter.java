package CriticalThinking.Module6;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class filewriter {

    private static final String SECRET_KEY = "MySecretKey12345"; // Replace with your secret key
    private static final String FILE_PROPERTY = "secure.file.path"; // System property for the file path

    public static void main(String[] args) {
        System.setProperty(FILE_PROPERTY, "securefile.txt");
        try {
            String filePath = System.getProperty(FILE_PROPERTY);
            if (filePath != null) {
                writeData(filePath);
                encryptFile(filePath);
            } else {
                System.out.println("File path not specified. Set the '" + FILE_PROPERTY + "' system property.");
            }
        } catch (IOException e) {
            System.out.println("Error writing data: " + e.getMessage());
        } catch (GeneralSecurityException e) {
            System.out.println("Security error: " + e.getMessage());
        }
    }

    static void writeData(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(filePath));

            System.out.print("Enter data to write to the file: \n");
            String data = reader.readLine().trim();

            writer.write(data);
            System.out.println("Data written to the file.");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    static void encryptFile(String filePath) throws IOException, GeneralSecurityException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        FileOutputStream encryptedOutputStream = new FileOutputStream(filePath + ".encrypted");

        Cipher cipher = createCipher(Cipher.ENCRYPT_MODE);
        CipherOutputStream cipherOutputStream = new CipherOutputStream(encryptedOutputStream, cipher);

        byte[] buffer = new byte[8192]; // Increased buffer size for better performance
        int bytesRead;
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            cipherOutputStream.write(buffer, 0, bytesRead);
        }

        cipherOutputStream.close();
        fileInputStream.close();

        File originalFile = new File(filePath);
        originalFile.delete();

        File encryptedFile = new File(filePath + ".encrypted");
        encryptedFile.renameTo(originalFile);
    }

    static Cipher createCipher(int cipherMode) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(cipherMode, secretKeySpec);
        return cipher;
    }
}

