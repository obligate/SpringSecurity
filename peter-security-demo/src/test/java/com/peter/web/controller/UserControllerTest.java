/**
 * 
 */
package com.peter.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author peter
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	/**
	 * 伪造一个mvc 容器
	 */
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void whenQuerySuccess() throws Exception {
//			mockMvc.perform(MockMvcRequestBuilders.get("/user")
//					.contentType(MediaType.APPLICATION_JSON_UTF8))
//					.andExpect(MockMvcResultMatchers.status().isOk()) // 期望返回的结果是ok
//					.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3)); // 期望返回的数据的长度是3

		String result = mockMvc.perform(get("/user")
//					.param("username", "peter")
				.param("username", "peter").param("age", "5").param("ageTo", "15").param("others", "others")
//					.param("size", "15")
//					.param("page", "3")
//					.param("sort", "age,desc")
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()) // 期望返回的结果是ok
				.andExpect(jsonPath("$.length()").value(3))// 期望返回的数据的长度是3
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}

	@Test
	public void whenGetInfoSuccess() throws Exception {
		String result = mockMvc.perform(get("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()) // 期望返回的结果是ok，status是200
				.andExpect(jsonPath("$.username").value("tom")).andReturn().getResponse().getContentAsString();
		System.out.println(result);

	}

	@Test
	public void whenGetInfoFail() throws Exception {
		mockMvc.perform(get("/user/a").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void whenCreateSuccess() throws Exception {
		Date birthday = new Date();
		System.out.println(birthday.getTime());
		String content = "{\"username\":\"tom\",\"password\":null,\"birthday\":" + birthday.getTime() + "}";
		String result = mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id").value("1")).andReturn().getResponse()
				.getContentAsString();
		System.out.println(result);
	}

	@Test
	public void whenUpdateSuccess() throws Exception {
		Date birthday = new Date(
				LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		System.out.println(birthday.getTime());
		String content = "{\"id\":\"1\",\"username\":\"tom\",\"password\":null,\"birthday\":" + birthday.getTime()
				+ "}";
		String result = mockMvc.perform(put("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id").value("1")).andReturn().getResponse()
				.getContentAsString();
		System.out.println(result);
	}

	@Test
	public void whenDeleteSuccess() throws Exception {
		mockMvc.perform(delete("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk());
	}

	@Test
	public void whenUploadSuccess() throws Exception {
		// v5.0+ fileUpLoad方法已经过时了
		String result = mockMvc
				.perform(multipart("/file").file(new MockMultipartFile("file", "test.txt", "multipart/form-data",
						"hello upload".getBytes("UTF-8"))))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}

	@Test
	/**
	 * 也可以直接通过浏览器访问下载    http://localhost:8080/file/1533787526924
	 * @throws Exception
	 */
	public void whenFileDownloadSuccess() throws Exception {
		String file = mockMvc.perform(get("/file/1533787526924"))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		System.out.println(file);
	}
}
