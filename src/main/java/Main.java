import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

public class Main {

    static PrintWriter stdout = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true);

    public static void main(String[] args) {
        boolean run = true;

        Cipher cipher = cipherFactory(args);

        while(run){
            System.out.println("Encrypt (press E) / Decrypt (press D) / Quit (press Q)");
            String in = System.console().readLine().toLowerCase();
            switch (in) {
                case "e":
                    System.out.println("please input the filepath to the file to be encrypted");
                    String filepath = System.console().readLine();
                    try{
                        BufferedReader reader = new BufferedReader(new FileReader(filepath));
                        BufferedWriter writer = new BufferedWriter(new FileWriter("encrypted_input.txt"));
                        String nextLine;
                        while((nextLine = reader.readLine()) != null){
                            writer.write(cipher.encrypt(nextLine) + "\n");
                        }
                        reader.close();
                        writer.close();
                        stdout.println("your encrypted text was written to: encrypted_input.txt");
                    }catch(Exception e){
                        System.out.println("exception: " + e.getMessage());
                    }
                    break;
                case "d":
                    System.out.println("please input the filepath to the file to be decrypted");
                    String filepathEncrypted = System.console().readLine();
                    try{
                        BufferedReader reader = new BufferedReader(new FileReader(filepathEncrypted));
                        BufferedWriter writer = new BufferedWriter(new FileWriter("decrypted_output.txt"));
                        String nextLine;
                        while((nextLine = reader.readLine()) != null){
                            writer.write(cipher.decrypt(nextLine) + "\n");
                        }
                        reader.close();
                        writer.close();
                        stdout.println("your decrypted text was written to: decrypted_output.txt");
                    }catch(Exception e){
                        System.out.println("exception: " + e.getMessage());
                    }
                    break;
                case "q": run = false; break;
                default: System.out.println("invalid option"); break;
            }
        }
    }

    public static Cipher cipherFactory(String[] args){
        System.out.println("please input a seed number (any number)");
        int seed = Integer.parseInt(System.console().readLine());
        System.out.println("please input a threshold number (0.0 - 1.0)");
        float threshold = Float.parseFloat(System.console().readLine());
        Cipher cipher = new Cipher(seed, threshold);
        return cipher;
    }


}
