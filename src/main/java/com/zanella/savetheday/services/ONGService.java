package com.zanella.savetheday.services;

import com.zanella.savetheday.dto.ONGDto;
import com.zanella.savetheday.entities.ONG;
import com.zanella.savetheday.repositories.ONGRepository;
import com.zanella.savetheday.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ONGService {

    @Value("${img.prefix.client.profile}")
    private String prefix;

    @Autowired
    private ONGRepository repository;

    @Autowired
    private AmazonS3Service amazonS3Service;

    @Autowired
    private ImageService imageService;

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

        repository.save(obj);
        return obj;
    }

    @Transactional( rollbackOn = Exception.class )
    public ONG update(Integer id, ONGDto dto) {
        ONG obj = fromDto(dto);
        ONG newObj = findById(id);
        this.updateData(newObj, obj);
        return repository.save(newObj);
    }

    @Transactional
    public ONG updatePicture(Integer id, MultipartFile multipartFile) {
        ONG ong = this.findById(id);

        BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
        String fileName = prefix + ong.getId() + ".jpg";

        URI uri = amazonS3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");

        ong.setFoto(uri.toString());
        return repository.save(ong);
    }

    @Transactional( rollbackOn = Exception.class )
    public void delete(Integer id) {
        this.findById(id);
        repository.deleteById(id);
    }

    private void updateData(ONG newObj, ONG obj) {
        newObj.setDataFundacao(obj.getDataFundacao() != null ? obj.getDataFundacao() : newObj.getDataFundacao());
        newObj.setCNPJ(obj.getCNPJ() != null ? obj.getCNPJ() : newObj.getCNPJ());
        newObj.setTelefone(obj.getTelefone() != null ? obj.getTelefone() : newObj.getTelefone());
        newObj.setNome(obj.getNome() != null ? obj.getNome() : newObj.getNome());
        newObj.setSenha(obj.getSenha() != null ? obj.getSenha() : newObj.getSenha());
    }

    public ONG fromDto(ONGDto dto) {
        return new ONG(
                null, dto.getNome(), dto.getDataFundacao(),
                dto.getCNPJ(), dto.getTelefone(), dto.getEmail(),
                dto.getSenha(), null
        );
    }

}
