package entities.catalogue.search.spec;

public class TitleSpec implements Spec<String> {
    private String title;
    public TitleSpec(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean matches(Spec<String> other) {
        if (other instanceof TitleSpec) {
            TitleSpec otherTitleSpec = (TitleSpec) other;
            return title.equalsIgnoreCase(otherTitleSpec.getTitle());
        }
        return false;
    }
}
