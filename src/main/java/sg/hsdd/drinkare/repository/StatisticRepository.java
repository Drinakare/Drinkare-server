package sg.hsdd.drinkare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.hsdd.drinkare.entity.Statistic;
import sg.hsdd.drinkare.entity.Test;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatisticRepository  extends JpaRepository<Statistic, Long>{
    Optional<Statistic> findByUserIdAndPartyYearAndPartyMonth(Long userID, Long partyYear, Long partyMonth);
    List<Statistic> findByUserId(Long userID);
    List<Statistic> findByUserIdAndPartyYear(Long userID, Long partyYear);
}
