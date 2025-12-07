package model;

public class Professor extends Usuario implements Autenticacao, Relatorio {
    private String especialidade;
    private String registro;

    public Professor(String nome, String especialidade, String registro, String login, String senha) {
        super(nome, login, senha);
        this.especialidade = especialidade;
        this.registro = registro;
    }

    public String getEspecialidade() { return especialidade; }
    public String getRegistro() { return registro; }

    public void validar() {
        if (getNome() == null || getNome().trim().isEmpty()) throw new IllegalArgumentException("Nome do professor inválido");
        if (especialidade == null || especialidade.trim().isEmpty()) throw new IllegalArgumentException("Especialidade do professor inválida");
        if (registro == null || registro.trim().isEmpty()) throw new IllegalArgumentException("Registro do professor inválido");
    }

    @Override
    public boolean autenticar(String login, String senha) {
        return verificarCredenciais(login, senha);
    }

    @Override
    public String gerarRelatorio() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Relatório do Professor ---\n");
        sb.append("Nome: ").append(getNome()).append('\n');
        sb.append("Especialidade: ").append(especialidade).append('\n');
        sb.append("Registro: ").append(registro).append('\n');
        return sb.toString();
    }
}

