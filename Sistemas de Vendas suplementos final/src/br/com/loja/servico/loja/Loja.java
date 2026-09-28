package br.com.loja.servico.loja;

import br.com.loja.modelo.produto.Produto;
import br.com.loja.modelo.venda.Venda;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Loja {
    private final ArrayList<Produto> produtos = new ArrayList<>();
    private final ArrayList<Venda> vendas = new ArrayList<>();

    public void adicionarProduto(Produto produto) {
        for (Produto produtoExistente : produtos) {
            if (produtoExistente.getId() == produto.getId()) {
                throw new IllegalArgumentException("Ja existe um produto com esse codigo.");
            }
        }
        produtos.add(produto);
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public ArrayList<Venda> getVendas() {
        return vendas;
    }

    public Venda registrarVenda(int idProduto, int quantidade) {
        for (Produto produto : produtos) {
            if (produto.getId() == idProduto) {
                produto.retirarEstoque(quantidade);
                Venda venda = new Venda(produto, quantidade);
                vendas.add(venda);
                return venda;
            }
        }

        throw new IllegalArgumentException("Produto nao encontrado.");
    }

    public BigDecimal getTotalVendido() {
        BigDecimal total = BigDecimal.ZERO;
        for (Venda venda : vendas) {
            total = total.add(venda.getTotal());
        }
        return total;
    }
}