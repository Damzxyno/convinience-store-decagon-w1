package operations.interfaces;

import exceptions.InvalidOperationException;
import models.Applicant;
import models.Company;

public interface ApplicantOperation{
    void apply(Company company, Applicant applicant) throws InvalidOperationException;
}
