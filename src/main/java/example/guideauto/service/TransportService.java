package example.guideauto.service;

import example.guideauto.entity.Transport;
import example.guideauto.entity.enums.Category;
import example.guideauto.entity.enums.Types;
import java.util.List;

public interface TransportService {

    List<Transport> findAll();

    Boolean saveInBase(Transport transport);

    Transport patchTransport(Transport transportNew);

    Transport findTransportGOSNumber(String gosNumber);

    List<Transport> findTransportModelAndCategory(String model, Category category);

    List<Transport> findTransportMark(String mark);

    List<Transport> findTransportModel(String model);

    List<Transport> findTransportCategory(Category category);

    List<Transport> findTransportType(Types type);

    List<Transport> findTransportYear(int year);

    List<Transport> findTransportTrailer(boolean availabilityTrailer);

    boolean delete(Long id);
}
