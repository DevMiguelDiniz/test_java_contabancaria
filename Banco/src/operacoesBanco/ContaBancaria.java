package operacoesBanco;

import java.util.Scanner;  // Importa a classe Scanner para leitura de dados
import java.util.Random;   // Importa a classe Random para gerar números aleatórios
import java.lang.Math;     // Importa a classe Math para realizar operações matemáticas

// Classe que representa uma conta bancária
public class ContaBancaria {
    // Atributos privados da conta
    private String cpf;    // CPF do titular da conta
    private int numero;    // Número da conta
    private double limite; // Limite de crédito da conta
    private double saldo;  // Saldo disponível na conta
    public Scanner sc = new Scanner(System.in);  // Scanner para leitura de dados (interação com o usuário)

    // Construtor que inicializa a conta bancária
    public ContaBancaria(int numero, String cpf, double saldo, double limite) {
        this.numero = numero;  // Número da conta
        this.cpf = cpf;        // CPF do titular
        this.saldo = saldo;    // Saldo inicial
        this.limite = limite;  // Limite inicial
    }

    // Método para realizar saque
    public void saque(double valor) {
        // Verifica se o valor do saque é menor ou igual ao saldo + limite disponível
        if (valor <= this.saldo + this.limite) {
            saldo -= valor;  // Deduz o valor do saque do saldo
            System.out.println("Saque de R$" + valor + " realizado com sucesso. Saldo atual: R$" + saldo);
        } else {
            // Caso o saque seja maior que o saldo + limite, exibe mensagem de erro
            System.out.println("Saque não permitido. Valor ultrapassa o limite disponível.");
        }
        // Recalcula o limite após o saque (lógica pode estar incorreta e precisa de ajustes)
        limite = calculoLimite() + (valor - this.saldo);
    }

    // Método para realizar depósito
    public void deposito(double valor) {
        // Se o saldo for negativo, aplica uma taxa sobre o saldo
        if (saldo < 0) {
            saldo += valor - descontarTaxa();  // Adiciona o valor do depósito e desconta a taxa
            System.out.println("Deposito de R$ " + valor + " realizado com sucesso. Taxa de R$ " + descontarTaxa() + " aplicada. Saldo atual = R$" + saldo);
        } else {
            saldo += valor;  // Se o saldo for positivo, adiciona o valor normalmente
            System.out.println("Deposito de R$ " + valor + " realizado com sucesso. Saldo atual = R$" + saldo);
        }
        // Recalcula o limite após o depósito
        limite = calculoLimite();
    }

    // Método privado para calcular a taxa de desconto (3% do saldo negativo)
    private double descontarTaxa() {
        return Math.abs(this.saldo * 0.03);  // Calcula 3% do saldo negativo
    }

    // Métodos "getters" para acessar os atributos privados
    public double getSaldo() {
        return saldo;
    }

    public double getLimite() {
        return limite;
    }

    public int getNumero() {
        return numero;
    }

    public String getCpf() {
        return cpf;
    }

    // Método estático sem uso (pode ser removido ou implementado)
    public static void getLimite(double limite) {
        // Método vazio
    }

    // Método para calcular o limite com base no saldo
    public double calculoLimite() {
        // Se o saldo for negativo ou zero, o limite é zero
        if (saldo <= 0) {
            limite = 0;
        } else {
            limite = saldo / 2;  // Limite é metade do saldo se for positivo
        }
        return limite;  // Retorna o novo limite calculado
    }

    // Método principal que inicia o programa (função main)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);   // Cria um objeto Scanner para entrada de dados
        Random aleatorio = new Random(12345);  // Inicializa um gerador de números aleatórios com seed fixa

        // Gera um número de conta aleatório
        int numeroConta = aleatorio.nextInt();
        sc.nextLine();  // Captura a quebra de linha após a leitura do número

        // Exibe mensagem de boas-vindas e solicita o CPF do usuário
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Sistema Bancario");
        System.out.println("Seja bem vindo!");
        System.out.println("Digite o cpf (escreva somente os números):");
        String cpfConta = sc.next();  // Lê o CPF digitado
        System.out.println("----------------------------------------------------------------------------------------");

        double saldoConta = 0;   // Inicializa o saldo da conta com 0
        double limiteConta = 0;  // Inicializa o limite da conta com 0

        // Cria um objeto da classe ContaBancaria com os dados fornecidos
        ContaBancaria conta = new ContaBancaria(numeroConta, cpfConta, saldoConta, limiteConta);

        // Loop para exibir o menu de operações até o usuário decidir sair
        while (true) {
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("\nEscolha uma operação:");
            System.out.println("1. Saque");
            System.out.println("2. Depósito");
            System.out.println("3. Ver Saldo e Limite");
            System.out.println("4. Sair");
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.print("Opção: ");

            int opcao = sc.nextInt();  // Lê a opção escolhida pelo usuário
            System.out.println("----------------------------------------------------------------------------------------");

            // Switch para tratar as opções do menu
            switch (opcao) {
                case 1:
                    // Realiza saque
                    System.out.print("Digite o valor para saque: R$");
                    double valorSaque = sc.nextDouble();
                    conta.saque(valorSaque);
                    break;

                case 2:
                    // Realiza depósito
                    System.out.print("Digite o valor para depósito: R$");
                    double valorDeposito = sc.nextDouble();
                    conta.deposito(valorDeposito);
                    break;

                case 3:
                    // Exibe saldo e limite
                    System.out.println("Saldo atual: R$" + conta.getSaldo());
                    System.out.println("Limite atual: R$" + conta.getLimite());
                    break;

                case 4:
                    // Encerra o programa
                    System.out.println("Encerrando o programa.");
                    sc.close();  // Fecha o Scanner
                    return;

                default:
                    // Trata opções inválidas
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }
    }
}
