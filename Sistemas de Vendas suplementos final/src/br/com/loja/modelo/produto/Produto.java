package br.com.loja.modelo.produto;

import java.math.BigDecimal;

public class Produto {
    private final int id;
    private final String nome;
    private final String categoria;
    private final BigDecimal preco;
    private int quantidadeEstoque;

    public Produto(int id, String nome, String categoria, BigDecimal preco, int quantidadeEstoque) {
        if (id <= 0 || nome == null || nome.isBlank() || preco == null
                || preco.signum() < 0 || quantidadeEstoque < 0) {
            throw new IllegalArgumentException("Dados do produto invalidos.");
        }
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void retirarEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        if (quantidade > quantidadeEstoque) {
            throw new IllegalArgumentException("Estoque insuficiente.");
        }
        quantidadeEstoque -= quantidade;
    }
}