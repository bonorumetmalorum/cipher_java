import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Cipher {

    private final int maxVal = Character.MAX_CODE_POINT;

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
        for(int i = 0; i < chars.length; i++){
            encrypted[i] = (char)(chars[i] + (cryptRandom.nextInt() % maxVal));
        }
        return new String(encrypted);
    }

    private String deconfuse(String ciphertext){
        Random cryptRandom = new Random(key);
        char[] chars = ciphertext.toCharArray();
        char[] encrypted = new char[chars.length];
        for(int i = 0; i < chars.length; i++){
            encrypted[i] = (char)(chars[i] - (cryptRandom.nextInt() % maxVal));
        }
        return new String(encrypted);
    }

    private String diffuse(String ciphertext){
        Random transposeRandom = new Random(key);
        char[] permuted = ciphertext.toCharArray();
        for(int i = 0; i < ciphertext.length()-1; i+=2){
            char buffChar;
            if(transposeRandom.nextFloat() > threshold){
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
        String deconfused = deconfuse(ct);
        String undiffused = undiffuse(deconfused);
        return undiffused;
    }


}
