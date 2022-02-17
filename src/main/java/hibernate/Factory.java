package hibernate;

public class Factory {
    private static FilmMapper film = null;
    private static ActorMapper actor = null;
    private static GenreMapper genre = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance(){
        if (instance == null)
            instance = new Factory();
        return instance;
    }

    public FilmMapper getFilmMapper(){
        if (film == null)
            film = new FilmMapper();
        return film;
    }

    public ActorMapper getActorMapper(){
        if (actor == null)
            actor = new ActorMapper();
        return actor;
    }
    public GenreMapper getGenreMapper(){
        if (genre == null)
            genre = new GenreMapper();
        return genre;
    }
}
