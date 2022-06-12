package desafiomv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import desafiomv.model.Conta;

public class ContaDAO {
	
	private Connection conexao = null;
	
	private String createtablepf = "create table contaspf( id int GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1) not null primary key , numero int not null unique , saldo float not null, credito float not null, movimentacao int not null, clientepf char(11) not null unique , foreign key (clientepf) references clientespf(cpf))";
	
	private String createtablepj = "create table contaspj( id int GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1) not null primary key, numero int not null unique, saldo float not null, credito float not null, movimentacao int not null, clientepj char(14) not null unique, foreign key (clientepj) references clientespj(cnpj))";
	
	public ContaDAO(Connection conexao) throws SQLException {
		
		this.conexao = conexao;
		
		//if(!checkIfTableExists("contaspf") && !checkIfTableExists("contaspj")) {			
			PreparedStatement ps = this.conexao.prepareStatement(this.createtablepf);
			ps.execute();
			ps = this.conexao.prepareStatement(this.createtablepj);
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
	
	public void adicionarconta(Conta conta, String cpfcnpj) {
		
		try {
			
			PreparedStatement ps = null;
			
			if (cpfcnpj.length()==11) {
				
				ps = this.conexao.prepareStatement("insert into contaspf(numero, saldo, credito, movimentacao, clientepf) values(?,?,?,?,?)");
				
				ps.setString(1, conta.getNumero());
				ps.setString(2, conta.getSaldo());
				ps.setString(3, conta.getCredito());
				ps.setString(4, conta.getMovimentacao());
				ps.setString(5, conta.getCliente());
				ps.execute();
				ps.close();
				
			}
			
			else if(cpfcnpj.length()==14) {
				
				ps = this.conexao.prepareStatement("insert into contaspj(numero, saldo, credito, movimentacao, clientepj) values(?,?,?,?,?)");
				
				ps.setString(1, conta.getNumero());
				ps.setString(2, conta.getSaldo());
				ps.setString(3, conta.getCredito());
				ps.setString(4, conta.getMovimentacao());
				ps.setString(5, conta.getCliente());
				ps.execute();
				ps.close();
				
			}
			
		} catch (Exception e) {
			
			System.out.println("Erro: "+e.getMessage());
			
		}
		
	}

	public List<String> findAll() {
		
		try {
			
			List<String> cliente = new ArrayList<String>();
			
			PreparedStatement ps = this.conexao.prepareStatement("select * from contaspf");
				
			ResultSet rs = ps.executeQuery();
				
			while (rs.next()) {
					
				cliente.add(rs.getString("numero").toString());
				cliente.add(rs.getString("saldo").toString());
				cliente.add(rs.getString("credito").toString());
				cliente.add(rs.getString("movimentacao").toString());
				cliente.add(rs.getString("clientepf").toString());
					
			}
			
			ps = this.conexao.prepareStatement("select * from contaspj");
			
			rs = ps.executeQuery();
				
			while (rs.next()) {
					
				cliente.add(rs.getString("numero").toString());
				cliente.add(rs.getString("saldo").toString());
				cliente.add(rs.getString("credito").toString());
				cliente.add(rs.getString("movimentacao").toString());
				cliente.add(rs.getString("clientepj").toString());
					
			}
			
			return cliente;
			
		} catch (Exception e) {
			
			System.out.println("Erro: "+e.getMessage());
			e.printStackTrace();
			throw new RuntimeException();
			
		}
	}
	
}
