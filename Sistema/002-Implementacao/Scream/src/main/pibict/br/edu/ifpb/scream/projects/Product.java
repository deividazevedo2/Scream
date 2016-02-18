package br.edu.ifpb.scream.projects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Hugo Correia
 */
@Entity
@Table(name = "product")
public class Product implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100, name = "name")
    private String name;
    
    @Column(nullable = false, length = 200, name = "description")
    private String description;    
    
//    @OneToMany(mappedBy="produto", cascade=CascadeType.ALL)
//    private List<ItemProductBacklog> listItensProduct;
    
//    @OneToMany(mappedBy="produto", cascade=CascadeType.ALL)
//    private List<Projeto> listProjeto;
    
//    //Um produto possui vários itens de definição de pronto
//    @OneToOne(mappedBy="produto", cascade=CascadeType.ALL)
//    private DefinicaoDePronto definicaoDePronto;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String descricao) {
        this.description = descricao;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
//    public List<ItemProductBacklog> getListItensProduct() {
//        return listItensProduct;
//    }
//    
//    public void setListItensProduct(List<ItemProductBacklog> listItensProduct) {
//        this.listItensProduct = listItensProduct;
//    }


    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && id != null)
            ? id.equals(((Product) other).id)
            : (other == this);
    }

    @Override
    public int hashCode() {
        return (id != null) 
            ? (getClass().hashCode() + id.hashCode())
            : super.hashCode();
    }
    
    @Override
    public String toString() {
        return "Product[" + "name=" + name + ", description=" + description+"]";
    }
      
}
