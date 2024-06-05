package br.com.fiap.javaGlobalSolution.service.pessoa;

import br.com.fiap.javaGlobalSolution.dto.request.pessoa.FuncionarioRequest;
import br.com.fiap.javaGlobalSolution.dto.response.pessoa.FuncionarioResponse;
import br.com.fiap.javaGlobalSolution.entity.pessoa.Funcionario;
import br.com.fiap.javaGlobalSolution.repository.pessoa.FuncionarioRepository;
import br.com.fiap.javaGlobalSolution.service.maquina.BarcoDeRecargaService;
import br.com.fiap.javaGlobalSolution.service.ServiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuncionarioService implements ServiceDTO<Funcionario, FuncionarioRequest, FuncionarioResponse> {

    @Autowired
    private final FuncionarioRepository repo;

    @Autowired
    private final BarcoDeRecargaService barcoDeRecargaService;

    @Autowired
    private final PessoaService pessoaService;

    @Override
    public Funcionario toEntity(FuncionarioRequest r) {

        var pessoa = pessoaService.findById(r.pessoa().id());

        return Funcionario.builder()
                .nome(r.nome())
                .funcao(r.funcao())
                .cpf(r.cpf())
                .email(r.email())
                .pessoa(pessoa)
                .build();
    }

    @Override
    public FuncionarioResponse toResponse(Funcionario e) {

        var barcoDeRecarga = e.getBarcoDeRecarga().stream().map(barcoDeRecargaService::toResponse).toList();

        var pessoa = pessoaService.toResponse(e.getPessoa());

        return FuncionarioResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .funcao(e.getFuncao())
                .cpf(e.getCpf())
                .email(e.getEmail())
                .barcoDeRecarga(barcoDeRecarga)
                .pessoa(pessoa)
                .build();
    }

    @Override
    public List<Funcionario> findAll(Example<Funcionario> example) {
        return repo.findAll(example);
    }

    @Override
    public Funcionario findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Funcionario save(Funcionario e) {
        return repo.save(e);
    }
}
