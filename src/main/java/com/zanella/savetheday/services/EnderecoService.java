package com.zanella.savetheday.services;

import com.zanella.savetheday.dto.EnderecoDto;
import com.zanella.savetheday.entities.Endereco;
import com.zanella.savetheday.entities.ONG;
import com.zanella.savetheday.repositories.EnderecoRepository;
import com.zanella.savetheday.services.exceptions.ObjectNotFoundException;
import com.zanella.savetheday.services.exceptions.OngAddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private ONGService ongService;

    public Endereco findById(Integer id) {
        Optional<Endereco> obj = repository.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Object not found! " + "Id: " + id) );
    }

    public List<Endereco> findAll() {
        return repository.findAll();
    }

    @Transactional( rollbackOn = Exception.class )
    public Endereco add(EnderecoDto dto) {
        Endereco obj = fromDto(dto);
        obj.setId(null);

        ONG ong = ongService.findById(obj.getOng().getId());
        if(ong.getEndereco() != null) {
            throw new OngAddressException("Esta ong ja tem um endereço.");
        }
        obj.setOng(ong);
        ong.setEndereco(obj);
        return repository.save(obj);
    }

    @Transactional( rollbackOn = Exception.class )
    public Endereco update(Integer id, EnderecoDto dto) {
        Endereco endereco = this.findById(id);
        Endereco newData = this.fromDto(dto);
        this.updateData(endereco, newData);
//        ONG ong = ongService.findById(endereco.getOng().getId());
//        ong.setEndereco(endereco);
        return repository.save(endereco);
    }

    private void updateData(Endereco endereco, Endereco newData) {
        endereco.setUF(newData.getUF());
        endereco.setCidade(newData.getCidade());
        endereco.setRua(newData.getRua());
        endereco.setBairro(newData.getBairro());
        endereco.setComplemento(newData.getComplemento());
        endereco.setNumero(newData.getNumero());
        endereco.setCEP(newData.getCEP());
    }


    public Endereco fromDto(EnderecoDto dto) {
        return new Endereco(null, dto.getUF(), dto.getCidade(), dto.getRua(), dto.getBairro(), dto.getComplemento(),
                dto.getNumero(), dto.getCEP(), dto.getOng());
    }
}
