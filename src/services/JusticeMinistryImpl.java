package services;

import data.DigitalSignature;
import data.Goal;
import publicadministration.Citizen;
import publicadministration.CrimConviction;
import publicadministration.CrimConvictionsColl;
import publicadministration.CriminalRecordCertf;
import publicadministration.exceptions.DigitalSignatureException;
import publicadministration.exceptions.RepeatedCrimConvictionException;
import publicadministration.exceptions.WrongCrimConvictionFormatException;

import java.time.LocalDate;
import java.util.Random;

import java.net.ConnectException;

public class JusticeMinistryImpl implements JusticeMinistry {

    CriminalRecordCertf certf;

    @Override
    public CriminalRecordCertf getCriminalRecordCertf(Citizen persD, Goal g) throws WrongCrimConvictionFormatException, RepeatedCrimConvictionException {
        certf = new CriminalRecordCertf(persD.getNif(), persD.getName(), g, new DigitalSignature(signDigitally()), new CrimConvictionsColl());
        verifyCitizen(persD);
        generatePDF(certf);
        return certf;
    }

    private byte[] signDigitally() {
        return new byte[]{1, 2, 3};
    }

    private void verifyCitizen(Citizen citizen) throws WrongCrimConvictionFormatException, RepeatedCrimConvictionException {
        System.out.println("\033[32mVerificando la existencia de condenas penales...\033[0m");
        //Make a pause to simulate the verification process

        Random rndm = new Random();
        if(rndm.nextInt(0, 1) == 0) {
            certf.getCrimConvs().addCriminalConviction(new CrimConviction(LocalDate.of(2022, 12, 27), "Driving while being drunk", "250€ fine"));
        }

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Citizen "+citizen.getNif() + " verificado.");
    }

    private void generatePDF(CriminalRecordCertf certf) {
        System.out.println("\033[32mGenerando certificado en formato PDF...\033[0m");
        //Make a pause to simulate the generation process
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Certificado en formato PDF generado.");
    }


    private void registerProced(Goal goal){
        System.out.println("\033[32mRegistrando procedimiento para\033[0m " + goal.getDetailedGoal() + "...");
        //Make a pause to simulate the verification process
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Procedure registered.");
    }
}
