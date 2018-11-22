import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class CipherTest {

    Cipher cipher = new Cipher(1, 0.25f);
    Logger logger = Logger.getLogger("cipher logger");

    @Property public void  testEncryptDecrypt(@From(StringGenerator.class) String pt){
        logger.setLevel(Level.INFO);
        String encrypted = cipher.encrypt(pt);
        String decrypted = cipher.decrypt(encrypted);
        assertEquals(pt, decrypted);
    }

    @Test
    public void simpleTest(){
        String test = "Z\\vTKuUzch";
        String encrypted = cipher.encrypt(test);
        System.out.println("encrypted as: " + encrypted);
        String decrypted = cipher.decrypt(encrypted);
        System.out.println("decrypted as: " + decrypted);
        assertEquals(decrypted, test);
    }


}
