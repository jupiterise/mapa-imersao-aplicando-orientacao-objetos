package service;

import model.Aluno;
import model.Professor;
import model.Curso;

public class ReportService {
    public String relatorioAluno(Aluno a) { return a.gerarRelatorio(); }
    public String relatorioProfessor(Professor p) { return p.gerarRelatorio(); }
    public String relatorioCurso(Curso c) { return c.gerarRelatorio(); }
}

