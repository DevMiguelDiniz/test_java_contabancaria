package operacoesBanco;

import java.util.Scanner;
import java.util.Random;

public class ContaBancaria {
    private String cpf;
    private int numero;
    private double limite;
    private double saldo;

    // Usado para construir a classe
    public ContaBancaria(int numero, String cpf, double saldo, double limite) {
        this.numero = numero;
        this.cpf = cpf;
        this.saldo = saldo;
        this.limite = limite;
    }

    // Metodo do saque, chama a função que calcula o limite (metade do saldo)
    public void saque(double valor) {
        if (valor <= saldo + limite) {
            saldo -= valor;
            System.out.println("Saque de R$" + valor + " realizado com sucesso. Saldo atual: R$" + saldo);
        } else {
            System.out.println("Saque não permitido. Valor ultrapassa o limite disponível.");
        }
        calculoLimite();
    }

    // Metodo do deposito
    public void deposito(double valor) {
        if (saldo < 0) {
            double taxa = saldo * -0.03;
            saldo += valor - taxa;
            System.out.println("Depósito de R$" + valor + " realizado com sucesso. Taxa de R$" + taxa + " aplicada. Saldo atual: R$" + saldo);
        } else {
            saldo += valor;
            System.out.println("Depósito de R$" + valor + " realizado com sucesso. Saldo atual: R$" + saldo);
        }
        calculoLimite();
    }

    // Metodo do limite
    public void calculoLimite() {
        if (saldo <= 0) {
            limite = 0;
        } else {
            limite = saldo / 2;
        }
    }

    // Métodos de acesso para os atributos
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random aleatorio = new Random();

        int numeroConta = Math.abs(aleatorio.nextInt());

        System.out.println("Digite o CPF (somente números):");
        String cpfConta = sc.next();

        double saldoConta = 0;
        double limiteConta = 0;

        // Cria a conta bancária com saldo e limite iniciais
        ContaBancaria conta = new ContaBancaria(numeroConta, cpfConta, saldoConta, limiteConta);

        // Exibe as operações disponíveis para o usuário
        while (true) {
            System.out.println("\nEscolha uma operação:");
            System.out.println("1. Saque");
            System.out.println("2. Depósito");
            System.out.println("3. Ver Saldo e Limite");
            System.out.println("4. Sair");
            System.out.print("Opção: ");
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o valor para saque: R$");
                    double valorSaque = sc.nextDouble();
                    conta.saque(valorSaque);
                    break;

                case 2:
                    System.out.print("Digite o valor para depósito: R$");
                    double valorDeposito = sc.nextDouble();
                    conta.deposito(valorDeposito);
                    break;

                case 3:
                    System.out.println("Saldo atual: R$" + conta.getSaldo());
                    System.out.println("Limite atual: R$" + conta.getLimite());
                    break;

                case 4:
                    System.out.println("Encerrando o programa.");
                    sc.close();
                    return;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }
    }
}
