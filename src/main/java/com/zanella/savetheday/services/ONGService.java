package com.zanella.savetheday.services;

import com.zanella.savetheday.entities.ONG;
import com.zanella.savetheday.repositories.ONGRepository;
import com.zanella.savetheday.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
    public ONG add(ONG obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    @Transactional( rollbackOn = Exception.class )
    public ONG update(Integer id, ONG obj) {
        ONG newObj = this.findById(id);
        this.updateData(newObj, obj);
        return repository.save(newObj);
    }

    private void updateData(ONG newObj, ONG obj) {
        newObj.setDataFundacao(obj.getDataFundacao() != null ? obj.getDataFundacao() : newObj.getDataFundacao());
        newObj.setCNPJ(obj.getCNPJ() != null ? obj.getCNPJ() : newObj.getCNPJ());
        newObj.setTelefone(obj.getTelefone() != null ? obj.getTelefone() : newObj.getTelefone());
        newObj.setEndereco(obj.getEndereco() != null ? obj.getEndereco() : newObj.getEndereco());

        if( obj.getNome() != null ) {
            newObj.setNome(obj.getNome());
        }

        if( obj.getSenha() != null ) {
            newObj.setSenha(obj.getSenha());
        }
    }
}
