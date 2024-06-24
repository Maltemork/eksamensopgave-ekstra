package edu.semester3.eksamen.backend.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.semester3.eksamen.backend.project.dto.DisciplineDTO;
import edu.semester3.eksamen.backend.project.dto.ResultDTO;
import edu.semester3.eksamen.backend.project.model.Discipline;
import edu.semester3.eksamen.backend.project.model.Result;
import edu.semester3.eksamen.backend.project.service.DisciplineService;
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
class DisciplineControllerTest {

    @MockBean
    private DisciplineService disciplineService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private final Discipline testDiscipline = new Discipline("Discipline", "Description");
    private final DisciplineDTO testDisciplineDTO = new DisciplineDTO(testDiscipline);

    private final Result testResult = new Result();
    private final ResultDTO testResultDTO = new ResultDTO(testResult);

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void getAllDisciplines() throws Exception {
        when(disciplineService.getAllDisciplines()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/disciplines"))
                .andExpect(status().isOk());
    }

    @Test
    void getDiscipline() throws Exception {
        when(disciplineService.getById(anyInt())).thenReturn(testDiscipline);
        mockMvc.perform(get("/disciplines/find-by-id/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void getDisciplineById() throws Exception {
        when(disciplineService.getById(anyInt())).thenReturn(testDiscipline);
        mockMvc.perform(get("/disciplines/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void getDisciplineByName() throws Exception {
        when(disciplineService.getByName(any())).thenReturn(Collections.singletonList(testDiscipline));

        mockMvc.perform(get("/disciplines/find-by-name/{name}", "Discipline"))
                .andExpect(status().isOk());
    }

    @Test
    void createDiscipline() throws Exception {
        when(disciplineService.addDiscipline(any())).thenReturn(testDisciplineDTO);

        mockMvc.perform(post("/disciplines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testDisciplineDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void updateDiscipline() throws Exception {
        when(disciplineService.updateDiscipline(any())).thenReturn(testDiscipline);

        mockMvc.perform(put("/disciplines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testDisciplineDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void getResults() throws Exception {
        when(disciplineService.getResults(anyInt())).thenReturn(Collections.singletonList(testResult));

        mockMvc.perform(get("/disciplines/{id}/results", 1))
                .andExpect(status().isOk());
    }

    @Test
    void deleteDiscipline() throws Exception {
        mockMvc.perform(delete("/disciplines/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void addResult() throws Exception {
        mockMvc.perform(post("/disciplines/{id}/results", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testResultDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void updateResult() throws Exception {
        mockMvc.perform(put("/disciplines/{id}/results", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testResultDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteResult() throws Exception {
        mockMvc.perform(delete("/disciplines/{id}/results/{resultId}", 1, 1))
                .andExpect(status().isOk());
    }
}