package no.borber.monsterShop.basket;

public class BasketItem {
    private String monsterType;
    private double pris;
    private int number;

    public BasketItem(String monsterType, double price) {
        this.monsterType = monsterType;
        this.pris = price;
    }

    public void addMonster() {
        this.number++;
    }

    public String getMonsterType() {
        return monsterType;
    }

    public int getNumber() {
        return number;
    }

    public void removeMonster() {
        number--;
    }

    public double getPrice() {
        return pris;
    }
}
