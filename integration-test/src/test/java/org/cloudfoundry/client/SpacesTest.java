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

package org.cloudfoundry.client;

import org.cloudfoundry.AbstractIntegrationTest;
import org.cloudfoundry.client.v2.organizations.AssociateOrganizationUserRequest;
import org.cloudfoundry.client.v2.spaces.AssociateSpaceAuditorByUsernameRequest;
import org.cloudfoundry.client.v2.spaces.AssociateSpaceAuditorRequest;
import org.cloudfoundry.client.v2.spaces.AssociateSpaceDeveloperByUsernameRequest;
import org.cloudfoundry.client.v2.spaces.AssociateSpaceDeveloperRequest;
import org.cloudfoundry.client.v2.spaces.AssociateSpaceManagerByUsernameRequest;
import org.cloudfoundry.client.v2.spaces.AssociateSpaceManagerRequest;
import org.cloudfoundry.client.v2.spaces.CreateSpaceRequest;
import org.cloudfoundry.client.v2.spaces.GetSpaceRequest;
import org.cloudfoundry.client.v2.users.ListUsersRequest;
import org.cloudfoundry.operations.util.v2.Paginated;
import org.cloudfoundry.operations.util.v2.Resources;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import reactor.core.publisher.Mono;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public final class SpacesTest extends AbstractIntegrationTest {

    public static final String TEST_NEW_SPACE_NAME = "test-new-space-name";

    private String spaceTestUsername;
    
    @Before
    public void setUserName() throws Exception {
        this.spaceTestUsername = this.testUsername; // TODO: avoid testUsername when we can create a new user
    }

    @Test
    public void associateAuditor() {
        this.organizationId
                .then(orgId -> Mono.when(
                        createSpaceId(this.cloudFoundryClient, orgId, TEST_NEW_SPACE_NAME),
                        getUserId(this.cloudFoundryClient, orgId, this.spaceTestUsername))
                )
                .then(tuple -> {
                    String spaceId = tuple.t1;
                    String userId = tuple.t2;

                    AssociateSpaceAuditorRequest request = AssociateSpaceAuditorRequest.builder()
                            .id(spaceId)
                            .auditorId(userId)
                            .build();

                    return this.cloudFoundryClient.spaces().associateAuditor(request);
                })
                .subscribe(this.testSubscriber()
                        .assertCount(1));
    }

    @Test
    public void associateAuditorByUsername() {
        this.organizationId
                .then(orgId ->
                        createSpaceId(this.cloudFoundryClient, orgId, TEST_NEW_SPACE_NAME)
                )
                .then(spaceId -> {
                    AssociateSpaceAuditorByUsernameRequest request = AssociateSpaceAuditorByUsernameRequest.builder()
                            .id(spaceId)
                            .username(this.spaceTestUsername)
                            .build();

                    return this.cloudFoundryClient.spaces().associateAuditorByUsername(request);
                })
                .subscribe(this.testSubscriber()
                        .assertCount(1));
    }

    @Test
    public void associateDeveloper() {
        this.organizationId
                .then(orgId -> Mono.when(
                        createSpaceId(this.cloudFoundryClient, orgId, TEST_NEW_SPACE_NAME),
                        getUserId(this.cloudFoundryClient, orgId, this.spaceTestUsername))
                )
                .then(tuple -> {
                    String spaceId = tuple.t1;
                    String userId = tuple.t2;

                    AssociateSpaceDeveloperRequest request = AssociateSpaceDeveloperRequest.builder()
                            .id(spaceId)
                            .developerId(userId)
                            .build();

                    return this.cloudFoundryClient.spaces().associateDeveloper(request);
                })
                .subscribe(this.testSubscriber()
                        .assertCount(1));
    }

    @Test
    public void associateDeveloperByUsername() {
        this.organizationId
                .then(orgId ->
                        createSpaceId(this.cloudFoundryClient, orgId, TEST_NEW_SPACE_NAME)
                )
                .then(spaceId -> {
                    AssociateSpaceDeveloperByUsernameRequest request = AssociateSpaceDeveloperByUsernameRequest.builder()
                            .id(spaceId)
                            .username(this.spaceTestUsername)
                            .build();

                    return this.cloudFoundryClient.spaces().associateDeveloperByUsername(request);
                })
                .subscribe(this.testSubscriber()
                        .assertCount(1));
    }

    @Test
    public void associateManager() {
        this.organizationId
                .then(orgId -> Mono.when(
                        createSpaceId(this.cloudFoundryClient, orgId, TEST_NEW_SPACE_NAME),
                        getUserId(this.cloudFoundryClient, orgId, this.spaceTestUsername))
                )
                .then(tuple -> {
                    String spaceId = tuple.t1;
                    String userId = tuple.t2;

                    AssociateSpaceManagerRequest request = AssociateSpaceManagerRequest.builder()
                            .id(spaceId)
                            .managerId(userId)
                            .build();

                    return this.cloudFoundryClient.spaces().associateManager(request);
                })
                .subscribe(this.testSubscriber()
                        .assertCount(1));
    }

    @Test
    public void associateManagerByUsername() {
        this.organizationId
                .then(orgId ->
                        createSpaceId(this.cloudFoundryClient, orgId, TEST_NEW_SPACE_NAME)
                )
                .then(spaceId -> {
                    AssociateSpaceManagerByUsernameRequest request = AssociateSpaceManagerByUsernameRequest.builder()
                            .id(spaceId)
                            .username(testUsername)
                            .build();

                    return this.cloudFoundryClient.spaces().associateManagerByUsername(request);
                })
                .subscribe(this.testSubscriber()
                        .assertCount(1));
    }

    // TODO: Implement missing client API
    @Ignore("TODO: awaiting https://www.pivotaltracker.com/story/show/101522656")
    @Test
    public void associateSecurityGroup() {
        fail("TODO: Implement client API create security group");
    }

    @Test
    public void create() {
        this.organizationId
                .then(orgId ->
                        createSpaceId(this.cloudFoundryClient, orgId, TEST_NEW_SPACE_NAME)
                )
                .subscribe(this.testSubscriber()
                        .assertCount(1));
    }

    @Test
    public void get() {
        this.organizationId
                .then(orgId ->
                        createSpaceId(this.cloudFoundryClient, orgId, TEST_NEW_SPACE_NAME)
                )
                .then(spaceId -> {
                    GetSpaceRequest request = GetSpaceRequest.builder()
                            .id(spaceId)
                            .build();

                    return this.cloudFoundryClient.spaces().get(request)
                            .map(response -> Resources.getEntity(response).getName());
                })
                .subscribe(this.<String>testSubscriber()
                        .assertThat(spaceName -> assertEquals(TEST_NEW_SPACE_NAME, spaceName)));
    }

    // TODO: Implement missing client API
    @Ignore("TODO: awaiting https://www.pivotaltracker.com/story/show/101522656")
    @Test
    public void listSecurityGroups() {
        fail("TODO: Implement client API create security group");
    }

    // TODO: Implement missing client API
    @Ignore("TODO: awaiting https://www.pivotaltracker.com/story/show/101522658")
    @Test
    public void removeSecurityGroup() {
        fail("TODO: Implement client API delete security group");
    }

    private static Mono<String> createSpaceId(CloudFoundryClient cloudFoundryClient, String orgId, String spaceName) {
        CreateSpaceRequest request = CreateSpaceRequest.builder()
                .organizationId(orgId)
                .name(spaceName)
                .build();

        return cloudFoundryClient.spaces().create(request)
                .map(Resources::getId);
    }

    private static Mono<String> getUserId(CloudFoundryClient cloudFoundryClient, String orgId, String username) {
        return findUserId(cloudFoundryClient, username)
                .then(userId -> {
                    AssociateOrganizationUserRequest request = AssociateOrganizationUserRequest.builder()
                            .id(orgId)
                            .userId(userId)
                            .build();

                    return cloudFoundryClient.organizations().associateUser(request)
                            .map(ignoreResponse -> userId);
                });
    }

    private static Mono<String> findUserId(CloudFoundryClient cloudFoundryClient, String username) {
        return Paginated.requestResources(
                page -> {
                    ListUsersRequest request = ListUsersRequest.builder()
                            .page(page)
                            .build();
                    return cloudFoundryClient.users().listUsers(request);
                })
                .filter(resource -> Resources.getEntity(resource).getUsername().equals(username))
                .single()
                .map(Resources::getId);
    }

}
