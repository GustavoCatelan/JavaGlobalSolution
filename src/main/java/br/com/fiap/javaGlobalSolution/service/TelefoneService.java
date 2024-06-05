package br.com.fiap.javaGlobalSolution.service;

import br.com.fiap.javaGlobalSolution.dto.request.TelefoneRequest;
import br.com.fiap.javaGlobalSolution.dto.response.TelefoneResponse;
import br.com.fiap.javaGlobalSolution.entity.Fabricante;
import br.com.fiap.javaGlobalSolution.entity.Funcionario;
import br.com.fiap.javaGlobalSolution.entity.Telefone;
import br.com.fiap.javaGlobalSolution.repository.TelefoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TelefoneService implements ServiceDTO<Telefone, TelefoneRequest, TelefoneResponse>{

    @Autowired
    private final TelefoneRepository repo;

    @Autowired
    private final FabricanteService fabricanteService;

    @Autowired
    private final FuncionarioService funcionarioService;

    @Override
    public Telefone toEntity(TelefoneRequest r) {

        var fabricante = fabricanteService.findById(r.fabricante().id());
        if (Objects.isNull(fabricante)){
            return null;
        }
        Funcionario funcionario = null;

        if(Objects.nonNull(r.funcionario().id())){

             funcionario = funcionarioService.findById(r.funcionario().id());
            if (Objects.isNull(funcionario)){
                return null;
            }
        }



        return Telefone.builder()
                .ddi(r.ddi())
                .ddd(r.ddd())
                .numero(r.numero())
                .fabricante(fabricante)
                .funcionario(funcionario)
                .build();

    }

    @Override
    public TelefoneResponse toResponse(Telefone e) {

        var fabricante = fabricanteService.toResponse(e.getFabricante());
        if (Objects.isNull(e.getFabricante())){
            return null;
        }

        var funcionario = funcionarioService.toResponse(e.getFuncionario());
        if (Objects.isNull(funcionario)){
            return null;
        }

        return TelefoneResponse.builder()
                .id(e.getId())
                .ddi(e.getDdi())
                .ddd(e.getDdd())
                .numero(e.getNumero())
                .fabricante(fabricante)
                .funcionario(funcionario)
                .build();


    }


    @Override
    public List<Telefone> findAll(Example<Telefone> example) {
        return repo.findAll(example);
    }

    @Override
    public Telefone findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Telefone save(Telefone e) {
        return repo.save(e);
    }

}
