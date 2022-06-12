package desafiomv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import desafiomv.model.Cliente;


public class ClientepjDAO {
	
	private Connection conexao = null;
	private String createtable = "create table clientespj(id int GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1) primary key not null, nome varchar(100) not null, cnpj char(14) unique not null, telefone varchar(11) not null, email varchar(100) not null, idendereco int, foreign key (idendereco) references endereco(id))";
	
	public ClientepjDAO(Connection conexao) throws SQLException {

		this.conexao = conexao;
		
		//if(!checkIfTableExists("clientespj")) {
			PreparedStatement ps = this.conexao.prepareStatement(this.createtable);
			ps.execute();
			ps.close();
		//}
	}
	
	/*private boolean checkIfTableExists(String tableName) throws SQLException {
		
		List<String> cliente = new ArrayList<String>();
		
		PreparedStatement ps = this.conexao.prepareStatement("SELECT * FROM USER_TABLES WHERE TABLE_NAME = upper('?')");
		
		ps.setString(1, tableName);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.getString("TABLE_NAME").toString().isEmpty() || rs.getString("TABLE_NAME").toString() == null) {
			return false;
		}
		
		ps.close();
		
		return true;
	}*/
	
	public List<String> findByCnpj(String cnpj) {
		
		try {
			
			List<String> cliente = new ArrayList<String>();
			
			PreparedStatement ps = this.conexao.prepareStatement("select * from clientespj where cnpj=?");
			
			ps.setString(1, cnpj);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				cliente.add(rs.getString("nome").toString());
				cliente.add(rs.getString("cnpj").toString());
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
			
			PreparedStatement ps = this.conexao.prepareStatement("insert into clientespj(nome, cnpj, email, telefone, idendereco) values(?,?,?,?,?)");
			
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCnpj());
			ps.setString(3, cliente.getEmail());
			ps.setString(4, cliente.getTelefone());
			ps.setString(5, cliente.getEndereco().getId());
			
			ps.execute();
			ps.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<String> verdados() {
		
		try {
			
			List<String> clientes = new ArrayList<String>();
			
			PreparedStatement ps = this.conexao.prepareStatement("select * from clientespj");
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				clientes.add(rs.getString("nome").toString());
				clientes.add(rs.getString("cnpj").toString());
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
	
	public void deletar(String cnpj) {
		
		try {
			
			PreparedStatement ps = this.conexao.prepareStatement("delete from clientespj where cnpj=?");
			
			ps.setString(1, cnpj);
			
			ps.execute();
			
			ps.close();
			
		} catch (Exception e) {
			
			System.out.println("Erro: "+e.getMessage());
			
		}
		
	}

}
