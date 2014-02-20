package no.borber.monsterbutikken.handlekurv;

public class Ordrelinje {
    private String monsternavn;
    private double pris;
    private int antall;

    public Ordrelinje(String monsternavn, double pris) {
        this.monsternavn = monsternavn;
        this.pris = pris;
    }

    public void addMonster() {
        this.antall++;
    }

    public String getMonsternavn() {
        return monsternavn;
    }

    public int getAntall() {
        return antall;
    }

    public void fjernMonster() {
        antall--;
    }

    public double getPris() {
        return pris;
    }
}
