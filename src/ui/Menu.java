package ui;

import model.*;
import repository.InMemoryRepository;
import service.ReportService;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private final InMemoryRepository<Aluno> repoAlunos;
    private final InMemoryRepository<Professor> repoProfessores;
    private final InMemoryRepository<Curso> repoCursos;
    private final InMemoryRepository<Turma> repoTurmas;
    private final ReportService reportService;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(InMemoryRepository<Aluno> a, InMemoryRepository<Professor> p, InMemoryRepository<Curso> c, InMemoryRepository<Turma> t, ReportService r) {
        this.repoAlunos = a;
        this.repoProfessores = p;
        this.repoCursos = c;
        this.repoTurmas = t;
        this.reportService = r;
    }

    public void executar() {
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("1) Cadastrar Curso");
            System.out.println("2) Cadastrar Professor");
            System.out.println("3) Cadastrar Aluno");
            System.out.println("4) Criar Turma");
            System.out.println("5) Matricular Aluno em Turma");
            System.out.println("6) Registrar Avaliação");
            System.out.println("7) Gerar Relatórios");
            System.out.println("8) Cenários de Teste");
            System.out.println("0) Sair");
            System.out.print("Escolha: ");
            String o = scanner.nextLine().trim();
            switch (o) {
                case "1": cadastrarCurso(); break;
                case "2": cadastrarProfessor(); break;
                case "3": cadastrarAluno(); break;
                case "4": criarTurma(); break;
                case "5": matricularAluno(); break;
                case "6": registrarAvaliacao(); break;
                case "7": gerarRelatorios(); break;
                case "8": cenariosTeste(); break;
                case "0": running = false; break;
                default: System.out.println("Opção inválida");
            }
        }
        System.out.println("Encerrando menu.");
    }

    private void cadastrarCurso() {
        System.out.print("Nome do curso: ");
        String nome = scanner.nextLine();
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Carga horária: ");
        int carga = parseInt(scanner.nextLine(), 0);
        Curso c = new Curso(nome, codigo, carga);
        repoCursos.save(c);
        System.out.println("Curso cadastrado: " + c.getNome());
    }

    private void cadastrarProfessor() {
        System.out.print("Nome do professor: ");
        String nome = scanner.nextLine();
        System.out.print("Especialidade: ");
        String esp = scanner.nextLine();
        System.out.print("Registro: ");
        String reg = scanner.nextLine();
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        Professor p = new Professor(nome, esp, reg, login, senha);
        repoProfessores.save(p);
        System.out.println("Professor cadastrado: " + nome);
    }

    private void cadastrarAluno() {
        System.out.print("Nome do aluno: ");
        String nome = scanner.nextLine();
        System.out.print("Matrícula: ");
        String mat = scanner.nextLine();
        System.out.println("Escolha curso (indice):");
        listarCursos();
        int idx = parseInt(scanner.nextLine(), -1);
        Curso c = null;
        if (idx >= 0 && idx < repoCursos.findAll().size()) c = repoCursos.findAll().get(idx);
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        Aluno a = new Aluno(nome, mat, c, login, senha);
        repoAlunos.save(a);
        System.out.println("Aluno cadastrado: " + nome);
    }

    private void criarTurma() {
        System.out.print("Código da turma: ");
        String cod = scanner.nextLine();
        System.out.println("Escolha professor (indice):");
        listarProfessores();
        int pidx = parseInt(scanner.nextLine(), -1);
        Professor p = null;
        if (pidx >= 0 && pidx < repoProfessores.findAll().size()) p = repoProfessores.findAll().get(pidx);
        System.out.println("Escolha curso (indice):");
        listarCursos();
        int cidx = parseInt(scanner.nextLine(), -1);
        Curso c = null;
        if (cidx >= 0 && cidx < repoCursos.findAll().size()) c = repoCursos.findAll().get(cidx);
        Turma t = new Turma(cod, p, c);
        repoTurmas.save(t);
        System.out.println("Turma criada: " + cod);
    }

    private void matricularAluno() {
        System.out.println("Escolha turma:");
        listarTurmas();
        int tidx = parseInt(scanner.nextLine(), -1);
        if (tidx < 0 || tidx >= repoTurmas.findAll().size()) { System.out.println("Turma inválida"); return; }
        Turma t = repoTurmas.findAll().get(tidx);
        System.out.println("Escolha aluno:");
        listarAlunos();
        int aidx = parseInt(scanner.nextLine(), -1);
        if (aidx < 0 || aidx >= repoAlunos.findAll().size()) { System.out.println("Aluno inválido"); return; }
        Aluno a = repoAlunos.findAll().get(aidx);
        boolean ok = t.adicionarAluno(a);
        System.out.println(ok ? "Aluno matriculado." : "Falha: aluno já matriculado ou inválido.");
    }

    private void registrarAvaliacao() {
        System.out.println("Escolha aluno:");
        listarAlunos();
        int aidx = parseInt(scanner.nextLine(), -1);
        if (aidx < 0 || aidx >= repoAlunos.findAll().size()) { System.out.println("Aluno inválido"); return; }
        Aluno a = repoAlunos.findAll().get(aidx);
        System.out.print("Descrição da avaliação: ");
        String desc = scanner.nextLine();
        System.out.print("Nota (0-10): ");
        double nota = parseDouble(scanner.nextLine(), -1);
        Avaliacao av = new Avaliacao(desc);
        try {
            av.atribuirNota(nota);
            a.adicionarAvaliacao(av);
            System.out.println("Avaliação registrada.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void gerarRelatorios() {
        System.out.println("1) Todos os alunos\n2) Todos os professores\n3) Todos os cursos\n4) Todas as turmas\nOutro) Voltar");
        String o = scanner.nextLine().trim();
        switch (o) {
            case "1": for (Aluno a : repoAlunos.findAll()) System.out.println(reportService.relatorioAluno(a)); break;
            case "2": for (Professor p : repoProfessores.findAll()) System.out.println(reportService.relatorioProfessor(p)); break;
            case "3": for (Curso c : repoCursos.findAll()) System.out.println(reportService.relatorioCurso(c)); break;
            case "4": for (Turma t : repoTurmas.findAll()) System.out.println(t.resumo()); break;
            default: return;
        }
    }

    private void cenariosTeste() {
        System.out.println("Executando cenários de sucesso e falha...");
        Curso c = new Curso("Teste Curso", "T-001", 40);
        repoCursos.save(c);
        Professor p = new Professor("Prof Teste", "Teste", "R-999", "ptest", "p123");
        repoProfessores.save(p);
        Aluno a = new Aluno("Aluno Teste", "M-999", c, "atest", "a123");
        repoAlunos.save(a);
        Turma t = new Turma("TUR-1", p, c);
        repoTurmas.save(t);
        boolean m1 = t.adicionarAluno(a);
        System.out.println("Cenário sucesso - matricular: " + (m1 ? "OK" : "FAIL"));
        try {
            Avaliacao av = new Avaliacao("Prova X");
            av.atribuirNota(11);
            System.out.println("Cenário falha - nota inválida aceita? ERRO");
        } catch (IllegalArgumentException ex) {
            System.out.println("Cenário falha - nota inválida rejeitada: OK");
        }
        boolean m2 = t.adicionarAluno(a);
        System.out.println("Cenário falha - matricular duas vezes: " + (m2 ? "ERRO" : "OK (prevenido)"));
    }

    private void listarCursos() {
        List<Curso> l = repoCursos.findAll();
        for (int i = 0; i < l.size(); i++) System.out.println(i + ") " + l.get(i).getNome());
    }

    private void listarProfessores() {
        List<Professor> l = repoProfessores.findAll();
        for (int i = 0; i < l.size(); i++) System.out.println(i + ") " + l.get(i).getNome());
    }

    private void listarAlunos() {
        List<Aluno> l = repoAlunos.findAll();
        for (int i = 0; i < l.size(); i++) System.out.println(i + ") " + l.get(i).getNome());
    }

    private void listarTurmas() {
        List<Turma> l = repoTurmas.findAll();
        for (int i = 0; i < l.size(); i++) System.out.println(i + ") " + l.get(i).getCodigo());
    }

    private int parseInt(String s, int fallback) {
        try { return Integer.parseInt(s.trim()); } catch (Exception e) { return fallback; }
    }

    private double parseDouble(String s, double fallback) {
        try { return Double.parseDouble(s.trim().replace(',', '.')); } catch (Exception e) { return fallback; }
    }
}
