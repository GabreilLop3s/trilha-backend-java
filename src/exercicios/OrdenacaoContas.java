package exercicios;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Conta {
    private String titular;
    private double saldo;

    public Conta(String titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return titular + " -> R$ " + String.format("%.2f", saldo);
    }
}

public class OrdenacaoContas {
    public static void main(String[] args) {
        List<Conta> contas = new ArrayList<>();
        contas.add(new Conta("Gabriel", 2500.00));
        contas.add(new Conta("Mariana", 980.50));
        contas.add(new Conta("Lucas", 5400.00));
        contas.add(new Conta("Fernanda", 1200.00));

        // Ordena do menor para o maior saldo (crescente)
        contas.sort(Comparator.comparingDouble(Conta::getSaldo));
        System.out.println("=== Ordem Crescente (Menor -> Maior) ===");
        contas.forEach(System.out::println);

        // Ordena do maior para o menor saldo (decrescente)
        contas.sort(Comparator.comparingDouble(Conta::getSaldo).reversed());
        System.out.println("\n=== Ordem Decrescente (Maior -> Menor) ===");
        contas.forEach(System.out::println);
    }
}

/* ============================================================================
 * DICIONÁRIO DO CÓDIGO
 * ============================================================================
 *
 * - List<Conta>: Interface que representa uma lista ordenada de objetos do
 *   tipo Conta. É boa prática declarar o tipo pela interface (List) e
 *   instanciar a implementação (ArrayList).
 *   (Equivalente no Delphi: TObjectList<TConta>)
 *
 * - ArrayList<>(): Implementação de lista dinâmica baseada em array que cresce
 *   conforme novos itens são adicionados com .add().
 *
 * - @Override: Anotação que indica ao compilador que estamos sobrescrevendo um
 *   método herdado da classe mãe (neste caso, toString() da classe Object).
 *   (Equivalente no Delphi: diretiva override)
 *
 * - toString(): Método padrão do Java chamado automaticamente sempre que um
 *   objeto é impresso no console. Define como o objeto aparece em texto.
 *
 * - String.format("%.2f", saldo): Formata um número decimal como texto com
 *   exatamente 2 casas decimais. (Equivalente no Delphi: FormatFloat('0.00', Saldo))
 *
 * - contas.sort(...): Método da própria lista que reorganiza seus elementos
 *   no lugar (in-place) seguindo a regra passada entre parênteses.
 *
 * - Comparator.comparingDouble(...): Fábrica de comparadores do Java que monta
 *   automaticamente a lógica de comparação numérica (menor, igual ou maior)
 *   extraindo um campo double de cada objeto.
 *
 * - Conta::getSaldo: Method Reference (referência de método). É um atalho limpo
 *   para dizer ao Comparator: "para cada Conta, chame o método getSaldo()".
 *
 * - .reversed(): Encadeado após o Comparator, inverte a ordem da comparação
 *   (transforma crescente em decrescente).
 *
 * - forEach(System.out::println): Percorre todos os itens da lista e passa
 *   cada um deles para o println exibir no console.
 * ============================================================================
 */
