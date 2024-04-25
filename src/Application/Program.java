package Application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {
    public static void main(String[] args) {
        
        Locale.setDefault(Locale.US);
        Scanner tec = new Scanner(System.in);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Digite os dados do contrato: ");

        System.out.print("Número: ");
        int num = Integer.parseInt(tec.nextLine());
        System.out.print("Data: ");
        LocalDate date = LocalDate.parse(tec.nextLine(), fmt);
        System.out.print("Valor do contrato: ");
        double contractValue = Double.parseDouble(tec.nextLine());

        System.out.print("Digite o número de parcelas: ");
        int months = Integer.parseInt(tec.nextLine());

        Contract contract = new Contract(num, date, contractValue);

        ContractService service = new ContractService();

        service.processContract(contract, months, new PaypalService());

        System.out.println("Parcelas:");
        for(Installment installment : contract.getInstallments()){
            System.out.println(installment);
        }

        tec.close();
    }
}
