package flow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import flow.domain.RMedia;

public interface MediaRepository extends JpaRepository<RMedia, Long> {

}
