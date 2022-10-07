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
@Table(name = "fgl_kit_vtex", catalog = "fgl_produto", schema = "")
@NamedQueries({@NamedQuery(name = "FglKitVtex.findAll", query = "SELECT f FROM FglKitVtex f")})
public class FglKitVtex implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;
	@Column(name = "vtex_id")
	private Integer vtexId;
	@Column(name = "sku_id")
	private Integer skuId;
	@Column(name = "kit_id")
	private String kitId;
	@Column(name = "ean_vtex")
	private String eanVtex;
	@Column(name = "sku_vtex")
	private String skuVtex;
	@Column(name = "specification_vtex")
	private String specificationVtex;
	@Column(name = "product_vtex")
	private String productVtex;
	@Column(name = "context_vtex")
	private String contextVtex;
	@Column(name = "variations_vtex")
	private String variationsVtex;

}

