package br.com.vlemes.osworks.domain.repository;

import br.com.vlemes.osworks.domain.model.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {
}
