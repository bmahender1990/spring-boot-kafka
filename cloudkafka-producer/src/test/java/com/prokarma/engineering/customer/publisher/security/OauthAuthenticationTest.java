package com.prokarma.engineering.customer.publisher.security;


import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.prokarma.engineering.customer.publisher.AbstractTest;
import com.prokarma.engineering.customer.publisher.service.impl.CustomUserDetailsServiceImpl;
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
  private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;


  private String validPayload =
      "{\r\n" + "  \"customerNumber\": \"Abcd123\",\r\n" + "  \"firstName\": \"Testcustomer\",\r\n"
          + "  \"lastName\": \"Testcustomer\",\r\n" + "  \"birthdate\": \"15-06-1990\",\r\n"
          + "  \"country\": \"India\",\r\n" + "  \"countryCode\": \"IN\",\r\n"
          + "  \"mobileNumber\": 9701682182,\r\n" + "  \"email\": \"abc@gmail.com\",\r\n"
          + "  \"customerStatus\": \"Open\",\r\n" + "  \"address\": {\r\n"
          + "    \"addressLine1\": \"string1\",\r\n" + "    \"addressLine2\": \"string1\",\r\n"
          + "    \"street\": \"string1\",\r\n" + "    \"postalCode\": 50431\r\n" + "  }\r\n" + "}";


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
    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON_VALUE))
            .andReturn();
    int status = mvcResult.getResponse().getStatus();
    assertEquals(401, status);
  }

  @Test
  public void accessToken() throws Exception {

    ResultActions result = obtainAccessToken("user", "user");
    result.andExpect(status().isOk());
    String resultString = result.andReturn().getResponse().getContentAsString();

    String accessToken = JacksonJsonParser().parseMap(resultString).get("access_token").toString();
    System.out.println("accessToken------>" + accessToken);
  }

  @Test
  public void badCredentials() throws Exception {

    ResultActions result = obtainAccessToken("user2", "user");
    result.andExpect(status().is4xxClientError());
    String resultString = result.andReturn().getResponse().getContentAsString();

    String error_description =
        JacksonJsonParser().parseMap(resultString).get("error_description").toString();
    assertEquals("Bad credentials", error_description);
  }

  @Test
  public void badCredentialsPassword() throws Exception {

    ResultActions result = obtainAccessToken("user2", "user2");
    result.andExpect(status().is4xxClientError());
    String resultString = result.andReturn().getResponse().getContentAsString();

    String error_description =
        JacksonJsonParser().parseMap(resultString).get("error_description").toString();
    assertEquals("Bad credentials", error_description);
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
