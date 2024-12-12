package br.com.fiap.api.repository;

import br.com.fiap.api.model.Mensagem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;




public class MensagemRepositoryTest {

    @Mock
    private MensagemRepository mensagemRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setuup(){
        openMocks = MockitoAnnotations.openMocks( this);

    }
    @AfterEach
    void tearDown() throws  Exception{
        openMocks.close();
    }

    @Test
    void devePermitirRegistrarMensagem() {
        // Arrange
        var mensagem = gerarMesagem();
        when(mensagemRepository.save(any(Mensagem.class))).thenReturn(mensagem);

        //Act
        var mensagemRecebida = mensagemRepository.save(mensagem);

        //Assert
        assertThat(mensagemRecebida)
                .isNotNull()
                .isEqualTo(mensagem);

        verify(mensagemRepository, times(1)).save(any(Mensagem.class));
    }
    @Test
    void devePermitirBuscarMensagem(){
        // Arrange
        var id = UUID.randomUUID();
        var mensagem = gerarMesagem();
        mensagem.setId(id);

        when(mensagemRepository.findById(any(UUID.class)))
                .thenReturn(Optional.of(mensagem));

        //Act
        var mensagemRecebidaOpcional = mensagemRepository.findById(id);
        
        // Assert
        assertThat(mensagemRecebidaOpcional)
                .isPresent()
                .containsSame(mensagem);
        mensagemRecebidaOpcional.ifPresent(mensagemRecebida -> {
            assertThat(mensagemRecebida.getId()).isEqualTo(mensagem.getId());
        });

        verify(mensagemRepository, times(1)).findById(any(UUID.class));
                
    }


    private Mensagem gerarMesagem(){
        return Mensagem.builder()
                .usuario("Joao")
                .conteudo("conteudo da mensagem")
                .build();
    }

 }
