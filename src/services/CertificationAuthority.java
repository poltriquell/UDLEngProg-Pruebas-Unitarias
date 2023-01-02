package services;

import data.Nif;
import data.Password;
import data.SmallCode;
import publicadministration.Citizen;
import services.exceptions.*;

import java.time.LocalDate;

public class CertificationAuthority implements CertificationAuthorityInterface{
    Citizen citizen;

    public CertificationAuthority(Citizen citizen) {
        this.citizen = citizen;
    }

    @Override
    public boolean sendPIN(Nif nif, LocalDate date) throws NifNotRegisteredException, IncorrectValDateException, AnyMobileRegisteredException {
        if (!nif.equals(citizen.getNif())) {
            throw new NifNotRegisteredException("El NIF no está registrado.");
        }
        if (!date.equals(citizen.getValDate())) {
            throw new IncorrectValDateException("La fecha de validez no es correcta.");
        }
        if (citizen.getMobileNumb() == null) {
            throw new AnyMobileRegisteredException("No hay ningún número de móvil registrado.");
        }
        return true;
    }

    @Override
    public boolean checkPIN(Nif nif, SmallCode pin) throws NotValidPINException{
        if (!pin.equals(citizen.getPIN())) {
            throw new NotValidPINException("El PIN introducido no es correcto.");
        }

        return true;
    }

    @Override
    public byte checkCredent(Nif nif, Password passw) throws NifNotRegisteredException, NotValidCredException, AnyMobileRegisteredException {
        if (!nif.equals(citizen.getNif())) {
            throw new NifNotRegisteredException("El NIF no está registrado.");
        }
        if (!passw.equals(passw.getPassword())) {
            throw new NotValidCredException("La contraseña no es correcta.");
        }
        if (citizen.getMobileNumb() == null) {
            throw new AnyMobileRegisteredException("No hay ningún número de móvil registrado.");
        }
        if (citizen.hasDoubleFactorPINActivated()) {
            return 2;
        }
        return 1;
    }
}
