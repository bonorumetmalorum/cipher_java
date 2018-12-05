public class Main {

    public static void main(String[] args) {
        Cipher cipher = cipherFactory(args);

        while(true){
            System.out.println("input text to be encrypted or enter Q to quit");
            String in = System.console().readLine();
            if(!in.toLowerCase().equals("q")){
                String encrypted = cipher.encrypt(in);
                System.out.println("encrypted: " + encrypted);
                String decrypted = cipher.decrypt(encrypted);
                System.out.println("decrypted: " + decrypted);
            }
            else{
                break;
            }
        }
    }

    public static Cipher cipherFactory(String[] args){
        System.out.println("please input a seed number (any number)");
        int seed = Integer.parseInt(System.console().readLine());
        System.out.println("please input a threshold number (0.0 - 1.0)");
        float threshold = Float.parseFloat(System.console().readLine());
        return new Cipher(seed, threshold);
    }


}
