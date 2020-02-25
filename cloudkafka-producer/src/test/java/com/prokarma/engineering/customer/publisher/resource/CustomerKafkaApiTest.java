package com.prokarma.engineering.customer.publisher.resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.apache.kafka.common.KafkaException;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.prokarma.engineering.customer.publisher.model.Customer;
import com.prokarma.engineering.customer.publisher.service.KafkaProducerService;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class CustomerKafkaApiTest {

  @Spy
  private KafkaProducerService kafkaProducerService;

  @InjectMocks
  private CustomerKafkaApiController CustomerKafkaApiController;

  @Mock
  private Customer customer;

  private final String empty = "";


  @Test
  public void addCustomerWithSuccess() {

    BDDMockito.doNothing().when(kafkaProducerService).send(customer);
    // BDDMockito.then(new
    // ResponseEntity(Status.ABORTED)).when(kafkaProducerService.send(customer));
    // BDDMockito.then(new RuntimeException("Something Went Wrong")).when(kafkaProducerService)
    // .send(customer);
    // when(kafkaProducerService.send()).then("");
    ResponseEntity<?> response = CustomerKafkaApiController.customer(customer, "authorization",
        "applicationId", "activityId");
    assertThat(response).isNotNull();
    assertThat(response.getStatusCodeValue()).isEqualTo(202);
  }

  @Test
  public void addCustomerWithFailure() {
    BDDMockito.doThrow(new KafkaException("Something Went Wrong with kafka"))
        .when(kafkaProducerService).send(customer);
    ResponseEntity<?> response = CustomerKafkaApiController.customer(customer, "authorization",
        "applicationId", "activityId");
    assertThrows(KafkaException.class,
        () -> CustomerKafkaApiController.customer(customer, empty, empty, empty));
    // assertThat(response.getStatusCodeValue()).isEqualTo(202);

  }

}
