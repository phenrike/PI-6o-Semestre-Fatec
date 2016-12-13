package Controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Model.Ingrediente;

@RestController
public class IngredienteController {

	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

	@RequestMapping(value = "/ingredientes", method = RequestMethod.GET)
	public List<Ingrediente> carregarIngredientes() {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("WebServicePizzaJustInTime");
		EntityManager entityManager = factory.createEntityManager();

		List<Ingrediente> listaIngredientes = (entityManager.createQuery("SELECT i FROM Ingrediente i",
				Ingrediente.class)).getResultList();

		entityManager.close();
		factory.close();

		return listaIngredientes;
	}

	@RequestMapping(value = "/ingredientes/{id}", method = RequestMethod.GET)
	public Ingrediente carregarIngredientePorId(@PathVariable("id") int id) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("WebServicePizzaJustInTime");
		EntityManager entityManager = factory.createEntityManager();

		Ingrediente ingrediente = (entityManager.createQuery("SELECT i FROM Ingrediente i WHERE i.id=" + id,
				Ingrediente.class)).getSingleResult();

		entityManager.close();
		factory.close();

		return ingrediente;
	}

}
