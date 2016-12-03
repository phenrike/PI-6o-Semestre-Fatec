package Model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BuscaIngrediente {

	public Ingrediente buscarIngrediente(String id) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("WebServicePizzaJustInTime");
		EntityManager entityManager = factory.createEntityManager();

		Ingrediente query = entityManager.createQuery("SELECT i FROM Ingrediente i WHERE i.id=" + id, Ingrediente.class)
				.getSingleResult();

		entityManager.close();
		factory.close();

		return query;
	}
}
