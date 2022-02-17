package hibernate;

import java.util.HashSet;
import java.util.Set;

public class Genre {
    public Genre(){}

    private Long ID = Long.valueOf(0);
    private String name = "";
    private Set films = new HashSet();

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

    public Set getFilms() {
        return films;
    }

    public void setFilms(Set films) {
        this.films = films;
    }

    @Override
    public String toString(){
        return name;
    }
}
