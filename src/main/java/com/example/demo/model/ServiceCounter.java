@Entity
public class ServiceCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String counterName;

    private Boolean isActive;

    public Long getId() { return id; }
    public String getCounterName() { return counterName; }
    public Boolean getIsActive() { return isActive; }

    public void setId(Long id) { this.id = id; }
    public void setCounterName(String counterName) { this.counterName = counterName; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}
