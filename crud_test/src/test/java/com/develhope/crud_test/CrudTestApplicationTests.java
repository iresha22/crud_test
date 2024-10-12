package com.develhope.crud_test;

import com.develhope.crud_test.controllers.StudentController;
import com.develhope.crud_test.entities.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
class CrudTestApplicationTests {
	// parameters
	@Autowired
	private StudentController studentController;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
		assertThat(studentController).isNotNull();
	}
	// PRIVATE CREATE USER METHOD so i can use this method in the other test methods
	//WHY? so i don't need to go and create a new student every method & i already have a student in my db
	//created a new student
	private MvcResult createdStudent() throws Exception{
		Student student = new Student(1L,"Iresha","fernando",true);
		return createdStudentRequest(student);
	}
	// prendi la richiesta e ritorna il body della risposta
	private MvcResult createdStudentRequest(Student student) throws Exception{
		if(student == null) ;
		String studentJson = objectMapper.writeValueAsString(student);
		return this.mockMvc.perform(post("/Students/newstudent") // makes the test request we want
						.contentType(MediaType.APPLICATION_JSON)
						.content(studentJson))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
	}

	// TEST CREATE USER METHOD
	// to create the object user and put it in mockmvc you need to wrap it in objectwrapper and then turn it into a string
	@Test
	public void createStudentTest() throws Exception{
		MvcResult result = createdStudent();
		// object mapper to read the body
		Student studentFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(),Student.class);
		assertThat(studentFromResponse.getId()).isNotNull();
	}

	// TEST TO READ STUDENT METHOD
    @Test
	public void readStudentList() throws Exception{
		// created the response
		createdStudent();
		MvcResult result = this.mockMvc.perform(get("/Students/studentlist"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		// code that needs to read the body of the response
		List<Student> studentsFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(),List.class);
		System.out.println("Students in the database are " + studentsFromResponse.size());
		assertThat(studentsFromResponse.size()).isNotZero();

	}


	//TEST to update user
	@Test
	public void updateStudentTest() throws Exception{
		Student student = new Student(4L,"amanda", "fernando",true);
		assertThat(student.getId()).isNotNull();

		String newName = "John";
		student.setName(newName);

		String studentJson = objectMapper.writeValueAsString(student);

		MvcResult result = this.mockMvc.perform(post("/Students/new/2") // makes the test request we want
						.contentType(MediaType.APPLICATION_JSON)
						.content(studentJson))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		Student studentFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(),Student.class);
		assertThat(studentFromResponse.getName()).isEqualTo(newName);
	}

	// TEST to delete student
	public void deleteStudentTest()throws Exception{
		Student student = new Student(4L,"amanda", "fernando",true);
		assertThat(student.getId()).isNotNull();

		String studentJson = objectMapper.writeValueAsString(student);

		MvcResult result = this.mockMvc.perform(post("/Students/4")) // makes the test request we want
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		Student studentFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(),Student.class);
		assertThat(studentFromResponse).isNotNull();
	}

}
