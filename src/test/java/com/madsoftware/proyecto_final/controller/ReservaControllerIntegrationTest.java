package com.madsoftware.proyecto_final.controller;

import com.madsoftware.proyecto_final.config.TestSecurityConfig;
import com.madsoftware.proyecto_final.model.Cliente;
import com.madsoftware.proyecto_final.model.Evento;
import com.madsoftware.proyecto_final.model.Reserva;
import com.madsoftware.proyecto_final.repository.ClienteRepository;
import com.madsoftware.proyecto_final.repository.EventoRepository;
import com.madsoftware.proyecto_final.repository.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Import(TestSecurityConfig.class)
class ReservaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Test
    @WithMockUser
    void listarReservas_debeRetornarListaDeReservas() throws Exception {
        mockMvc.perform(get("/reserva"))
               .andExpect(status().isOk())
               .andExpect(view().name("reserva-list"))
               .andExpect(model().attributeExists("reservas"));
    }

    @Test
    @WithMockUser
    void crearReserva_conDatosValidos_debeCrearReservaYRedireccionar() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNombre("Test Cliente");
        clienteRepository.save(cliente);

        Evento evento = new Evento();
        evento.setNombre("Test Evento");
        evento.setCapacidad(10);
        evento.setFechaHora(LocalDateTime.now().plusDays(1));
        eventoRepository.save(evento);

        mockMvc.perform(post("/reserva")
               .param("cliente.id", cliente.getId().toString())
               .param("evento.id", evento.getId().toString())
               .param("fechaHora", evento.getFechaHora().toString()))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/reserva"));
    }
}