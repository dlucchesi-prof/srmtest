package br.com.srmtest.testdaniel.service;

import br.com.srmtest.testdaniel.model.Client;
import br.com.srmtest.testdaniel.repository.ClientRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClientService {

    Logger                                              logger          = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private ClientRepository                            clientRepository;
    private Map<String, String>                         errorMap;

    public Client save(Client client){
        Client ret = null;
        if (validadeClient(client)){
            applyRule(client);
            ret = clientRepository.save(client);
        }
        return ret;
    }

    public Client find(Long id){
        Client ret = null;
        Optional<Client> tmp = clientRepository.findById(id);
        if (tmp.isPresent())
            ret = tmp.get();
        return ret;
    }

    public List<Client> list(){
        List<Client> ret = clientRepository.findAll();
        return ret;
    }

    private void applyRule(Client client) {
        switch (client.getRisk()){
            case B:
                client.setRate(new Double(0.1));
                break;
            case C:
                client.setRate(new Double(0.2));
                break;
            default:
                client.setRate(new Double(0));
                break;
        }
    }

    private boolean validadeClient(Client client) {
        boolean ret = true;
        errorMap = new HashMap<>();

        if (client == null){
            errorMap.put("Client", "empty");
            logger.error("Client Empty!");
            ret = false;
        }
        if (StringUtils.isBlank(client.getName())){
            errorMap.put("Name", "empty");
            logger.error("Client name empty: " + client.toString());
            ret = false;
        }
        if (client.getLimit() == null){
            errorMap.put("Limit", "empty");
            logger.error("Client limit empty: " + client.toString());
            ret = false;
        }
        if (client.getRisk() == null){
            errorMap.put("Risk group", "empty");
            logger.error("Client risk empty: " + client.toString());
            ret = false;
        }

        return ret;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }
}
