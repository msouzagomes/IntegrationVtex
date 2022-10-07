package br.com.frigelar.integracao.vtex.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fgl_kit_ax", catalog = "fgl_produto", schema = "")
@NamedQueries({@NamedQuery(name = "FglKitAx.findAll", query = "SELECT f FROM FglKitAx f")})
public class FglKitAx implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "vtex_id")
    private Integer vtexId;
    @Column(name = "sku")
    private Integer sku;
    @Column(name = "codigo_kit")
    private String codigoKit;
    @Column(name = "descricao_completa")
    private String descricaoCompleta;
    @Column(name = "tipo_de_kit")
    private String tipoDeKit;
    @Column(name = "fabricante")
    private String fabricante;
    @Column(name = "marca")
    private String marca;
    @Column(name = "grupo_comercial")
    private String grupoComercial;
    @Column(name = "voltagem")
    private String voltagem;
    @Column(name = "perc_deconto_no_preco_de_venda")
    private String percDecontoNoPrecoDeVenda;
    @Column(name = "perc_de_ajuste_na_qtde_de_kg")
    private String percDeAjusteNaQtdeDeKg;
    @Column(name = "liberado_ecommerce")
    private String liberadoEcommerce;
    @Column(name = "destaque")
    private Character destaque;
    @Column(name = "validade")
    private String validade;
    @Column(name = "produto")
    private String produto;
    @Column(name = "capacidade")
    private String capacidade;
    @Column(name = "tecnologia")
    private String tecnologia;
    @Column(name = "garantia_estendida")
    private String garantiaEstendida;
    @Column(name = "referencia")
    private String referencia;
    @Column(name = "ref_do_fornecedor")
    private String refDoFornecedor;
    @Column(name = "ref_da_marca")
    private String refDaMarca;
    @Column(name = "fornecedor")
    private String fornecedor;
    @Column(name = "aplicacao_peca")
    private String aplicacaoPeca;
    @Column(name = "segmento")
    private String segmento;
    @Column(name = "departamentalizacao")
    private String departamentalizacao;
    @Column(name = "descriminacao_depart")
    private String descriminacaoDepart;
    @Column(name = "categoria")
    private String categoria;
    @Column(name = "subcategoria")
    private String subcategoria;


}