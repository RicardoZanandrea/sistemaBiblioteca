import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> acervo = new ArrayList<>();

    public void adicionar(Livro livro) throws Exception {
        if (livro.getTitulo() == null || livro.getTitulo().isEmpty())
            throw new Exception("Título obrigatório: informe o título do livro.");
        if (livro.getAutor() == null || livro.getAutor().isEmpty())
            throw new Exception("Autor obrigatório: informe o nome do autor.");
        if (livro.getAnoPublicacao() < 1400 || livro.getAnoPublicacao() > LocalDate.now().getYear())
            throw new Exception("Ano de publicação inválido: deve estar entre 1400 e o ano atual.");
        if (livro.getnPaginas() <= 0)
            throw new Exception("Número de páginas inválido: o livro deve ter pelo menos uma página.");

        for (Livro livroAcervo : acervo) {
            if (livroAcervo.getTitulo().equalsIgnoreCase(livro.getTitulo()))
                throw new Exception("Livro duplicado: já existe um cadastro com este título.");
        }
        acervo.add(livro);
        System.out.println("Livro adicionado ao acervo: " + livro.getTitulo()); // Log para verificar
        System.out.println("Total de livros no acervo: " + acervo.size()); // Log para verificar
    }

    public List<Livro> pesquisarPorTitulo(String titulo) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                livrosEncontrados.add(livro);
            }
        }
        return livrosEncontrados;
    }

    public void removerPorTitulo(String titulo) throws Exception {
        Livro livroAlvo = null;
        for (Livro livro : acervo) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                livroAlvo = livro;
                break;
            }
        }

        if (livroAlvo != null) {
            acervo.remove(livroAlvo);
        } else {
            throw new Exception("Livro não encontrado.");
        }
    }

    public List<Livro> pesquisarPorAno(int ano) {
        List<Livro> livrosPorAno = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getAnoPublicacao() == ano) {
                livrosPorAno.add(livro);
            }
        }
        return livrosPorAno;
    }

    public List<Livro> pesquisarPorAutor(String autor) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                livrosEncontrados.add(livro);
            }
        }
        return livrosEncontrados;
    }
}
