package model;

public abstract class Usuario {
    private String nome;
    private String login;
    private String senha;

    public Usuario(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public String getNome() { return nome; }
    public String getLogin() { return login; }

    protected boolean verificarCredenciais(String login, String senha) {
        if (login == null || senha == null) return false;
        return this.login.equals(login) && this.senha.equals(senha);
    }

    @Override
    public String toString() {
        return String.format("Usuario{nome='%s', login='%s'}", nome, login);
    }
}

