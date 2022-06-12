package desafiomv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import desafiomv.ConnectionFactory;
import desafiomv.model.Cliente;
import desafiomv.model.Endereco;


public class ClientepfDAO {
	
	private Connection conexao = null;
	private String createtable = "create table clientespf(id int GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1) primary key not null,nome varchar(100) not null, cpf char(11) unique not null, telefone varchar(11) not null, email varchar(100) not null, idendereco int, foreign key (idendereco) references endereco(id))";
	
	ConnectionFactory conn = new ConnectionFactory();
	EnderecoDAO enderecodao = new EnderecoDAO(conn.getConnection(), false);
	
	public ClientepfDAO(Connection conexao) throws SQLException {
		
		this.conexao = conexao;
		
		//if(!checkIfTableExists("clientespf")) {			
			PreparedStatement ps = this.conexao.prepareStatement(this.createtable);
			ps.execute();
			ps.close();
		//}
	}
	
	/*private boolean checkIfTableExists(String tableName) throws SQLException {
				
		List<String> cliente = new ArrayList<String>();
		
		PreparedStatement ps = this.conexao.prepareStatement("SELECT * FROM USER_TABLES WHERE TABLE_NAME = upper('clientespf')");
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		if(rs.getString("TABLE_NAME").toString().isEmpty() || rs.getString("TABLE_NAME").toString() == null) {
			return false;
		}
		
		ps.close();
		
		return true;
	}*/
	
	public List<String> findByCpf(String cpf) {
		
		try {
			
			List<String> cliente = new ArrayList<String>();
			
			PreparedStatement ps = this.conexao.prepareStatement("select * from clientespf where cpf = ?");
			
			ps.setString(1, cpf);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				cliente.add(rs.getString("nome").toString());
				cliente.add(rs.getString("cpf").toString());
				cliente.add(rs.getString("telefone").toString());
				cliente.add(rs.getString("email").toString());
				
			}
			
			ps.close();
			
			return cliente;
			
		} catch (Exception e) {
			System.out.println("Erro: "+e.getMessage());
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public void cadastrar(Cliente cliente) {
		try {
			
			PreparedStatement ps = null;
			
			if(cliente.getEndereco() != null) {
				ps = this.conexao.prepareStatement("insert into clientespf(nome, cpf, email, telefone, idendereco) values(?,?,?,?,?)");
				ps.setString(5, cliente.getEndereco().getId());
			}else {
				ps = this.conexao.prepareStatement("insert into clientespf(nome, cpf, email, telefone) values(?,?,?,?)");				
			}
					
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getEmail());
			ps.setString(4, cliente.getTelefone());
			
			ps.execute();
			ps.close();
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<String> verdados() {
		
		try {
			
			List<String> clientes = new ArrayList<String>();
			
			PreparedStatement ps = this.conexao.prepareStatement("select * from clientespf");
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				clientes.add(rs.getString("nome").toString());
				clientes.add(rs.getString("cpf").toString());
				clientes.add(rs.getString("telefone").toString());
				clientes.add(rs.getString("email").toString());
				
			}
			
			ps.close();
			
			return clientes;
			
		} catch (Exception e) {
			System.out.println("Erro: "+e.getMessage());
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public void atualizar(String email, String telefone, String cpf) {
		
		try {
			
			PreparedStatement ps = this.conexao.prepareStatement("update clientespf set email = ?, telefone = ? where cpf = ?");
			
			if (email.length()>0 && telefone.length()>0) {
				ps.setString(1, email);
				ps.setString(2, telefone);
				ps.setString(3, cpf);
				ps.execute();
			}
			
			if (telefone.length()>0 && email.length()<1) {
				ps = this.conexao.prepareStatement("update clientespf set telefone = ? where cpf = ?");
				ps.setString(1, telefone);
				ps.setString(2, cpf);
				ps.execute();
			}
			
			if (email.length()>0 && telefone.length()<1) {
				ps = this.conexao.prepareStatement("update clientespf set email = ? where cpf = ?");
				ps.setString(1, email);
				ps.setString(2, cpf);
				ps.execute();
			}
			
			if (email.length()<1 && telefone.length()<1) {
				ps = null;
			}
			
			ps.close();
			
		} catch (Exception e) {
			
			System.out.println("Erro: "+e.getMessage());
			
		}
		
	}
	
	public void deletar(String cpf) {
		
		try {
			
			PreparedStatement ps = this.conexao.prepareStatement("delete from clientespf where cpf=?");
			
			ps.setString(1, cpf);
			
			ps.execute();
			
			ps.close();
			
		} catch (Exception e) {

			System.out.println("Erro: "+e.getMessage());
			
		}
		
	}

}
