@Service
public class TokenLogServiceImpl implements TokenLogService {

    @Autowired
    private TokenLogRepository repo;

    @Autowired
    private TokenRepository tokenRepo;

    @Override
    public TokenLog saveLog(TokenLog log) {

        Token token = tokenRepo.findById(log.getTokenId())
                .orElseThrow(() -> new RuntimeException("Token not found"));

        log.setToken(token);
        return repo.save(log);
    }

    @Override
    public List<TokenLog> getLogsByTokenId(Long tokenId) {
        return repo.findByToken_IdOrderByLoggedAtAsc(tokenId);
    }
}
