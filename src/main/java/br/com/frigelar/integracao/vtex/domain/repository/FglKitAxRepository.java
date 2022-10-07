package br.com.frigelar.integracao.vtex.domain.repository;

import br.com.frigelar.integracao.vtex.domain.entity.FglKitAx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FglKitAxRepository extends JpaRepository<FglKitAx, Long> {

}
