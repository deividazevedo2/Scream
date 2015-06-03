package br.ifpb.monteiro.scream.entities;

import br.ifpb.monteiro.scream.entities.enums.DefinicaoDeProntoEnum;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
@Table(name = "definicao_pronto")
public class DefinicaoDePronto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = true, length = 255, name = "descricao")
    private String descricao;
    
    @Column(nullable = false, length = 15, name = "tipo_definicao" ,updatable = false)
    private DefinicaoDeProntoEnum tipoDefinicao;

//    @OneToMany(mappedBy = "unimplemented_yet")
//    private List<already_implemented> already_implemented;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="projeto_id")
    private Projeto projeto;
    
    
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

    public DefinicaoDeProntoEnum getTipoDefinicao() {
        return tipoDefinicao;
    }

    public void setTipoDefinicao(DefinicaoDeProntoEnum tipoDefinicao) {
        this.tipoDefinicao = tipoDefinicao;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
  
    
    
}
