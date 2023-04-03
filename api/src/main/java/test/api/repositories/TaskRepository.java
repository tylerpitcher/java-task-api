package test.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import test.api.models.Task;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
}