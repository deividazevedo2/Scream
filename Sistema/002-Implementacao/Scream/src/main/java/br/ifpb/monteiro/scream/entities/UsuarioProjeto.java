package br.ifpb.monteiro.scream.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Classe para modelar e comportar a entidade
 *
 * @author Mauricio
 */
@Entity
@Table(name = "usuario_projeto")
//@PrimaryKeyJoinColumn(name = "id")
public class UsuarioProjeto implements Serializable {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 30, name = "horario")
    private Timestamp horario;
    
    //GALERA ESSE ATRIBUTO ESTAVA admin MAS É UMA PALAVRA RESERVADA DO SQL E PODERIA DAR PROBLEMA
    @Column(nullable = false, length = 30, name = "administrador")
    private Boolean administrador;
    
    @ManyToOne
    @JoinColumn(name="id_usuario_do_projeto", referencedColumnName = "id_usuario_do_projeto")
    private UsuarioProjeto usuarioProjeto;
    
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
    
    public UsuarioProjeto getUsuarioProjeto() {
        return usuarioProjeto;
    }
    
    public void setUsuarioProjeto(UsuarioProjeto usuarioProjeto) {
        this.usuarioProjeto = usuarioProjeto;
    }
    
}
