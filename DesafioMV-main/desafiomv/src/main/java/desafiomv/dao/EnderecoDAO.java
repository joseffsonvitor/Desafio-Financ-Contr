package desafiomv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import desafiomv.model.Endereco;

public class EnderecoDAO {
	
	private Connection conexao = null;
	
	private String createtable = "create table endereco(id int GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1) primary key not null,logradouro varchar(100) not null, numero varchar(10)  not null, cidade varchar(30) not null, bairro varchar(30) not null, cep varchar(20) not null)";
	
	public EnderecoDAO(Connection conexao, boolean needsToCreate) throws SQLException {
		this.conexao = conexao;
		
		if(needsToCreate) {
			criarTabela();
		}
	}
	
	private void criarTabela() throws SQLException {
		PreparedStatement ps = this.conexao.prepareStatement(this.createtable);
		ps.execute();
		ps.close();
	}
	
	public void cadastrar(Endereco endereco) {
		try {
			
			PreparedStatement ps = null;
			
			ps = this.conexao.prepareStatement("insert into endereco(logradouro, numero, cidade, bairro, cep) values(?,?,?,?,?)");
			
			ps.setString(1, endereco.getLogradouro());
			ps.setString(2, endereco.getNumero());
			ps.setString(3, endereco.getCidade());
			ps.setString(4, endereco.getBairro());
			ps.setString(5, endereco.getCep());
			
			ps.execute();
			ps.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void atualizarEndereco() {
		
	}
	
	public void deletarEndereco() {
		
	}




}
