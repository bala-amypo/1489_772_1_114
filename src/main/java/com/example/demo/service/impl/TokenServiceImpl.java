@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository repo;

    @Autowired
    private ServiceCounterRepository counterRepo;

    @Override
    public Token saveToken(Token token) {

        ServiceCounter counter = counterRepo.findById(token.getServiceCounterId())
                .orElseThrow(() -> new RuntimeException("Service Counter not found"));

        token.setServiceCounter(counter);
        return repo.save(token);
    }

    @Override
    public List<Token> getAllTokens() {
        return repo.findAll();
    }
}
