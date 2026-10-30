
import java.util.ArrayList;
import java.util.Scanner;

class Pessoa {

    private String Nome;
    private static int Id = 0;
    private final int UserID;
    private String NivelDeAcesso;
    private boolean Estado;

    // Construtor
    public Pessoa(String nome, String niveldeacesso) {

        this.Nome = nome;
        this.NivelDeAcesso = niveldeacesso;
        this.UserID = ++Id;
        this.Estado = false;
    }

    // Getters e Setters
    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public int getUserID() {
        return UserID;
    }

    public String getNivelDeAcesso() {
        return NivelDeAcesso;
    }

    public void setNivelDeAcesso(String niveldeacesso) {
        this.NivelDeAcesso = niveldeacesso;
    }

    public boolean getEstado() {
        return Estado;
    }

    // Registar entrada
    public void entrouNoEstabelecimento() {

        if (NivelDeAcesso.equals("Administrador") ||
            NivelDeAcesso.equals("Funcionario")) {

            if (!Estado) {
                Estado = true;
                System.out.println(Nome + " entrou no estabelecimento.");
            } else {
                System.out.println(Nome + " ja se encontra dentro.");
            }

        } else {
            System.out.println(Nome + " nao tem permissao para entrar.");
        }
    }

    // Registar saida
    public void saiuDoEstabelecimento() {

        if (Estado) {
            Estado = false;
            System.out.println(Nome + " saiu do estabelecimento.");
        } else {
            System.out.println(Nome + " ja se encontra fora.");
        }
    }

    // Mostrar informacoes
    public void User() {

        System.out.println("ID: " + UserID);
        System.out.println("Nome: " + Nome);
        System.out.println("Nivel de Acesso: " + NivelDeAcesso);

        if (Estado) {
            System.out.println("Estado: Dentro do estabelecimento");
        } else {
            System.out.println("Estado: Fora do estabelecimento");
        }

        System.out.println("--------------------------------------");
    }
}


// CLASSE PRINCIPAL
public class Project {

    static ArrayList<Pessoa> Pessoas = new ArrayList<>();
    static Scanner input = new Scanner(System.in);


