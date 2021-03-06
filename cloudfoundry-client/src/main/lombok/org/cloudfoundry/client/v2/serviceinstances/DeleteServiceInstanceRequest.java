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

package org.cloudfoundry.client.v2.serviceinstances;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import org.cloudfoundry.client.QueryParameter;
import org.cloudfoundry.client.Validatable;
import org.cloudfoundry.client.ValidationResult;

public final class DeleteServiceInstanceRequest implements Validatable {

    /**
     * The accept incomplete flag
     *
     * @param acceptsIncomplete (experimental) Set to `true` if the client allows asynchronous provisioning. The cloud controller may respond before the service is deleted.
     * @return the accept incomplete flag
     */
    @Getter(onMethod = @__(@QueryParameter("accepts_incomplete")))
    private final boolean acceptsIncomplete;

    /**
     * The id
     *
     * @param id the id
     * @return the id
     */
    @Getter(onMethod = @__(@JsonIgnore))
    private final String id;

    /**
     * The purge flag
     *
     * @param purge (experimental) Recursively remove a service instance and child objects from Cloud Foundry database without making requests to a service broker .
     * @return the purge flag
     */
    @Getter(onMethod = @__(@QueryParameter("purge")))
    private final boolean purge;

    @Builder
    DeleteServiceInstanceRequest(boolean acceptsIncomplete,
                                 String id,
                                 boolean purge) {
        this.acceptsIncomplete = acceptsIncomplete;
        this.id = id;
        this.purge = purge;
    }

    @Override
    public ValidationResult isValid() {
        ValidationResult.ValidationResultBuilder builder = ValidationResult.builder();

        if (this.id == null) {
            builder.message("id must be specified");
        }

        return builder.build();
    }

}
