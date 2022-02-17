package hibernate;

import java.sql.SQLException;
import java.util.Collection;

public interface GenreInterface {
    public void addGenre(Genre genre) throws SQLException;
    public void deleteGenre(Genre genre) throws SQLException;
    public Collection getAllGenres() throws SQLException;
}
