package entities.catalogue.search.spec;

import entities.catalogue.Genre;

public class GenreSpec implements Spec<Genre> {
    private Genre genre;
    public GenreSpec(Genre genre) {
        this.genre = genre;
    }

    public Genre getGenre() {
        return genre;
    }
    @Override
    public boolean matches(Spec<Genre> other) {
        if (other != null) {
            GenreSpec otherGenreSpec = (GenreSpec) other;
            return genre == otherGenreSpec.getGenre();

        }
        return false;
    }
}
