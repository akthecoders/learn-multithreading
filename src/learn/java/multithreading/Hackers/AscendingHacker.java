package learn.java.multithreading.Hackers;

public class AscendingHacker extends HackerThread {

    public AscendingHacker(Vault vault) {
        super(vault);
    }

    @Override
    public void run() {
        for(int guess = 0; guess < Main.MAX_PASSWORD; guess++) {
            if(this.vault.isCorrectPassword(guess)) {
                System.out.println(this.getName() + " guess the password " + guess);
                System.exit(0);
            }
        }
    }
}
