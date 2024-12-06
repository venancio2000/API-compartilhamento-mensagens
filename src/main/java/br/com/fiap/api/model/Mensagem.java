package br.com.fiap.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mensagem {

    @Id
    private UUID id;

    @NotEmpty(message = "usuario não pode estar vazio")
    @Column(nullable = false)
    private String usuario;

    @NotEmpty(message = "Conteudo não pode estar vazio")
    @Column(nullable = false)
    private String conteudo;

    @Builder.Default
    private LocalDateTime dataCriacaoMensagem = LocalDateTime.now();

    @Builder.Default
    private int gostei = 0;


}
