package cz.ememsoft.micro.service;

import cz.ememsoft.micro.service.client.PrefixClient;
import cz.ememsoft.micro.service.service.TransactionService;
import cz.ememsoft.micro.service.repository.BankAccountRepository;
import cz.ememsoft.micro.service.resource.BankResource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

@SpringBootTest
@AutoConfigureWireMock(port = 8082, stubs = "classpath:/stubs")
public class IntegrationTest {

  @MockBean
  private TransactionService transactionService;

  private PrefixClient prefixClient;

  @Test
  void demo() {

    System.out.println(prefixClient.getPrefix());
  }
}
