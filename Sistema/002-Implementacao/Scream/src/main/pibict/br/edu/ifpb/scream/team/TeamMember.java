package br.edu.ifpb.scream.team;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.management.relation.Role;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.ifpb.scream.core.UserAccount;
import br.edu.ifpb.scream.team.Roles;

/**
 * Classe para modelar e comportar a entidade
 *
 * @author Mauricio
 */
@Entity
@Table(name = "team_member")
//@PrimaryKeyJoinColumn(name = "id")
public class TeamMember implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, length = 30, name = "horario")
    private Timestamp horario;

    //GALERA ESSE ATRIBUTO ESTAVA admin MAS É UMA PALAVRA RESERVADA DO SQL E PODERIA DAR PROBLEMA
    @Column(nullable = false, length = 30, name = "administrador")
    private Boolean administrador;
    
    @ManyToOne @JoinColumn(name="id_userAccount")
    private UserAccount userAccount;
    
    @Column(nullable = true, name = "roles")
    private Roles roles;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getHorario() {
        return horario;
    }

    public void setHorario(Timestamp horario) {
        this.horario = horario;
    }

    public Boolean getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }
    
//    public Projeto getProjeto() {
//		return projeto;
//	}
//    public void setProjeto(Projeto projeto) {
//		this.projeto = projeto;
//	}
    
    public UserAccount getUserAccount() {
		return userAccount;
	}
    public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
    public Roles getRoles() {
		return roles;
	}
    public void setRoles(Roles roles) {
		this.roles = roles;
	}

}