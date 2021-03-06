package com.prokarma.engineering.customer.publisher.security;


import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.prokarma.engineering.customer.publisher.AbstractTest;
import com.prokarma.engineering.customer.publisher.model.Customer;
import com.prokarma.engineering.customer.publisher.resource.CustomerKafkaApiController;
import com.prokarma.engineering.customer.publisher.service.impl.KafkaProducerServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OauthAuthenticationTest extends AbstractTest {

  @Before
  public void setUp() {
    super.setUp();
  }

  @InjectMocks
  private KafkaProducerServiceImpl kafkaProducerService;

  @InjectMocks
  private CustomerKafkaApiController customerKafkaApi;

  @Mock
  private Customer customer;

  private HttpHeaders httpHeaders;

  private String validPayload =
      "{\r\n" + "  \"customerNumber\": \"Abcd123\",\r\n" + "  \"firstName\": \"Testcustomer\",\r\n"
          + "  \"lastName\": \"Testcustomer\",\r\n" + "  \"birthdate\": \"15-06-1990\",\r\n"
          + "  \"country\": \"India\",\r\n" + "  \"countryCode\": \"IN\",\r\n"
          + "  \"mobileNumber\": 9701682182,\r\n" + "  \"email\": \"abc@gmail.com\",\r\n"
          + "  \"customerStatus\": \"Open\",\r\n" + "  \"address\": {\r\n"
          + "    \"addressLine1\": \"string1\",\r\n" + "    \"addressLine2\": \"string1\",\r\n"
          + "    \"street\": \"string1\",\r\n" + "    \"postalCode\": 51231\r\n" + "  }\r\n" + "}";


  @Test
  public void kafkaCustomerWithoutAccess() throws Exception {
    String uri = "/prokarma/v1/customer";
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();
    assertEquals(401, status);
  }


  @Test
  public void kafkaCustomerAccess() throws Exception {
    String uri = "/prokarma/v1/customer";
    httpHeaders = new HttpHeaders();
    httpHeaders.add("Activity-Id", "mobile");
    httpHeaders.add("Application-Id", "12345");

    ResultActions result = obtainAccessToken("my-trusted-client", "my-trusted-client:secret");
    result.andExpect(status().isOk());
    String resultString = result.andReturn().getResponse().getContentAsString();

    String accessToken = JacksonJsonParser().parseMap(resultString).get("access_token").toString();
    httpHeaders.add("Authorization", "Bearer " + accessToken);

    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).content(validPayload)
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON_VALUE)
        .headers(httpHeaders)).andReturn();
    int status = mvcResult.getResponse().getStatus();
    String response = mvcResult.getResponse().getContentAsString();
    String responseStatus = JacksonJsonParser().parseMap(response).get("status").toString();
    assertEquals(202, status);
    assertEquals("success", responseStatus);
  }

  @Test
  public void accessToken() throws Exception {

    ResultActions result = obtainAccessToken("my-trusted-client", "my-trusted-client:secret");
    result.andExpect(status().isOk());
    String resultString = result.andReturn().getResponse().getContentAsString();

    String accessToken = JacksonJsonParser().parseMap(resultString).get("access_token").toString();
  }

  @Test
  public void badCredentials() throws Exception {

    ResultActions result = obtainAccessToken("my-trusted-client", "my-trusted-client:secret123");
    result.andExpect(status().is4xxClientError());

  }

  @Test
  public void badCredentialsPassword() throws Exception {

    ResultActions result = obtainAccessToken("my-trusted-clientqweq", "my-trusted-client:secret");
    result.andExpect(status().is4xxClientError());
    String resultString = result.andReturn().getResponse().getContentAsString();
    System.out.println("---->" + resultString);
    String error_description = JacksonJsonParser().parseMap(resultString).get("error").toString();
    assertEquals("invalid_client", error_description);
  }

  private ResultActions obtainAccessToken(String client_id, String password) throws Exception {

    MultiValueMap<String, String> params = new LinkedMultiValueMap();
    params.add("grant_type", "client_credentials");
    params.add("client_id", client_id);

    String base64ClientCredentials = new String(Base64.encodeBase64(password.getBytes()));

    ResultActions result = mvc.perform(post("/oauth/token").params(params)
        .header("Authorization", "Basic " + base64ClientCredentials)
        .accept("application/json;charset=UTF-8"));
    return result;
  }

}
