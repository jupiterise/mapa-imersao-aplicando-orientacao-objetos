package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Turma {
    private String codigo;
    private Professor professor;
    private Curso curso;
    private final List<Aluno> listaAlunos = new ArrayList<>();

    public Turma(String codigo, Professor professor, Curso curso) {
        this.codigo = codigo;
        this.professor = professor;
        this.curso = curso;
    }

    public String getCodigo() { return codigo; }
    public Professor getProfessor() { return professor; }
    public Curso getCurso() { return curso; }

    public boolean adicionarAluno(Aluno a) {
        if (a == null) return false;
        if (listaAlunos.contains(a)) return false;
        listaAlunos.add(a);
        return true;
    }

    public boolean removerAluno(Aluno a) {
        return listaAlunos.remove(a);
    }

    public List<Aluno> getListaAlunos() { return Collections.unmodifiableList(listaAlunos); }

    public String resumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Resumo da Turma ---\n");
        sb.append("Código: ").append(codigo).append('\n');
        sb.append("Professor: ").append(professor != null ? professor.getNome() : "(nenhum)").append('\n');
        sb.append("Curso: ").append(curso != null ? curso.getNome() : "(nenhum)").append('\n');
        sb.append("Alunos matriculados: ").append(listaAlunos.size()).append('\n');
        if (!listaAlunos.isEmpty()) {
            for (Aluno a : listaAlunos) sb.append(" - ").append(a.getNome()).append('\n');
        }
        return sb.toString();
    }
}

