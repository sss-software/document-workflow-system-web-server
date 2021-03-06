package pl.edu.pw.ee.pyskp.documentworkflow.data.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.pw.ee.pyskp.documentworkflow.data.domain.Project;
import pl.edu.pw.ee.pyskp.documentworkflow.data.domain.Task;
import pl.edu.pw.ee.pyskp.documentworkflow.data.domain.User;

import java.util.stream.Stream;

/**
 * Created by piotr on 13.12.16.
 */
public interface TaskRepository extends MongoRepository<Task, ObjectId> {
    Stream<Task> findByProject(Project project);

    Stream<Task> findByParticipantsContainingOrAdministrator(User participant, User admin);

    Stream<Task> findByProject_Id(ObjectId projectId);

    boolean existsByProject_IdAndName(ObjectId projectId, String name);
}
