package DAO;

import java.util.List;

import Model.Usuario;

public class ImplUsuarioDAO implements UsuarioDAO {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void adicionarPontos(String login, int pontos) {
		

	}

	@Override
	public void inserir(Usuario u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario recuperar(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> ranking() {
		// TODO Auto-generated method stub
		return null;
	}

}
