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
@Table(name = "fgl_relacao_categsub_vtex_ax", catalog = "fgl_produto", schema = "")
@NamedQueries({@NamedQuery(name = "FglRelationVtexAx.findAll", query = "SELECT f FROM FglRelationVtexAx f")})
public class FglRelationVtexAx implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "category_id_ax")
    private Integer categoryIdAx;

    @Column(name = "name_category_ax")
    private String nameCategoryAx;

    @Column(name = "subcategory_id_ax")
    private Integer subCategoryIdAx;

    @Column(name = "name_subcategory_ax")
    private String nameSubCategoryAx;

    @Column(name = "category_id_vtex")
    private Integer categoryIdVtex;

    @Column(name = "name_category_vtex")
    private String nameCategoryVtex;

    @Column(name = "subcategory_id_vtex")
    private Integer subCategoryIdVtex;

    @Column(name = "name_subcategory_vtex")
    private String nameSubCategoryVtex;

    @Column(name = "isactive")
    private char isActive;

}
