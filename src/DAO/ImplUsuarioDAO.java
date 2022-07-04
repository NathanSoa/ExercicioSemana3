package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
		
		try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/courseraDB", "root", "5432es")) {
			String sql = "UPDATE usuario SET pontos = pontos + ? WHERE login = ?";
			PreparedStatement stmt =  c.prepareStatement(sql);
			
			stmt.setInt(1, pontos);
			stmt.setString(2, login);
			
			stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void inserir(Usuario u) {
		
		try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/courseraDB", "root", "5432es")) {
			String sql = "INSERT INTO usuario(login, email, nome, senha, pontos) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stmt =  c.prepareStatement(sql);
			
			stmt.setString(1, u.getLogin());
			stmt.setString(2, u.getEmail());
			stmt.setString(3, u.getNome());
			stmt.setString(4, u.getSenha());
			stmt.setInt(5, u.getPontos());
			
			stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
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
