package Controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import Model.Login;
import Model.LoginErro;
import Model.Usuario;

@RestController
public class UsuarioController {

	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

	@RequestMapping(value = "/login/{login}/{senha}", method = RequestMethod.GET)
	public ModelAndView Logar(@PathVariable("login") String login, @PathVariable("senha") String senha) {

		ModelAndView model = null;
		Login log = (Login) context.getBean(Login.class);

		if (log.logar(login, senha)) {
			model = new ModelAndView("/usuarios/" + login + "/" + senha);

		} else {
			System.out.println(login + " " + senha);
			model = new ModelAndView("/loginErro/");
		}

		return model;
	}

	@RequestMapping(value = "/loginErro/", method = RequestMethod.GET)
	public LoginErro reportarErroLogin() {

		LoginErro le = (LoginErro) context.getBean(LoginErro.class);

		return le;
	}

	@RequestMapping(value = "/usuarios/{nomeAcesso}/{passeAcesso}", method = RequestMethod.GET)
	public Usuario carregarUsuario(@PathVariable("nomeAcesso") String nomeAcesso,
			@PathVariable("passeAcesso") String passeAcesso) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("WebServicePizzaJustInTime");
		EntityManager entityManager = factory.createEntityManager();

		Usuario usuario = entityManager.createQuery(
				"SELECT u FROM Usuario u WHERE u.login='" + nomeAcesso + "' and u.senha='" + passeAcesso + "'",
				Usuario.class).getSingleResult();

		entityManager.close();
		factory.close();

		return usuario;
	}

	@RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET)
	public Usuario carregarUsuarioPorId(@PathVariable("id") int id) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("WebServicePizzaJustInTime");
		EntityManager entityManager = factory.createEntityManager();

		Usuario usuario = (entityManager.createQuery("SELECT u FROM Usuario u WHERE u.id=" + id, Usuario.class))
				.getSingleResult();

		entityManager.close();
		factory.close();

		return usuario;
	}
}
