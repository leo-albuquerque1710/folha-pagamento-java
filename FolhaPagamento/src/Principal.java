import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** Entrada da aplicação: menu, leitura e armazenamento em memória. */
public class Principal {
    public static void main(String[] args) {
        ArrayList<Colaborador> colaboradores = new ArrayList<>();
        try (Scanner sc = new Scanner(System.in, "UTF-8")) {
            int opcao;
            do {
                System.out.println("\n=== FOLHA DE PAGAMENTO ===");
                System.out.println("1 - Cadastrar colaborador");
                System.out.println("2 - Listar colaboradores");
                System.out.println("3 - Consultar por matrícula");
                System.out.println("4 - Calcular folha completa");
                System.out.println("0 - Sair");
                opcao = lerInteiro(sc, "Opção: ", 0, 4);
                switch (opcao) {
                    case 1: cadastrar(sc, colaboradores); break;
                    case 2: listar(colaboradores); break;
                    case 3: consultar(sc, colaboradores); break;
                    case 4: exibirFolha(colaboradores); break;
                    case 0: System.out.println("Programa encerrado."); break;
                    default: break;
                }
            } while (opcao != 0);
        } catch (NoSuchElementException e) {
            System.out.println("\nEntrada encerrada. Programa finalizado.");
        }
    }

    private static void cadastrar(Scanner sc, ArrayList<Colaborador> colaboradores) {
        System.out.println("\nTipos: 1 - Padrão | 2 - Comissionado | 3 - Produção");
        int tipo = lerInteiro(sc, "Tipo: ", 1, 3);
        int matricula;
        do {
            matricula = lerInteiro(sc, "Matrícula (inteiro positivo): ", 1, Integer.MAX_VALUE);
            if (buscar(colaboradores, matricula) == null) break;
            System.out.println("Matrícula já cadastrada. Informe outra.");
        } while (true);
        String nome = lerTexto(sc, "Nome completo: ");

        // A referência da superclasse pode armazenar qualquer uma das subclasses.
        Colaborador colaborador;
        while (true) {
            try {
                switch (tipo) {
                    case 1:
                        colaborador = new ColaboradorPadrao(matricula, nome);
                        break;
                    case 2:
                        double vendas = lerDecimal(sc, "Valor das vendas (R$): ");
                        double percentual = lerDecimal(sc, "Comissão (%; exemplo: 5 para 5%): ");
                        colaborador = new ColaboradorComissionado(matricula, nome, vendas, percentual);
                        break;
                    case 3:
                        int pecas = lerInteiro(sc, "Quantidade de peças: ", 0, Integer.MAX_VALUE);
                        double valor = lerDecimal(sc, "Valor por peça (R$): ");
                        colaborador = new ColaboradorProducao(matricula, nome, pecas, valor);
                        break;
                    default:
                        throw new IllegalStateException("Tipo inválido.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Informe novamente os valores adicionais.");
            }
        }
        colaboradores.add(colaborador);
        System.out.println("Colaborador cadastrado com sucesso!");
        colaborador.exibirDados();
    }

    private static Colaborador buscar(ArrayList<Colaborador> colaboradores, int matricula) {
        for (Colaborador colaborador : colaboradores) {
            if (colaborador.getMatricula() == matricula) return colaborador;
        }
        return null;
    }

    private static void listar(ArrayList<Colaborador> colaboradores) {
        if (colaboradores.isEmpty()) {
            System.out.println("Nenhum colaborador cadastrado.");
            return;
        }
        for (Colaborador colaborador : colaboradores) {
            System.out.println("\n------------------------------");
            colaborador.exibirDados();
        }
    }

    private static void consultar(Scanner sc, ArrayList<Colaborador> colaboradores) {
        if (colaboradores.isEmpty()) {
            System.out.println("Nenhum colaborador cadastrado.");
            return;
        }
        int matricula = lerInteiro(sc, "Matrícula: ", 1, Integer.MAX_VALUE);
        Colaborador colaborador = buscar(colaboradores, matricula);
        if (colaborador == null) System.out.println("Colaborador não encontrado.");
        else colaborador.exibirDados();
    }

    private static void exibirFolha(ArrayList<Colaborador> colaboradores) {
        if (colaboradores.isEmpty()) {
            System.out.println("Nenhum colaborador cadastrado.");
            return;
        }
        // Soma em centavos para o total corresponder aos salários exibidos.
        BigDecimal total = BigDecimal.ZERO;
        NumberFormat moeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        System.out.println("\n=== FOLHA COMPLETA ===");
        for (Colaborador colaborador : colaboradores) {
            BigDecimal salario = BigDecimal.valueOf(colaborador.calcularSalarioFinal())
                    .setScale(2, RoundingMode.HALF_EVEN);
            total = total.add(salario);
            System.out.println(colaborador.getMatricula() + " - " + colaborador.getNome()
                    + " (" + colaborador.getTipo() + "): " + moeda.format(salario));
        }
        System.out.println("Quantidade de colaboradores: " + colaboradores.size());
        System.out.println("Total da folha: " + moeda.format(total));
    }

    // Todas as entradas usam nextLine, evitando quebras de linha pendentes no Scanner.
    private static String lerTexto(Scanner sc, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = sc.nextLine().trim();
            if (!texto.isEmpty()) return texto;
            System.out.println("O campo não pode ficar vazio.");
        }
    }

    private static int lerInteiro(Scanner sc, String mensagem, int minimo, int maximo) {
        while (true) {
            String texto = lerTexto(sc, mensagem);
            try {
                int valor = Integer.parseInt(texto);
                if (valor >= minimo && valor <= maximo) return valor;
            } catch (NumberFormatException e) {
                // O mesmo aviso cobre texto, casas decimais e valores fora do tipo int.
            }
            System.out.println("Informe um inteiro entre " + minimo + " e " + maximo + ".");
        }
    }

    private static double lerDecimal(Scanner sc, String mensagem) {
        while (true) {
            String texto = lerTexto(sc, mensagem).replace(',', '.');
            try {
                double valor = Double.parseDouble(texto);
                if (Double.isFinite(valor) && valor >= 0) return valor;
            } catch (NumberFormatException e) {
                // Repetir a leitura sem encerrar o programa.
            }
            System.out.println("Informe um número não negativo, sem separador de milhar (ex.: 1250,50).");
        }
    }
}
