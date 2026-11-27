package com.gabriel.banco;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class ContaBancariaTest {

    @Test
    void deveSomarValorAoDepositar() {
        // Arrange (Preparação)
        ContaBancaria conta = new ContaBancaria(new BigDecimal("100.00"));

        // Act (Ação)
        conta.depositar(new BigDecimal("50.00"));

        // Assert (Verificação)
        assertEquals(new BigDecimal("150.00"), conta.getSaldo());
    }

    @Test
    void deveLancarExcecaoQuandoSaldoForInsuficiente() {
        // Arrange
        ContaBancaria conta = new ContaBancaria(new BigDecimal("100.00"));

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> {
            conta.sacar(new BigDecimal("200.00"));
        });
    }
}

/* ============================================================================
 * DICIONÁRIO DO CÓDIGO (TESTES + MAVEN)
 * ============================================================================
 *
 * - pom.xml (Project Object Model): Arquivo central do Maven que define o nome
 *   do projeto, a versão do Java e baixa bibliotecas externas (dependências).
 *
 * - <scope>test</scope>: Diz ao Maven que o JUnit só existe durante os testes e
 *   não será empacotado junto com o programa final.
 *
 * - @Test: Anotação do JUnit 5 que marca um método como um caso de teste
 *   executável automaticamente pelo Maven.
 *
 * - Padrão AAA (Arrange, Act, Assert): Organização padrão de testes unitários:
 *   prepara o cenário, executa a ação e valida o resultado esperado.
 *
 * - assertEquals(esperado, atual): Verifica se o resultado gerado pelo código
 *   é idêntico ao valor esperado. Se divergir, o teste falha.
 *
 * - assertThrows(ClasseErro.class, () -> { ... }): Valida se o código realmente
 *   dispara a exceção esperada quando recebe uma entrada inválida.
 *
 * - target/ (pasta gerada pelo Maven): Equivalente à pasta 'out' do IntelliJ.
 *   Guarda o código compilado e os relatórios de teste; deve ficar no .gitignore.
 * ============================================================================
 */