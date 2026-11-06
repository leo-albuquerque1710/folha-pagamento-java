/** Salário base acrescido do adicional por peças produzidas. */
public class ColaboradorProducao extends Colaborador {
    private final int quantidadePecas;
    private final double valorPorPeca;

    public ColaboradorProducao(int matricula, String nome,
                              int quantidadePecas, double valorPorPeca) {
        super(matricula, nome);
        if (quantidadePecas < 0) {
            throw new IllegalArgumentException("A quantidade de peças não pode ser negativa.");
        }
        validarValor(valorPorPeca, "Valor por peça");
        this.quantidadePecas = quantidadePecas;
        this.valorPorPeca = valorPorPeca;
        validarValor(calcularSalarioFinal(), "Salário calculado");
    }

    public int getQuantidadePecas() { return quantidadePecas; }
    public double getValorPorPeca() { return valorPorPeca; }

    public double calcularProdutividade() {
        return quantidadePecas * valorPorPeca;
    }

    @Override
    public double calcularSalarioFinal() {
        return getSalarioBase() + calcularProdutividade();
    }

    @Override
    public String getTipo() { return "Produção"; }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Quantidade de peças: " + quantidadePecas);
        System.out.println("Valor por peça: " + formatarMoeda(valorPorPeca));
        System.out.println("Produtividade: " + formatarMoeda(calcularProdutividade()));
    }
}
