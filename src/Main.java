import model.*;
import repository.InMemoryRepository;
import service.ReportService;
import ui.Menu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        System.out.println("Curso: Análise e Desenvolvimento de Sistemas");
        System.out.println("Disciplina: Imersão - APLICANDO ORIENTAÇÃO A OBJETOS");
        System.out.println("Aluno: Leonardo Felipe Silva - RA: 23321371-5");
        System.out.println("Data/Hora: " + dataHora);
        System.out.println("============================================================");

        Curso curso = new Curso("Análise e Desenvolvimento de Sistemas", "ADS-101", 400);
        Professor professor = new Professor("Mariana Oliveira", "Sistemas", "PROF-3001", "mariana", "prof@123");
        Aluno aluno = new Aluno("Leonardo Felipe Silva", "23321371-5", curso, "leo", "senha123");

        try {
            Avaliacao p1 = new Avaliacao("Prova 1"); p1.atribuirNota(8.5);
            Avaliacao p2 = new Avaliacao("Trabalho A"); p2.atribuirNota(9.0);
            aluno.adicionarAvaliacao(p1);
            aluno.adicionarAvaliacao(p2);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao atribuir nota: " + e.getMessage());
        }

        InMemoryRepository<Curso> cursos = new InMemoryRepository<>();
        InMemoryRepository<Professor> professores = new InMemoryRepository<>();
        InMemoryRepository<Aluno> alunos = new InMemoryRepository<>();
        InMemoryRepository<model.Turma> turmas = new InMemoryRepository<>();

        cursos.save(curso);
        professores.save(professor);
        alunos.save(aluno);

        ReportService reportService = new ReportService();
        Menu menu = new Menu(alunos, professores, cursos, turmas, reportService);
        menu.executar();
    }
}
