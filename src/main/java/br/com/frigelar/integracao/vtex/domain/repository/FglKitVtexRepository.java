package br.com.frigelar.integracao.vtex.domain.repository;

import br.com.frigelar.integracao.vtex.domain.entity.FglKitVtex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FglKitVtexRepository extends JpaRepository<FglKitVtex, Long> {

}
