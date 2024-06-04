package br.com.fiap.javaGlobalSolution.service;

import br.com.fiap.javaGlobalSolution.dto.request.FuncionarioRequest;
import br.com.fiap.javaGlobalSolution.dto.response.FuncionarioResponse;
import br.com.fiap.javaGlobalSolution.entity.Funcionario;
import br.com.fiap.javaGlobalSolution.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FuncionarioService implements ServiceDTO<Funcionario, FuncionarioRequest, FuncionarioResponse> {

    @Autowired
    private final FuncionarioRepository repo;

    @Autowired
    private final BarcoDeRecargaService barcoDeRecargaService;

    @Override
    public Funcionario toEntity(FuncionarioRequest r) {

        return Funcionario.builder()
                .nome(r.nome())
                .funcao(r.funcao())
                .cpf(r.cpf())
                .email(r.email())
                .build();
    }

    @Override
    public FuncionarioResponse toResponse(Funcionario e) {

        var barcoDeRecarga = e.getBarcoDeRecarga().stream().map(barcoDeRecargaService::toResponse).toList();

        return FuncionarioResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .funcao(e.getFuncao())
                .cpf(e.getCpf())
                .email(e.getEmail())
                .barcoDeRecarga(barcoDeRecarga)
                .build();
    }

    @Override
    public List<Funcionario> findAll(Example<Funcionario> example) {
        return repo.findAll(example);
    }

    @Override
    public Optional<Funcionario> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Funcionario save(Funcionario e) {
        return repo.save(e);
    }
}
