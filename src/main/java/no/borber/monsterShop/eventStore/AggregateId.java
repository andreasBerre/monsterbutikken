package no.borber.monsterShop.eventStore;


public abstract class AggregateId {

    private final String aggregateId;

    protected AggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AggregateId that = (AggregateId) o;

        return !(aggregateId != null ? !aggregateId.equals(that.aggregateId) : that.aggregateId != null);
    }

    @Override
    public int hashCode() {
        return aggregateId != null ? aggregateId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return aggregateId;
    }
}
