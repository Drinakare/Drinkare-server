package sg.hsdd.drinkare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.hsdd.drinkare.entity.Statistic;
import sg.hsdd.drinkare.entity.Test;

import java.util.Optional;

@Repository
public interface StatisticRepository  extends JpaRepository<Statistic, Long>{
    Optional<Statistic> findByUserIdAndPartyMonth(Long userID, Long partyMonth);
    Optional<Statistic> findByUserId(Long userID);
}
