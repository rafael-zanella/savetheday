package com.zanella.savetheday.services;

import com.zanella.savetheday.dto.ONGDto;
import com.zanella.savetheday.entities.ONG;
import com.zanella.savetheday.repositories.ONGRepository;
import com.zanella.savetheday.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ONGService {

    @Autowired
    private ONGRepository repository;

    public ONG findById(Integer id) {
        Optional<ONG> obj = repository.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Object not found! " + "Id: " + id) );
    }

    public List<ONG> findAll() {
        return repository.findAll();
    }

    @Transactional( rollbackOn = Exception.class)
    public ONG add(ONGDto dto) {
        ONG obj = fromDto(dto);
        obj.setId(null);
        return repository.save(obj);
    }

    @Transactional( rollbackOn = Exception.class )
    public ONG update(Integer id, ONGDto dto) {
        ONG obj = fromDto(dto);
        ONG newObj = findById(id);
        this.updateData(newObj, obj);
        return repository.save(newObj);
    }

    private void updateData(ONG newObj, ONG obj) {
        newObj.setDataFundacao(obj.getDataFundacao() != null ? obj.getDataFundacao() : newObj.getDataFundacao());
        newObj.setCNPJ(obj.getCNPJ() != null ? obj.getCNPJ() : newObj.getCNPJ());
        newObj.setTelefone(obj.getTelefone() != null ? obj.getTelefone() : newObj.getTelefone());
        newObj.setEndereco(obj.getEndereco() != null ? obj.getEndereco() : newObj.getEndereco());
        newObj.setNome(obj.getNome() != null ? obj.getNome() : newObj.getNome());
        newObj.setSenha(obj.getSenha() != null ? obj.getSenha() : newObj.getSenha());
    }

    private ONG fromDto(ONGDto dto) {
        return new ONG(
                null, dto.getNome(), dto.getDataFundacao(),
                dto.getCNPJ(), dto.getTelefone(), dto.getEmail(),
                dto.getSenha(), dto.getEndereco()
        );
    }
}
