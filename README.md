# ExercicioSemana3
Exercício prático da semana 3 do curso Desenvolvimento Ágil com Java Avançado, curso oferecido pelo ITA na plataforma Coursera.

## Enunciado

O objetivo dessa aula é exercitar a criação de classes que utilizam JDBC para acessar o banco de dados.

Primeiramente deve ser criado o banco de dados chamado "coursera" e criada a tabela usuario com o script abaixo:

```sql
CREATE TABLE usuario
(
  login text NOT NULL,
  email text,
  nome text,
  senha text,
  pontos integer,
  CONSTRAINT usuario_pkey PRIMARY KEY (login)
)
```

Deve ser criada uma classe Usuario com as informações presentes na tabela e uma interface com os segunites métodos:

```java
public interface UsuarioDAO {
   
   //insere um novo usuário no banco de dados
   public void inserir(Usuario u);
   
   //recupera o usuário pelo seu login
   public Usuario recuperar(String login);
   
   //adiciona os pontos para o usuário no banco
   public void adicionarPontos(String login, int pontos);
   
   //retorna a lista de usuários ordenada por pontos (maior primeiro)
   public List<Usuario> ranking();

}
```

Como o foco do exercício não é a criação de consultas, segue as consultas correspondentes a cada um dos métodos:

```sql
INSERT INTO usuario(login, email, nome, senha, pontos) VALUES (?, ?, ?, ?, ?);

SELECT * FROM usuario WHERE login = ?;

UPDATE usuario SET pontos = pontos + ? WHERE login = ?;

SELECT * FROM usuario ORDER BY pontos DESC;
```

Como exercício, o aluno deverá implementar uma classe que implementa a interface apresentada fazendo acesso ao banco de dados utilizando JDBC. Outros frameworks não devem ser utilizados.

Cada um dos métodos acima deve ser testado utilizando o DBUnit.
