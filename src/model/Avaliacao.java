package model;

public class Avaliacao {
    private double nota = -1.0;
    private String descricao;

    public Avaliacao(String descricao) {
        this.descricao = descricao;
    }

    public double getNota() { return nota; }
    public String getDescricao() { return descricao; }

    public void atribuirNota(double valor) {
        if (valor < 0.0 || valor > 10.0) throw new IllegalArgumentException("Nota deve estar entre 0 e 10");
        this.nota = valor;
    }

    @Override
    public String toString() {
        return String.format("Avaliacao{descricao='%s', nota=%.2f}", descricao, nota);
    }
}

