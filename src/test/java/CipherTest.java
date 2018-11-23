import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class CipherTest {

    Cipher cipher = new Cipher(123457890, 0.75f);
    Logger logger;
    FileHandler output;
    SimpleFormatter formatter;


    @Before public void setup() throws IOException{
        output = new FileHandler("logs/output.log");
        logger = Logger.getLogger("cipher logger");
        formatter = new SimpleFormatter();
        output.setFormatter(formatter);
        logger.addHandler(output);
    }

    @Property public void  testEncryptDecrypt(@From(StringGenerator.class) String pt){
        logger.setLevel(Level.INFO);
        String encrypted = cipher.encrypt(pt);
        String decrypted = cipher.decrypt(encrypted);
        String info = "\npt: " + pt + "\nencrypted: " + encrypted + "\ndecrypted: " + decrypted;
        logger.info(info);
        assertEquals(pt, decrypted);
    }
}
