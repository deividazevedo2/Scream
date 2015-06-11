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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Mauricio
 */
@Entity
@Table(name = "item_product_backlog")
public class ItemProductBacklog  implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 300, name = "descricao")
    private String descricao;
    
    @Column(nullable = true, length = 30, name = "aceitoPO")
    private boolean aceitoPO;
    
    @Column(nullable = true, length = 100, name = "data_inicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    
    @Column(nullable = true, length = 100, name = "data_termino")
    @Temporal(TemporalType.DATE)
    private Date dataTermino;
    
    @Column(nullable = true, length = 10, name = "peso")
    private int peso;
    
    @Column(nullable = true, length = 100, name = "prioridade")
    private int prioridade;
    
    @Column(nullable = true, length = 100, name = "complexidade")
    private int complexidade;
    
    @Column(nullable = true, length = 100, name = "valor_ganho")
    private Double valorGanho;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="produto_id")
    private Produto produto;
    
    //Um item de PB possui vários critérios de aceitação
    @OneToMany(mappedBy="itemProductBacklog", cascade=CascadeType.ALL)
    private List<TesteAceitacao> listCriterioAceitacao;

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

    public boolean isAceitoPO() {
        return aceitoPO;
    }

    public void setAceitoPO(boolean aceitoPO) {
        this.aceitoPO = aceitoPO;
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

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getComplexidade() {
        return complexidade;
    }

    public void setComplexidade(int complexidade) {
        this.complexidade = complexidade;
    }

    public Double getValorGanho() {
        return valorGanho;
    }

    public void setValorGanho(Double valorGanho) {
        this.valorGanho = valorGanho;
    } 
    
    public Produto getProduto() {
		return produto;
	}
    public void setProduto(Produto produto) {
		this.produto = produto;
	}
//
//	public List<CriterioAceitacao> getListCriterioAceitacao() {
//		return listCriterioAceitacao;
//	}
//
//	public void setListCriterioAceitacao(
//			List<CriterioAceitacao> listCriterioAceitacao) {
//		this.listCriterioAceitacao = listCriterioAceitacao;
//	}
    
    
    
}
