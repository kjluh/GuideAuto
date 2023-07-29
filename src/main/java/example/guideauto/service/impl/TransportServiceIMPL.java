package example.guideauto.service.impl;

import example.guideauto.entity.Transport;
import example.guideauto.entity.enums.Category;
import example.guideauto.entity.enums.Types;
import example.guideauto.repository.TransportRepository;
import example.guideauto.service.TransportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class TransportServiceIMPL implements TransportService {

    private TransportRepository repository;

    public TransportServiceIMPL(TransportRepository repository) {
        this.repository = repository;
    }

    Logger logger = LoggerFactory.getLogger(TransportRepository.class);

    private static final Pattern gosNumber = Pattern.compile(
            "([A-z)]{1})(\\d{3})([A-z)]{2})(\\d+)");


    public List<Transport> findAll() {
        return repository.findAll();
    }
    /**
     * Метод получения всего транспорта из базы
     * @return коллекцию объектов транспорт
     */
    public Boolean saveInBase(Transport transport) {
        logger.getName();
        String newTransportGOSN = transport.getGosNumber();
        Transport transportIBase = repository.findTransportByGosNumberIgnoreCase(newTransportGOSN);
        Matcher matcher = gosNumber.matcher(transport.getGosNumber());
        if (null == transportIBase && matcher.find()) {
            repository.save(transport);
            return true;
        }
        return false;
    }

    /**
     * Метод обновления транспорта
     * @param transportNew получает объект, транспорт для обновления
     * @return обновленный объект
     */
    public Transport patchTransport(Transport transportNew) {
        return repository.save(transportNew);
    }

    /**
     * Метод для поиска объекта в бд
     * @param gosNumber параметр для поиска
     * @return найденный объект
     */
    public Transport findTransportGOSNumber(String gosNumber) {
        return repository.findTransportByGosNumberIgnoreCase(gosNumber);
    }

    /**
     * Метод для поиска объекта в бд по 2 параметрам
     * @param model тип модели для поиска
     * @param category тип категории транспорта для поиска
     * @return коллекцию найденных объектов транспорт
     */
    public List<Transport> findTransportModelAndCategory(String model, Category category) {
        return repository.findTransportByModelIgnoreCaseAndAndCategory(model, category);
    }

    /**
     * Метод для поиска объекта в бд
     * @param mark тип марки для поиска
     * @return коллекцию найденных объектов транспорт
     */
    public List<Transport> findTransportMark(String mark) {
        if (null != mark) {
            return repository.findTransportByMarkIgnoreCase(mark);
        }
        return repository.findAll().stream().sorted((a,d)->a.getMark().compareTo(d.getMark())).collect(Collectors.toList());
    }

    /**
     * Метод для поиска объекта в бд
     * @param model тип модели для поиска
     * @return коллекцию найденных объектов транспорт
     */
    public List<Transport> findTransportModel(String model) {
        return repository.findTransportByModelIgnoreCase(model);
    }

    /**
     * Метод для поиска объекта в бд
     * @param category тип категории транспорта для поиска
     * @return коллекцию найденных объектов транспорт
     */
    public List<Transport> findTransportCategory(Category category) {
        return repository.findTransportByCategory(category);
    }

    /**
     * Метод для поиска объекта в бд
     * @param type тип транспорта для поиска
     * @return коллекцию найденных объектов транспорт
     */
    public List<Transport> findTransportType(Types type) {
        return repository.findTransportByTypes(type);
    }

    /**
     * Метод для поиска объекта в бд
     * @param year год выпуска транспорта для поиска
     * @return коллекцию найденных объектов транспорт
     */
    public List<Transport> findTransportYear(int year) {
        return repository.findTransportByYearRelease(year);
    }

    /**
     * Метод для поиска объекта в бд
     * @param availabilityTrailer наличие / отсутствие прицепа для поиска
     * @return коллекцию найденных объектов транспорт
     */
    public List<Transport> findTransportTrailer(boolean availabilityTrailer) {
        return repository.findTransportByAvailabilityTrailer(availabilityTrailer);
    }

    /**
     * Метод для удаления объекта из бд
     * @param id ИД для удаления по нему из бд
     * @return положительный / отрицательный результат удаления
     */
    public boolean delete(Long id) {
        Transport transport = repository.findById(id).orElse(null);
        if (null != transport) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
