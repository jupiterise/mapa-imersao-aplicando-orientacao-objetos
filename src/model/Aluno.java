package model;

import java.util.ArrayList;
import java.util.List;

public class Aluno extends Usuario implements Autenticacao, Relatorio {
    private String matricula;
    private Curso curso;
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    public Aluno(String nome, String matricula, Curso curso, String login, String senha) {
        super(nome, login, senha);
        this.matricula = matricula;
        this.curso = curso;
    }

    public String getMatricula() { return matricula; }
    public Curso getCurso() { return curso; }

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        if (avaliacao == null) throw new IllegalArgumentException("Avaliação nula");
        avaliacoes.add(avaliacao);
    }

    public List<Avaliacao> getAvaliacoes() { return new ArrayList<>(avaliacoes); }

    public void validar() {
        if (getNome() == null || getNome().trim().isEmpty()) throw new IllegalArgumentException("Nome do aluno inválido");
        if (matricula == null || matricula.trim().isEmpty()) throw new IllegalArgumentException("Matrícula do aluno inválida");
        if (curso == null) throw new IllegalArgumentException("Aluno deve estar matriculado em um curso");
    }

    @Override
    public boolean autenticar(String login, String senha) {
        return verificarCredenciais(login, senha);
    }

    @Override
    public String gerarRelatorio() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Relatório do Aluno ---\n");
        sb.append("Nome: ").append(getNome()).append('\n');
        sb.append("Matrícula: ").append(matricula).append('\n');
        sb.append("Curso: ").append(curso != null ? curso.getNome() : "(nenhum)").append('\n');
        sb.append("Avaliações:\n");
        if (avaliacoes.isEmpty()) sb.append(" (nenhuma)\n");
        else {
            for (Avaliacao av : avaliacoes) {
                sb.append(String.format(" - %s: %.2f\n", av.getDescricao(), av.getNota()));
            }
        }
        return sb.toString();
    }
}

