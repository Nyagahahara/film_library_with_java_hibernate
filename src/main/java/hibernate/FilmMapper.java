package hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FilmMapper implements FilmInterface {

    public void addFilm(Film film) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(film);
            session.getTransaction().commit();
        } catch (Exception e) {

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateFilm(long id, Film film) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(film);
            session.getTransaction().commit();
        } catch (Exception e) {

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Film getFilmById(long id) throws SQLException {
        Session session = null;
        Film film = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            film = (Film) session.load(Film.class, id);
        } catch (Exception e) {

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return film;
    }

    public Collection getAllFilms() throws SQLException {
        Session session = null;
        List films = new ArrayList<Film>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            //CriteriaBuilder builder = session.getCriteriaBuilder();
            //CriteriaQuery<Film> criteria = builder.createQuery(Film.class);
            //films = session.createCriteria("from Film").list();
            //films = criteria.getRe
            //Query query =
            films = session.createQuery("from Film").getResultList();
            for (int i = 0; i < films.size(); i++)
            	System.out.println(films.get(i).toString());
        } catch (Exception e) {

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return films;
    }

    public void deleteFilm(Film film) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(film);
            session.getTransaction().commit();
        } catch (Exception e) {

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Collection getFilmsByActor(Actor actor) throws SQLException {
        Session session = null;
        ArrayList<Film> films = new ArrayList<Film>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("select distinct f FROM Film  as f INNER JOIN f.actors as fa INNER JOIN Actor as a with fa.ID = :param1");//all
            query.setParameter("param1", actor.getID());
            films = (ArrayList<Film>) query.getResultList();
            for (int i = 0; i < films.size(); i++) {
            	System.out.println(films.get(i).toString());
            }
            System.out.println("Size: " + films.size());
            session.getTransaction().commit();
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return films;
    }

    public Collection getFilmsByGenre(Genre genre) throws SQLException {
        Session session = null;
        List films = new ArrayList<Film>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            System.out.println("meow");
            Query query = session.createQuery("select distinct f FROM Film  as f INNER JOIN f.genres as fg INNER JOIN Genre as g with fg.ID = :param1");//all
            query.setParameter("param1", genre.getID());
            films = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return films;
    }

    public Collection getFilmsByMinRating(double minRating) throws SQLException {
        Session session = null;
        List films = new ArrayList<Film>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery(
					"from Film where rating >= :param1");
			query.setParameter("param1", minRating);
			films = query.getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
        return films;
    }
}
