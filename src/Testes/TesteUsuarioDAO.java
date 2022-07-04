package Testes;


import static org.junit.Assert.assertEquals;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Before;
import org.junit.Test;

import DAO.ImplUsuarioDAO;
import Model.Usuario;

public class TesteUsuarioDAO {

	JdbcDatabaseTester jdt;
	ImplUsuarioDAO usuDAO = new ImplUsuarioDAO();
	
	
	@Before
	public void setUp() throws Exception {
		jdt = new JdbcDatabaseTester("com.mysql.cj.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/courseraDB", "usuarioTeste", "senhaPadrao");		
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		jdt.setDataSet(loader.load("/arquivo.xml"));
		jdt.onSetup();
	}

	@Test
	public void recuperaTodosPorRanking() {
		List<Usuario> lista = usuDAO.ranking();
		assertEquals(3, lista.size());
		assertEquals("15", String.valueOf(lista.get(0).getPontos()));
	}
	
	@Test
	public void insereNovo() throws SQLException, Exception {
		usuDAO.inserir(new Usuario("nathan 4", "nathan4@gmail.com", "nathan 4", "senhaNathan4", 30));
		
		IDataSet currentDataSet = jdt.getConnection().createDataSet();
		ITable currentTable = currentDataSet.getTable("USUARIO");
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet expectedDataSet = loader.load("/testeInserir.xml");
		ITable expectedTable = expectedDataSet.getTable("USUARIO");
		
		Assertion.assertEquals(expectedTable, currentTable);
	}
	
	@Test
	public void AumentaPontosUsuario() throws SQLException, Exception {
		usuDAO.adicionarPontos("nathan 3", 35);
		
		IDataSet currentDataSet = jdt.getConnection().createDataSet();
		ITable currentTable = currentDataSet.getTable("USUARIO");
		FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet expectedDataSet = loader.load("/testeAdicionaPontos.xml");
		ITable expectedTable = expectedDataSet.getTable("USUARIO");
		
		Assertion.assertEquals(expectedTable, currentTable);
	}
	
	@Test
	public void recuperaPorLogin() {
		Usuario u = usuDAO.recuperar("nathan");
		assertEquals(u.getLogin(), "nathan");
		assertEquals(u.getEmail(), "nathan@email.com");
		assertEquals(u.getNome(), "nathan");
		assertEquals(u.getSenha(), "senhaNathan");
		assertEquals(u.getPontos(), 5);
	}
}
