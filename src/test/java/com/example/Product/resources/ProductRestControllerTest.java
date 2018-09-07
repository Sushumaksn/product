package com.example.Product.resources;

import static org.junit.Assert.assertEquals;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.example.Product.model.Item;
import com.example.Product.repository.ProductData;
import com.example.Product.service.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductRestController.class, secure = false)
public class ProductRestControllerTest {
	@Autowired
	private MockMvc mockMvc;
	private ProductService service;

	public void addProduct(String name, Integer sellIn, Integer quality) {
		service = new ProductService();
		Item item = new Item(name, sellIn, quality);
		item.setEntryDate(new Date());
		Date sellInDate = new Date();
		sellInDate.setDate(sellInDate.getDate() + item.getSellIn());
		item.setSellDate(sellInDate);
		ProductData.getProductsList().add(item);
	}

	@Test
	public void testAddProduct() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add").accept(MediaType.APPLICATION_JSON)
				.content(
						"{\r\n" + "	\"name\":\"Sulfuras\",\r\n" + "	\"sellIn\":15,\r\n" + "	\"quality\":40\r\n" + "}")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		System.out.println("\n\n#################### Result ##################### \n\n" + result.getResponse().getContentAsString() + "\n\n");
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		JSONAssert.assertEquals("{\"message\":\"Product added successfully\"}",
				result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testGetProductsList() throws Exception {
		addProduct("Sulfuras", 15, 40);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/productsList/20-09-2018")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("\n\n#################### Result ##################### \n\n" + result.getResponse().getContentAsString() + "\n\n");

		String expected = "[\r\n" + "    {\r\n" + "        \"name\": \"Sulfuras\",\r\n" + "        \"sellIn\": 2,\r\n"
				+ "        \"quality\": 40\r\n" + "    }\r\n" + "]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testGetProductsList_all() throws Exception {
		addProduct("+5 Dexterity Vest", 15, 40);
		addProduct("Aged Brie", 15, 40);
		addProduct("Elixir of the Mongoose", 15, 40);
		addProduct("Sulfuras", 15, 40);
		addProduct("Concert backstage passes", 15, 40);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/productsList/20-09-2018")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("\n\n#################### Result ##################### \n\n" + result.getResponse().getContentAsString() + "\n\n");

		String expected = "[{\"name\":\"Sulfuras\",\"sellIn\":2,\"quality\":40},{\"name\":\"+5 Dexterity Vest\",\"sellIn\":2,\"quality\":28},{\"name\":\"Aged Brie\",\"sellIn\":2,\"quality\":52},{\"name\":\"Elixir of the Mongoose\",\"sellIn\":2,\"quality\":28},{\"name\":\"Sulfuras\",\"sellIn\":2,\"quality\":40},{\"name\":\"Concert backstage passes\",\"sellIn\":2,\"quality\":60}]\r\n"
				+ "";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
}