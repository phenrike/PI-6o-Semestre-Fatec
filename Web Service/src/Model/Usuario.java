package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuario {

	private int id;
	private String nome;
	private String email;
	private String login;
	private String senha;
	private String telefone;
	private String dadosCartao;
	private String endereco;

	public Usuario() {

	}

	public void carregarUsuario(String login, String senha) {
		try {

			ConexaoDB conexao = new ConexaoDB();
			conexao.iniciarConexao();
			ResultSet rs = conexao.executarQuery("select * from usuario where login='" + login + "' and senha='" + senha + "'");

			if (rs.next()) {
				this.id = rs.getInt("ID");
				this.nome = rs.getString("NOME");
				this.email = rs.getString("EMAIL");
				this.login = rs.getString("LOGIN");
				this.senha = rs.getString("SENHA");
				this.telefone = rs.getString("TELEFONE");
				this.dadosCartao = rs.getString("DADOSCARTAO");
				this.endereco = rs.getString("ENDERECO");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDadosCartao() {
		return dadosCartao;
	}

	public void setDadosCartao(String dadosCartao) {
		this.dadosCartao = dadosCartao;
	}
}
