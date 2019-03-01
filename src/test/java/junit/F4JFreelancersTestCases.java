package junit;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.hamcrest.Matchers.is;

import io.openshift.booster.service.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FreelancerJPAResource.class)
public class F4JFreelancersTestCases {

	MockMvc mockMvc;
	
	@Autowired
	FreelancerJPAResource freelancerJPAResource;
	
	@MockBean
	Freelancer freelancer;
	
	@MockBean
	FreelancerRepository freelancerRepository;	

    
    private List<Freelancer> freelancerList;	// List of samples freelancers
    private Freelancer oneFreelancer;			// One sample of freelancer
    private Freelancer emptyFreelancer;			// Empty freelancer object
    
	
    @Before
    public void setup() throws Exception {
    	this.mockMvc = standaloneSetup(this.freelancerJPAResource).build();
		
    	Freelancer f1 = new Freelancer(1, "Ivan","Au","ivan.au@gmail.com","Vert.x, Spring Boot, Thorntail");
    	Freelancer f2 = new Freelancer(2, "Jenny","Chow","jenny.chow@hotmail.com","Vert.x, Spring Boot, Thorntail, Java");
    	Freelancer f3 = new Freelancer(3, "Kelly","Woo","kelly.woo@outlook.com","Vert.x, Spring Boot, Thorntail, Java, JPA");
   	
    	freelancerList = new ArrayList<>();
    	freelancerList.add(f1);
    	freelancerList.add(f2);
    	freelancerList.add(f3);
    	oneFreelancer = f2;
    	emptyFreelancer = null;
    }
    
	@Test
	public void Case09AllFreelancers() throws Exception {
		when(freelancerJPAResource.retrieveAllUsers()).thenReturn(freelancerList);
		mockMvc.perform(get("/freelancers").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].emailAddress", is("ivan.au@gmail.com")))
			.andExpect(jsonPath("$[1].emailAddress", is("jenny.chow@hotmail.com")))
			.andExpect(jsonPath("$[2].emailAddress", is("kelly.woo@outlook.com")));
	}
	
	@Test
	public void Case10OneFreelancer() throws Exception {
		when(freelancerJPAResource.retreiveFreelancer(2)).thenReturn(oneFreelancer);
		mockMvc.perform(get("/freelancers/2").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("emailAddress", is("jenny.chow@hotmail.com")));
	}
	
	@Test
	public void Case11FreelancerNotFound() throws Exception {
		when(freelancerJPAResource.retreiveFreelancer(99)).thenReturn(emptyFreelancer);
		mockMvc.perform(get("/freelancers/99").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().string(""));
	}
	
}
