package mx.com.dxesoft.suneofinanzas.repository;

import mx.com.dxesoft.suneofinanzas.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
