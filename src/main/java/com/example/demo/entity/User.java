@Entity
@Table(
    name = "users",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
    }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    // ADMIN / STAFF
    private String role;

    public User() {}

    // Set default role automatically
    @PrePersist
    public void setDefaultRole() {
        if (this.role == null || this.role.isEmpty()) {
            this.role = "STAFF";
        }
    }

    // getters & setters
}
