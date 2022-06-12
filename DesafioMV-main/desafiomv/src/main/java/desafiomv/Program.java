package desafiomv;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import desafiomv.dao.ClientepfDAO;
import desafiomv.dao.ClientepjDAO;
import desafiomv.dao.ContaDAO;
import desafiomv.dao.EnderecoDAO;
import desafiomv.model.Cliente;
import desafiomv.model.Conta;
import desafiomv.model.Endereco;
import desafiomv.model.ImprimeRelatorio;
import net.sf.jasperreports.engine.JRException;

public class Program {

	public static void main(String[] args) throws SQLException, InterruptedException, JRException {
		
		Scanner input = new Scanner(System.in);
		
		Random random = new Random();
		
		System.out.println("Conectando...");
		
		ConnectionFactory conn = new ConnectionFactory();
		
		Endereco endereco = new Endereco();
		
		EnderecoDAO enderecodao = new EnderecoDAO(conn.getConnection(), true);
		
		ClientepfDAO clientepfdao = new ClientepfDAO(conn.getConnection());
		
		ClientepjDAO clientepjdao = new ClientepjDAO(conn.getConnection());
		
		Cliente cliente = new Cliente();
		
		ContaDAO contadao = new ContaDAO(conn.getConnection());
		
		Conta conta = new Conta();
		
		
		//INÍCIO DO PROGRAMA
		boolean loopcond = true;
		while (loopcond) {
			
			int opcmenu = 0;
			
			try {
				
				System.out.println("Olá bem vindo(a) ao sistema financeiro/controladaria. O que deseja?\n1-Menu de clientes\n2-Menu de endereços\n3-Menu de contas\n4-Sair");
				opcmenu = input.nextInt();
				
			} catch (Exception e) {
				
				System.out.println("Erro: "+e.getMessage()+". Programa encerrado subitamente.");
				break;
				
			}
			
			switch (opcmenu) {
			//CRUD DE CLIENTES
			case 1:
				
				boolean loopcli = true;
				
				while (loopcli) {
					
					System.out.println("Este é o menu de clientes, ações disponíveis:\n1-Cadastrar novo cliente\n2-Ver lista de clientes\n3-Achar cliente específico\n4-Atualizar dados de cliente\n5-deletar cliente\n6-Gerar relatório de clientes\n7-Voltar ao início");
					int opccli = input.nextInt();
					
					//SE É PF OU PJ
					if (opccli==1) {
						
						boolean condpfpj = true;
						int pfpj = 0;
						
						while (condpfpj) {
							
							System.out.println("Escolha o tipo de identificação:\n1=PF\n2=PJ");
							
							try {
								pfpj = input.nextInt();
								
								if (pfpj==1) {
									
									cliente.setCnpj(null);
									condpfpj = false;
									
								}
								else if(pfpj==2) {
									
									cliente.setCpf(null);
									condpfpj = false;
									
								}
								else {
									
									System.out.println("Opção inválida");
									
								}
							} catch (Exception e) {
								
								System.out.println("Erro: "+e.getMessage());
								
							}
						}
						
						//CADASTRO DE CLIENTES
						System.out.println("Digite o nome:");
						cliente.setNome(input.next());
						
						if (pfpj==1) {
							
							int opcaoEndereco;
							
							System.out.println("Digite o cpf:");
							cliente.setCpf(input.next());
							
							System.out.println("Digite o email:");
							cliente.setEmail(input.next());
							
							System.out.println("Digite o telefone:");
							cliente.setTelefone(input.next());
							
							System.out.println("Deseja inserir um endereco? (1 p/ sim, 2 p/ não)");
							opcaoEndereco = input.nextInt();
							
							//cadastro de endereco
							if(opcaoEndereco == 1) {
								Endereco enderecoNovoCliente = new Endereco();
															
								System.out.println("Digite o logradouro: ");
								enderecoNovoCliente.setLogradouro(input.next());
								
								System.out.println("Digite o número: ");
								enderecoNovoCliente.setNumero(input.next());
								
								System.out.println("Digite a cidade: ");
								enderecoNovoCliente.setCidade(input.next());
								
								System.out.println("Digite o bairro: ");
								enderecoNovoCliente.setBairro(input.next());
								
								System.out.println("Digite o cep: ");
								enderecoNovoCliente.setCep(input.next());
								
								enderecodao.cadastrar(enderecoNovoCliente);
								cliente.setEndereco(enderecoNovoCliente);	
							}
							
							clientepfdao.cadastrar(cliente);
						}
						else if (pfpj==2) {
							
							String opcaoEndereco = new String();
							
							System.out.println("Digite o cnpj");
							cliente.setCnpj(input.next());
							
							System.out.println("Digite o email:");
							cliente.setEmail(input.next());
							
							System.out.println("Digite o telefone:");
							cliente.setTelefone(input.next());
							
							System.out.println("Deseja inserir um endereco? (1 p/ sim, 2 p/ não)");
							opcaoEndereco = input.next();
							
							//cadastro de endereco
							if(opcaoEndereco == "1") {
								Endereco enderecoNovoCliente = new Endereco();
															
								System.out.println("Digite o logradouro: ");
								enderecoNovoCliente.setLogradouro(input.next());
								
								System.out.println("Digite o número: ");
								enderecoNovoCliente.setNumero(input.next());
								
								System.out.println("Digite a cidade: ");
								enderecoNovoCliente.setCidade(input.next());
								
								System.out.println("Digite o bairro: ");
								enderecoNovoCliente.setBairro(input.next());
								
								System.out.println("Digite o cep: ");
								enderecoNovoCliente.setCep(input.next());
								
								enderecodao.cadastrar(enderecoNovoCliente);
								cliente.setEndereco(enderecoNovoCliente);
							}
							
							clientepjdao.cadastrar(cliente);
						}
					}
					
					//FAZ UMA LISTA DOS CLIENTES CADASTRADOS
					else if(opccli==2) {
						
						System.out.println("Lista de clientes:\n");
						int cont = 0;
						int indice = 1;
						
						for (String dado : clientepfdao.verdados()) {
							
							if (cont%4 == 0) {
								
								System.out.print("----------------------------------------------------------------------------------------------------\n"+indice+"-");
								indice++;
								
							}
							
							System.out.print(dado+" | ");
							cont++;
							
							if (cont%4 == 0) {
								
								System.out.println("\n----------------------------------------------------------------------------------------------------\n");
							
							}
							
						}
						
						for (String dado : clientepjdao.verdados()) {
							
							if (cont%4 == 0) {
								
								System.out.print("----------------------------------------------------------------------------------------------------\n"+indice+"-");
								indice++;
								
							}
							
							System.out.print(dado+" | ");
							cont++;
							
							if (cont%4 == 0) {
								
								System.out.println("\n----------------------------------------------------------------------------------------------------\n");
							
							}
							
						}
						
					}
					
					//PROCURA DE CLIENTE ESPECÍFICO
					else if(opccli==3) {
						
						System.out.println("Procurar por:\n1-cpf ou 2-cnpj");
						int tipoidentificacao = input.nextInt();
						
						if (tipoidentificacao==1) {
							
							System.out.println("digite o cpf:");
							
							String cpf = input.next();
													
							if (!clientepfdao.findByCpf(cpf).isEmpty()) {
								System.out.print("-----------------------------------------------------------------------------------------------------------\n  | ");
								for (String dado : clientepfdao.findByCpf(cpf)) {
									
									System.out.print(dado+" | ");
									
								}
								
								System.out.println("\n-----------------------------------------------------------------------------------------------------------");
								
							}
							else {
								System.out.println("Usuário não encontrado!");
							}
							
						}
						
						else if (tipoidentificacao==2) {
							
							System.out.println("Digite o cnpj:");
							
							String cnpj = input.next();
							
							if (clientepjdao.findByCnpj(cnpj).size()>0) {

								System.out.print("-----------------------------------------------------------------------------------------------------------\n  | ");
								for (String dado : clientepjdao.findByCnpj(cnpj)) {
									
									System.out.print(dado+" | ");
									
								}
								
								System.out.println("\n-----------------------------------------------------------------------------------------------------------");
								
							}
							else {
								System.out.println("Usuário não encontrado!");
							}
						}
						
					}
					
					//ALTERAR DADOS DE CLIENTE
					else if(opccli==4) {
						
						System.out.println("Digite o cpf ou cpnj do cliente a ser alterado:");
						String identificacao = input.next();
						
						if(identificacao.length() == 11) {
							
							if (clientepfdao.findByCpf(identificacao).size()>0) {
								
								System.out.println("Mudar email?\n1-SIM\n2-NÃO");
								
								int sn = input.nextInt();
								
								if(sn==1) {
									System.out.println("Novo email:");
									String email = input.next();
									clientepfdao.atualizar(email, "", identificacao);
								}else if(sn==2) {
									System.out.println(">>>");
								}
										
								System.out.println("Deseja mudar telefone?\n1-SIM\n2-NÃO");
								
								sn = input.nextInt();
								
								if(sn==1) {
									System.out.println("Novo telefone:");
									String telefone = input.next();
									clientepfdao.atualizar("", telefone, identificacao);
								}else if(sn==2) {
									System.out.println(">>>");
								}
								
							}
							
							else if(clientepfdao.findByCpf(identificacao).size()<1) {
								System.out.println("Cliente não encontrado!");
							}
							
						}
						
						else if(identificacao.length() == 14) {
							
							if (clientepjdao.findByCnpj(identificacao).size()>0) {
								
								System.out.println("Mudar email?\n1-SIM\n2-NÃO");
								
								int sn = input.nextInt();
								
								if(sn==1) {
									System.out.println("Novo email:");
									String email = input.next();
									clientepfdao.atualizar(email, "", identificacao);
								}else if(sn==2) {
									System.out.println(">>>");
								}
										
								System.out.println("Deseja mudar telefone?\n1-SIM\n2-NÃO");
								
								sn = input.nextInt();
								
								if(sn==1) {
									System.out.println("Novo telefone:");
									String telefone = input.next();
									clientepfdao.atualizar("", telefone, identificacao);
								}else if(sn==2) {
									System.out.println(">>>");
								}
								
							}
							
							else if(clientepjdao.findByCnpj(identificacao).size()<1) {
								System.out.println("Cliente não encontrado");
							}
							
						}
						else if(identificacao.length() != 11 || identificacao.length() != 14) {
							System.out.println("Formato incorreto!");
						}
						
					}
					
					//DELETAR CLIENTE
					else if(opccli==5) {
						
						System.out.println("digite o cpf ou cnpj do cliente a ser deletado:");
						
						String pfpj = input.next();
						
						if (pfpj.length()==11) {
							clientepfdao.deletar(pfpj);
							if(clientepfdao.findByCpf(pfpj).size()<1) {
								System.out.println("Usuário não encontrado!");
							}
						}
						else if(pfpj.length()==14) {
							clientepjdao.deletar(pfpj);
							if(clientepjdao.findByCnpj(pfpj).size()<1) {
								System.out.println("Usuário não encontrado!");
							}
						}
						else if(pfpj.length() != 11 || pfpj.length() != 14) {
							System.out.println("Formato incorreto!");
						}
						
					}
					
					else if(opccli==6) {
						String x = "Cliente: " + cliente.getNome() + 
								   "\nEndereço: " + endereco.getLogradouro() + ", " + endereco.getNumero() + ", " + endereco.getBairro() + ", " + endereco.getCidade() + ", " + endereco.getCep() +
								   "\nMovimentações: " + conta.getMovimentacao() + ", Número Conta: " + conta.getNumero() + 
								   "\nSaldo: " + conta.getSaldo() + ", Crédito: " + conta.getCredito();
						
						ImprimeRelatorio imprimeRelatorio = new ImprimeRelatorio();
						imprimeRelatorio.imprimirRelatorio(x);
					}
					
					//RETORNO AO MENU ANTERIOR
					else if(opccli==7) {
						
						System.out.println("Voltando...");
						loopcli = false;
						
					}
				}
				
				break;
				
			
				
			//CRUD DE ENDEREÇOS
			case 2:
				//System.out.println("Menu incompleto");
				//System.out.println("Este é o menu de endereços, ações disponíveis:\n1-Associar um endereço à cliente\n2-ver lista de endereços\n3-atualizar endereço\n4-deletar endereço");
				
			boolean loopend = true;
			
			while (loopend) {
					
				System.out.println("Este é o menu de endereços, ações disponíveis:\n1-Associar um endereço à cliente\n2-ver lista de endereços\n3-atualizar endereço\n4-deletar endereço");
				int opcend = input.nextInt();
				
				//CADASTRAR ENDEREÇO
				
				System.out.println("Digite o logradouro: ");
				endereco.setLogradouro(input.next());
				
				System.out.println("Digite o número: ");
				endereco.setNumero(input.next());
				
				System.out.println("Digite a cidade: ");
				endereco.setCidade(input.next());
				
				System.out.println("Digite o bairro: ");
				endereco.setBairro(input.next());
				
				System.out.println("Digite o cep: ");
				endereco.setCep(input.next());
				
				if(opcend == 1) {
					enderecodao.cadastrar(endereco);
				}
				/*else if(opcend == 2) {
					enderecodao.listarEnderecos();
				}else if(opcend == 3) {
					enderecodao.atualizarEndereco();
				}else if(opcend == 4) {
					enderecodao.deletarEndereco();
				}*/
				
				loopend = false;
			}
			
			break;
				
			//CRUD DE CONTAS (INCOMPLETO FALTA O DEBITO)
			case 3:
				
				boolean condconta = true;
				
				while(condconta) {
					
					System.out.println("Este é o menu de contas, ações disponíveis:\n1-Associar nova conta à cliente\n2-ver lista de contas\n3-atualizar informações da conta\n4-excluir conta\n5-voltar ao menu inicial");
					
					int opcconta = input.nextInt();
					
					if (opcconta==1) {
						
						System.out.print("O número da conta será:");
						int num = random.nextInt(10000000+99999999)+10000000;
						System.out.println(num);
						conta.setNumero(""+num);
						System.out.println("Digite o cpf ou cnpj do(a) cliente a ser asoociado:");
						String cpfcnpj = input.next();
						if (cpfcnpj.length()==11) {
							if (clientepfdao.findByCpf(cpfcnpj).size()>0) {
								conta.setCliente(cpfcnpj);
							}
							else if (clientepfdao.findByCpf(cpfcnpj).size()<1) {
								System.out.println("Cliente não encontrado");
							}
						}
						else if (cpfcnpj.length()==14) {
							if (clientepjdao.findByCnpj(cpfcnpj).size()>0) {
								conta.setCliente(cpfcnpj);
							}
							else if (clientepjdao.findByCnpj(cpfcnpj).size()<1) {
								System.out.println("Cliente não encontrado");
							}
						}
						System.out.println("Para conclusão é necessário fazer um movimento inicial. Onde deverá ser o movimento inicial?\n1-Débito(incompleto)\n2-Crédito");
						int opcmov = input.nextInt();
						if (opcmov==1) {
							System.out.println("Digite o saldo/Debito inicial");
							conta.setSaldo(input.next());
							conta.setCredito("0");
						}
						else if(opcmov==2) {
							System.out.println("Digite o saldo/crédito inicial");
							conta.setCredito(input.next());
							conta.setSaldo(conta.getCredito());
						}
						else {
							System.out.println("Opcão inválida!");
						}
						
						conta.setMovimentacao("1");
						
						contadao.adicionarconta(conta,cpfcnpj);
						
					}
					else if(opcconta==2) {
						
						System.out.println("Lista de contas:\n<<<-------------------------------------------------------------->>>");
						System.out.println(" | número | | saldo | | crédito | | movimentacões | | cliente |");
						int count = 0;
						
						for (String dado : contadao.findAll()) {
							
							if (count%5==0) {
								System.out.println();
							}
							
							System.out.print(" | "+dado+" | ");
							count++;
							
							if (count%5==0) {
								System.out.println();
							}
							
						}
						System.out.println("\n<<<-------------------------------------------------------------->>>");
						
					}
					else if(opcconta==5) {
						condconta = false;
					}
				}
				
				break;
				
			//FINALIZAÇÂO DO PROGRAMA
			case 4:
				
				System.out.println("Saindo...");
				Thread.currentThread();
				Thread.sleep(1000);
				System.out.println("Até a próxima!");
				loopcond = false;
				break;
				
			default:
				
				System.out.println("Opção inválida");
				
				break;
				
			}
		}
		input.close();
	}

}
