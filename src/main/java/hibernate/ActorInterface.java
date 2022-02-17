package hibernate;

import java.sql.SQLException;
import java.util.Collection;

public interface ActorInterface {
    public void addActor(Actor actor) throws SQLException;
    public void deleteActor(Actor actor) throws SQLException;
    public Collection getAllActors() throws SQLException;
}