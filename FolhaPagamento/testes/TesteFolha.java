/** Testes sem bibliotecas externas. Execute conforme o README. */
public class TesteFolha {
    private static int verificacoes = 0;

    public static void main(String[] args) {
        Colaborador padrao = new ColaboradorPadrao(1, "Ana Silva");
        ColaboradorComissionado comissao = new ColaboradorComissionado(2, "Bruno Souza", 10000, 5);
        ColaboradorProducao producao = new ColaboradorProducao(3, "Carla Santos", 100, 3.5);
        igual(2000, padrao.calcularSalarioFinal());
        igual(500, comissao.calcularComissao());
        igual(2500, comissao.calcularSalarioFinal());
        igual(350, producao.calcularProdutividade());
        igual(2350, producao.calcularSalarioFinal());
        igual(2000, new ColaboradorComissionado(4, "Diego Lima", 0, 5).calcularSalarioFinal());
        igual(2000, new ColaboradorComissionado(4, "Diego Lima", 100, 0).calcularSalarioFinal());
        igual(2000, new ColaboradorProducao(5, "Eva Costa", 0, 4).calcularSalarioFinal());
        igual(2000, new ColaboradorProducao(5, "Eva Costa", 10, 0).calcularSalarioFinal());
        igual(2000.25, new ColaboradorComissionado(6, "Fabio Alves", 10, 2.5).calcularSalarioFinal());
        // Despacho polimórfico: cada objeto calcula o próprio salário.
        double total = 0;
        for (Colaborador c : new Colaborador[] {padrao, comissao, producao}) total += c.calcularSalarioFinal();
        igual(6850, total);
        invalido(() -> new ColaboradorPadrao(0, "Ana Silva"));
        invalido(() -> new ColaboradorPadrao(-1, "Ana Silva"));
        invalido(() -> new ColaboradorPadrao(1, "  "));
        invalido(() -> new ColaboradorPadrao(1, null));
        invalido(() -> new ColaboradorComissionado(1, "Ana Silva", -1, 5));
        invalido(() -> new ColaboradorComissionado(1, "Ana Silva", 100, -5));
        invalido(() -> new ColaboradorProducao(1, "Ana Silva", -1, 5));
        invalido(() -> new ColaboradorProducao(1, "Ana Silva", 1, -5));
        invalido(() -> new ColaboradorComissionado(1, "Ana Silva", Double.NaN, 5));
        invalido(() -> new ColaboradorProducao(1, "Ana Silva", 1, Double.POSITIVE_INFINITY));
        invalido(() -> new ColaboradorProducao(1, "Ana Silva", 2, Double.MAX_VALUE));
        invalido(() -> new ColaboradorComissionado(1, "Ana Silva", Double.MAX_VALUE, 200));
        System.out.println("OK: " + verificacoes + " verificações concluídas.");
    }

    private static void igual(double esperado, double obtido) {
        if (!Double.isFinite(obtido) || Math.abs(esperado - obtido) > 0.000001) {
            throw new AssertionError("Esperado: " + esperado + "; obtido: " + obtido);
        }
        verificacoes++;
    }

    private static void invalido(Runnable acao) {
        try {
            acao.run();
        } catch (IllegalArgumentException e) {
            verificacoes++;
            return;
        }
        throw new AssertionError("O dado inválido deveria ser rejeitado.");
    }
}
