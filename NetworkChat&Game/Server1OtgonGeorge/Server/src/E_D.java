import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.*;
import java.util.*;
public class E_D {
 
    private static SecretKeySpec cheie_secreta;
    private static byte[] cheie;
 
    public static void setare_cheie(String myKey) 
    {
        MessageDigest x = null;
        try {
            cheie = myKey.getBytes("UTF-8");
            x = MessageDigest.getInstance("SHA-1");
            cheie = x.digest(cheie);
            cheie = Arrays.copyOf(cheie, 16); 
            cheie_secreta = new SecretKeySpec(cheie, "AES");
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    public static String criptare(String strToEncrypt, String secret) 
    {
        try
        {
            setare_cheie(secret);
            Cipher cifru = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cifru.init(Cipher.ENCRYPT_MODE, cheie_secreta);
            return Base64.getEncoder().encodeToString(cifru.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
            System.out.println("eroare la criptare: " + e.toString());
        }
        return null;
    }
 
    public static String decriptare(String strToDecrypt, String secret) 
    {
        try
        {
            setare_cheie(secret);
            Cipher cifru = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cifru.init(Cipher.DECRYPT_MODE, cheie_secreta);
            return new String(cifru.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (Exception e) 
        {
            System.out.println("eroare la decriptare: " + e.toString());
        }
        return null;
    }
}
