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
public class Projeto implements Identifiable, Serializable{

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

	//    @OneToMany(mappedBy = "projeto")
	//    private List<UsuarioProjeto> listUsuarioProjeto;

	//    @ManyToOne
	@OneToMany(mappedBy = "projeto")
	private List<DefinicaoDePronto> definicoesDePronto;


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

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


	public List<DefinicaoDePronto> getDefinicoesDePronto() {
		return definicoesDePronto;
	}

	public void setDefinicoesDePronto(List<DefinicaoDePronto> definicoesDePronto) {
		this.definicoesDePronto = definicoesDePronto;
	}
	@Override
	public boolean equals(Object obj){
		if(this==obj)
			return true;
		if(obj==null)
			return false;
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		final Projeto other = (Projeto) obj;
		if (id != other.id)
			return false;
		return true;
	}
}


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

