package pl.edu.pw.ee.pyskp.documentworkflow.data.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.pw.ee.pyskp.documentworkflow.data.domain.FileMetadata;
import pl.edu.pw.ee.pyskp.documentworkflow.data.domain.Task;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by piotr on 06.01.17.
 */
public interface FileMetadataRepository extends MongoRepository<FileMetadata, ObjectId> {
    long countByTaskIn(Collection<Task> tasks);

    void deleteByTask_Id(ObjectId taskId);

    Stream<FileMetadata> findByTask(Task task);

    long countByTask(Task task);

    List<FileMetadata> findByTaskIn(List<Task> tasks);

    List<FileMetadata> findByTask_Id(ObjectId taskId);
}
