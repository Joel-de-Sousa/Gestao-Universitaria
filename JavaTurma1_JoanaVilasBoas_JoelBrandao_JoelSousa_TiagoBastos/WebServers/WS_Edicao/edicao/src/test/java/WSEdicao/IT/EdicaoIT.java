package WSEdicao.IT;
/*
public class EdicaoIT {
@Autowired
    PropostaRepository propostaRepository;
    @Autowired
    PropostaService propostaService;
    @MockBean
    private UtilizadorRestRepository utilizadorRestRepository;
    @MockBean
    private OrganizacaoRestRepository organizacaoRestRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test

    void shouldPostNewPropostaIT() throws Exception {

        UtilizadorRestDTO utilizadorDouble = mock(UtilizadorRestDTO.class);
        when(utilizadorDouble.getCodUtilizador()).thenReturn(1);

        OrganizacaoRestDTO organizacaoDouble = mock(OrganizacaoRestDTO.class);
        when(organizacaoDouble.getNr()).thenReturn(257837248L);

        int generatedCodEdicao = Integer.parseInt(RandomStringUtils.randomNumeric(4));
        String generatedTitulo = RandomStringUtils.randomAlphanumeric(20);
        String generatedProblema = RandomStringUtils.randomAlphanumeric(20);
        String generatedObjectivo = RandomStringUtils.randomAlphanumeric(20);

        NewPropostaInfoDTO newPropostaInfoDTO = new NewPropostaInfoDTO(utilizadorDouble.getCodUtilizador(),
                (int) organizacaoDouble.getNr(), generatedCodEdicao, generatedTitulo,
                generatedProblema, generatedObjectivo);


        Optional<UtilizadorRestDTO> opUtilizador = Optional.of(utilizadorDouble);
        when(utilizadorRestRepository.findUtilizadorByCodUtilizador(1)).thenReturn(opUtilizador);
        Optional<OrganizacaoRestDTO> opOrganizacao = Optional.of(organizacaoDouble);
        when(organizacaoRestRepository.findOrganizacaoByNifOrganizacao(257837248L)).thenReturn(opOrganizacao);


        // POST

        MvcResult resultPost = mockMvc
                .perform(MockMvcRequestBuilders
                .post("/propostas")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(newPropostaInfoDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();


        String resultContentStr = resultPost.getResponse().getContentAsString();
        JSONObject resultJsonObject = new JSONObject( resultContentStr );

        int codUtilizador = newPropostaInfoDTO.getCodUtilizador();
        int nif = newPropostaInfoDTO.getNifOrganizacao();
        int codEdicao = newPropostaInfoDTO.getCodEdicao();
        String titulo = newPropostaInfoDTO.getTitulo();
        String problema = newPropostaInfoDTO.getProblema();
        String objetivo = newPropostaInfoDTO.getObjetivo();
        String estado = String.valueOf(Proposta.Estado.PENDENTE);

        assertEquals(codUtilizador, resultJsonObject.getInt("codUtilizador"));
        assertEquals(nif, resultJsonObject.getInt("nifOrganizacao"));
        assertEquals(codEdicao, resultJsonObject.getInt("codEdicao"));
        assertEquals(titulo , resultJsonObject.get("titulo"));
        assertEquals(problema , resultJsonObject.get("problema"));
        assertEquals(objetivo , resultJsonObject.get("objetivo"));
        assertEquals(estado , resultJsonObject.get("estado"));


        // GET Proposta/{codProposta = 1}

        MvcResult resultGet = mockMvc
                .perform(MockMvcRequestBuilders
                .get("/propostas/" + 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultContentStr3 = resultGet.getResponse().getContentAsString();
        JSONObject resultJsonObject3 = new JSONObject( resultContentStr3 );

        int codUtilizador3 = newPropostaInfoDTO.getCodUtilizador();
        int nif3 = newPropostaInfoDTO.getNifOrganizacao();
        int codEdicao3 = newPropostaInfoDTO.getCodEdicao();
        String titulo3 = newPropostaInfoDTO.getTitulo();
        String problema3 = newPropostaInfoDTO.getProblema();
        String objetivo3 = newPropostaInfoDTO.getObjetivo();
        String estado3 = String.valueOf(Proposta.Estado.PENDENTE);


        assertEquals(codUtilizador3, resultJsonObject3.getInt("codUtilizador"));
        assertEquals(nif3, resultJsonObject3.getInt("nifOrganizacao"));
        assertEquals(codEdicao3, resultJsonObject3.getInt("codEdicao"));
        assertEquals(titulo3 , resultJsonObject3.get("titulo"));
        assertEquals(problema3 , resultJsonObject3.get("problema"));
        assertEquals(objetivo3 , resultJsonObject3.get("objetivo"));
        assertEquals(estado3 , resultJsonObject3.get("estado"));

        assertNotNull(resultContentStr3);
    }
}*/