package services;

import data.Goal;
import publicadministration.Citizen;
import publicadministration.DigitalSignatureException;

/**
 * External services involved in procedures from population
 */
public interface JusticeMinistry { // External service for the Justice Ministry
    CriminalRecordCertf getCriminalRecordCertf (Citizen persD, Goal g)
            throws DigitalSignatureException,ConnectException;
}

