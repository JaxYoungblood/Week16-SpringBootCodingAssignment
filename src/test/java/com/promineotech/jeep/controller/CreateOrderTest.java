package com.promineotech.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
//import org.springframework.test.jdbc.JdbcTestUtils;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.entity.Order;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
    "classpath:flyway/migrations/V1.1__Jeep_Data.sql"}, config = @SqlConfig(encoding = "utf-8"))


class CreateOrderTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @LocalServerPort
  private int serverPort;

  //@Autowired
  //private JdbcTemplate jdbcTemplate;

  @Test
  void testCreateOrderReturnsSuccess201() {

  //GIVEN: an order as JSON
    String body = createOrderBody();
    String uri = String.format("http://localhost:%d/orders", serverPort);
    
    //int numRowOrders = JdbcTestUtils.countRowsInTable(jdbcTemplate, "orders");
    //int numRowOptions = JdbcTestUtils.countRowsInTable(jdbcTemplate, "order_options");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);

  //WHEN: order is sent
    ResponseEntity<Order> response =
        restTemplate.exchange(uri, HttpMethod.POST, bodyEntity, Order.class);

  //THEN: 201 status returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

  //AND: order is correct
    assertThat(response.getBody()).isNotNull();

    Order order = response.getBody();
    assertThat(order.getCustomer().getCustomerId()).isEqualTo("ATTAWAY_HECKTOR");
    assertThat(order.getModel().getModelId()).isEqualTo(JeepModel.GRAND_CHEROKEE);
    assertThat(order.getModel().getTrimLevel()).isEqualTo("Trackhawk");
    assertThat(order.getModel().getNumDoors()).isEqualTo(4);
    assertThat(order.getColor().getColorId()).isEqualTo("EXT_GREEN_METALIC");
    assertThat(order.getEngine().getEngineId()).isEqualTo("2_0_HYBRID");
    assertThat(order.getTire().getTireId()).isEqualTo("255_GOODYEAR");
    assertThat(order.getOptions()).hasSize(4);
    
    //assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "orders")).isEqualTo(numRowOrders + 1);
    //assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "order_options")).isEqualTo(numRowOptions + 6);

  }// end TEST testCreateOrderReturnsSuccess201


  protected String createOrderBody() {
    //@formatter:off
            return "{\n"
                + "  \"customer\":\"ATTAWAY_HECKTOR\",\n"
                + "  \"model\":\"GRAND_CHEROKEE\",\n"
                + "  \"trim\":\"Trackhawk\",\n"
                + "  \"doors\":4,\n"
                + "  \"color\":\"EXT_GREEN_METALIC\",\n"
                + "  \"engine\":\"2_0_HYBRID\",\n"
                + "  \"tire\":\"255_GOODYEAR\",\n"
                + "  \"options\":[\n"
                + "    \"EXT_AEV_LIFT\",\n"
                + "    \"EXT_WARN_WINCH\",\n"
                + "    \"EXT_WARN_BUMPER_FRONT\",\n"
                + "    \"EXT_WARN_BUMPER_REAR\",\n"
                + "  ]\n"
                + "}";
        //@formatter:on
  }// end STRING

}// end CLASS

