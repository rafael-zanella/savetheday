package com.zanella.savetheday.services;

import com.zanella.savetheday.entities.ONG;
import com.zanella.savetheday.repositories.ONGRepository;
import com.zanella.savetheday.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ONGService {

    @Autowired
    private ONGRepository repository;

    public ONG findById(Integer id) {
        Optional<ONG> obj = repository.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Object not found! " + "Id: " + id) );
    }


    @Transactional( rollbackOn = Exception.class)
    public ONG add(ONG obj) {
        obj.setId(null);
        return repository.save(obj);
    }
}
