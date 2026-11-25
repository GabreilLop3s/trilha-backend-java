package exercicios;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PrecisaoDecimal {
    public static void main(String[] args) {
        // 1. Soma usando double (ponto flutuante binário - impreciso para dinheiro)
        double d1 = 0.1;
        double d2 = 0.2;
        double somaDouble = d1 + d2;
        System.out.println("Com double (0.1 + 0.2)     = " + somaDouble);

        // 2. Soma usando BigDecimal criado com String (cálculo decimal exato)
        BigDecimal b1 = new BigDecimal("0.1");
        BigDecimal b2 = new BigDecimal("0.2");
        BigDecimal somaExata = b1.add(b2);
        System.out.println("Com BigDecimal (String)    = " + somaExata);

        // 3. Armadilha clássica: passar double direto no construtor do BigDecimal
        BigDecimal armadilha = new BigDecimal(0.1).add(new BigDecimal(0.2));
        System.out.println("BigDecimal(double) [ERRO]  = " + armadilha);

        // 4. Exemplo financeiro: divisão com arredondamento explícito
        BigDecimal parcela = new BigDecimal("100.00")
                .divide(new BigDecimal("3"), 2, RoundingMode.HALF_EVEN);
        System.out.println("100.00 / 3 arredondado     = R$ " + parcela);
    }
}

/* ============================================================================
 * DICIONÁRIO DO CÓDIGO
 * ============================================================================
 *
 * - double: Tipo primitivo de ponto flutuante binário (64 bits, padrão IEEE 754).
 *   Muito rápido para cálculos científicos e gráficos, mas sofre imprecisão em
 *   frações decimais como 0.1 e 0.2.
 *
 * - BigDecimal: Classe do pacote java.math para aritmética decimal de precisão
 *   arbitrária (exata). É o padrão obrigatório para valores monetários, taxas
 *   e impostos no Java. (Equivalente no Delphi: Currency / TBcd)
 *
 * - new BigDecimal("0.1"): Construtor recomendado. Recebe o número em texto
 *   (String) para guardar os dígitos decimais exatos, sem conversão binária.
 *
 * - .add(b2): Método de soma do BigDecimal. Como BigDecimal é um objeto (e não
 *   tipo primitivo), não aceita operadores como +, -, * ou /.
 *
 * - .divide(divisor, escala, modo): Divide dois BigDecimals exigindo quantas
 *   casas decimais manter (escala = 2) e qual regra de arredondamento aplicar,
 *   evitando erro em dízimas periódicas (como 100 / 3).
 *
 * - RoundingMode.HALF_EVEN: Arredondamento bancário (arredonda para o vizinho
 *   mais próximo; em caso de empate exato no .5, arredonda para o número par).
 * ============================================================================
 */