package payment.payment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
//@Component
@Getter @Setter
public class LocalData {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardName;
    private String usedAmount;
    private String storeName;

    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDate usedDate;

    @JsonFormat(pattern = "HHmm")
    private LocalTime usedTime;
}
