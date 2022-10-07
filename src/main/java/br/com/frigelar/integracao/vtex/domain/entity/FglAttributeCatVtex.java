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
@Table(name = "fgl_atributos_categ_vtex", catalog = "fgl_produto", schema = "")
@NamedQueries({@NamedQuery(name = "FglAttributeCatVtex.findAll", query = "SELECT f FROM FglAttributeCatVtex f")})
public class FglAttributeCatVtex implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "subcateg_vtex_id")
    private Integer subcategVtexId;

    @Column(name = "subcateg_ax_id")
    private Integer subcategAxId;

    @Column(name = "dados_vtex")
    private String dadosVtex;
}
