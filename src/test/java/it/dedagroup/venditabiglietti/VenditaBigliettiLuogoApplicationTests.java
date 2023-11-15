package it.dedagroup.venditabiglietti;

import static it.dedagroup.venditabiglietti.utils.UtilPath.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.dedagroup.venditabiglietti.model.Luogo;
import it.dedagroup.venditabiglietti.repository.LuogoRepository;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@ContextConfiguration(classes = VenditaBigliettiLuogoApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureMockMvc

class VenditaBigliettiLuogoApplicationTests {
	@Autowired
	MockMvc mock;
	@Autowired
	LuogoRepository repo;
	@Test
	@Order(1)
	public void inserimentoLuogo() throws Exception {
		String json = new ObjectMapper().writeValueAsString(new Luogo("via delle vie,13","interno 1","roma","00143","roma","italiana"));
		mock.perform(MockMvcRequestBuilders.post(INSERT_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andDo(print());
	}

	@Test
	@Order(1)
	public void inserimentoLuogoSenzaDati() throws Exception {
		mock.perform(MockMvcRequestBuilders.post(INSERT_PATH)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andDo(print());
	}
	@Test
	@Order(2)
	public void modificaLuogo() throws Exception {
		String json = new ObjectMapper().writeValueAsString(new Luogo(1,"piazzale delle piazze,13","interno 1","roma","00143","roma","italiana",false,0));
		mock.perform(MockMvcRequestBuilders.post("/luogo/modify")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.jsonPath("$.riga1").value("piazzale delle piazze,13"))
				.andDo(print());

	}

	@Test
	@Order(2)
	public void modificaLuogoSenzaDati() throws Exception {
		mock.perform(MockMvcRequestBuilders.post("/luogo/modify")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andDo(print());

	}

	@Test
	@Order(3)
	public void eliminaLuogo() throws Exception {
		mock.perform(MockMvcRequestBuilders.post("/luogo/delete/1"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content().string("Luogo eliminato con successo!"))
				.andReturn();
	}

	@Test
	@Order(3)
	public void eliminaLuogoSenzaId() throws Exception {
		mock.perform(MockMvcRequestBuilders.post("/luogo/delete"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
	}
	@Test
	@Order(4)
	public void trovaLuogoTramiteId() throws Exception {
		mock.perform(MockMvcRequestBuilders.get(FIND_BY_ID_PATH+"/1"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andReturn();
	}
	@Test
	@Order(5)
	public void trovaLuoghiTramiteRiga1() throws Exception {
		mock.perform(MockMvcRequestBuilders.get(FIND_ALL_BY_RIGA1_PATH+"/piazzale delle piazze,13"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andReturn();
	}
	@Test
	@Order(6)
	public void trovaLuoghiTramiteRiga1EComune() throws Exception {
		mock.perform(MockMvcRequestBuilders.get(FIND_ALL_BY_RIGA1_AND_COMUNE+"/piazzale delle piazze,13/roma"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andReturn();
	}
	@Test
	@Order(7)
	public void trovaLuoghiTramiteRiga1ERiga2() throws Exception {
		mock.perform(MockMvcRequestBuilders.get(FIND_ALL_BY_RIGA1_RIGA2_PATH+"/piazzale delle piazze,13/interno 1"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andReturn();
	}
	@Test
	@Order(8)
	public void trovaLuoghiTramiteRiga1ERiga2EComune() throws Exception {
		mock.perform(MockMvcRequestBuilders.get(FIND_ALL_BY_RIGA1_RIGA2_COMUNE_PATH+"/piazzale delle piazze,13/interno 1/roma"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andReturn();
	}
	@Test
	@Order(9)
	public void trovaLuoghiTramiteCap() throws Exception {
		mock.perform(MockMvcRequestBuilders.get(FIND_ALL_BY_CAP_PATH+"/00143"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andReturn();
	}
	@Test
	@Order(10)
	public void trovaLuoghiTramiteComune() throws Exception {
		mock.perform(MockMvcRequestBuilders.get(FIND_ALL_BY_COMUNE_PATH+"/roma"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andReturn();
	}
	@Test
	@Order(11)
	public void trovaLuoghiTramiteProvincia() throws Exception {
		mock.perform(MockMvcRequestBuilders.get(FIND_ALL_BY_PROVINCIA_PATH+"/roma"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andReturn();
	}
	@Test
	@Order(12)
	public void trovaLuoghiTramiteNazionalita() throws Exception {
		mock.perform(MockMvcRequestBuilders.get(FIND_ALL_BY_NAZIONALITA_PATH+"/italiana"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andReturn();
	}
	@Test
	@Order(13)
	public void trovaLuoghiTramiteNazionalitaEComune() throws Exception {
		mock.perform(MockMvcRequestBuilders.get(FIND_ALL_BY_NAZIONALITA_AND_COMUNE+"/italiana/roma"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andReturn();
	}
	@Test
	@Order(14)
	public void save(){
		assertThat(repo.save(new Luogo(2,"piazzale delle piazze,13","interno 1","roma","00143","roma","italiana",false,0)))
				.extracting(Luogo::getId)
				.isNotEqualTo(1);
	}

	@Test
	@Order(15)
	public void findById(){
		assertThat(repo.findById(1L)).get()
				.extracting(Luogo::getRiga1)
				.isNotEqualTo("via delle vie,13");
	}

	@Test
	@Order(16)
	public void findAll(){
		List<Luogo> luoghi = repo.findAll();
		assertEquals(2,luoghi.size());
	}

	@Test
	@Order(17)
	public void findAllByIds(){
		List<Long> ids = Arrays.asList(1L,2L);
		List<Luogo> luoghi = repo.findAllById(ids);
		assertEquals(2,luoghi.size());
	}

	@Test
	@Order(18)
	public void findAllLuogoByRiga1AndComune(){
		List<Luogo> luoghi = repo.findAllLuogoByRiga1AndComune("piazzale delle piazze,13","roma");
		assertEquals(2,luoghi.size());
	}
	@Test
	@Order(19)
	public void findAllLuogoByRiga1(){
		List<Luogo> luoghi = repo.findAllLuogoByRiga1("piazzale delle piazze,13");
		assertEquals(2,luoghi.size());
	}
	@Test
	@Order(20)
	public void findLuogoByRiga1AndRiga2AndComune(){
		assertThat(repo.findAllByRiga1AndRiga2AndComune("piazzale delle piazze,13","interno 1","roma"))
				.isNotNull();
	}

	@Test
	@Order(21)
	public void findLuogoByRiga1AndRiga2AndComuneTest(){
		List<Luogo> luogo = repo.findAllByRiga1AndRiga2AndComune("piazzale delle piazze,13","interno 1","roma");
		assertEquals(2,luogo.size());
	}
	@Test
	@Order(22)
	public void findLuogoByRiga1AndRiga2(){
		assertThat(repo.findAllByRiga1AndRiga2("piazzale delle piazze,13","interno 1"))
				.isNotNull();
	}
	@Test
	@Order(23)
	public void findLuogoByRiga1AndRiga2Test2(){
		List<Luogo> luogo = repo.findAllByRiga1AndRiga2("piazzale delle piazze,13","interno 1");
		assertEquals(2,luogo.size());
	}

	@Test
	@Order(24)
	public void findAllLuogoByCap(){
		List<Luogo> luoghi = repo.findAllLuogoByCap("00143");
		assertEquals(2,luoghi.size());
	}
	@Test
	@Order(25)
	public void findAllLuogoByComune(){
		List<Luogo> luoghi = repo.findAllLuogoByComune("roma");
		assertEquals(2,luoghi.size());
	}
	@Test
	@Order(26)
	public void findAllLuogoByProvincia(){
		List<Luogo> luoghi = repo.findAllLuogoByProvincia("roma");
		assertEquals(2,luoghi.size());
	}
	@Test
	@Order(27)
	public void findAllLuogoByNazionalita(){
		List<Luogo> luoghi = repo.findAllLuogoByNazionalita("italiana");
		assertEquals(2,luoghi.size());
	}
	@Test
	@Order(28)
	public void findAllLuogoByNazionalitaAndComune(){
		List<Luogo> luoghi = repo.findAllLuogoByNazionalitaAndComune("italiana","roma");
		assertEquals(2,luoghi.size());
	}
}
