package example.guideauto.service.impl;

import example.guideauto.entity.Transport;
import example.guideauto.repository.TransportRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TransportServiceIMPL {

    private TransportRepository repository;

    public TransportServiceIMPL(TransportRepository repository) {
        this.repository = repository;
    }

    public Boolean saveInBase(Transport transport) {
        String newTransportGOSN = transport.getGOSNumber().toLowerCase();
        Transport transportIBase = repository.findTransportByGOSNumberIgnoreCase(newTransportGOSN);
        if (null == transportIBase) {
            repository.save(transport);
            return true;
        }
        return false;// В контроллере сделать выдачу ошибки, данное тс уже в базе
    }

    public Transport patchTransport(Transport transportNew){
        return repository.save(transportNew);
    }

    public Transport findTransportGOSNumber(String gosNumber){
        return repository.findTransportByGOSNumberIgnoreCase(gosNumber);
    }

    public ArrayList<Transport> findTransportModelAndCategory(String model, String category){
        return repository.findTransportByModelIgnoreCaseAndAndCategory(model,category);
    }

    public ArrayList<Transport> findTransportMark(String mark){
        return repository.findTransportByMarkIgnoreCase(mark);
    }
    public ArrayList<Transport> findTransportModel(String model){
        return repository.findTransportByModelIgnoreCase(model);
    }
    public ArrayList<Transport> findTransportCategory(String category){
        return repository.findTransportByCategoryIgnoreCase(category);
    }
    public ArrayList<Transport> findTransportType(String type){
        return repository.findTransportByTypesIgnoreCase(type);
    }
    public ArrayList<Transport> findTransportYear(int year){
        return repository.findTransportByYearRelease(year);
    }
    public ArrayList<Transport> findTransportTrailer(boolean availabilityTrailer){
        return repository.findTransportByAvailabilityTrailer(availabilityTrailer);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