    // Ler numeros inteiros
    public static int lerInteiro(String mensagem) {

        while (true) {

            System.out.print(mensagem);

            try {
                return Integer.parseInt(input.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduza um numero valido!");
            }
        }
    }


    // Cadastrar pessoa
    public static void cadastrarPessoa() {

        System.out.println("\n========= ADICIONAR PESSOA =========");

        System.out.print("Nome: ");
        String nome = input.nextLine();

        if (nome.trim().isEmpty()) {
            System.out.println("O nome nao pode estar vazio!");
            return;
        }

        System.out.println("\nNiveis de acesso:");
        System.out.println("1 - Administrador");
        System.out.println("2 - Funcionario");
        System.out.println("3 - Visitante");

        int opcao = lerInteiro("Escolha o nivel de acesso: ");

        String nivel;

        switch (opcao) {

            case 1:
                nivel = "Administrador";
                break;

            case 2:
                nivel = "Funcionario";
                break;

            case 3:
                nivel = "Visitante";
                break;

            default:
                System.out.println("Opcao invalida!");
                return;
        }

        Pessoa novaPessoa = new Pessoa(nome, nivel);

        Pessoas.add(novaPessoa);

        System.out.println("\nPessoa adicionada com sucesso!");
        System.out.println("ID atribuido: " + novaPessoa.getUserID());
    }


    // Pesquisar pessoa pelo ID
    public static Pessoa pesquisarPessoa(int id) {

        for (Pessoa p : Pessoas) {

            if (p.getUserID() == id) {
                return p;
            }
        }

        return null;
    }


    // Remover pessoa
    public static void removerPessoa() {

        System.out.println("\n========= REMOVER PESSOA =========");

        int id = lerInteiro("Introduza o ID da pessoa: ");

        Pessoa p = pesquisarPessoa(id);

        if (p != null) {

            if (p.getEstado()) {
                System.out.println("Nao pode remover uma pessoa que esta dentro do estabelecimento!");
                return;
            }

            Pessoas.remove(p);

            System.out.println("Pessoa removida com sucesso!");

        } else {
            System.out.println("Pessoa nao encontrada!");
        }
    }


    // Alterar nome
    public static void alterarNome() {

        System.out.println("\n========= ALTERAR NOME =========");

        int id = lerInteiro("Introduza o ID da pessoa: ");

        Pessoa p = pesquisarPessoa(id);

        if (p != null) {

            System.out.print("Novo nome: ");
            String novoNome = input.nextLine();

            if (novoNome.trim().isEmpty()) {
                System.out.println("O nome nao pode estar vazio!");
                return;
            }

            p.setNome(novoNome);

            System.out.println("Nome alterado com sucesso!");

        } else {
            System.out.println("Pessoa nao encontrada!");
        }
    }


    // Alterar nivel de acesso
    public static void alterarNivelDeAcesso() {

        System.out.println("\n========= ALTERAR NIVEL DE ACESSO =========");

        int id = lerInteiro("Introduza o ID da pessoa: ");

        Pessoa p = pesquisarPessoa(id);

        if (p != null) {

            System.out.println("Pessoa: " + p.getNome());
            System.out.println("Nivel atual: " + p.getNivelDeAcesso());

            System.out.println("\nNovo nivel de acesso:");
            System.out.println("1 - Administrador");
            System.out.println("2 - Funcionario");
            System.out.println("3 - Visitante");

            int opcao = lerInteiro("Escolha: ");

            String novoNivel;

            switch (opcao) {

                case 1:
                    novoNivel = "Administrador";
                    break;

                case 2:
                    novoNivel = "Funcionario";
                    break;

                case 3:
                    novoNivel = "Visitante";
                    break;

                default:
                    System.out.println("Opcao invalida!");
                    return;
            }

            if (novoNivel.equals("Visitante") && p.getEstado()) {
                System.out.println("Registe primeiro a saida da pessoa!");
                return;
            }

            p.setNivelDeAcesso(novoNivel);

            System.out.println("Nivel de acesso alterado com sucesso!");

        } else {
            System.out.println("Pessoa nao encontrada!");
        }
    }


    // Listar todas as pessoas
    public static void listarPessoas() {

        System.out.println("\n========= LISTA DE PESSOAS =========");

        if (Pessoas.isEmpty()) {
            System.out.println("Nao existem pessoas cadastradas.");
            return;
        }

        for (Pessoa p : Pessoas) {
            p.User();
        }
    }


    // Pesquisar pessoa
    public static void consultarPessoa() {

        System.out.println("\n========= PESQUISAR PESSOA =========");

        int id = lerInteiro("Introduza o ID: ");

        Pessoa p = pesquisarPessoa(id);

        if (p != null) {
            p.User();
        } else {
            System.out.println("Pessoa nao encontrada!");
        }
    }


    // Registar entrada
    public static void registarEntrada() {

        System.out.println("\n========= REGISTAR ENTRADA =========");

        int id = lerInteiro("Introduza o ID: ");

        Pessoa p = pesquisarPessoa(id);

        if (p != null) {
            p.entrouNoEstabelecimento();
        } else {
            System.out.println("Pessoa nao encontrada!");
        }
    }


    // Registar saida
    public static void registarSaida() {

        System.out.println("\n========= REGISTAR SAIDA =========");

        int id = lerInteiro("Introduza o ID: ");

        Pessoa p = pesquisarPessoa(id);

        if (p != null) {
            p.saiuDoEstabelecimento();
        } else {
            System.out.println("Pessoa nao encontrada!");
        }
    }


    // Listar pessoas dentro
    public static void listarPessoasDentro() {

        System.out.println("\n========= PESSOAS DENTRO =========");

        int contador = 0;

        for (Pessoa p : Pessoas) {

            if (p.getEstado()) {
                p.User();
                contador++;
            }
        }

        if (contador == 0) {
            System.out.println("Nao existem pessoas dentro do estabelecimento.");
        }

        System.out.println("Total dentro: " + contador);
    }


    // Estatisticas
    public static void contarPessoas() {

        int dentro = 0;
        int fora = 0;

        for (Pessoa p : Pessoas) {

            if (p.getEstado()) {
                dentro++;
            } else {
                fora++;
            }
        }

        System.out.println("\n========= ESTATISTICAS =========");
        System.out.println("Total de pessoas: " + Pessoas.size());
        System.out.println("Pessoas dentro: " + dentro);
        System.out.println("Pessoas fora: " + fora);
    }


    // MENU PRINCIPAL
    public static void main(String[] args) {

        int opcao;

        do {

            System.out.println("\n");
            System.out.println("======================================");
            System.out.println("       SISTEMA DE GESTAO DE PESSOAS");
            System.out.println("======================================");

            System.out.println("1  - Adicionar pessoa");
            System.out.println("2  - Remover pessoa");
            System.out.println("3  - Alterar nome");
            System.out.println("4  - Alterar nivel de acesso");
            System.out.println("5  - Listar todas as pessoas");
            System.out.println("6  - Pesquisar pessoa");
            System.out.println("7  - Registar entrada");
            System.out.println("8  - Registar saida");
            System.out.println("9  - Listar pessoas dentro");
            System.out.println("10 - Mostrar estatisticas");
            System.out.println("0  - Sair");

            System.out.println("======================================");

            opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {

                case 1:
                    cadastrarPessoa();
                    break;

                case 2:
                    removerPessoa();
                    break;

                case 3:
                    alterarNome();
                    break;

                case 4:
                    alterarNivelDeAcesso();
                    break;

                case 5:
                    listarPessoas();
                    break;

                case 6:
                    consultarPessoa();
                    break;

                case 7:
                    registarEntrada();
                    break;

                case 8:
                    registarSaida();
                    break;

                case 9:
                    listarPessoasDentro();
                    break;

                case 10:
                    contarPessoas();
                    break;

                case 0:
                    System.out.println("\nPrograma encerrado!");
                    break;

                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }

        } while (opcao != 0);

        input.close();
    }
}
