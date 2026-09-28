package br.com.loja.modelo.venda;

import br.com.loja.modelo.produto.Produto;

import java.math.BigDecimal;

public class Venda {
    private final Produto produto;
    private final int quantidade;

    public Venda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getTotal() {
        return produto.getPreco().multiply(BigDecimal.valueOf(quantidade));
    }
}