import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Cipher {

    private final int maxVal = Character.MAX_VALUE;

    private int key;

    private final float threshold;

    Cipher(int seed, float threshold){
        key = seed;
        this.threshold = threshold;
    }

    private String confuse(String plaintext){
        Random cryptRandom = new Random(key);
        char[] chars = plaintext.toCharArray();
        char[] encrypted = new char[chars.length];
        long displace;
        for(int i = 0; i < chars.length; i++){
            displace = cryptRandom.nextInt(maxVal);
            System.out.println("displaced by: " + displace );
            encrypted[i] = (char)((chars[i] + displace)%maxVal);
            System.out.println((int)encrypted[i]);
        }
        return new String(encrypted);
    }

    private String deconfuse(String ciphertext){
        Random cryptRandom = new Random(key);
        char[] chars = ciphertext.toCharArray();
        char[] encrypted = new char[chars.length];
        int displace;
        for(int i = 0; i < chars.length; i++){
            displace = cryptRandom.nextInt(maxVal) ;
            System.out.println("displaced by: " + displace );
            encrypted[i] = (char)((chars[i] - displace)%maxVal);
            System.out.println((int)encrypted[i]);
        }
        return new String(encrypted);
    }

    private String diffuse(String ciphertext){
        Random transposeRandom = new Random(key);
        char[] permuted = ciphertext.toCharArray();
        for(int i = 0; i < ciphertext.length()-1; i+=2){
            char buffChar;
            if(transposeRandom.nextFloat() > threshold){
                System.out.println("diffused");
                buffChar = permuted[i];
                permuted[i] = permuted[i+1];
                permuted[i+1] = buffChar;
            }
        }
        return new String(permuted);
    }

    private String undiffuse(String ciphertext){
        Random transposeRandom = new Random(key);
        char[] permuted = ciphertext.toCharArray();
        for(int i = 0; i < ciphertext.length()-1; i+=2){
            char buffChar;
            if(transposeRandom.nextFloat() > threshold){
                System.out.println("undiffused");
                buffChar = permuted[i+1];
                permuted[i+1] = permuted[i];
                permuted[i] = buffChar;
            }
        }
        return new String(permuted);
    }

    public String encrypt(String pt){
        String confused = confuse(pt);
        String diffused = diffuse(confused);
        return diffused;
    }

    public String decrypt(String ct){
        String undiffused = undiffuse(ct);
        String deconfused = deconfuse(undiffused);
        return deconfused;
    }


}
