package br.ifpb.monteiro.ads.projeto2.scream.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Classe para modelar e comportar a entidade Conta
 *
 * @author Mauricio
 */
@Entity
@Table(name = "conta")
@PrimaryKeyJoinColumn(name = "id")
public class Conta implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@OneToMany(mappedBy = "id_usuario_do_projeto")
    // List<UsuarioProjeto> listUsuarioProjeto;
    @Column(nullable = false, length = 30, name = "nome")
    private String nome;

    @Column(nullable = false, length = 16, name = "senha")
    private String senha;

    @Column(nullable = false, length = 10, name = "usuario")
    private String usuario;

    @Column(nullable = false, length = 40, name = "email")
    private String email;

    public Long getId() {
        return id;
    }

     public void setId(Long id) {
        this.id = id;
    }
    
    //public List<UsuarioProjeto> getListUsuarioProjeto() {
    //    return listUsuarioProjeto;
    //}
    //public void setListUsuarioProjeto(List<UsuarioProjeto> listUsuarioProjeto) {
    //    this.listUsuarioProjeto = listUsuarioProjeto;
    //}
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
}
