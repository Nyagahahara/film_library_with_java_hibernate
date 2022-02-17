package hibernate;

import java.util.HashSet;
import java.util.Set;

public class Actor {

    public Actor(){}

    private Long ID = Long.valueOf(0);
    private String firstName = "";
    private String secondName = "";
    private Set films = new HashSet();

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String second_name) {
        this.secondName = second_name;
    }

    public Set getFilms() {
        return films;
    }

    public void setFilms(Set films) {
        this.films = films;
    }

    @Override
    public String toString(){
        return (firstName + " " + secondName);
    }

}

