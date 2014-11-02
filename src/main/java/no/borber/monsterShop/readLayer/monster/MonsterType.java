package no.borber.monsterShop.readLayer.monster;

public class MonsterType implements Comparable<MonsterType>{
    private final String name;
    private final double price;

    public MonsterType(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonsterType that = (MonsterType) o;

        return !(name != null ? !name.equals(that.name) : that.name != null);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }


    @Override
    public int compareTo(MonsterType monsterJson) {
        if(monsterJson == null) return 0;
        return name.compareTo(monsterJson.getName());
    }
}
