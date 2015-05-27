package br.ifpb.monteiro.scream.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Mauricio
 */
@Entity
@Table(name = "projeto")
public class Projeto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100, name = "nome")
    private String nome;
    
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    
    @Temporal(TemporalType.DATE)
    private Date dataTermino;
    
    @Column(nullable = false, length = 255, name = "finalizado")
    private Boolean isCompleted;
    
    @Column(nullable = false, length = 10, name = "duracao_sprint")
    private int sprintDuration;
    
    @OneToMany(mappedBy = "usuarioProjeto")
    private List<UsuarioProjeto> listUsuarioProjeto;
    
    @OneToMany(mappedBy = "criterioAceitacao")
    private List<CriterioAceitacao> criterios;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Boolean getIsCompleted() {
        return isCompleted;
    }
    
    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    
    public int getSprintDuration() {
        return sprintDuration;
    }
    
    public void setSprintDuration(int sprintDuration) {
        this.sprintDuration = sprintDuration;
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
//
//    public List<UsuarioProjeto> getListUsuarioProjeto() {
//        return listUsuarioProjeto;
//    }
//
//    public void setListUsuarioProjeto(List<UsuarioProjeto> listUsuarioProjeto) {
//        this.listUsuarioProjeto = listUsuarioProjeto;
//    }
//
//    public List<CriterioAceitacao> getCriterios() {
//        return criterios;
//    }
//
//    public void setCriterios(List<CriterioAceitacao> criterios) {
//        this.criterios = criterios;
//    }
//     
}
