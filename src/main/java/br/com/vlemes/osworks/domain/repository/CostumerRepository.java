package br.com.vlemes.osworks.domain.repository;

import br.com.vlemes.osworks.domain.model.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer, Long> {

    List<Costumer> findByName(String name);

    List<Costumer> findByNameContaining(String name);

    Costumer findByEmail(String email);
}
