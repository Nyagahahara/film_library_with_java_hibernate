package hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public interface FilmInterface {
    public void addFilm(Film film) throws SQLException;
    public void deleteFilm(Film film) throws SQLException;
    public void updateFilm(long id, Film film) throws SQLException;
    public Collection getAllFilms() throws SQLException;
    public Film getFilmById(long id) throws SQLException;
    public Collection getFilmsByActor(Actor actor) throws SQLException;
    public Collection getFilmsByGenre(Genre genre) throws SQLException;
    public Collection getFilmsByMinRating(double minRating) throws SQLException;
}
