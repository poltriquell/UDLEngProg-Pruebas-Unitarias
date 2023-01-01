package services;

import data.DigitalSignature;
import data.Goal;
import publicadministration.Citizen;
import publicadministration.CrimConvictionsColl;
import publicadministration.CriminalRecordCertf;

public class JusticeMinistryImpl implements JusticeMinistry {

    @Override
    public CriminalRecordCertf getCriminalRecordCertf(Citizen citizen, Goal g){
        return new CriminalRecordCertf(citizen.getNif(), citizen.getName(), g, new DigitalSignature(signDigitally()), new CrimConvictionsColl());
    }

    private byte[] signDigitally() {
        return new byte[]{1, 2, 3};
    }
}
