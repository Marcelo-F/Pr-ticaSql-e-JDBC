package pos_java_jdbc.pos_java_jdbc;

import java.awt.List;
import java.sql.PreparedStatement;
import java.awt.List; 
import org.junit.Test;
import dao.UserPosDao;
import model.BeanUserFone;
import model.Telefone;
import model.Userpostgres;

public class TesteBancoJdbc {

	@Test
	public void initBanco() { /*Inserir na tabela*/
		UserPosDao userPosDao = new UserPosDao();
		Userpostgres userpostgres = new Userpostgres();

		userpostgres.setNome("José");
		userpostgres.setEmail("jose.alfred@hotmail.com");

		userPosDao.salvar(userpostgres);

	}

	
	  @Test 
	  public void initListar() { UserPosDao dao = new UserPosDao(); try {
	 /*Mostrar os itens da lista*/
		  java.util.List<Userpostgres>list = dao.listar();
	  
	  for (Userpostgres userpostgres : list) { System.out.println(userpostgres);
	  System.out.println("----------------------------------------"); } } catch
	  (Exception e) { e.printStackTrace(); }
	 
	  }
	 

	@Test
	public void initBuscar() { /*Busca um item no banco de dados*/
		UserPosDao dao = new UserPosDao();
		try {
			Userpostgres userpostgres = dao.buscar(1l);

			System.out.println(userpostgres);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void initAtualizar() { /*Atualizar os dados*/
		try {

			UserPosDao dao = new UserPosDao();
			Userpostgres objetoBanco = dao.buscar(5l);

			objetoBanco.setNome("Nome mudado com o método atualizar");
			dao.atualizar(objetoBanco);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	@Test
	public void iniDeletar() { /*Deletar um item do banco de dados*/

		try {

			UserPosDao dao = new UserPosDao();
			dao.deletar(5l);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	@Test
	public void testeInsertTelefone() { /*Insere o telefone*/

		Telefone telefone = new Telefone();
		telefone.setNumero("(88)99999-999");
		telefone.setTipo("Casa");
		telefone.setUsuario(2L);

		UserPosDao dao = new UserPosDao();
		dao.salvarTelefone(telefone);

	}

	@Test
	public void testeCarregaFonesUser() {  

		UserPosDao dao = new UserPosDao();

		java.util.List<BeanUserFone> beanUserFones = dao.listaUserFone(1L);

		for (BeanUserFone beanUserFone : beanUserFones) {
			System.out.println(beanUserFone);
			System.out.println("---------------------------------------------");
		}

	}
	@Test
	public void testeDeleteUserFone() {/*Deleta o telefone*/
		
		UserPosDao dao = new UserPosDao();
		dao.deleteFonesPorUser(6l);
		
		
	}

}
