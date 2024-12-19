package com.example.Tech_Trends.controllers;
import com.example.Tech_Trends.entity.Trend;
import com.example.Tech_Trends.entity.User;
import com.example.Tech_Trends.enums.Category;
import com.example.Tech_Trends.repositories.TrendRepository;
import com.example.Tech_Trends.repositories.UserRepository;
import com.example.Tech_Trends.services.TrendService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
class TrendControllerTest {

    @Autowired
    private TrendService trendService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrendRepository trendRepository;

    @Autowired
    MockMvc mockMvc;

    private String expectedTrendRequest = """
            {
                "title":"Latest Tech News",
                "description":"Updates about gadgets",
                "category":"AI",
                "imgUrl":"",
                "userId":1
            }""";


    @Test
    public void testAddAValidTrend() throws Exception {
        User defaultUser = new User("username", "username@email.com", "password");

        userRepository.save(defaultUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/trends")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectedTrendRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Latest Tech News"))
                .andExpect(jsonPath("$.category").value("AI"))
                .andExpect(jsonPath("$.imgUrl").value(""));
    }

    @Test
    void testGetAllTrendWhenCallGetAll() throws Exception {

        User defaultUser = new User("username", "username@email.com", "password");
        User savedUser = userRepository.save(defaultUser);

        Trend trend1 = new Trend("Latest Tech News","some-description" , Category.AI, "https://example.com/tech-news.jpg", savedUser);
        Trend trend2 = new Trend("Health Tips", "some-description", Category.OTHER, "https://example.com/health.jpg", savedUser);
        trendRepository.save(trend1);
        trendRepository.save(trend2);


        mockMvc.perform(MockMvcRequestBuilders.get("/trends")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("Latest Tech News"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].title").value("Health Tips"));
    }

    @Test
    void getTrendById() throws Exception {

        User defaultUser = new User("username", "username@email.com", "password");
        User savedUser = userRepository.save(defaultUser);

        Trend trend1 = new Trend("Latest Tech News","some-description" , Category.AI, "https://example.com/tech-news.jpg", savedUser);
        Trend savedTrend = trendRepository.save(trend1);

        mockMvc.perform(MockMvcRequestBuilders.get("/trends/" + savedTrend.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Latest Tech News"))
                .andExpect(jsonPath("$.description").value("some-description"));
    }
}