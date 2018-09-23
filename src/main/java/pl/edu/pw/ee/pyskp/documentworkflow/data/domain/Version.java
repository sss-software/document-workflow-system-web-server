package pl.edu.pw.ee.pyskp.documentworkflow.data.domain;

import com.datastax.driver.core.DataType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Created by piotr on 11.12.16.
 */
@Data
@EqualsAndHashCode(of = {"fileId", "saveDate"})
@Table("version")
public class Version {
    @CassandraType(type = DataType.Name.BIGINT)
    @PrimaryKeyColumn(name = "file_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private Long fileId;

    @CassandraType(type = DataType.Name.TIMESTAMP)
    @PrimaryKeyColumn(name = "save_date", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private OffsetDateTime saveDate;

    @Column("version_string")
    @CassandraType(type = DataType.Name.TEXT)
    private String versionString;

    @Column("message")
    @CassandraType(type = DataType.Name.TEXT)
    private String message;

    @Column("author")
    @CassandraType(type = DataType.Name.UDT, userTypeName = "user_summary")
    private UserSummary author;

    @Column("file_content")
    @CassandraType(type = DataType.Name.BLOB)
    private ByteBuffer fileContent;

    @Column("check_sum")
    @CassandraType(type = DataType.Name.TEXT)
    private String checkSum;

    @Column("differences")
    @CassandraType(type = DataType.Name.LIST, typeArguments = DataType.Name.UDT, userTypeName = "difference")
    private List<Difference> differences;
}