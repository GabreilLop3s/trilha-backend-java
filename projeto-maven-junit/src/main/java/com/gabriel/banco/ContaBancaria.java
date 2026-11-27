package com.gabriel.banco;

import java.math.BigDecimal;

public class ContaBancaria {
    private BigDecimal saldo;

    public ContaBancaria(BigDecimal saldoInicial) {
        if (saldoInicial.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Saldo inicial não pode ser negativo.");
        }
        this.saldo = saldoInicial;
    }

    public void depositar(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor de depósito deve ser positivo.");
        }
        this.saldo = this.saldo.add(valor);
    }

    public void sacar(BigDecimal valor) {
        if (valor.compareTo(this.saldo) > 0) {
            throw new IllegalStateException("Saldo insuficiente para saque.");
        }
        this.saldo = this.saldo.subtract(valor);
    }

    public BigDecimal getSaldo() {
        return saldo;
    }
}

/* ============================================================================
 * DICIONÁRIO DO CÓDIGO (PRODUÇÃO)
 * ============================================================================
 *
 * - package com.gabriel.banco: Declara o pacote lógico da classe. Deve espelhar
 *   exatamente o caminho das pastas após src/main/java.
 *
 * - BigDecimal.ZERO: Constante pronta do Java para representar o valor 0 exato.
 *
 * - compareTo(...) < 0 / <= 0 / > 0: Forma correta de comparar dois BigDecimals:
 *   retorna negativo se for menor, 0 se for igual, e positivo se for maior.
 *
 * - IllegalArgumentException / IllegalStateException: Exceções padrão do Java
 *   (Unchecked) usadas para barrar regras de negócio inválidas sem precisar
 *   declarar 'throws' na assinatura do método.
 * ============================================================================
 */