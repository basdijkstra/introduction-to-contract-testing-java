package order;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.LambdaDsl;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;
import java.util.UUID;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "address-provider", pactVersion = PactSpecVersion.V3)
public class AddressServiceGetContractTest {

    @Pact(provider = "address-provider", consumer = "order-consumer")
    public RequestResponsePact pactForGetExistingAddressId(PactDslWithProvider builder) {

        /**
         * TODO: Add two fields to the expected response:
         *   - one field 'state', which should be a string (can be any string)
         *   - another field 'country', which should also be a string, but this one
         *       only accepts 'United States' or 'Canada' as a value
         *
         * TODO: Also add example values to be used when populating the mock response, for example
         *   'Tennessee' for the state and 'United States' for the country.
         */

        DslPart body = LambdaDsl.newJsonBody((o) -> o
                .uuid("id", UUID.fromString(AddressId.EXISTING_ADDRESS_ID))
                .stringType("addressType", "billing")
                .stringType("street", "Main Street")
                .integerType("number", 123)
                .stringType("city", "Nothingville")
                .integerType("zipCode", 54321)
        ).build();

        Map<String, Object> providerStateParams = Map.of("addressId", AddressId.EXISTING_ADDRESS_ID);

        return builder.given("Address exists", providerStateParams)
                .uponReceiving("Retrieving an existing address ID")
                .path(String.format("/address/%s", AddressId.EXISTING_ADDRESS_ID))
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(body)
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "pactForGetExistingAddressId")
    public void testFor_GET_existingAddressId_shouldYieldExpectedAddressData(MockServer mockServer) {

        AddressServiceClient client = new AddressServiceClient(mockServer.getUrl());

        Address address = client.getAddress(AddressId.EXISTING_ADDRESS_ID);

        Assertions.assertEquals(AddressId.EXISTING_ADDRESS_ID, address.getId());

        /**
         * TODO: Add assertions that check that the 'state' and 'country' response fields
         *   will be parsed and read correctly if returned by the provider.
         */
    }

    @Pact(provider = "address-provider", consumer = "order-consumer")
    @Disabled
    public RequestResponsePact pactForGetNonExistingAddressId(PactDslWithProvider builder) {

        /**
         * TODO: Remove the @Disabled annotation, then define a new Pact segment that records the behaviour
         *   for the situation where the address ID is not known on the provider side.
         *   - Expect that the provider returns an HTTP 404 in that case
         *   - There is no response body here, so you don't have to write expectations for that
         *   - Use the provider state 'Address does not exist' and pass in AddressId.NON_EXISTING_ADDRESS_ID as a parameter
         *   - Use 'Retrieving an address ID that does not exist' as a description in uponReceiving()
         */

        return null;
    }

    @Test
    @Disabled
    @PactTestFor(pactMethod = "pactForGetNonExistingAddressId")
    public void testFor_GET_nonexistingAddressId_shouldYieldHttp404(MockServer mockServer) {

        /**
         * TODO: Remove the @Disabled annotation, then write a test that calls
         *   getAddress(AddressId.NON_EXISTING_ADDRESS_ID) on an AddressServiceClient pointing
         *   to the MockServer and verify that doing so throws a NotFoundException using Assertions.assertThrows().
         *   See the tests for the customer-consumer for an example of the syntax if you need it.
         */
    }
}
