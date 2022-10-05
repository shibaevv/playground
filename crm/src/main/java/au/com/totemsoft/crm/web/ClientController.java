package au.com.totemsoft.crm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import au.com.totemsoft.crm.exception.NotAllowedException;
import au.com.totemsoft.crm.exception.NotFoundException;
import au.com.totemsoft.crm.jpa.ClientRepository;
import au.com.totemsoft.crm.model.ClientEntity;

@RestController
public class ClientController {

    @Autowired private ClientRepository clientRepository;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientEntity create(@RequestBody ClientEntity client) {
        return clientRepository.save(client);
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientEntity getClient(@PathVariable("id") Integer id) {
        return clientRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("NotFoundException: No value present"));
    }

    @DeleteMapping("/remove/{id}")
    @PutMapping("/remove/{id}")
    //@ResponseStatus(HttpStatus.OK)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public void remove(@PathVariable("id") Integer id) {
        //clientRepository.deleteById(id);
        throw new NotAllowedException("" + id);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ClientEntity save(@RequestBody ClientEntity client) {
        return clientRepository.save(client);
    }

}
