package no.borber.monsterShop.monsterTypes;

public class MonsterTypeJson implements Comparable<MonsterTypeJson>{
    private final String name;
    private final double price;

    public MonsterTypeJson(String name, double price) {
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

        MonsterTypeJson that = (MonsterTypeJson) o;

        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }


    @Override
    public int compareTo(MonsterTypeJson monsterJson) {
        if(monsterJson == null) return 0;
        return name.compareTo(monsterJson.getName());
    }
}
