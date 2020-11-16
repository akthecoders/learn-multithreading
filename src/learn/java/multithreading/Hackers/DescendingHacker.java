package learn.java.multithreading.Hackers;

public class DescendingHacker extends HackerThread {
    public DescendingHacker(Vault vault) {
        super(vault);
    }

    @Override
    public void run() {
        for(int guess = Main.MAX_PASSWORD; guess >= 0; guess--) {
            if(this.vault.isCorrectPassword(guess)) {
                System.out.println(this.getName() + " guess the password " + guess);
                System.exit(0);
            }
        }
    }
}
