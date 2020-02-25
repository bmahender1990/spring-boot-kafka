package com.prokarma.engineering.customer.publisher.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.prokarma.engineering.customer.publisher.AbstractTest;
import com.prokarma.engineering.customer.publisher.model.Customer;
import com.prokarma.engineering.customer.publisher.service.KafkaProducerService;


@AutoConfigureMockMvc
@EmbeddedKafka(partitions = 1, topics = {"tjryc3xl-default"})
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerKafkaApiIntegrationTest extends AbstractTest {

  @Autowired
  private MockMvc mockMvc;

  @Spy
  private KafkaProducerService kafkaProducerService;

  private HttpHeaders httpHeaders;

  private String applicaitonId = "test_case";

  private String uri = "/prokarma/v1/customer";

  private String validPayload =
      "{\r\n" + "  \"customerNumber\": \"Abcd123\",\r\n" + "  \"firstName\": \"Testcustomer\",\r\n"
          + "  \"lastName\": \"Testcustomer\",\r\n" + "  \"birthdate\": \"15-06-1990\",\r\n"
          + "  \"country\": \"India\",\r\n" + "  \"countryCode\": \"IN\",\r\n"
          + "  \"mobileNumber\": 9701682182,\r\n" + "  \"email\": \"abc@gmail.com\",\r\n"
          + "  \"customerStatus\": \"Open\",\r\n" + "  \"address\": {\r\n"
          + "    \"addressLine1\": \"string1\",\r\n" + "    \"addressLine2\": \"string1\",\r\n"
          + "    \"street\": \"string1\",\r\n" + "    \"postalCode\": 50431\r\n" + "  }\r\n" + "}";

  private String invalidPayload =
      "{\r\n" + "  \"customerNumber\": \"Abcd123\",\r\n" + "  \"firstName\": \"Testcustomer\",\r\n"
          + "  \"lastName\": \"Testcustomer\",\r\n" + "  \"birthdate\": \"06-1990\",\r\n"
          + "  \"country\": \"India\",\r\n" + "  \"countryCode\": \"IN\",\r\n"
          + "  \"mobileNumber\": 9701682182,\r\n" + "  \"email\": \"@gmail.com\",\r\n"
          + "  \"customerStatus\": \"Open\",\r\n" + "  \"address\": {\r\n"
          + "    \"addressLine1\": \"string1\",\r\n" + "    \"addressLine2\": \"string1\",\r\n"
          + "    \"street\": \"string1\",\r\n" + "    \"postalCode\": 50431\r\n" + "  }\r\n" + "}";


  @Before
  public void setup() throws Exception {
    super.setUp();
    httpHeaders = new HttpHeaders();
    httpHeaders.add("Activity-Id", "mobile");
    httpHeaders.add("Application-Id", applicaitonId);
    httpHeaders.add("Authorization", "Bearer " + accessToken());
  }

  @Test
  public void AddCustomerSuccess() throws Exception {
    BDDMockito.doNothing().when(kafkaProducerService).send(new Customer());;
    mockMvc.perform(MockMvcRequestBuilders.post(uri).content(validPayload)
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        .headers(httpHeaders)).andExpect(MockMvcResultMatchers.status().isAccepted());
  }


  @Test
  public void testAddUserAuthorized_Failure() throws Exception {
    BDDMockito.doNothing().when(kafkaProducerService);
    mockMvc.perform(MockMvcRequestBuilders.post(uri).content(invalidPayload)
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        .headers(httpHeaders)).andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  public void testFailurevalidation() throws Exception {
    BDDMockito.doNothing().when(kafkaProducerService).send(new Customer());;
    String firstNameInvalidJsonPayload = "{\r\n" + "  \"customerNumber\": \"45\",\r\n"
        + "  \"firstName\": \"mahender\",\r\n" + "  \"lastName\": \"badeddsert\",\r\n"
        + "  \"birthdate\": \"15-06-1990\",\r\n" + "  \"country\": \"India\",\r\n"
        + "  \"countryCode\": \"IN\",\r\n" + "  \"mobileNumber\": 9701682182,\r\n"
        + "  \"email\": \"abc@gmail.com\",\r\n" + "  \"customerStatus\": \"Open\",\r\n"
        + "  \"address\": {\r\n" + "    \"addressLine1\": \"string1\",\r\n"
        + "    \"addressLine2\": \"string1\",\r\n" + "    \"street\": \"string1\",\r\n"
        + "    \"postalCode\": 12341\r\n" + "  }\r\n" + "}";
    mockMvc
        .perform(MockMvcRequestBuilders.post(uri).content(firstNameInvalidJsonPayload)
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
            .headers(httpHeaders))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.message")
            .value("[firstName should have 10 to 50 characters]"));
  }


  public String accessToken() throws Exception {
    ResultActions result = obtainAccessToken("user", "user");
    result.andExpect(status().isOk());
    String resultString = result.andReturn().getResponse().getContentAsString();
    System.out.println(
        "----->" + JacksonJsonParser().parseMap(resultString).get("access_token").toString());
    return JacksonJsonParser().parseMap(resultString).get("access_token").toString();
  }

  private ResultActions obtainAccessToken(String username, String password) throws Exception {
    MultiValueMap<String, String> params = new LinkedMultiValueMap();
    params.add("grant_type", "password");
    params.add("client_id", "my-trusted-client");
    params.add("username", username);
    params.add("password", password);

    String base64ClientCredentials =
        new String(Base64.encodeBase64("my-trusted-client:secret".getBytes()));

    ResultActions result = mvc.perform(post("/oauth/token").params(params)
        .header("Authorization", "Basic " + base64ClientCredentials)
        .accept("application/json;charset=UTF-8"));
    return result;
  }

}
