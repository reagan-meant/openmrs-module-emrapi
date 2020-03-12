import java.util.ArrayList;
import java.util.List;

import org.openmrs.Visit;
import org.openmrs.VisitType;
import org.openmrs.module.emrapi.visit.VisitDomainWrapper2_2;

import org.openmrs.module.emrapi.domainwrapper.DomainWrapper;

/**
 * class implementing the DiagnosisService while delegating calls to the core module
 * */
public class PatientDomainWrapper2_2 extends PatientDomainWrapper implements DomainWrapper {
    
    public List<VisitDomainWrapper2_2> getVisitsByTypeUsingWrappers2_2(VisitType visitType) {
		List<VisitDomainWrapper2_2> visitDomainWrappers = new ArrayList<VisitDomainWrapper2_2>();

		for (Visit visit : (visitType != null ) ? getVisitsByType(visitType) : getAllVisits()) {
			VisitDomainWrapper2_2 visitWrapper = (VisitDomainWrapper2_2) domainWrapperFactory.newVisitDomainWrapper(visit);
			visitWrapper.setEmrApiProperties(emrApiProperties);
			visitDomainWrappers.add(visitWrapper);
		}

		return visitDomainWrappers;
    }
    
    public List<VisitDomainWrapper2_2> getAllVisitsUsingWrappers2_2() {
		return getVisitsByTypeUsingWrappers2_2(null);
	}
    
}