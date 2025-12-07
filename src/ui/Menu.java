package ui;

import java.util.Scanner;
import model.Aluno;
import model.Professor;
import model.Curso;
import service.ReportService;

public class Menu {
    private final ReportService reportService = new ReportService();

    public void executar(Aluno aluno, Professor professor, Curso curso) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("Escolha um relatório para gerar:");
            System.out.println("1) Relatório do Aluno");
            System.out.println("2) Relatório do Professor");
            System.out.println("3) Relatório do Curso");
            System.out.println("4) Gerar todos os relatórios");
            System.out.println("0) Sair");
            System.out.print("Opção: ");

            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            int opc;
            try { opc = Integer.parseInt(line); } catch (NumberFormatException e) { System.out.println("Opção inválida"); continue; }

            if (opc == 0) {
                System.out.println("Encerrando");
                break;
            }

            switch (opc) {
                case 1:
                    System.out.println(reportService.relatorioAluno(aluno));
                    break;
                case 2:
                    System.out.println(reportService.relatorioProfessor(professor));
                    break;
                case 3:
                    System.out.println(reportService.relatorioCurso(curso));
                    break;
                case 4:
                    System.out.println(reportService.relatorioAluno(aluno));
                    System.out.println(reportService.relatorioProfessor(professor));
                    System.out.println(reportService.relatorioCurso(curso));
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
        sc.close();
    }
}

