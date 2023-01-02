package services;

import data.*;
import publicadministration.*;
import publicadministration.exceptions.DigitalSignatureException;
import publicadministration.exceptions.RepeatedCrimConvictionException;
import publicadministration.exceptions.WrongCrimConvictionFormatException;

import java.net.ConnectException;

public interface JusticeMinistry {      // External service for the Justice Ministry
    CriminalRecordCertf getCriminalRecordCertf(Citizen persD, Goal g) throws DigitalSignatureException, ConnectException, WrongCrimConvictionFormatException, RepeatedCrimConvictionException;
}
