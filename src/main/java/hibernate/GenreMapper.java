package hibernate;

import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenreMapper implements GenreInterface {

    public void addGenre(Genre genre) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(genre);
            session.getTransaction().commit();
        } catch (Exception e) {
e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void deleteGenre(Genre genre) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(genre);
            session.getTransaction().commit();
        } catch (Exception e) {

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Collection getAllGenres() throws SQLException {
        Session session = null;
        List genres = new ArrayList<Genre>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            //genres = session.createCriteria(Genre.class).list();
            genres = session.createQuery("from Genre").getResultList();
        } catch (Exception e) {

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return genres;
    }
}
