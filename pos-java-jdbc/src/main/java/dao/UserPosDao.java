package dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.List;

import conexaojdbc.SingleConection;
import model.BeanUserFone;
import model.Telefone;
import model.Userpostgres;

public class UserPosDao {

	private Connection connection;

	public UserPosDao() {
		connection = SingleConection.getConnection();

	}

	public void salvar(Userpostgres userpostgres) {
		try {
			String sql = "insert into userpostgres (nome,email) values (?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, userpostgres.getNome());
			insert.setString(2, userpostgres.getEmail());
			insert.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();

			} catch (SQLException e1) {

				e.printStackTrace();
			}

		}

	}
	
	
	

	public Userpostgres buscar(Long id) throws Exception {

		Userpostgres retorno = new Userpostgres();

		String sql = "select * from userpostgres where id= " + id;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) { // Retorna apenas um ou nenhum

			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("Nome"));
			retorno.setEmail(resultado.getString("email"));

		}

		return retorno;
	}

	

	
	public void salvarTelefone(Telefone telefone) {
		
		try {
			String sql = "INSERT INTO telefoneuser(numero,tipo,usuariopessoa) VALUES (?,?,?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, telefone.getNumero());
			statement.setString(2, telefone.getTipo());
			statement.setLong(3,telefone.getUsuario());
			statement.execute();
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
		connection.rollback();
		}catch(SQLException e1) {
		e1.printStackTrace();
	}}
	}
	public List<Userpostgres> listar() throws Exception {
		List<Userpostgres> list = new ArrayList<Userpostgres>();

		String sql = "select * from userpostgres";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			Userpostgres userpostgres = new Userpostgres();
			userpostgres.setId(resultado.getLong("id"));
			userpostgres.setNome(resultado.getString("nome"));
			userpostgres.setEmail(resultado.getString("email"));

			list.add(userpostgres);
		}

		return list;
	}
	
	

	public List<BeanUserFone> listaUserFone(Long idUser) {

		List<BeanUserFone> beanUserFones = new ArrayList<BeanUserFone>();

		String sql = " select nome, numero, email from telefoneuser as fone ";
		sql += " inner join userpostgres as userp ";
		sql += " on fone.usuariopessoa = userp.id ";
		sql += "where userp.id = " + idUser;

		try {

			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				BeanUserFone userFone = new BeanUserFone();

				userFone.setEmail(resultSet.getString("email"));
				userFone.setNome(resultSet.getString("nome"));
				userFone.setNumero(resultSet.getString("numero"));

				beanUserFones.add(userFone);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return beanUserFones;

	}
	
	public void atualizar(Userpostgres userpostgres) {
		try {

			String sql = "update userpostgres set nome= ? where id =" + userpostgres.getId();

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userpostgres.getNome());

			statement.execute();
			connection.commit();
		} catch (Exception e) {

			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public void deletar(Long id) {
		try {
			
			
			String sql =" delete from userpostgres where id ="+id; 
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();

			}
			e.printStackTrace();

		}
	}
	
	public void deleteFonesPorUser(Long idUser) {
		try {
		String sqlFone = "delete from telefoneuser where usuariopessoa =" +idUser;
		String sqlUser="delete from userpostgres where id ="+idUser;
		
		PreparedStatement preparedStatement = connection.prepareStatement(sqlFone);
		preparedStatement.executeUpdate();
		connection.commit();
		preparedStatement =connection.prepareStatement(sqlUser);
		preparedStatement.executeUpdate();
		connection.commit();
		
	}catch (Exception e) {
		e.printStackTrace();
		try {connection.rollback();
	}catch (SQLException e1) {
		e1.printStackTrace();
	}
		
	
	
	
}}}