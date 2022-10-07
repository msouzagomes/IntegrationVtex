package br.com.frigelar.integracao.vtex.service.impl;

import br.com.frigelar.integracao.vtex.consumer.VtexSystem;
import br.com.frigelar.integracao.vtex.domain.entity.FglAttributeCatVtex;
import br.com.frigelar.integracao.vtex.domain.entity.FglRelationVtexAx;
import br.com.frigelar.integracao.vtex.domain.repository.FglAttributesRepository;
import br.com.frigelar.integracao.vtex.domain.repository.FglRelationVtexAxRepository;
import br.com.frigelar.integracao.vtex.dto.response.ProdutoResponse;
import br.com.frigelar.integracao.vtex.service.MigrationAttributesVtexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
class MigrationAttributesVtexServiceImpl implements MigrationAttributesVtexService {

    @Autowired
    private VtexSystem vtexSystem;

    private FglRelationVtexAxRepository fglRelationVtexAxRepository;
    private FglAttributesRepository fglAttributesRepository;

    public MigrationAttributesVtexServiceImpl(FglRelationVtexAxRepository fglRelationVtexAxRepository, FglAttributesRepository fglAttributesRepository) {
        this.fglRelationVtexAxRepository = fglRelationVtexAxRepository;
        this.fglAttributesRepository = fglAttributesRepository;
    }

    @Override
    public ProdutoResponse createsNewAttributesVtex() {
        log.info("Iniciando");
        try {
            List<FglRelationVtexAx> category = this.fglRelationVtexAxRepository.findAll();
            //List<FglRelacaoVtexAx> category = this.fglRelacaoVtexAxRepository.listRestrict();
            //System.out.println("Objeto=" + category);
            category.stream().forEach(c -> {
                try {
                    if (c.getCategoryIdAx() == 0 || c.getCategoryIdVtex() == 0)  {
                        log.info(String.format(" \n====== Categorias AX/VTEX  -  ZERADO(ID: %s) (CategoryAXId: %s or CategoryVtexId: %s =======\n",
                                c.getId(), c.getCategoryIdAx(), c.getCategoryIdVtex())
                        );
                        return;
                    }
                    this.saveAttributesVtex(c);
                } catch (Exception e) {
                    throw new IllegalArgumentException(e.getMessage());
                }
            });

        } catch (Exception e) {
            log.error("00 - Erro migrar Atributos Vtex " +e.getMessage());
        }
        log.info("Finalizado");
        return ProdutoResponse.builder().code("0").message("Sucesso").build();
    }

    private void saveAttributesVtex(FglRelationVtexAx c){
        try {
            this.fglAttributesRepository.save(
                    FglAttributeCatVtex.builder()
                            .subcategVtexId(c.getSubCategoryIdVtex())
                            .subcategAxId(c.getSubCategoryIdAx())
                            .dadosVtex(this.vtexSystem.jsonVtexSystem("/api/catalog_system/pub/specification/field/listTreeByCategoryId/".concat(c.getSubCategoryIdVtex().toString())).trim())
                            .build()
            );
        } catch (Exception e) {
            log.error("01 - Erro migrar Atributos Vtex " +e.getMessage() + " Id :" + c.getId() + " CategoryAXId: " + c.getCategoryIdAx()+ " kitId: " + c.getCategoryIdVtex().toString());
        }
    }
}
