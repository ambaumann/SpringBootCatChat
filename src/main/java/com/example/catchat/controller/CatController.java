package com.example.catchat.controller;

import com.example.catchat.model.CatResource;
import com.example.catchat.repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rest/cat")
public class CatController {
    @Autowired
    private CatRepository catRepository;

    @GetMapping
    public List<CatResource> listCats() {
        return catRepository.findAll();
    }

    @PutMapping
    public void addCat(@RequestBody CatResource cat) {
        catRepository.save(cat);
    }

    @DeleteMapping
    public void terminateCat(@RequestBody CatResource cat) {
        catRepository.delete(cat);
    }

    @PostMapping
    public void updateCat(@RequestBody CatResource cat) {
        catRepository.save(cat);
    }


    //TODO hangle different media types. For now default to just the gif
    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces= MediaType.IMAGE_GIF_VALUE)
    public byte[] getCat(@PathVariable Long id, ModelMap model) throws Exception {

        CatResource cat = catRepository.getOne(id);

        if(cat != null && cat.getURL() != null) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(
                    new ByteArrayHttpMessageConverter());

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.IMAGE_GIF));

            HttpEntity<String> entity = new HttpEntity(headers);

            ResponseEntity<byte[]> response = restTemplate.exchange(cat.getURL(),
                    HttpMethod.GET, entity, byte[].class, "1");

            if (response.getStatusCode() == HttpStatus.OK) {
                //probably have to take more care here...
                //shrug
                Files.write(Paths.get("Cat" +cat.getName()+ cat.getId()+ ".gif"), response.getBody());
            }

            return response.getBody();
        } else {
            throw new Exception("Need to implement better error handling.");
        }
    }
}
