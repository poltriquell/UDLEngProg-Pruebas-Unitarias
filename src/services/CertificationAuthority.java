package services;

import data.*;
import services.exceptions.*;

import java.util.Date;

public interface CertificationAuthority {
    boolean sendPIN(Nif nif, Date date) throws NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException, ConnectException;
    boolean checkPIN(Nif nif, SmallCode pin) throws NotValidPINException, ConnectException;
}
