/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.emrapi.visit;

import org.openmrs.Visit;
import org.openmrs.api.VisitService;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.emrapi.encounter.exception.VisitNotFoundException;
import org.openmrs.module.emrapi.visit.contract.VisitRequest;
import org.openmrs.module.emrapi.visit.contract.VisitResponse;

public class EmrVisitServiceImpl extends BaseOpenmrsService implements EmrVisitService {
    private VisitService visitService;
    private VisitResponseMapper visitResponseMapper;

    public EmrVisitServiceImpl(VisitService visitService, VisitResponseMapper visitResponseMapper) {
        this.visitService = visitService;
        this.visitResponseMapper = visitResponseMapper;
    }

    @Override
    public VisitResponse find(VisitRequest visitRequest) {
        Visit visit = visitService.getVisitByUuid(visitRequest.getVisitUuid());
        if(visit == null)
            throw new VisitNotFoundException("Visit by uuid "+ visitRequest.getVisitUuid() + " does not exist");
        return visitResponseMapper.map(visit);
    }


}
