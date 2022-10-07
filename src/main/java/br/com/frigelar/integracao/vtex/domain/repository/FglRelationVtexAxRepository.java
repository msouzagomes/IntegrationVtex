package br.com.frigelar.integracao.vtex.domain.repository;

import br.com.frigelar.integracao.vtex.domain.entity.FglRelationVtexAx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FglRelationVtexAxRepository extends JpaRepository<FglRelationVtexAx, Long> {

}
