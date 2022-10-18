package com.unit17.seliganatrilha.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Entity
@Table(name = "TB_TRILHA")
@Getter
@Setter
@NoArgsConstructor
public class Trilha {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(nullable = false, unique = true)
    private UUID idTrilha;

    @Column(nullable = false, unique = true)
    private String nome;
    
    @Column(nullable = false)
    private String descricao;
    
    @Column(nullable = false)
    private Boolean status;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @ToString.Exclude
    Usuario usuario;
    public Trilha(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.status = false;
    }
}
