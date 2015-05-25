package br.ifpb.monteiro.scream.entities;

import br.ifpb.monteiro.scream.entities.enums.DefinicaoDeProntoEnum;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Mauricio
 */
public class DefinicaoDePronto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 255, name = "descricao")
    private String descricao;
    
    @Column(nullable = false, length = 15, name = "tipo_definicao" ,updatable = false)
    private DefinicaoDeProntoEnum tipoDefinicao;
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

    public DefinicaoDeProntoEnum getTipoDefinicao() {
        return tipoDefinicao;
    }

    public void setTipoDefinicao(DefinicaoDeProntoEnum tipoDefinicao) {
        this.tipoDefinicao = tipoDefinicao;
    }
    
}
