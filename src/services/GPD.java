package services;

import data.Goal;
import publicadministration.*;
import services.exceptions.*;

public interface GPD {    // External service that represents the  General Police Direction
    boolean verifyData(Citizen persData, Goal goal) throws IncorrectVerificationException, ConnectException;
}
