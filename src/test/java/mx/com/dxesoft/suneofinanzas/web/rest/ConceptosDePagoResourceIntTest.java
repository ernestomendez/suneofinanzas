package mx.com.dxesoft.suneofinanzas.web.rest;

import mx.com.dxesoft.suneofinanzas.SuneofinanzasApp;

import mx.com.dxesoft.suneofinanzas.domain.ConceptosDePago;
import mx.com.dxesoft.suneofinanzas.repository.ConceptosDePagoRepository;
import mx.com.dxesoft.suneofinanzas.service.ConceptosDePagoService;
import mx.com.dxesoft.suneofinanzas.web.rest.errors.ExceptionTranslator;

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

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ConceptosDePagoResource REST controller.
 *
 * @see ConceptosDePagoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuneofinanzasApp.class)
public class ConceptosDePagoResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATION_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DISABLED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DISABLED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_TAXPERCENTAJE = 0;
    private static final Integer UPDATED_TAXPERCENTAJE = 1;

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    @Autowired
    private ConceptosDePagoRepository conceptosDePagoRepository;

    @Autowired
    private ConceptosDePagoService conceptosDePagoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restConceptosDePagoMockMvc;

    private ConceptosDePago conceptosDePago;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ConceptosDePagoResource conceptosDePagoResource = new ConceptosDePagoResource(conceptosDePagoService);
        this.restConceptosDePagoMockMvc = MockMvcBuilders.standaloneSetup(conceptosDePagoResource)
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
    public static ConceptosDePago createEntity(EntityManager em) {
        ConceptosDePago conceptosDePago = new ConceptosDePago()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .creationDate(DEFAULT_CREATION_DATE)
            .disabledDate(DEFAULT_DISABLED_DATE)
            .taxpercentaje(DEFAULT_TAXPERCENTAJE)
            .active(DEFAULT_ACTIVE);
        return conceptosDePago;
    }

    @Before
    public void initTest() {
        conceptosDePago = createEntity(em);
    }

    @Test
    @Transactional
    public void createConceptosDePago() throws Exception {
        int databaseSizeBeforeCreate = conceptosDePagoRepository.findAll().size();

        // Create the ConceptosDePago
        restConceptosDePagoMockMvc.perform(post("/api/conceptos-de-pagos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conceptosDePago)))
            .andExpect(status().isCreated());

        // Validate the ConceptosDePago in the database
        List<ConceptosDePago> conceptosDePagoList = conceptosDePagoRepository.findAll();
        assertThat(conceptosDePagoList).hasSize(databaseSizeBeforeCreate + 1);
        ConceptosDePago testConceptosDePago = conceptosDePagoList.get(conceptosDePagoList.size() - 1);
        assertThat(testConceptosDePago.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testConceptosDePago.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testConceptosDePago.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testConceptosDePago.getDisabledDate()).isEqualTo(DEFAULT_DISABLED_DATE);
        assertThat(testConceptosDePago.getTaxpercentaje()).isEqualTo(DEFAULT_TAXPERCENTAJE);
        assertThat(testConceptosDePago.isActive()).isEqualTo(DEFAULT_ACTIVE);
    }

    @Test
    @Transactional
    public void createConceptosDePagoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = conceptosDePagoRepository.findAll().size();

        // Create the ConceptosDePago with an existing ID
        conceptosDePago.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConceptosDePagoMockMvc.perform(post("/api/conceptos-de-pagos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conceptosDePago)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ConceptosDePago> conceptosDePagoList = conceptosDePagoRepository.findAll();
        assertThat(conceptosDePagoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = conceptosDePagoRepository.findAll().size();
        // set the field null
        conceptosDePago.setName(null);

        // Create the ConceptosDePago, which fails.

        restConceptosDePagoMockMvc.perform(post("/api/conceptos-de-pagos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conceptosDePago)))
            .andExpect(status().isBadRequest());

        List<ConceptosDePago> conceptosDePagoList = conceptosDePagoRepository.findAll();
        assertThat(conceptosDePagoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTaxpercentajeIsRequired() throws Exception {
        int databaseSizeBeforeTest = conceptosDePagoRepository.findAll().size();
        // set the field null
        conceptosDePago.setTaxpercentaje(null);

        // Create the ConceptosDePago, which fails.

        restConceptosDePagoMockMvc.perform(post("/api/conceptos-de-pagos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conceptosDePago)))
            .andExpect(status().isBadRequest());

        List<ConceptosDePago> conceptosDePagoList = conceptosDePagoRepository.findAll();
        assertThat(conceptosDePagoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllConceptosDePagos() throws Exception {
        // Initialize the database
        conceptosDePagoRepository.saveAndFlush(conceptosDePago);

        // Get all the conceptosDePagoList
        restConceptosDePagoMockMvc.perform(get("/api/conceptos-de-pagos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(conceptosDePago.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].disabledDate").value(hasItem(DEFAULT_DISABLED_DATE.toString())))
            .andExpect(jsonPath("$.[*].taxpercentaje").value(hasItem(DEFAULT_TAXPERCENTAJE)))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())));
    }

    @Test
    @Transactional
    public void getConceptosDePago() throws Exception {
        // Initialize the database
        conceptosDePagoRepository.saveAndFlush(conceptosDePago);

        // Get the conceptosDePago
        restConceptosDePagoMockMvc.perform(get("/api/conceptos-de-pagos/{id}", conceptosDePago.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(conceptosDePago.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()))
            .andExpect(jsonPath("$.disabledDate").value(DEFAULT_DISABLED_DATE.toString()))
            .andExpect(jsonPath("$.taxpercentaje").value(DEFAULT_TAXPERCENTAJE))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingConceptosDePago() throws Exception {
        // Get the conceptosDePago
        restConceptosDePagoMockMvc.perform(get("/api/conceptos-de-pagos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConceptosDePago() throws Exception {
        // Initialize the database
        conceptosDePagoService.save(conceptosDePago);

        int databaseSizeBeforeUpdate = conceptosDePagoRepository.findAll().size();

        // Update the conceptosDePago
        ConceptosDePago updatedConceptosDePago = conceptosDePagoRepository.findOne(conceptosDePago.getId());
        updatedConceptosDePago
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .creationDate(UPDATED_CREATION_DATE)
            .disabledDate(UPDATED_DISABLED_DATE)
            .taxpercentaje(UPDATED_TAXPERCENTAJE)
            .active(UPDATED_ACTIVE);

        restConceptosDePagoMockMvc.perform(put("/api/conceptos-de-pagos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedConceptosDePago)))
            .andExpect(status().isOk());

        // Validate the ConceptosDePago in the database
        List<ConceptosDePago> conceptosDePagoList = conceptosDePagoRepository.findAll();
        assertThat(conceptosDePagoList).hasSize(databaseSizeBeforeUpdate);
        ConceptosDePago testConceptosDePago = conceptosDePagoList.get(conceptosDePagoList.size() - 1);
        assertThat(testConceptosDePago.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testConceptosDePago.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testConceptosDePago.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testConceptosDePago.getDisabledDate()).isEqualTo(UPDATED_DISABLED_DATE);
        assertThat(testConceptosDePago.getTaxpercentaje()).isEqualTo(UPDATED_TAXPERCENTAJE);
        assertThat(testConceptosDePago.isActive()).isEqualTo(UPDATED_ACTIVE);
    }

    @Test
    @Transactional
    public void updateNonExistingConceptosDePago() throws Exception {
        int databaseSizeBeforeUpdate = conceptosDePagoRepository.findAll().size();

        // Create the ConceptosDePago

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restConceptosDePagoMockMvc.perform(put("/api/conceptos-de-pagos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(conceptosDePago)))
            .andExpect(status().isCreated());

        // Validate the ConceptosDePago in the database
        List<ConceptosDePago> conceptosDePagoList = conceptosDePagoRepository.findAll();
        assertThat(conceptosDePagoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteConceptosDePago() throws Exception {
        // Initialize the database
        conceptosDePagoService.save(conceptosDePago);

        int databaseSizeBeforeDelete = conceptosDePagoRepository.findAll().size();

        // Get the conceptosDePago
        restConceptosDePagoMockMvc.perform(delete("/api/conceptos-de-pagos/{id}", conceptosDePago.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ConceptosDePago> conceptosDePagoList = conceptosDePagoRepository.findAll();
        assertThat(conceptosDePagoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConceptosDePago.class);
        ConceptosDePago conceptosDePago1 = new ConceptosDePago();
        conceptosDePago1.setId(1L);
        ConceptosDePago conceptosDePago2 = new ConceptosDePago();
        conceptosDePago2.setId(conceptosDePago1.getId());
        assertThat(conceptosDePago1).isEqualTo(conceptosDePago2);
        conceptosDePago2.setId(2L);
        assertThat(conceptosDePago1).isNotEqualTo(conceptosDePago2);
        conceptosDePago1.setId(null);
        assertThat(conceptosDePago1).isNotEqualTo(conceptosDePago2);
    }
}
