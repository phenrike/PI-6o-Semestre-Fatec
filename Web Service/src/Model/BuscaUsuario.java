package Model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BuscaUsuario {

	public Usuario buscarUsuario(String id) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("WebServicePizzaJustInTime");
		EntityManager entityManager = factory.createEntityManager();

		Usuario query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.id=" + id, Usuario.class)
				.getSingleResult();

		entityManager.close();
		factory.close();

		return query;
	}

}
