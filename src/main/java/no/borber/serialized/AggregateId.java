package no.borber.serialized;


public abstract class AggregateId {

    private final String aggregateId;

    protected AggregateId(String aggregateId) {
        if (aggregateId == null || aggregateId.isEmpty())
            throw new IllegalArgumentException("Attempt to create aggregate id with null or empty string.");
        else
            this.aggregateId = aggregateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AggregateId that = (AggregateId) o;

        return aggregateId.equals(that.aggregateId);
    }

    @Override
    public int hashCode() {
        return aggregateId.hashCode();
    }

    @Override
    public String toString() {
        return aggregateId;
    }
}
