/** Colaborador que recebe somente o salário base, sem atributos adicionais. */
public class ColaboradorPadrao extends Colaborador {
    public ColaboradorPadrao(int matricula, String nome) {
        super(matricula, nome);
    }

    @Override
    public double calcularSalarioFinal() { return getSalarioBase(); }

    @Override
    public String getTipo() { return "Padrão"; }
}
