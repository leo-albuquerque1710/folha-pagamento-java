/** Salário base acrescido de comissão sobre as vendas. */
public class ColaboradorComissionado extends Colaborador {
    private final double valorVendas;
    private final double percentualComissao;

    public ColaboradorComissionado(int matricula, String nome,
                                  double valorVendas, double percentualComissao) {
        super(matricula, nome);
        validarValor(valorVendas, "Valor das vendas");
        validarValor(percentualComissao, "Percentual de comissão");
        this.valorVendas = valorVendas;
        this.percentualComissao = percentualComissao;
        validarValor(calcularSalarioFinal(), "Salário calculado");
    }

    public double getValorVendas() { return valorVendas; }
    public double getPercentualComissao() { return percentualComissao; }

    public double calcularComissao() {
        return valorVendas * (percentualComissao / 100.0);
    }

    @Override
    public double calcularSalarioFinal() {
        return getSalarioBase() + calcularComissao();
    }

    @Override
    public String getTipo() { return "Comissionado"; }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Vendas: " + formatarMoeda(valorVendas));
        System.out.println("Percentual de comissão: " + percentualComissao + "%");
        System.out.println("Comissão: " + formatarMoeda(calcularComissao()));
    }
}
