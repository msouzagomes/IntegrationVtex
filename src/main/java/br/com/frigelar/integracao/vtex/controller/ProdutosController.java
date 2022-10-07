package br.com.frigelar.integracao.vtex.controller;

import br.com.frigelar.integracao.vtex.service.ChargeAttributesVtexAXService;
import br.com.frigelar.integracao.vtex.service.MigrationAttributesVtexService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.frigelar.integracao.vtex.dto.response.ProdutoResponse;
import br.com.frigelar.integracao.vtex.service.MigrationVtexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(tags = "Integracao")
@RequestMapping("api/v1/products")
@Slf4j
public class ProdutosController {

	private MigrationVtexService migrationVtexService;
	private MigrationAttributesVtexService migrationAttributesVtexService;
	private ChargeAttributesVtexAXService chargeAttributesVtexAXService;

	public ProdutosController(MigrationVtexService migrationVtexService,
							  MigrationAttributesVtexService migrationAttributesVtexService,
							  ChargeAttributesVtexAXService chargeAttributesVtexAXService) {
		super();
		this.migrationVtexService = migrationVtexService;
		this.migrationAttributesVtexService = migrationAttributesVtexService;
		this.chargeAttributesVtexAXService = chargeAttributesVtexAXService;
	}

	@ApiOperation(value = "Migrar dados Vtex para BackOffice")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "CREATED", response = ProdutoResponse.class),
			@ApiResponse(code = 401, message = "Não Autorizado"),
			@ApiResponse(code = 500, message = "Erro interno do servidor")})
	@PostMapping(value = "/migrationVtex", produces = "application/json")
	@ResponseBody
	public ResponseEntity<ProdutoResponse> databaseMigrationVtex(@RequestHeader("Authorization") String authorization)  {
		//log.info("Chamando databaseMigrationVtex");
		if (authorization.equalsIgnoreCase("true")) {
			return new  ResponseEntity<>(this.migrationVtexService.createsNewProductVtex(), HttpStatus.OK);
		}
		return new  ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@ApiOperation(value = "Carregar Tabela Relação Atributos Vtex/AX By Frigelar")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "CREATED", response = ProdutoResponse.class),
			@ApiResponse(code = 401, message = "Não Autorizado"),
			@ApiResponse(code = 500, message = "Erro interno do servidor")})
	@PostMapping(value = "/chargeRelationsAttributesVtexAX", produces = "application/json")
	public ResponseEntity<ProdutoResponse> chargeAttributesVtexAX(@RequestHeader("Authorization") String authorization)  {
		log.info("Chamando Charge Atributes Vtex/AX");
		if (authorization.equalsIgnoreCase("true")) {
			return new  ResponseEntity<>(this.chargeAttributesVtexAXService.createsNewAttributesRelationVtexAX(), HttpStatus.OK);
		}
		return new  ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@ApiOperation(value = "Migrar Atributos Vtex para BackOffice")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "CREATED", response = ProdutoResponse.class),
			@ApiResponse(code = 401, message = "Não Autorizado"),
			@ApiResponse(code = 500, message = "Erro interno do servidor")})
	@PostMapping(value = "/migrationAttributesVtex", produces = "application/json")
	public ResponseEntity<ProdutoResponse> databaseMigrationAtributesVtex(@RequestHeader("Authorization") String authorization)  {
		log.info("Chamando databaseMigration Atributes Vtex");
		if (authorization.equalsIgnoreCase("true")) {
			return new  ResponseEntity<>(this.migrationAttributesVtexService.createsNewAttributesVtex(), HttpStatus.OK);
		}
		return new  ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

}