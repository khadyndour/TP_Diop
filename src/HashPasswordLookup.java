import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPasswordLookup {
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = String.format("%02x", b);
                hexStringBuilder.append(hex);
            }

            return hexStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String findPasswordForHash(String targetHash, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String password = parts[0].trim();
                    String hashedPassword = parts[1].trim();
                    if (hashedPassword.equalsIgnoreCase(targetHash)) {
                        return password;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String targetHash = "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad";
        String filePath = "C:/Users/Core/Desktop/TPDIOP/liste.csv";

        String foundPassword = findPasswordForHash(targetHash, filePath);

        if (foundPassword != null) {
            System.out.println("Mot de passe trouvé : " + foundPassword);
        } else {
            System.out.println("Aucun mot de passe correspondant au hash trouvé.");
        }
    }
}
