package Model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.springframework.stereotype.Service;

@Service
public class Login {

	public boolean logar(String login, String senha) {

		boolean resultado = false;

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("WebServicePizzaJustInTime");
		EntityManager entityManager = factory.createEntityManager();

		try {

			Usuario query = entityManager
					.createQuery("SELECT u FROM Usuario u WHERE u.login='" + login + "' and u.senha='" + senha + "'",
							Usuario.class)
					.getSingleResult();

			if (query != null) {
				if (query.getId() > 0) {
					resultado = true;
				}
			}

			entityManager.close();
			factory.close();
		} catch (NoResultException e) {
			return false;
		}

		return resultado;
	}
}
