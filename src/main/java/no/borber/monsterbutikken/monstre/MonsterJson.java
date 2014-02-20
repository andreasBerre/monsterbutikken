package no.borber.monsterbutikken.monstre;

public class MonsterJson implements Comparable<MonsterJson>{
    private final String navn;
    private final double pris;

    public MonsterJson(String navn, double pris) {
        this.navn = navn;
        this.pris = pris;
    }

    public String getNavn() {
        return navn;
    }

    public double getPris() {
        return pris;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonsterJson that = (MonsterJson) o;

        return !(navn != null ? !navn.equals(that.navn) : that.navn != null);

    }

    @Override
    public int hashCode() {
        return navn != null ? navn.hashCode() : 0;
    }


    @Override
    public int compareTo(MonsterJson monsterJson) {
        if(monsterJson == null) return 0;
        return navn.compareTo(monsterJson.getNavn());
    }
}
