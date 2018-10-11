package pl.edu.pw.ee.pyskp.documentworkflow.data.domain;

import com.datastax.driver.core.DataType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "email")
@UserDefinedType("user_summary")
public class UserSummary {
    @Column("email")
    @CassandraType(type = DataType.Name.TEXT)
    private String email;

    @Column("first_name")
    @CassandraType(type = DataType.Name.TEXT)
    private String firstName;

    @Column("last_name")
    @CassandraType(type = DataType.Name.TEXT)
    private String lastName;

    public UserSummary(User user) {
        email = user.getEmail();
        firstName = user.getFirstName();
        lastName = user.getLastName();
    }
}
