package com.credera.parks;

import com.credera.parks.common.dto.TicketDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collection;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParksApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads() {
	}


	@Test
	public void getAllTicketsAPI() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
				.get("/tickets")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[*].id").exists());
	}

	@Test
	public void getCategoriesAPI() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/categories")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(6)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[5].name").exists());
	}


	@Test
	public void getParksAPI() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/parks")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(10)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[2].parkName").exists());
	}

	@Test
	public void createTicketsAPI() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
				.post("/tickets")
				.content(asJsonString(new TicketDTO(3, "test@email", 3,"description")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.categoryName").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.dateCreated").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.parkName").exists());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void updateTicketsAPI() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.put("/tickets/{id}", 3)
				.content(asJsonString(new TicketDTO(3,2, "update@email.com", 2, "updateDescription")))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("update@email.com"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.parkId").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("$.description").value("updateDescription"));
	}

}
