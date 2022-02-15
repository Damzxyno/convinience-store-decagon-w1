package operations.implementations;

import exceptions.InvalidOperationException;
import models.Applicant;
import models.Company;
import operations.interfaces.ApplicantOperation;

public class ApplicantOperationImpl implements ApplicantOperation {
    public void apply(Company company, Applicant applicant) throws InvalidOperationException {
        if (!company.getApplicantList().contains(applicant)){
            company.getApplicantList().add(applicant);
        } else throw new InvalidOperationException ("You already applied!");
    }
}
