package br.ifpb.monteiro.scream.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Markus
 */
@Entity
@Table(name = "produto")
public class Produto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100, name = "nome")
    private String nome;
    
    @Column(nullable = false, length = 200, name = "descricao")
    private String descricao;
    
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    
    @Temporal(TemporalType.DATE)
    private Date dataTermino;
    
    //Um produto possui vários itens de PB
    @OneToMany(mappedBy="produto", cascade=CascadeType.ALL)
    private List<ItemProductBacklog> listItensProduct;
    
//    //Um produto possui vários itens de definição de pronto
//    @OneToOne(mappedBy="produto", cascade=CascadeType.ALL)
//    private DefinicaoDePronto definicaoDePronto;
    
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
    
    public Date getDataInicio() {
        return dataInicio;
    }
    
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
    
    public Date getDataTermino() {
        return dataTermino;
    }
    
    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public List<ItemProductBacklog> getListItensProduct() {
        return listItensProduct;
    }
    
    public void setListItensProduct(List<ItemProductBacklog> listItensProduct) {
        this.listItensProduct = listItensProduct;
    }
//    
//    public List<Projeto> getListProjeto() {
//        return listProjeto;
//    }
//    
//    public void setListProjeto(List<Projeto> listProjeto) {
//        this.listProjeto = listProjeto;
//    }

    @Override
    public String toString() {
        return "Produto{" + "nome=" + nome + ", descricao=" + descricao +
                ", dataInicio=" + dataInicio + ", dataTermino=" + dataTermino + '}';
    }
    
    
    
}
