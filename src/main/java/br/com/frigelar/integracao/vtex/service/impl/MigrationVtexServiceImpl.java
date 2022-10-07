package br.com.frigelar.integracao.vtex.service.impl;

import br.com.frigelar.integracao.vtex.consumer.VtexSystem;
import br.com.frigelar.integracao.vtex.domain.entity.FglKitAx;
import br.com.frigelar.integracao.vtex.domain.entity.FglKitVtex;
import br.com.frigelar.integracao.vtex.domain.repository.FglKitAxRepository;
import br.com.frigelar.integracao.vtex.domain.repository.FglKitVtexRepository;
import br.com.frigelar.integracao.vtex.dto.response.ProdutoResponse;
import br.com.frigelar.integracao.vtex.service.MigrationVtexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MigrationVtexServiceImpl implements MigrationVtexService {

	@Autowired
	private VtexSystem vtexSystem;

	private FglKitAxRepository fglKitAxRepository;
	private FglKitVtexRepository fglKitVtexRepository;

	public MigrationVtexServiceImpl(FglKitAxRepository fglKitAxRepository, FglKitVtexRepository fglKitVtexRepository) {
		this.fglKitAxRepository = fglKitAxRepository;
		this.fglKitVtexRepository = fglKitVtexRepository;
	}

	@Override
	public ProdutoResponse createsNewProductVtex() {
		log.info("Iniciando");
		try {
			List<FglKitAx> kit = this.fglKitAxRepository.findAll();
			kit.stream().forEach(k -> {
				try {

					if(	k.getSku() == 0 ||
						k.getVtexId() == 0) {
						log.info(
							String.format(" \n====== Kit AX ZERADO(ID: %s) (vtexId: %s or SKU: %s =======\n",
							k.getId(), k.getVtexId(), k.getSku())
						);
						return;
					}

					this.getMyHeap();
					this.saveVtex(k);
				} catch (Exception e) {
					throw new IllegalArgumentException(e.getMessage());
				}
			});
		} catch (Exception e) {
			log.error("00 - Erro migrar dados Vtex " +e.getMessage());
//			return ProdutoResponse.builder()
//					.code("1")
//					.message("Erro " + e.getMessage())
//					.build();
		}
		log.info("Finalizando");
		return ProdutoResponse.builder().code("0").message("Sucesso").build();
	}
	private void saveVtex(FglKitAx k){
		try {
			this.fglKitVtexRepository.save(
					FglKitVtex.builder()
							.vtexId(k.getVtexId())
							.skuId(k.getSku())
							.kitId(k.getCodigoKit())
							//.kitId(k.getVtexId().toString()) //Trocado pelo de cima
							.skuVtex(this.vtexSystem.jsonVtexSystem("/api/catalog/pvt/stockkeepingunit/".concat(k.getVtexId().toString())).trim())
							.eanVtex(this.vtexSystem.jsonVtexSystem("/api/catalog/pvt/stockkeepingunit/".concat(k.getSku().toString().concat("/ean"))).trim())
							.specificationVtex(this.vtexSystem.jsonVtexSystem("/api/catalog_system/pvt/products/".concat(k.getVtexId().toString().concat("/specification"))).trim())
							.productVtex(this.vtexSystem.jsonVtexSystem("/api/catalog/pvt/product/".concat(k.getVtexId().toString())).trim())
							.contextVtex(this.vtexSystem.jsonVtexSystem("/api/catalog_system/pvt/sku/stockkeepingunitbyid/".concat(k.getSku().toString())).trim())
							.variationsVtex(this.vtexSystem.jsonVtexSystem("/api/catalog_system/pub/products/variations/".concat(k.getVtexId().toString())).trim())
							.build());
		} catch (Exception e) {
			log.error("01 - Erro migrar dados Vtex " +e.getMessage() + " VtexId :" +k.getVtexId() + " skuId: " + k.getSku() + " kitId: " + k.getVtexId().toString());
		}
	}

	private void getMyHeap() {
		long heapSize = Runtime.getRuntime().totalMemory();
		long heapMaxSize = Runtime.getRuntime().maxMemory();
		long heapFreeSize = Runtime.getRuntime().freeMemory();

		log.info(String.format(
			"\n==========================================================================================="+
			"\n=== Max current: %s ||| Max: %s ||| Available: %s" +
			"\n===========================================================================================",
			heapSize, heapMaxSize, heapFreeSize)
		);
	}
}
