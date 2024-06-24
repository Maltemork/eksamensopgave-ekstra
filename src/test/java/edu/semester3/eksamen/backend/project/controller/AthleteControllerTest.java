package edu.semester3.eksamen.backend.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.semester3.eksamen.backend.project.dto.AthleteDTO;
import edu.semester3.eksamen.backend.project.dto.ResultDTO;
import edu.semester3.eksamen.backend.project.dto.ResultWithDisciplineIntegerDTO;
import edu.semester3.eksamen.backend.project.enums.Gender;
import edu.semester3.eksamen.backend.project.enums.ResultTypes;
import edu.semester3.eksamen.backend.project.model.Athlete;
import edu.semester3.eksamen.backend.project.model.Discipline;
import edu.semester3.eksamen.backend.project.model.Result;
import edu.semester3.eksamen.backend.project.service.AthleteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AthleteControllerTest {

    @MockBean
    private AthleteService athleteService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private final Athlete testAthlete = new Athlete("John", Gender.MALE, "M");
    private final AthleteDTO testAthleteDTO = new AthleteDTO(testAthlete);
    private final Discipline testDiscipline = new Discipline("Discipline", "Description");
    private final Result testResult = new Result(1, testAthlete, testDiscipline, 1, ResultTypes.TIME, "01/01/2024", "1", "1", "1", "1");
    private final ResultDTO testResultDTO = new ResultDTO(testResult);

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void getAllAthletes() throws Exception {
        when(athleteService.getAllAthletes()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/athletes"))
                .andExpect(status().isOk());
    }

    @Test
    void getAthleteById() throws Exception {
        when(athleteService.getById(anyInt())).thenReturn(testAthlete);

        mockMvc.perform(get("/athletes/find-by-id/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void getAthletesByName() throws Exception {
        when(athleteService.getByName(any())).thenReturn(Collections.singletonList(testAthlete));

        mockMvc.perform(get("/athletes/find-by-name/{name}", "John"))
                .andExpect(status().isOk());
    }

    @Test
    void getResults() throws Exception {
        when(athleteService.getResults(anyInt())).thenReturn(Collections.singletonList(testResult));

        mockMvc.perform(get("/athletes/{id}/results", 1))
                .andExpect(status().isOk());
    }

    @Test
    void createAthlete() throws Exception {
        when(athleteService.addAthlete(any())).thenReturn(testAthleteDTO);

        mockMvc.perform(post("/athletes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testAthleteDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void updateAthlete() throws Exception {
        when(athleteService.updateAthlete(any())).thenReturn(testAthleteDTO);

        mockMvc.perform(put("/athletes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testAthleteDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAthlete() throws Exception {
        mockMvc.perform(delete("/athletes/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void addResult() throws Exception {
        when(athleteService.getById(anyInt())).thenReturn(testAthlete);
        mockMvc.perform(post("/athletes/{id}/results", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ResultWithDisciplineIntegerDTO(testResultDTO, 1))))
                .andExpect(status().isCreated());
    }

    @Test
    void updateResult() throws Exception {
        mockMvc.perform(put("/athletes/results/{resultId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testResultDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void getResultById() throws Exception {
        when(athleteService.getResultById(anyInt())).thenReturn(new Result());

        mockMvc.perform(get("/athletes/results/find-by-id/{resultId}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void getAllResults() throws Exception {
        when(athleteService.getAllResults()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/athletes/results"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteResult() throws Exception {
        mockMvc.perform(delete("/athletes/results/{resultId}", 1))
                .andExpect(status().isOk());
    }
}