import java.text.NumberFormat;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

/** Superclasse com os dados comuns a todos os colaboradores. */
public abstract class Colaborador {
    public static final double SALARIO_BASE = 2000.00;

    private final int matricula;
    private final String nome;
    private final double salarioBase;

    protected Colaborador(int matricula, String nome) {
        if (matricula <= 0) {
            throw new IllegalArgumentException("A matrícula deve ser positiva.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ficar vazio.");
        }
        this.matricula = matricula;
        this.nome = nome.trim();
        this.salarioBase = SALARIO_BASE;
    }

    public int getMatricula() { return matricula; }
    public String getNome() { return nome; }
    public double getSalarioBase() { return salarioBase; }

    public abstract double calcularSalarioFinal();
    public abstract String getTipo();

    public void exibirDados() {
        System.out.println("Matrícula: " + matricula);
        System.out.println("Nome: " + nome);
        System.out.println("Tipo: " + getTipo());
        System.out.println("Salário base: " + formatarMoeda(salarioBase));
        System.out.println("Salário final: " + formatarMoeda(calcularSalarioFinal()));
    }

    public static String formatarMoeda(double valor) {
        return NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                .format(BigDecimal.valueOf(valor).setScale(2, RoundingMode.HALF_EVEN));
    }

    protected static void validarValor(double valor, String campo) {
        if (!Double.isFinite(valor) || valor < 0) {
            throw new IllegalArgumentException(campo + " deve ser um número finito e não negativo.");
        }
    }
}
