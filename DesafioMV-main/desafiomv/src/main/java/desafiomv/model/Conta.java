package desafiomv.model;

import java.io.Serializable;

public class Conta implements Serializable{


	private static final long serialVersionUID = 1L;
	
	
	private String numero;
	private String saldo;
	private String credito;
	private String movimentacao;
	private String cliente;
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getSaldo() {
		return saldo;
	}
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	public String getCredito() {
		return credito;
	}
	public void setCredito(String credito) {
		this.credito = credito;
	}
	public String getMovimentacao() {
		return movimentacao;
	}
	public void setMovimentacao(String movimentacao) {
		this.movimentacao = movimentacao;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

}
