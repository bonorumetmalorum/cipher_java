import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.java.lang.Encoded;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class CipherTest {

    @Property public void  testEncryptDecrypt(@Encoded.InCharset("UTF-8") String pt){
        Cipher cipher = new Cipher(1, (float) 0.25);
        String encrypted = cipher.encrypt(pt);
        System.out.println("Encrypted: "+ encrypted);
        String decrypted = cipher.decrypt(encrypted);
        System.out.println("Decrypted: "+ decrypted);
        assertEquals(pt, decrypted);
    }


}
