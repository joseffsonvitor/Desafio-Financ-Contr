# DesafioMV

DESAFIO: CÉLULA FINANCEIRO E CONTROLADORIA


Conexão com o banco de dados: Oracle SQL Developer.

USERNAME = "system";
PASSWORD = "12345678";
DATABASE_URL = "jdbc:oracle:thin:@//localhost:1521/xe";


Ao ser executado o Main, ele gera automaticamente 5 tabelas: 
1. contaspf
2. contaspj
3. clientespj
4. clientespf
5. endereco

Caso precise rodar novamente é necessário realizar o Drop table na ordem acima no banco de dados. Pois faltou implementar o 'if not exists' na criação das tabelas, devido que o BD Oracle, não suporta essa função.

Obs: O projeto também está inclompleto, não consegui realizar tudo que foi pedido.










