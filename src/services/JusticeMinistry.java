package services;

import data.*;
import publicadministration.*;
import publicadministration.exceptions.DigitalSignatureException;

import java.net.ConnectException;

public interface JusticeMinistry {      // External service for the Justice Ministry
    CriminalRecordCertf getCriminalRecordCertf(Citizen persD, Goal g) throws DigitalSignatureException, ConnectException;
}
