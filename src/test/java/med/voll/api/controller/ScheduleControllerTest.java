package med.voll.api.controller;

import med.voll.api.domain.schedule.dto.ScheduleDataDTO;
import med.voll.api.domain.schedule.dto.ScheduleDetailingDataDTO;
import med.voll.api.domain.schedule.services.ScheduleService;
import med.voll.api.domain.shared.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ScheduleControllerTest {


    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ScheduleDataDTO> scheduleDataDTOJson;

    @Autowired
    private JacksonTester<ScheduleDetailingDataDTO> scheduleDetailingJson;

    @MockBean
    private ScheduleService schedule;

    @Test
    @DisplayName("Should return http 400 when information is invalid")
    @WithMockUser
    void scheduleScenario1() throws Exception {
        MockHttpServletResponse response = mvc.perform(post("/schedule"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("should return http 200 when information is valid")
    @WithMockUser
    void scheduleScenario2() throws Exception {
        LocalDateTime date = LocalDateTime.now().plusHours(1);
        Specialty specialty = Specialty.CARDIOLOGIA;

        ScheduleDetailingDataDTO scheduleDetailingDataDTO = new ScheduleDetailingDataDTO(null, 2L, 2L, date);

        when(schedule.toSchedule(any()))
                .thenReturn(scheduleDetailingDataDTO);

        MockHttpServletResponse response = mvc
                .perform(
                    post("/schedule")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(scheduleDataDTOJson.write(
                                    new ScheduleDataDTO(2L, 2L,date, specialty)
                            ).getJson())
                    )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        String expectedJson = scheduleDetailingJson.write(scheduleDetailingDataDTO).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }

}