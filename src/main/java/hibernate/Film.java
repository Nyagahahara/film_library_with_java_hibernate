package hibernate;

import java.util.HashSet;
import java.util.Set;

public class Film {

    public Film(){}

    private Long ID = Long.valueOf(0);
    private String name = "";
    private Double rating = 0.0;
    private Boolean seen = false;

    private Set<Actor> actors = new HashSet();
    private Set <Genre> genres = new HashSet();

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }

    public Set <Actor> getActors() {
        return actors;
    }

    public void setActors(Set actors) {
        this.actors = actors;
    }

    public Set <Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set genres) {
        this.genres = genres;
    }

    @Override
    public String toString(){
        return name;
    }

}
