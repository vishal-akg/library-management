package entities.catalogue.search.spec;

public class PublisherSpec implements Spec<String> {
    private String publisher;
    public PublisherSpec(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    @Override
    public boolean matches(Spec<String> other) {
        if (other instanceof PublisherSpec) {
            PublisherSpec otherPublisherSpec = (PublisherSpec) other;
            return publisher.equalsIgnoreCase(otherPublisherSpec.getPublisher());
        }
        return false;
    }
}
