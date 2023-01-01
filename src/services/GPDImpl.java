package services;

import data.Goal;
import publicadministration.Citizen;
import services.exceptions.ConnectException;
import services.exceptions.IncorrectVerificationException;

public class GPDImpl implements GPD {
    @Override
    public boolean verifyData(Citizen persData, Goal goal) throws IncorrectVerificationException, ConnectException {
        verifyCitizen(persData);
        registerProced(goal);
        return true;
    }

    private void verifyCitizen(Citizen persData){
        System.out.println("\033[32mVerificando la información del ciudadano...\033[0m");
        //Make a pause to simulate the verification process
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Citizen "+persData.getName() + " verificado.");
    }

    private void registerProced(Goal goal){
        System.out.println("\033[32mRegistrando procedimiento para\033[0m " + goal.getDetailedGoal() + "...");
        //Make a pause to simulate the verification process
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Selección de procedimiento realizado.");
    }
}
