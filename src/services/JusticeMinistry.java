package services;

import controller.Citizen;
import data.*;
import publicadministration.exceptions.DigitalSignatureException;

import java.net.ConnectException;

public interface JusticeMinistry {
    CriminalRecordCertf getCriminalRecordCertf(Citizen citizen, Goal g) throws DigitalSignatureException, ConnectException;
}
