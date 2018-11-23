public class Main {

    public static void main(String[] args) {
        Boolean run = true;

        int seed;

        float threshold;

        while(run){
            System.out.println("Encrypt (press E) / Decrypt (press D) / Quit (press Q)");
            if(System.console().readLine().toLowerCase() == "e"){
                System.out.println("please input a seed number");
                seed = Integer.parseInt(System.console().readLine());
                System.out.println("please input a threshold number (0.0 - 1.0)");
                threshold = Float.parseFloat(System.console().readLine());
                Cipher cipher = new Cipher(seed, threshold);
                System.out.println("please input your text to be encrypted");
                String plaintext = System.console().readLine();
                String encrpted = cipher.encrypt(plaintext);
                System.out.println("your encrypted text: " + encrpted);
            }
            else if(System.console().readLine().toLowerCase() == "d"){
                System.out.println("please input a seed number");
                seed = Integer.parseInt(System.console().readLine());
                System.out.println("please input a threshold number (0.0 - 1.0)");
                threshold = Float.parseFloat(System.console().readLine());
                Cipher cipher = new Cipher(seed, threshold);
                System.out.println("please input your text to be decrypted");
                String ciphertext = System.console().readLine();
                String decrypted = cipher.decrypt(ciphertext);
                System.out.println("your encrypted text: " + decrypted);
            }
            else if(System.console().readLine().toLowerCase() == "q"){
                run = false;
            }
            else{
                System.out.println("invalid option");
            }
        }
    }
}
