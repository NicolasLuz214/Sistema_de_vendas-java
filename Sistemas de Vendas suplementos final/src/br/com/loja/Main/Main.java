package br.com.loja.Main;

import br.com.loja.modelo.produto.Produto;
import br.com.loja.modelo.venda.Venda;
import br.com.loja.servico.loja.Loja;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Loja loja = new Loja();
        loja.adicionarProduto(new Produto(1, "Whey Protein", "Proteina", new BigDecimal("129.90"), 15));
        loja.adicionarProduto(new Produto(2, "Creatina", "Performance", new BigDecimal("79.90"), 20));
        loja.adicionarProduto(new Produto(3, "Pre-treino", "Energia", new BigDecimal("89.50"), 10));
        loja.adicionarProduto(new Produto(4, "BCAA", "Aminoacidos", new BigDecimal("64.90"), 12));

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== LOJA DE SUPLEMENTOS ===");
            System.out.println("1 - Listar produtos e estoque");
            System.out.println("2 - Registrar venda");
            System.out.println("3 - Consultar vendas");
            System.out.println("0 - Sair");
            opcao = lerInteiro(scanner, "Escolha uma opcao: ");

            if (opcao == 1) {
                listarProdutos(loja);
            } else if (opcao == 2) {
                registrarVenda(scanner, loja);
            } else if (opcao == 3) {
                listarVendas(loja);
            } else if (opcao != 0) {
                System.out.println("Opcao invalida.");
            }
        }
        System.out.println("Sistema encerrado.");
    }

    private static void listarProdutos(Loja loja) {
        System.out.println("\n--- Produtos ---");
        for (Produto produto : loja.getProdutos()) {
            System.out.printf("%d - %s (%s) | R$ %s | Estoque: %d%n",
                    produto.getId(), produto.getNome(), produto.getCategoria(),
                    produto.getPreco(), produto.getQuantidadeEstoque());
        }
    }

    private static void registrarVenda(Scanner scanner, Loja loja) {
        listarProdutos(loja);
        int idProduto = lerInteiro(scanner, "Codigo do produto (0 para cancelar): ");
        if (idProduto == 0) {
            return;
        }

        int quantidade = lerInteiro(scanner, "Quantidade: ");
        try {
            Venda venda = loja.registrarVenda(idProduto, quantidade);
            System.out.printf("Venda registrada: %d x %s | Total: R$ %s%n",
                    venda.getQuantidade(), venda.getProduto().getNome(), venda.getTotal());
        } catch (IllegalArgumentException exception) {
            System.out.println("Nao foi possivel registrar a venda: " + exception.getMessage());
        }
    }

    private static void listarVendas(Loja loja) {
        if (loja.getVendas().isEmpty()) {
            System.out.println("\nNenhuma venda registrada.");
            return;
        }

        System.out.println("\n--- Vendas ---");
        for (Venda venda : loja.getVendas()) {
            System.out.printf("%d x %s | R$ %s%n", venda.getQuantidade(),
                    venda.getProduto().getNome(), venda.getTotal());
        }
        System.out.println("Total vendido: R$ " + loja.getTotalVendido());
    }

    private static int lerInteiro(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException exception) {
                System.out.println("Digite um numero inteiro valido.");
            }
        }
    }
}