@Service
public class QueuePositionServiceImpl implements QueuePositionService {

    @Autowired
    private QueuePositionRepository repo;

    @Autowired
    private TokenRepository tokenRepo;

    @Override
    public QueuePosition saveQueuePosition(QueuePosition qp) {

        Token token = tokenRepo.findById(qp.getTokenId())
                .orElseThrow(() -> new RuntimeException("Token not found"));

        qp.setToken(token);
        return repo.save(qp);
    }

    // other methods unchanged
}
