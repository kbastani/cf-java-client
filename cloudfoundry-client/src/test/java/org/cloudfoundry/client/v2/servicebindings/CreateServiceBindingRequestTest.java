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

package org.cloudfoundry.client.v2.servicebindings;

import org.cloudfoundry.client.ValidationResult;
import org.junit.Test;

import static org.cloudfoundry.client.ValidationResult.Status.INVALID;
import static org.cloudfoundry.client.ValidationResult.Status.VALID;
import static org.junit.Assert.assertEquals;


public class CreateServiceBindingRequestTest {

    @Test
    public void isNotValidNoApplicationId() {
        ValidationResult result = CreateServiceBindingRequest.builder()
                .serviceInstanceId("test-service-instance-id")
                .build()
                .isValid();

        assertEquals(INVALID, result.getStatus());
        assertEquals("application id must be specified", result.getMessages().get(0));
    }

    @Test
    public void isNotValidNoApplicationIdNorServiceInstanceId() {
        ValidationResult result = CreateServiceBindingRequest.builder()
                .build()
                .isValid();

        assertEquals(INVALID, result.getStatus());
        assertEquals("application id must be specified", result.getMessages().get(0));
    }

    @Test
    public void isNotValidNoServiceInstanceId() {
        ValidationResult result = CreateServiceBindingRequest.builder()
                .applicationId("app-id")
                .build()
                .isValid();

        assertEquals(INVALID, result.getStatus());
        assertEquals("service instance id must be specified", result.getMessages().get(0));
    }

    @Test
    public void isValid() {
        ValidationResult result = CreateServiceBindingRequest.builder()
                .applicationId("app-id")
                .serviceInstanceId("test-service-instance-id")
                .build()
                .isValid();

        assertEquals(VALID, result.getStatus());
    }

}