package com.course.practicaljava.rest.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Duration;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.course.practicaljava.rest.domain.Car;
import com.course.practicaljava.rest.service.CarService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class CarRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private CarService randomCarService;

	@Test
	void testRandom() throws Exception {
		var endpoint = "/api/car/v1/random";

		for (int i = 0; i < 100; i++) {
			var requestBuilder = MockMvcRequestBuilders.get(endpoint);

			var mockResult = mockMvc.perform(requestBuilder).andReturn();
			var content = mockResult.getResponse().getContentAsString();

			var car = objectMapper.readValue(content, Car.class);

			assertTrue(CarService.BRANDS.contains(car.getBrand()));
			assertTrue(CarService.COLORS.contains(car.getColor()));
		}
	}

//	@Test
	void testEcho() {
		fail("Not yet implemented");
	}

//	@Test
	void testRandomCars() {
		fail("Not yet implemented");
	}

//	@Test
	void testCountCar() {
		fail("Not yet implemented");
	}

	@Test
	void testCreateCar() throws Exception {
		for (int i = 0; i < 100; i++) {
			var randomCar = randomCarService.generateCar();
			var carString = objectMapper.writeValueAsString(randomCar);

			var endpoint = "/api/car/v1/cars";
			var requestBuilder = MockMvcRequestBuilders.post(endpoint).contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(carString);

			assertTimeout(Duration.ofSeconds(1), () -> {
				mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful());
			});
		}
	}

//	@Test
	void testFindCarsById() {
		fail("Not yet implemented");
	}

//	@Test
	void testUpdateCarById() {
		fail("Not yet implemented");
	}

	@Test
	void testFindCarsByPath() throws Exception {
		int pageSize = 5;

		for (String brand : CarService.BRANDS) {
			for (String color : CarService.COLORS) {
				var endpoint = StringUtils.join("/api/car/v1/cars/", brand, "/", color);
				var requestBuilder = MockMvcRequestBuilders.get(endpoint).param("size", Integer.toString(pageSize))
						.param("page", "0");

				var mockResult = mockMvc.perform(requestBuilder).andReturn();
				String responseBody = mockResult.getResponse().getContentAsString();
				List<Car> listCars = objectMapper.readValue(responseBody, new TypeReference<List<Car>>() {
				});

				assertNotNull(listCars);
				assertTrue(listCars.size() >= 0 && listCars.size() <= pageSize);
			}
		}
	}

//	@Test
	void testFindCarsByParam() {
		fail("Not yet implemented");
	}

//	@Test
	void testFindCarsReleasedAfter() {
		fail("Not yet implemented");
	}

}
