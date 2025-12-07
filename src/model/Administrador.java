package model;

public class Administrador extends Usuario implements Autenticacao {
    public Administrador(String nome, String login, String senha) {
        super(nome, login, senha);
    }

    @Override
    public boolean autenticar(String login, String senha) {
        return verificarCredenciais(login, senha);
    }

    @Override
    public String toString() {
        return String.format("Administrador{nome='%s', login='%s'}", getNome(), getLogin());
    }
}

