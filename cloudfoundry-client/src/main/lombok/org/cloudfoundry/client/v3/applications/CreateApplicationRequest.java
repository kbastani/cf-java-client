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

package org.cloudfoundry.client.v3.applications;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Singular;
import org.cloudfoundry.client.Validatable;
import org.cloudfoundry.client.ValidationResult;

import java.util.HashMap;
import java.util.Map;

/**
 * The request payload for the Create Application operation
 */
@Data
public final class CreateApplicationRequest implements Validatable {

    /**
     * The buildpack
     *
     * @param buildpack the buildpack
     * @return the buildpack
     */
    @Getter(onMethod = @__(@JsonProperty("buildpack")))
    private final String buildpack;

    /**
     * The environment variables
     *
     * @param environmentVariables the environment variables
     * @return the environment variables
     */
    @Getter(onMethod = @__(@JsonProperty("environment_variables")))
    private final Map<String, String> environmentVariables;

    /**
     * The lifecycle
     *
     * @param lifecycle the application lifecycle details
     * @return the lifecycle details
     */
    @Getter(onMethod = @__(@JsonProperty("lifecycle")))
    private final Map<String, Object> lifecycle;

    /**
     * The name
     *
     * @param name the name
     * @return the name
     */
    @Getter(onMethod = @__(@JsonProperty("name")))
    private final String name;


    /**
     * The space id
     *
     * @param spaceId the space id
     * @return the space id
     */
    @Getter(onMethod = @__(@JsonProperty("relationships")))
    private final Map<String, Map<String, String>> relationships;

    @Builder
    CreateApplicationRequest(String buildpack,
                             @Singular Map<String, String> environmentVariables,
                             Map<String, Object> lifecycle,
                             String name,
                             String spaceId) {
        this.buildpack = buildpack;
        this.environmentVariables = environmentVariables;
        this.lifecycle = lifecycle;
        this.name = name;
        this.relationships = new HashMap<>();
        Map<String, String> space = new HashMap<>();
        space.put("guid", spaceId);
        this.relationships.put("space", space);
    }

    @Override
    public ValidationResult isValid() {
        ValidationResult.ValidationResultBuilder builder = ValidationResult.builder();

        if (this.name == null) {
            builder.message("name must be specified");
        }

        if (this.relationships == null) {
            builder.message("relationship space id must be specified");
        } else {
            if(this.relationships.get("space") == null) {
                builder.message("relationship space id must be specified");
            } else {
                if(this.relationships.get("space").get("guid") == null) {
                    builder.message("relationship space id must be specified");
                }
            }
        }

        return builder.build();
    }

}
