package Controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import Model.Ingrediente;
import Model.Login;
import Model.LoginErro;
import Model.Pedido;
import Model.Usuario;

@Configuration
@ComponentScan(value = { "Model" })
public class AppConfig {

	@Bean
	public Login login() {
		return new Login();
	}

	@Bean
	public LoginErro loginErro() {
		return new LoginErro();
	}

	@Bean
	public Pedido pedido() {
		return new Pedido();
	}

	@Bean
	public Usuario usuario() {
		return new Usuario();
	}

	@Bean
	public Ingrediente ingrediente() {
		return new Ingrediente();
	}

}
