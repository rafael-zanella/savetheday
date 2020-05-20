package com.zanella.savetheday.services;

import com.zanella.savetheday.dto.CasoDto;
import com.zanella.savetheday.entities.Caso;
import com.zanella.savetheday.entities.ONG;
import com.zanella.savetheday.entities.enums.Status;
import com.zanella.savetheday.repositories.CasoRepository;
import com.zanella.savetheday.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CasoService {

    @Autowired
    private CasoRepository repository;

    @Autowired
    private ONGService ongService;
    
    public Caso findById(Integer id) {
        Optional<Caso> obj = repository.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Object not found! " + "Id: " + id) );
    }

    public List<Caso> findAll() {
        return repository.findAll();
    }

    public List<Caso> findAllByCidade(String cidade) {
        return repository.findAllByOng_Endereco_CidadeContaining(cidade);
    }

    @Transactional( rollbackOn = Exception.class)
    public Caso add(CasoDto dto) {
        Caso obj = fromDto(dto);

        obj.setId(null);
        obj.setStatus(Status.ABERTO);

        ONG ong = ongService.findById(obj.getOng().getId());
        obj.setOng(ong);
        ong.getCasos().add(obj);
        repository.save(obj);
        return obj;
    }

    @Transactional( rollbackOn = Exception.class )
    public Caso update(Integer id, CasoDto dto) {
        Caso newCaso = this.fromDto(dto);
        Caso caso = this.findById(id);
        this.updateData(caso, newCaso);
        return repository.save(caso);
    }

    @Transactional( rollbackOn = Exception.class )
    public void delete(Integer id) {
        Caso caso = this.findById(id);
        ONG ong = ongService.findById(caso.getOng().getId());
        ong.getCasos().remove(caso);
        repository.deleteById(caso.getId());
    }

    private void updateData(Caso caso, Caso newCaso) {
        caso.setTitulo(newCaso.getTitulo());
        caso.setDescricao(newCaso.getDescricao());
        caso.setDataPublicacao(newCaso.getDataPublicacao());
        caso.setValorArrecadado(newCaso.getValorArrecadado());
        caso.setValorMeta(newCaso.getValorMeta());
    }

    public Caso fromDto(CasoDto dto) {
        return new Caso(
                null, dto.getTitulo(), dto.getDescricao(), dto.getDataPublicacao(), dto.getValorArrecadado(),
                dto.getValorMeta(), null, dto.getOng()
        );
    }

}
