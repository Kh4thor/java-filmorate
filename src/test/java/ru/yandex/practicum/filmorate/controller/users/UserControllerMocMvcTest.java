package ru.yandex.practicum.filmorate.controller.users;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.ResourceUtils;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerMocMvcTest {

	public static final String PATH = "/users";
	public static final String RESOURCE = "users/";

	private MockMvc mockMvc;

	@Autowired
	private void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Test
	public void test_createOrUpdateUser() {

		/*
		 * тестирование создания пользователя
		 */
		try {
			mockMvc.perform(MockMvcRequestBuilders.post(PATH).contentType(MediaType.APPLICATION_JSON)
					.content(getContentFromFile(RESOURCE + "CreateRequestUser.json")))
					.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content()
							.json(getContentFromFile(RESOURCE + "CreateResponseUser.json")));
		} catch (Exception e) {
			System.out.println("Ошибка при тестировании метода создания фильма");
		}

		/*
		 * тестирование обновления пользователя
		 */
		try {
			mockMvc.perform(MockMvcRequestBuilders.put(PATH).contentType(MediaType.APPLICATION_JSON)
					.content(getContentFromFile(RESOURCE + "RequestUpdateUser.json")))
					.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content()
							.json(getContentFromFile(RESOURCE + "RequestUpdateUser.json")));
		} catch (Exception e) {
			System.out.println("Ошибка при тестировании метода обновления пользователя");
		}

		/*
		 * тестирование метода удаления пользователя
		 */
		try {
			mockMvc.perform(MockMvcRequestBuilders.delete(PATH + "/1").contentType(MediaType.APPLICATION_JSON)
					.content(getContentFromFile(RESOURCE + "RequestUpdateUser.json")))
					.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			System.out.println("Ошибка при тестировании метода удаления пользователя");
		}
	}

	private String getContentFromFile(String fileName) {
		try {
			return Files.readString(ResourceUtils.getFile("classpath:" + fileName).toPath(), StandardCharsets.UTF_8);
		} catch (IOException exception) {
			throw new RuntimeException("Не удалось открыть файл: " + fileName, exception);
		}
	}
}