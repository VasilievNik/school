package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.service.StudentService;
import ru.hogwarts.school.model.Student;

import javax.persistence.JoinColumn;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByAge(int age);
    List<Student> findByAgeBetween(int min, int max);
    List<Student> readAllByNameTrue();


    @Query(value = "SELECT COUNT(*) FROM Student", nativeQuery = true)
    Integer getAmount();

    @Query(value = "SELECT AVG(age) from Student", nativeQuery = true)
    Integer getAvgAge();

    @Query(value = "SELECT * FROM Student LIMIT 4 AND OFFSET (COUNT(*)-5)", nativeQuery = true)
    List<Student> getLastFive();
}
