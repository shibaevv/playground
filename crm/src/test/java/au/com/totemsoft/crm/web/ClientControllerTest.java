package au.com.totemsoft.crm.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import au.com.totemsoft.crm.model.ClientEntity;

@ActiveProfiles("test")
//@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class ClientControllerTest {

    @Value("${server.servlet.context-path}")
    private String contextPath; // = "";

    @Autowired private MockMvc mockMvc;

    @Autowired private ObjectMapper objectMapper;

    private static ClientEntity client;

    @Test
    @Order(1)
    public void find_notFound() throws Exception {
        mockMvc.perform(get(contextPath + "/find/0")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.message", Matchers.is("NotFoundException: No value present")))
            .andExpect(jsonPath("$.errorCode", Matchers.is(HttpStatus.NOT_FOUND.value())))
            ;
    }

    @Test
    @Order(2)
    public void create() throws Exception {
        client = ClientEntity.builder()
            .name("Joe Blogs")
            .email("joe.blogs@company.com")
            .build();
        MvcResult mvcResult = mockMvc.perform(post(contextPath + "/create")
            .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
            .andExpect(status().isCreated())
            .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name", Matchers.is(client.getName())))
            .andExpect(jsonPath("$.email", Matchers.is(client.getEmail())))
            .andReturn();
        final String content = mvcResult.getResponse().getContentAsString();
        client = objectMapper.readValue(content, ClientEntity.class);
    }

    @Test
    @Order(3)
    public void find_created() throws Exception {
        mockMvc.perform(get(contextPath + "/find/" + client.getId())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", Matchers.is(client.getId()), Integer.class))
            .andExpect(jsonPath("$.name", Matchers.is(client.getName())))
            .andExpect(jsonPath("$.email", Matchers.is(client.getEmail())))
            ;
    }

    @Test
    @Order(4)
    public void update() throws Exception {
        client = ClientEntity.builder()
            .id(client.getId())
            .name("John Reed")
            .email("john.reed@company.com")
            .build();
        mockMvc.perform(put(contextPath + "/update")
            .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
            .andExpect(status().isOk())
            .andReturn();
    }

    @Test
    @Order(5)
    public void find_updated() throws Exception {
        mockMvc.perform(get(contextPath + "/find/" + client.getId())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", Matchers.is(client.getId()), Integer.class))
            .andExpect(jsonPath("$.name", Matchers.is(client.getName())))
            .andExpect(jsonPath("$.email", Matchers.is(client.getEmail())))
            ;
    }

    @Test
    @Order(6)
    public void remove() throws Exception {
        mockMvc.perform(delete(contextPath + "/remove/" + client.getId())
            .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
            .andExpect(status().isMethodNotAllowed())
            .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            //.andExpect(jsonPath("$.id", Matchers.is(client.getId()), Integer.class))
            .andExpect(jsonPath("$.errorCode", Matchers.is(HttpStatus.METHOD_NOT_ALLOWED.value()), Integer.class))
            .andExpect(jsonPath("$.message", Matchers.is(client.getId()), Integer.class))
            ;
        client = null;
    }

}
