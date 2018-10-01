package br.com.srmtest.testdaniel.controller;

import br.com.srmtest.testdaniel.model.Client;
import br.com.srmtest.testdaniel.model.Risk;
import br.com.srmtest.testdaniel.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/api/clients")
public class ClientController {

    @Autowired
    private ClientService                               clientService;

    @RequestMapping(path = "/save")
    public Client save(@RequestBody Client client){
        return clientService.save(client);
    }

    @RequestMapping(path = "/")
    public List<Client> list(){
        return clientService.list();
    }

    @RequestMapping(path = "/{id}")
    public Client list(@PathParam("id") Long id){
        return clientService.find(id);
    }

    @RequestMapping(path = "/risks")
    public List<String> listRisk(){
        List<String> ret = Stream.of(Risk.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return ret;
    }

}
