package no.borber.monsterShop.authentication;


import no.borber.serialized.AggregateId;

public class CustomerId extends AggregateId{
    public CustomerId(String customerName) {
        super(customerName);
    }
}
