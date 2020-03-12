import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.emrapi.diagnosis.Diagnosis;
import org.openmrs.module.emrapi.diagnosis.DiagnosisServiceImpl2_2;
import org.openmrs.module.emrapi.domainwrapper.DomainWrapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Wrapper around a Visit, that provides convenience methods to find particular
 * encounters of interest.
 */
public class VisitDomainWrapper2_2 extends VisitDomainWrapper implements DomainWrapper {

    @Autowired
    private DiagnosisServiceImpl2_2 diagnosisServiceImpl2_2;

    public List<Diagnosis> getAllUniqueDiagnoses(Patient patient,Boolean primaryOnly, Boolean confirmedOnly) {
        List<Diagnosis> allDiagnoses = new ArrayList<Diagnosis>();

        for (Diagnosis diagnosis : super.getUniqueDiagnoses(primaryOnly, confirmedOnly)) {
            allDiagnoses.add(diagnosis);
        }

        for (Diagnosis diagnosis : diagnosisServiceImpl2_2.getNewUniqueDiagnoses(patient, new Date())) {
            allDiagnoses.add(diagnosis);
        }
        return allDiagnoses;
    }
}


