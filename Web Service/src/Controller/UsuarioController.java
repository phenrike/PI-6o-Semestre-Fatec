package Controller;

import java.io.EOFException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import Model.ConexaoDB;
import Model.Ingrediente;
import Model.Login;
import Model.LoginErro;
import Model.Usuario;

@RestController
public class UsuarioController {

	@RequestMapping("/logar")
	public ModelAndView Logar(@RequestParam(value = "login", defaultValue = "") String login,
			@RequestParam(value = "senha", defaultValue = "") String senha) {

		ModelAndView model = null;
		Login log = new Login();
		if (log.logar(login, senha)) {
			model = new ModelAndView("/carregarUsuario" + "?" + "nomeAcesso=" + login + "&passeAcesso=" + senha);
		} else {
			model = new ModelAndView("/erro");
		}

		return model;
	}

	@RequestMapping("/carregarUsuario")
	public Usuario carregarUsuario(@RequestParam(value = "nomeAcesso", defaultValue = "") String nomeAcesso,
			@RequestParam(value = "passeAcesso", defaultValue = "") String passeAcesso) {
		Usuario usuario = new Usuario();
		usuario.carregarUsuario(nomeAcesso, passeAcesso);
		return usuario;
	}
	
	@RequestMapping("/carregarIngredientes")
	public ArrayList<Ingrediente> carregarIngredientes() {
		
		ConexaoDB conexao = new ConexaoDB();
		conexao.iniciarConexao();
		ResultSet rs = conexao.executarQuery("SELECT * FROM INGREDIENTE");
		ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		try {
			while (rs.next()) {
				  Ingrediente ingrediente = new Ingrediente();
				  ingrediente.setId(rs.getInt("ID"));
				  ingrediente.setNome(rs.getString("NOME"));
				  ingrediente.setPrecoPorGrama(rs.getDouble("PRECOPORGRAMA"));
				  ingredientes.add(ingrediente);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ingredientes;
	}

	@RequestMapping("/erro")
	public LoginErro reportarErroLogin() {
		LoginErro le = new LoginErro();
		return le;
	}
}
