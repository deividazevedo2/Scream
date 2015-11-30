package br.edu.ifpb.scream.core;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe para modelar e comportar a entidade UserAccount
 *
 * @author Mauricio
 */
@Entity
@Table(name = "user_account")
//@PrimaryKeyJoinColumn(name = "id")
public class UserAccount implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, name = "nome" )
    private String nome;

    @Column(nullable = false, length = 255, name = "senha" )
    private String senha;

    @Column(nullable = false, length = 30, name = "usuario" ,unique=true)
    private String usuario;

    @Column(nullable = false, length = 40, name = "email" ,unique=true)
    private String email;
    
    private String salt;
    
    @Column(nullable = true, length = 40, name = "user_authorization_Item")
    private List<UserAuthorizationItem> userAuthorizationItem;

    //@OneToMany(mappedBy = "id_usuario_do_projeto")
    // List<UsuarioProjeto> listUsuarioProjeto;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSalt() {
		return salt;
	}
    
    public void setSalt(String salt) {
		this.salt = salt;
	}
}
