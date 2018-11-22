import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.java.lang.Encoded;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

@RunWith(JUnitQuickcheck.class)
public class CipherTest {

    Cipher cipher = new Cipher(1, 0.25f);

    @Property public void  testEncryptDecrypt(@Encoded.InCharset("UTF-8") String pt){
        System.out.println("------------plaintext---------------");
        pt.chars().forEach(x -> System.out.print("|" + x));
        String encrypted = cipher.encrypt(pt);
        System.out.println("-------------Cipher text--------------");
        encrypted.chars().forEach(x -> System.out.print("|" + x));
        String decrypted = cipher.decrypt(encrypted);
        System.out.println("-------------decrypted plaintext--------------");
        decrypted.chars().forEach(x -> System.out.print("|" + x));
        assertEquals(pt, decrypted);
    }

    @Test
    public void simpleTest(){
        String test = "abcde%&*";
        String encrypted = cipher.encrypt(test);
        System.out.println("encrypted as: " + encrypted);
        String decrypted = cipher.decrypt(encrypted);
        System.out.println("decrypted as: " + decrypted);
        assertEquals(decrypted, test);
    }


}
