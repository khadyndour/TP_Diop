import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class kha {
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

    public static void main(String[] args) {
        List<String> passwords = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/Core/Desktop/TPDIOP/liste.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                passwords.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrer un mot de passe :");
        String password = scanner.nextLine();
        scanner.close();

        String hashedPassword = hashPassword(password);

        System.out.println("Mot de passe : " + password);
        System.out.println("Hachage du mot de passe : " + hashedPassword);
    }
}
