@Entity
public class TokenService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String counterName;
    private boolean active;

    // No need OneToMany now (simple project)
}
