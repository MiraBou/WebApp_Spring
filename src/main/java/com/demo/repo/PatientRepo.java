package  com.demo.repo;

import com.demo.entities.patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepo extends JpaRepository<patient, Long> {
        Page<patient> findByNomContains(String motCle, Pageable pageable);}
