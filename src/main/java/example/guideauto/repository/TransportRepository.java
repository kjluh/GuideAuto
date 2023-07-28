package example.guideauto.repository;

import example.guideauto.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface TransportRepository extends JpaRepository<Transport,Long> {
    Transport findTransportByGOSNumberIgnoreCase(String gosNumber);

    ArrayList<Transport> findTransportByModelIgnoreCaseAndAndCategory(String model, String category);

    ArrayList<Transport> findTransportByMarkIgnoreCase(String mark);
    ArrayList<Transport> findTransportByModelIgnoreCase(String model);
    ArrayList<Transport> findTransportByCategoryIgnoreCase(String category);
    ArrayList<Transport> findTransportByTypesIgnoreCase(String types);
    ArrayList<Transport> findTransportByYearRelease(int year);
    ArrayList<Transport> findTransportByAvailabilityTrailer(boolean availabilityTrailer);
}
