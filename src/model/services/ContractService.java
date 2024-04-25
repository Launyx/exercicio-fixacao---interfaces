package model.services;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
    

    public void processContract(Contract contract, Integer months, OnlinePaymentService onlinePaymentService){
        // O for para gerar as várias parcelas vai na main e não no contractservice
        double value = contract.getTotalValue() / months;   // Pegando o valor básico de cada parcela
        // passar o valor do contraro pelo paypalservice
        
        

        for (int i = 1; i <= months; i++){
            double interest = onlinePaymentService.interest(value, i);
            double fee = onlinePaymentService.paymentFee(value + interest);
            double quota = value + interest + fee;

            Installment installment = new Installment(contract.getDate().plusMonths(i), quota);
            contract.setInstallments(installment);
        }
    }
}
