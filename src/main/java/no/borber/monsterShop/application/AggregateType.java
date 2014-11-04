package no.borber.monsterShop.application;

public enum AggregateType {
    ORDER("ORDER"), BASKET("BASKET");

    private final String name;

    private AggregateType(String s) {
        name = s;
    }

    public boolean equalsName(String otherName){
        return (otherName == null)? false:name.equals(otherName);
    }

    public String toString(){
        return name;
    }

    }
