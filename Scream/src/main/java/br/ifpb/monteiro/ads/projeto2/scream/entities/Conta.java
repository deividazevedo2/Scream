/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifpb.monteiro.ads.projeto2.scream.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Classe para modelar e comportar a entidade Conta
 * @author Mauricio
 */
@Entity
@Table(name = "conta")
@PrimaryKeyJoinColumn(name = "id")
public class Conta implements Identifiable {
    @Id
    @SequenceGenerator(name="id_conta",
                       sequenceName="id_conta",
                       allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_conta")
    @Column(name = "id", updatable=false)
    private Long id;
    
    @OneToMany(mappedBy="id_usuario_projeto") 
    private List<UsuarioProjeto> listUsuarioProjeto;
          
    @Column(nullable=false, length=30, name = "nome")
    private String nome;
    
    @Column(nullable=false, length=16, name = "senha")
    private String senha;
    
    @Column(nullable=false, length=10, name = "usuario")
    private String usuario;
    
    @Column(nullable=false, length=40, name = "email")
    private String email;

    /**
     *
     * @return id
     */
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UsuarioProjeto> getListUsuarioProjeto() {
        return listUsuarioProjeto;
    }

    public void setListUsuarioProjeto(List<UsuarioProjeto> listUsuarioProjeto) {
        this.listUsuarioProjeto = listUsuarioProjeto;
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
    
    
    
    

}