package ca.uhn.fhir.rest.server;

/*
 * #%L
 * HAPI FHIR - Core Library
 * %%
 * Copyright (C) 2014 - 2015 University Health Network
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.util.ResourceReferenceInfo;

import java.util.Set;

/**
 * Used to set server bahaviour for adding included resources when responding to search operations. 
 * 
 * @author Created by Bill de Beaubien on 3/4/2015.
 * @see RestfulServer#setBundleInclusionRule(BundleInclusionRule)
 */
public enum BundleInclusionRule {
    /**
     * Decision is based on whether the resource's Include is in the IncludeSet (e.g. DiagnosticReport.result). Note that
     * the resource has to be populated to be included.
     */
    BASED_ON_INCLUDES {
        @Override
        public boolean shouldIncludeReferencedResource(ResourceReferenceInfo theReferenceInfo, Set<Include> theIncludes) {
            return theReferenceInfo.matchesIncludeSet(theIncludes);
        }
    },

    /**
     * Decision is based on whether the resource reference is set to a populated resource (in which case its included) or just
     * an id (in which case it's not included)
     */
    BASED_ON_RESOURCE_PRESENCE {
        @Override
        public boolean shouldIncludeReferencedResource(ResourceReferenceInfo theReferenceInfo, Set<Include> theIncludes) {
            return true;
        }
    };

    public abstract boolean shouldIncludeReferencedResource(ResourceReferenceInfo theReferenceInfo, Set<Include> theIncludes);
}
