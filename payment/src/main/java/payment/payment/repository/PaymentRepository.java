package payment.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import payment.payment.entity.LocalData;

public interface PaymentRepository extends JpaRepository<LocalData, Long> {

//    List<SearchResultDto> findByDateBetween(String startDate, String endDate);

//    List<SearchResultDto> findByTitleContainingIgnoreCaseAndDate(String title, LocalDate date);
}
