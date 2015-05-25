package br.ifpb.monteiro.scream.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Mauricio
 */
@Entity
@Table(name = "criterioAceitacao")
public class CriterioAceitacao implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 30, name = "descricao")
    private String descricao;
    
    @Column(nullable = false, length = 255, name = "estado_criterio")
    private Boolean estadoDocriterio;

//    @ManyToOne
//    @JoinColumn(name="id_criterio_aceitacao", referencedColumnName = "id_criterio_aceitacao")
//    private CriterioAceitacao criterioAceitacao;
//  
//    @OneToMany(mappedBy = "unimplemented_yet")
//    private List<Unimplemented_yet> unimplemented_yet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getEstadoDocriterio() {
        return estadoDocriterio;
    }

    public void setEstadoDocriterio(Boolean estadoDocriterio) {
        this.estadoDocriterio = estadoDocriterio;
    }

//    public CriterioAceitacao getCriterioAceitacao() {
//        return criterioAceitacao;
//    }
//
//    public void setCriterioAceitacao(CriterioAceitacao criterioAceitacao) {
//        this.criterioAceitacao = criterioAceitacao;
//    }
    
}
