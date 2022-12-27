package services;

import data.*;
import publicadministration.*;

import services.exceptions.*;

import java.util.Date;

public interface CertificationAuthority {           // External service that represents the different trusted certification entities
    boolean sendPIN(Nif nif, Date date) throws NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException, ConnectException;
    boolean checkPIN(Nif nif, SmallCode pin) throws NotValidPINException, ConnectException;
}
