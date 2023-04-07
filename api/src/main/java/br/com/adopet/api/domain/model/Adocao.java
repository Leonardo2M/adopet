package br.com.adopet.api.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name= "adocao")
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Pet pet;
    @OneToOne
    private Tutor tutor;
    private LocalDateTime data = LocalDateTime.now();
    public Adocao(Tutor tutor, Pet pet) {
        this.tutor = tutor;
        this.pet = pet;
    }
}
