/*
 * Copyright 2013-2016 the original author or authors.
 *
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
 */

package org.cloudfoundry.client.v2.spaces;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.cloudfoundry.client.Validatable;
import org.cloudfoundry.client.ValidationResult;

/**
 * The request payload for the Remove Auditor from the Space operation
 */
@Data
public final class RemoveSpaceAuditorRequest implements Validatable {

    /**
     * The auditor id
     *
     * @param auditorId the auditor id
     * @return the auditor id
     */
    @Getter(onMethod = @__(@JsonIgnore))
    private final String auditorId;

    /**
     * The id
     *
     * @param id the id
     * @return the id
     */
    @Getter(onMethod = @__(@JsonIgnore))
    private final String id;

    @Builder
    RemoveSpaceAuditorRequest(String auditorId, String id) {
        this.auditorId = auditorId;
        this.id = id;
    }

    @Override
    public ValidationResult isValid() {
        ValidationResult.ValidationResultBuilder builder = ValidationResult.builder();

        if (this.auditorId == null) {
            builder.message("auditor id must be specified");
        }

        if (this.id == null) {
            builder.message("id must be specified");
        }

        return builder.build();
    }

}
