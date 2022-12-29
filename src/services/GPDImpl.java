package services;

import data.Goal;
import publicadministration.Citizen;
import services.exceptions.ConnectException;
import services.exceptions.IncorrectVerificationException;

public class GPDImpl implements GPD {
    @Override
    public boolean verifyData(Citizen persData, Goal goal) throws IncorrectVerificationException, ConnectException {
        System.out.println("Se ha verificado la identidad del ciudadano " + persData.getName() + " con el objetivo " + goal.getGoalType() + " con éxito.");
        return true;
    }
}
