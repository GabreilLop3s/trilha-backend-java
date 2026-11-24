package exercicios;

    class DescontoInvalidoException extends Exception {
        public DescontoInvalidoException(String mensagem) {
            super(mensagem); // 'super' envia o texto do erro para a classe mãe (Exception)
        }
    }

    // 2. Classe mãe com os dados básicos
    class Funcionario {
        protected String nome;
        protected double salarioBase;

        public Funcionario(String nome, double salarioBase) {
            this.nome = nome;
            this.salarioBase = salarioBase;
        }
    }

    // 3. Classe filha: herda 'nome' e 'salarioBase' de Funcionario
    class FuncionarioCLT extends Funcionario {

        public FuncionarioCLT(String nome, double salarioBase) {
            super(nome, salarioBase); // Chama o construtor de Funcionario
        }

        // 'throws' avisa que este método pode disparar DescontoInvalidoException
        public double calcularLiquido(double totalDescontos) throws DescontoInvalidoException {
            if (totalDescontos > this.salarioBase) {
                // 'throw' para tudo aqui e dispara o erro (igual ao 'raise' no Pascal)
                throw new DescontoInvalidoException(
                        "Regra violada: descontos (R$ " + totalDescontos + ") superam o salário base."
                );
            }
            return this.salarioBase - totalDescontos;
        }
    }

    // 4. Executando e tratando o erro com 'try' e 'catch'
    public class FolhaPagamento {
        public static void main(String[] args) {
            FuncionarioCLT colaborador = new FuncionarioCLT("Gabriel", 3000.0);

            try {
                // Tenta rodar o cálculo
                double liquido = colaborador.calcularLiquido(3500.0);
                System.out.println("Salário líquido: R$ " + liquido);
            } catch (DescontoInvalidoException erro) {
                // Captura o erro lançado pelo 'throw' e lê a mensagem salva pelo 'super'
                System.out.println("Falha ao processar folha -> " + erro.getMessage());
            }
        }
    }

    /* ============================================================================
     * DICIONÁRIO DO CÓDIGO
     * ============================================================================
     *
     * - class: Define a estrutura de uma classe (molde para criar objetos).
     *   (Equivalente no Delphi: TMinhaClasse = class)
     *
     * - extends: Aplica herança. Faz a classe filha herdar atributos e métodos da
     *   classe mãe (FuncionarioCLT herda de Funcionario).
     *   (Equivalente no Delphi: TFuncionarioCLT = class(TFuncionario))
     *
     * - Exception: Classe nativa do Java para exceções verificadas (Checked
     *   Exceptions). Ao herdá-la, o compilador obriga quem chama o método a tratar
     *   o erro. (Equivalente no Delphi: Exception da unit SysUtils)
     *
     * - super(...): Aciona o construtor da classe mãe imediata para inicializar os
     *   dados herdados (nome, salário ou mensagem de erro).
     *   (Equivalente no Delphi: inherited Create(...))
     *
     * - protected: Modificador de acesso. Libera a variável para a própria classe,
     *   classes filhas e classes do mesmo pacote, bloqueando acesso externo.
     *   (Equivalente no Delphi: protected)
     *
     * - this: Referência à própria instância atual do objeto. Diferencia o atributo
     *   da classe (this.salarioBase) de parâmetros ou variáveis locais.
     *   (Equivalente no Delphi: Self)
     *
     * - throws (com "s"): Declarado na assinatura do método. Avisa que o método
     *   pode disparar aquele erro e repassa a obrigação de tratá-lo para quem o
     *   chamar. (Não existe declaração obrigatória equivalente em Pascal)
     *
     * - throw new (sem "s"): Usado dentro do código para instanciar e disparar a
     *   exceção naquele exato ponto, interrompendo a execução normal do método.
     *   (Equivalente no Delphi: raise EDescontoInvalido.Create(...))
     *
     * - try { ... }: Delimita o bloco de código monitorado que pode gerar uma
     *   falha durante a execução. (Equivalente no Delphi: try)
     *
     * - catch (Tipo erro): Captura o erro disparado pelo 'throw' caso corresponda
     *   ao tipo declarado, evitando que o programa feche abruptamente.
     *   (Equivalente no Delphi: except on E: EDescontoInvalido do)
     *
     * - erro.getMessage(): Método herdado de Exception que extrai o texto
     *   explicativo gravado anteriormente pelo super(mensagem).
     *   (Equivalente no Delphi: E.Message)
     *
     * - public static void main: Ponto de entrada obrigatório onde a JVM inicia a
     *   execução do programa. (Equivalente no Delphi: bloco begin...end do .dpr)
     * ============================================================================
     */
}
