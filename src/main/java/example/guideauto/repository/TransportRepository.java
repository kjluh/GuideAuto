package example.guideauto.repository;

import example.guideauto.entity.Transport;
import example.guideauto.entity.enums.Category;
import example.guideauto.entity.enums.Types;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TransportRepository extends JpaRepository<Transport,Long> {
    Transport findTransportByGosNumberIgnoreCase(String gosNumber);

    List<Transport> findTransportByModelIgnoreCaseAndAndCategory(String model, Category category);

    List<Transport> findTransportByMarkIgnoreCase(String mark);
    List<Transport> findTransportByModelIgnoreCase(String model);
    List<Transport> findTransportByCategory(Category category);
    List<Transport> findTransportByTypes(Types types);
    List<Transport> findTransportByYearRelease(int year);
    List<Transport> findTransportByAvailabilityTrailer(boolean availabilityTrailer);
}
