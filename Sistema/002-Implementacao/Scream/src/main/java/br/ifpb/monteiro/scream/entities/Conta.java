package br.ifpb.monteiro.scream.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe para modelar e comportar a entidade Conta
 *
 * @author Mauricio
 */
@Entity
@Table(name = "conta")
//@PrimaryKeyJoinColumn(name = "id")
public class Conta implements Serializable {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 30, name = "nome")
    private String nome;
    
    @Column(nullable = false, length = 255, name = "senha")
    private String senha;
    
    @Column(nullable = false, length = 10, name = "usuario")
    private String usuario;
    
    @Column(nullable = false, length = 40, name = "email")
    private String email;
    
    private String salt;
    
    @OneToMany(mappedBy = "usuario_projeto")
    private List<UsuarioProjeto> listUsuarioProjeto;
    
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
    
    public List<UsuarioProjeto> getListUsuarioProjeto() {
        return listUsuarioProjeto;
    }
    
    public void setListUsuarioProjeto(List<UsuarioProjeto> listUsuarioProjeto) {
        this.listUsuarioProjeto = listUsuarioProjeto;
    }
    
    public String getSalt() {
        return salt;
    }
    
    public void setSalt(String salt) {
        this.salt = salt;
    }
}
