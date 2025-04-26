import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Biblioteca biblio = new Biblioteca();  
    static Scanner input = new Scanner(System.in);  

    private static int inputNumerico(String mensagem) {
        int valor = 0;
        boolean entradaValida = false;
        System.out.println(mensagem);     
        do {
            String valorStr = input.nextLine();   
            try {
                valor = Integer.parseInt(valorStr);  
                entradaValida = true;
            } catch (Exception e) {
                System.out.println("Erro. Por favor informe um número Inteiro");
            }
        } while (!entradaValida);
        return valor;
    }
            
     private static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


     private static void adicionar() {
        Livro novoLivro = new Livro();
        System.out.println("--------- ADICIONANDO NOVO LIVRO ---------");
        System.out.print("Informe o título do livro: ");
        String titulo = input.nextLine();
        novoLivro.setTitulo(titulo);

        System.out.print("Informe o nome do autor: ");
        String autor = input.nextLine();
        novoLivro.setAutor(autor);

        System.out.print("Informe o ano de publicação: ");
        int ano = input.nextInt();
        novoLivro.setAnoPublicacao(ano);
        input.nextLine(); 

        System.out.print("Informe o número de páginas: ");
        int nPaginas = input.nextInt();
        novoLivro.setnPaginas(nPaginas);
        input.nextLine();  

        try {
            biblio.adicionar(novoLivro);
            System.out.println("Livro adicionado com Sucesso!");
        } catch (Exception e) {
             System.out.println("ERRO: " + e.getMessage());
        }

        input.nextLine();   
    }

     private static void pesquisar() {
        System.out.print("Informe o título do livro que você deseja pesquisar: ");
        String titulo = input.nextLine().trim();
        List<Livro> livrosEncontrados = biblio.pesquisarPorTitulo(titulo);
        
        if (livrosEncontrados.isEmpty()) {
            System.out.println("Nenhum livro encontrado com esse título.");
        } else {
            System.out.println("--------- LIVROS ENCONTRADOS ----------");
            for (Livro livro : livrosEncontrados) {
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Ano: " + livro.getAnoPublicacao());
                System.out.println("N. Páginas: " + livro.getnPaginas());
                System.out.println("-----------------------------");
            }
        } 
        System.out.println("Pressione Enter para continuar...");
        input.nextLine();
    }

    private static void remover() {
        System.out.print("Informe o Titulo do Livro que você deseja remover: ");
        String titulo = input.nextLine();
        try {
            biblio.removerPorTitulo(titulo);
            System.out.println("Livro Removido!");
        } catch (Exception e) {
            System.out.println("Erro ao Remover Esse Livro: " + e.getMessage());
        }
    }

    private static void pesquisarPorAutor() {
        System.out.print("Informe o autor que você deseja pesquisar: ");
        String autor = input.nextLine().trim();
        List<Livro> livrosEncontrados = biblio.pesquisarPorAutor(autor);
        
        if (livrosEncontrados.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            System.out.println("---------- LIVROS ENCONTRADOS ----------");
            for (Livro livro : livrosEncontrados) {
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Ano: " + livro.getAnoPublicacao());
                System.out.println("N. Páginas: " + livro.getnPaginas());
                System.out.println("-----------------------------");
            }
        }
        System.out.println("Pressione Enter para continuar...");
        input.nextLine();
    }

    private static void listarPorAno() {
        int ano = inputNumerico("Informe o ano de publicação para listar os livros:");

        List<Livro> livrosEncontrados = biblio.pesquisarPorAno(ano);

        if (livrosEncontrados.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o ano " + ano + ".");
        } else {
            System.out.println("---------- LIVROS ENCONTRADOS PARA O ANO " + ano + " ----------");
            for (Livro livro : livrosEncontrados) {
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Ano: " + livro.getAnoPublicacao());
                System.out.println("N. Páginas: " + livro.getnPaginas());
                System.out.println("-----------------------------");
            }
        }
    }

     public static void main(String[] args) {
        String menu = """
                SISTEMA DE GERENCIAMENTO DA BIBLIOTECA
                Escolha uma das opções:
                1 - Adicionar novo livro;
                2 - Pesquisar livro por título;
                3 - Pesquisar livro por autor;
                4 - Remover livro;
                5 - Listar livros por ano de publicação;
                0 - Sair;
                """;
        int opcao;
        do {
            limparTela();   
            opcao = inputNumerico(menu);   
            switch (opcao) {
                case 0:
                    System.out.println("Sessão encerrada.");
                    break;
                case 1:
                    adicionar();
                    break;
                case 2:
                    pesquisar();
                    break;
                case 3:
                    pesquisarPorAutor();
                    break;
                case 4:
                    remover();
                    break;
                case 5:
                    listarPorAno();
                    break;
    default:
        System.out.println("Opção Inválida!");
        input.nextLine();
        break;
}
        } while (opcao != 0);
    }
}