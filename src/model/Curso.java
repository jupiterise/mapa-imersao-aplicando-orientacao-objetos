package model;

import java.util.ArrayList;
import java.util.List;

public class Curso implements Relatorio {
    private String nome;
    private String codigo;
    private int cargaHoraria;

    public Curso(String nome, String codigo, int cargaHoraria) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
    }

    public String getNome() { return nome; }
    public String getCodigo() { return codigo; }
    public int getCargaHoraria() { return cargaHoraria; }

    public void validar() {
        if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("Nome do curso inválido");
        if (codigo == null || codigo.trim().isEmpty()) throw new IllegalArgumentException("Código do curso inválido");
        if (cargaHoraria <= 0) throw new IllegalArgumentException("Carga horária deve ser maior que zero");
    }

    public String detalharCurso() {
        return String.format("Curso: %s (Código: %s) - Carga Horária: %d horas", nome, codigo, cargaHoraria);
    }

    @Override
    public String toString() {
        return String.format("Curso{nome='%s', codigo='%s', cargaHoraria=%d}", nome, codigo, cargaHoraria);
    }

    @Override
    public String gerarRelatorio() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Relatório do Curso ---\n");
        sb.append("Nome: ").append(nome).append('\n');
        sb.append("Código: ").append(codigo).append('\n');
        sb.append("Carga Horária: ").append(cargaHoraria).append(" horas\n");
        return sb.toString();
    }
}

