package mx.com.dxesoft.suneofinanzas.web.rest;

import mx.com.dxesoft.suneofinanzas.SuneofinanzasApp;

import mx.com.dxesoft.suneofinanzas.domain.Montos;
import mx.com.dxesoft.suneofinanzas.domain.ConceptosDePago;
import mx.com.dxesoft.suneofinanzas.repository.MontosRepository;
import mx.com.dxesoft.suneofinanzas.service.MontosService;
import mx.com.dxesoft.suneofinanzas.web.rest.errors.ExceptionTranslator;

import mx.com.dxesoft.util.BidxichiMoney;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.money.MonetaryAmount;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MontosResource REST controller.
 *
 * @see MontosResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuneofinanzasApp.class)
public class MontosResourceIntTest {

    private static final BidxichiMoney DEFAULT_AMOUNT = BidxichiMoney.withCurrency("123", "MXN");
    private static final BidxichiMoney UPDATED_AMOUNT = BidxichiMoney.withCurrency("321", "MXN");

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CICLO = "AAAAAAAAAA";
    private static final String UPDATED_CICLO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    private static final BidxichiMoney DEFAULT_TAX = BidxichiMoney.withCurrency("23", "MXN");
    private static final BidxichiMoney UPDATED_TAX = BidxichiMoney.withCurrency("12", "MXN");

    @Autowired
    private MontosRepository montosRepository;

    @Autowired
    private MontosService montosService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMontosMockMvc;

    private Montos montos;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MontosResource montosResource = new MontosResource(montosService);
        this.restMontosMockMvc = MockMvcBuilders.standaloneSetup(montosResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Montos createEntity(EntityManager em) {
        Montos montos = new Montos()
            .amount(DEFAULT_AMOUNT)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .ciclo(DEFAULT_CICLO)
            .active(DEFAULT_ACTIVE)
            .tax(DEFAULT_TAX);
        // Add required entity
        ConceptosDePago concepto = ConceptosDePagoResourceIntTest.createEntity(em);
        em.persist(concepto);
        em.flush();
        montos.setConcepto(concepto);
        return montos;
    }

    @Before
    public void initTest() {
        montos = createEntity(em);
    }

    @Test
    @Transactional
    public void createMontos() throws Exception {
        int databaseSizeBeforeCreate = montosRepository.findAll().size();

        // Create the Montos
        restMontosMockMvc.perform(post("/api/montos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(montos)))
            .andExpect(status().isCreated());

        // Validate the Montos in the database
        List<Montos> montosList = montosRepository.findAll();
        assertThat(montosList).hasSize(databaseSizeBeforeCreate + 1);
        Montos testMontos = montosList.get(montosList.size() - 1);
        assertThat(testMontos.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testMontos.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testMontos.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testMontos.getCiclo()).isEqualTo(DEFAULT_CICLO);
        assertThat(testMontos.isActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testMontos.getTax()).isEqualTo(DEFAULT_TAX);
    }

    @Test
    @Transactional
    public void createMontosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = montosRepository.findAll().size();

        // Create the Montos with an existing ID
        montos.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMontosMockMvc.perform(post("/api/montos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(montos)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Montos> montosList = montosRepository.findAll();
        assertThat(montosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = montosRepository.findAll().size();
        // set the field null
        montos.setAmount(null);

        // Create the Montos, which fails.

        restMontosMockMvc.perform(post("/api/montos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(montos)))
            .andExpect(status().isBadRequest());

        List<Montos> montosList = montosRepository.findAll();
        assertThat(montosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMontos() throws Exception {
        // Initialize the database
        montosRepository.saveAndFlush(montos);

        // Get all the montosList
        restMontosMockMvc.perform(get("/api/montos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(montos.getId().intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.toString())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].ciclo").value(hasItem(DEFAULT_CICLO.toString())))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].tax").value(hasItem(DEFAULT_TAX.toString())));
    }

    @Test
    @Transactional
    public void getMontos() throws Exception {
        // Initialize the database
        montosRepository.saveAndFlush(montos);

        // Get the montos
        restMontosMockMvc.perform(get("/api/montos/{id}", montos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(montos.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.toString()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.ciclo").value(DEFAULT_CICLO.toString()))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.tax").value(DEFAULT_TAX.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMontos() throws Exception {
        // Get the montos
        restMontosMockMvc.perform(get("/api/montos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMontos() throws Exception {
        // Initialize the database
        montosService.save(montos);

        int databaseSizeBeforeUpdate = montosRepository.findAll().size();

        // Update the montos
        Montos updatedMontos = montosRepository.findOne(montos.getId());
        updatedMontos
            .amount(UPDATED_AMOUNT)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .ciclo(UPDATED_CICLO)
            .active(UPDATED_ACTIVE)
            .tax(UPDATED_TAX);

        restMontosMockMvc.perform(put("/api/montos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMontos)))
            .andExpect(status().isOk());

        // Validate the Montos in the database
        List<Montos> montosList = montosRepository.findAll();
        assertThat(montosList).hasSize(databaseSizeBeforeUpdate);
        Montos testMontos = montosList.get(montosList.size() - 1);
        assertThat(testMontos.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testMontos.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testMontos.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testMontos.getCiclo()).isEqualTo(UPDATED_CICLO);
        assertThat(testMontos.isActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testMontos.getTax()).isEqualTo(UPDATED_TAX);
    }

    @Test
    @Transactional
    public void updateNonExistingMontos() throws Exception {
        int databaseSizeBeforeUpdate = montosRepository.findAll().size();

        // Create the Montos

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMontosMockMvc.perform(put("/api/montos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(montos)))
            .andExpect(status().isCreated());

        // Validate the Montos in the database
        List<Montos> montosList = montosRepository.findAll();
        assertThat(montosList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMontos() throws Exception {
        // Initialize the database
        montosService.save(montos);

        int databaseSizeBeforeDelete = montosRepository.findAll().size();

        // Get the montos
        restMontosMockMvc.perform(delete("/api/montos/{id}", montos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Montos> montosList = montosRepository.findAll();
        assertThat(montosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Montos.class);
        Montos montos1 = new Montos();
        montos1.setId(1L);
        Montos montos2 = new Montos();
        montos2.setId(montos1.getId());
        assertThat(montos1).isEqualTo(montos2);
        montos2.setId(2L);
        assertThat(montos1).isNotEqualTo(montos2);
        montos1.setId(null);
        assertThat(montos1).isNotEqualTo(montos2);
    }
}
