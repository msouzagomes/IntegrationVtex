package br.com.frigelar.integracao.vtex.service.impl;

import br.com.frigelar.integracao.vtex.domain.entity.FglRelationVtexAx;
import br.com.frigelar.integracao.vtex.domain.repository.FglRelationVtexAxRepository;
import br.com.frigelar.integracao.vtex.dto.response.ProdutoResponse;
import br.com.frigelar.integracao.vtex.service.ChargeAttributesVtexAXService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Slf4j
@Service
public class ChargeAttributesVtexAXImpl implements ChargeAttributesVtexAXService {

    private String[] dadosgerais;

    private FglRelationVtexAxRepository fglRelationVtexAxRepository;

    public ChargeAttributesVtexAXImpl (FglRelationVtexAxRepository fglRelationVtexAxRepository) {
        super();
        this.fglRelationVtexAxRepository = fglRelationVtexAxRepository;
    }

    @Override
    public ProdutoResponse createsNewAttributesRelationVtexAX() {
        log.info("Iniciando");
        ChargeDataRelation();
        log.info("Finalizado");
        return null;
    }

    public ProdutoResponse ChargeDataRelation() {
        int conta, total;
        String texto = "1,17,Ar Condicionado,96,Split High Wall,2,Ar Condicionado,3,Split,1; " +
                "2,17,Ar Condicionado,84,Multi Split,2,Ar Condicionado,4,Multi Split,1; " +
                "3,17,Ar Condicionado,97,Split High Wall Window,2,Ar Condicionado,5,Split Window,1; " +
                "4,17,Ar Condicionado,94,Split Cassete,2,Ar Condicionado,6,Cassete,1; " +
                "5,17,Ar Condicionado,99,Split Teto,2,Ar Condicionado,7,Piso Teto,1; " +
                "6,17,Ar Condicionado,88,Portátil,2,Ar Condicionado,8,Portátil,1; " +
                "7,17,Ar Condicionado,74,Janela,2,Ar Condicionado,9,Ar Janela,1; " +
                "8,17,Ar Condicionado,95,Split Duto,2,Ar Condicionado,11,Duto,1; " +
                "9,17,Ar Condicionado,111,Splitão,2,Ar Condicionado,11,Duto,1; " +
                "10,17,Ar Condicionado,98,Split Piso E Teto,2,Ar Condicionado,13,Teto,1; " +
                "11,17,Ar Condicionado,59,Demais Produtos,2,Ar Condicionado,14,Acessórios,1; " +
                "12,18,Ar Condicionado Inverter,96,Split High Wall,2,Ar Condicionado,20,Split Inverter,1; " +
                "13,0,,0,,2,Ar Condicionado,28,Não Listado,1; " +
                "14,19,Eletrodomésticos,41,Adegas,17,Eletrodomésticos,29,Adega,1; " +
                "15,19,Eletrodomésticos,43,Aquecedores,17,Eletrodomésticos,35,Aquecedores,1; " +
                "16,19,Eletrodomésticos,46,Bebedouros De Garrafão,17,Eletrodomésticos,18,Bebedouro,1; " +
                "17,19,Eletrodomésticos,47,Bebedouros De Pressão,17,Eletrodomésticos,18,Bebedouro,1; " +
                "18,19,Eletrodomésticos,48,Bebedouros Industriais,17,Eletrodomésticos,18,Bebedouro,1; " +
                "19,19,Eletrodomésticos,51,Cervejeiras,17,Eletrodomésticos,21,Cervejeira,1; " +
                "20,19,Eletrodomésticos,52,Champanheiras,0,,0,,1; " +
                "21,19,Eletrodomésticos,55,Conservadores,17,Eletrodomésticos,24,Conservador,1; " +
                "22,19,Eletrodomésticos,70,Freezers,17,Eletrodomésticos,23,Freezer,1; " +
                "23,19,Eletrodomésticos,71,Frigobares,17,Eletrodomésticos,30,Frigobar,1; " +
                "24,19,Eletrodomésticos,89,Purificadores De Água,17,Eletrodomésticos,33,Purificador de Água,1; " +
                "25,19,Eletrodomésticos,92,Refrigeradores,17,Eletrodomésticos,22,Refrigerador,1; " +
                "26,20,Ventilação,57,Cortinas De Ar,36,Ventilação,37,Cortina de Ar,1; " +
                "27,20,Ventilação,105,Ventiladores,36,Ventilação,38,Ventilador,1; " +
                "28,20,Ventilação,64,Exaustores Comerciais,36,Ventilação,39,Exaustor,1; " +
                "29,20,Ventilação,65,Exaustores De Ambiente,36,Ventilação,39,Exaustor,1; " +
                "30,24,Peças Refrigeração Comercial,83,Motores,36,Ventilação,69,Micro Motor,1; " +
                "31,23,Compressores,44,Ar Condicionado,15,Peças e Acessórios,67,Peças de Reposição,1; " +
                "32,23,Compressores,91,Refrigeração Doméstica,15,Peças e Acessórios,67,Peças de Reposição,1; " +
                "33,25,Peças Eletrodomésticos,50,Centrífugas,15,Peças e Acessórios,56,Eletros e Refrigeração,1; " +
                "34,25,Peças Eletrodomésticos,61,Eletroportáteis,15,Peças e Acessórios,56,Eletros e Refrigeração,1; " +
                "35,25,Peças Eletrodomésticos,67,Fogões,15,Peças e Acessórios,56,Eletros e Refrigeração,1; " +
                "36,25,Peças Eletrodomésticos,68,Fornos De Micro-ondas,15,Peças e Acessórios,56,Eletros e Refrigeração,1; " +
                "37,25,Peças Eletrodomésticos,69,Fornos Elétricos,15,Peças e Acessórios,56,Eletros e Refrigeração,1; " +
                "38,25,Peças Eletrodomésticos,72,Geladeiras E Freezers,15,Peças e Acessórios,56,Eletros e Refrigeração,1; " +
                "39,25,Peças Eletrodomésticos,75,Lava E Seca,15,Peças e Acessórios,56,Eletros e Refrigeração,1; " +
                "40,25,Peças Eletrodomésticos,76,Lavadoras,15,Peças e Acessórios,56,Eletros e Refrigeração,1; " +
                "41,25,Peças Eletrodomésticos,77,Lavadoras De Louças,15,Peças e Acessórios,56,Eletros e Refrigeração,1; " +
                "42,25,Peças Eletrodomésticos,93,Secadoras,15,Peças e Acessórios,56,Eletros e Refrigeração,1; " +
                "43,25,Peças Eletrodomésticos,100,Tratamento Da Água,15,Peças e Acessórios,34,Filtros e Refis,1; " +
                "44,26,Peças Ar E Ventilação,73,Instalação De Ar,15,Peças e Acessórios,27,Kit Instalação,1; " +
                "45,26,Peças Ar E Ventilação,73,Instalação De Ar,15,Peças e Acessórios,26,Suporte,1; " +
                "46,21,Gases Refrigerantes,90,Refrigeração Comercial,15,Peças e Acessórios,59,Gases e Consumíveis,1; " +
                "47,26,Peças Ar E Ventilação,44,Ar Condicionado,15,Peças e Acessórios,32,Controle Remoto,1; " +
                "48,26,Peças Ar E Ventilação,44,Ar Condicionado,15,Peças e Acessórios,16,Cortina de Ar,1; " +
                "49,25,Peças Eletrodomésticos,75,Lava E Seca,15,Peças e Acessórios,57,Placas e Componentes,1; " +
                "50,25,Peças Eletrodomésticos,76,Lavadoras,15,Peças e Acessórios,57,Placas e Componentes,1; " +
                "51,25,Peças Eletrodomésticos,77,Lavadoras De Louças,15,Peças e Acessórios,57,Placas e Componentes,1; " +
                "52,25,Peças Eletrodomésticos,93,Secadoras,15,Peças e Acessórios,57,Placas e Componentes,1; " +
                "53,22,Tubos E Conexões,54,Conexões,15,Peças e Acessórios,58,Tubos, Mangueiras e Conexões,1; " +
                "54,22,Tubos E Conexões,60,Eletrodutos,15,Peças e Acessórios,58,Tubos, Mangueiras e Conexões,1; " +
                "55,22,Tubos E Conexões,101,Tubos De Alumínio,15,Peças e Acessórios,58,Tubos, Mangueiras e Conexões,1; " +
                "56,22,Tubos E Conexões,102,Tubos De Cobre,15,Peças e Acessórios,58,Tubos, Mangueiras e Conexões,1; " +
                "57,22,Tubos E Conexões,103,Tubos Isolantes,15,Peças e Acessórios,58,Tubos, Mangueiras e Conexões,1; " +
                "58,24,Peças Refrigeração Comercial,53,Componentes,15,Peças e Acessórios,67,Peças de Reposição,1; " +
                "59,24,Peças Refrigeração Comercial,63,Equipamentos,15,Peças e Acessórios,67,Peças de Reposição,1; " +
                "60,27,Ferramentas,49,Bombas De Vácuo,41,Ferramentas,50,Bombas de Vácuo,1; " +
                "61,27,Ferramentas,80,Manifolds,41,Ferramentas,51,Manifolds,1; " +
                "62,27,Ferramentas,78,Maçaricos,41,Ferramentas,62,Maçarico para Solda,1; " +
                "63,27,Ferramentas,66,Flangeadores,41,Ferramentas,52,Flangeadores,1; " +
                "64,27,Ferramentas,104,Vacuômetros,41,Ferramentas,44,Ferramentas de Medição,1; " +
                "65,27,Ferramentas,79,Mangueiras De Manifolds,41,Ferramentas,51,Manifolds,1; " +
                "66,27,Ferramentas,56,Cortadores De Tubos,41,Ferramentas,61,Cortador de Tubos,1; " +
                "67,27,Ferramentas,58,Curvadores De Tubos,41,Ferramentas,43,Ferramentas Manuais,1; " +
                "68,27,Ferramentas,62,Engates rápidos,15,Peças e Acessórios,58,Tubos, Mangueiras e Conexões,1; " +
                "69,27,Ferramentas,81,Manômetros,41,Ferramentas,60,Instrumentos de Medição,1; " +
                "70,27,Ferramentas,42,Alargadores De Tubos,41,Ferramentas,43,Ferramentas Manuais,1; " +
                "71,27,Ferramentas,45,Balanças,41,Ferramentas,60,Instrumentos de Medição,1; " +
                "72,27,Ferramentas,87,Pente De Aletas,41,Ferramentas,43,Ferramentas Manuais,1; " +
                "73,27,Ferramentas,59,Demais Produtos,15,Peças e Acessórios,73,Higiene e Segurança,1; " +
                "74,27,Ferramentas,59,Demais Produtos,41,Ferramentas,68,EPIs,1; " +
                "75,27,Ferramentas,59,Demais Produtos,41,Ferramentas,42,Ferramentas Elétricas,1; " +
                "76,0,,0,,41,Ferramentas,44,Ferramentas de Medição,1; " +
                "77,0,,0,,41,Ferramentas,53,Bombas de Limpeza,1; " +
                "78,0,,0,,41,Ferramentas,54,Bomba de Drenagem,1; " +
                "79,28,Câmaras Frigoríficas,82,Moduladas,40,Câmaras Frias,45,Câmaras Moduladas,1; " +
                "80,28,Câmaras Frigoríficas,82,Moduladas,40,Câmaras Frias,46,Portas,1; " +
                "81,28,Câmaras Frigoríficas,82,Moduladas,40,Câmaras Frias,47,Painéis,1; " +
                "82,28,Câmaras Frigoríficas,82,Moduladas,40,Câmaras Frias,48,Acessórios,1; " +
                "83,19,Eletrodomésticos,59,Demais Produtos,17,Eletrodomésticos,25,Umidificador,1; " +
                "84,0,,0,,17,Eletrodomésticos,31,Ventilação,1; " +
                "85,19,Eletrodomésticos,59,Demais Produtos,17,Eletrodomésticos,72,Outros Eletros,1; " +
                "86,19,Eletrodomésticos,59,Demais Produtos,36,Ventilação,49,Climatizador,1; " +
                "87,0,,0,,41,Ferramentas,63,Kits funcionais,1; " +
                "88,31,Ar Particionado,107,Fan Coil,0,,0,,1; " +
                "89,31,Ar Particionado,108,Self,0,,0,,1; " +
                "90,31,Ar Particionado,109,Unidades Condensadoras,0,,0,,1; " +
                "91,31,Ar Particionado,110,Unidades Evaporadoras,0,,0,,1";

        this.dadosgerais = texto.split(";");
        total = this.dadosgerais.length;
        log.info("Total: "+total);
        conta = 0;
        while (conta < total) {
            String[] valores = new String[10];
            String[] valor = this.dadosgerais;
            valores = this.dadosgerais[conta].split(",");
            try {
                this.sendDataRelationTableVtexAx(valores);
            } catch (Exception ex) {
                log.error("Erro ao salvar os dados de Relacao VTEX/AX - " + ex.getMessage());
                return ProdutoResponse.builder()
                        .code("1")
                        .message("Erro ao salvar os dados de Relacao VTEX/AX - " + ex.getMessage())
                        .build();
            }
            log.info("CONTADOR: " + conta);
            conta++;
        }
        return null;
    }

    private void sendDataRelationTableVtexAx(String[] valores) {
        try {
			 this.fglRelationVtexAxRepository.save(FglRelationVtexAx
                     .builder()
                     .categoryIdAx(Integer.parseInt(valores[1]))
                     .nameCategoryAx(valores[2])
                     .subCategoryIdAx(Integer.parseInt(valores[3]))
                     .nameSubCategoryAx(valores[4])
                     .categoryIdVtex(Integer.parseInt(valores[5]))
                     .nameCategoryVtex(valores[6])
                     .subCategoryIdVtex(Integer.parseInt(valores[7]))
                     .nameSubCategoryVtex(valores[8])
                     .isActive('1')
                     .build());
		} catch (Exception cg) {
			log.error("ID : "+ valores[0] + " CategoriaAX: "+ valores[1] + " Nome CategoriaAX: "+ valores[2] );
			log.error("Erro ao migrar os dados  " + cg.getMessage());
		}
    }


}
