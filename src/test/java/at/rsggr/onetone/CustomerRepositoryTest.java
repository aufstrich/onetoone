package at.rsggr.onetone;

import javax.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
class CustomerRepositoryTest {

  @Autowired
  CustomerRepository customerRepository;

  Long CUSTOMER_ID = 1337L;

  @BeforeEach
  @Transactional
  void setUp() {
    CustomerBillingAddress billingAddress = CustomerBillingAddress.builder()
        .customerId(CUSTOMER_ID)
        .type(AddressType.BILLING)
        .name("Technische Universität Wien Freihaus")
        .street("Wiedner Hauptstraße 8-10")
        .code("1040")
        .city("Wien")
        .build();
    CustomerDeliveryAddress deliveryAddress = CustomerDeliveryAddress.builder()
        .customerId(CUSTOMER_ID)
        .type(AddressType.DELIVERY)
        .name("EI 9")
        .street("Gußhausstraße 25 – 29")
        .code("1040")
        .city("Wien")
        .build();
    Customer customer = Customer.builder()
        .id(CUSTOMER_ID)
        .name("Juergen")
        .billingAddress(billingAddress)
        .deliveryAddress(deliveryAddress)
        .build();
    Customer savedCustomer = customerRepository.save(customer);
    log.info("saved {}", savedCustomer);
  }

  @Test
  void removeBillingAddress() {
    Customer customer = customerRepository.findById(CUSTOMER_ID).orElseThrow();

    Customer updatedCustomer = customer.toBuilder()
        .billingAddress(null)
        .build();

    customerRepository.save(updatedCustomer);

    Customer fetchtedCustomer = customerRepository.findById(CUSTOMER_ID).orElseThrow();
    Assertions.assertThat(fetchtedCustomer.getBillingAddress()).isNull();
    Assertions.assertThat(fetchtedCustomer.getDeliveryAddress()).isNotNull();
  }

}