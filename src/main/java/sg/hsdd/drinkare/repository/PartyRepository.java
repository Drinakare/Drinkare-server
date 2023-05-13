package sg.hsdd.drinkare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.hsdd.drinkare.entity.Party;

import java.util.Date;
import java.util.List;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
    List<Party> findAllByUserIdAndPartyDate(Long userId, Date partyDate);
    List<Party> findAllByUserIdAndPartyDateBetween(Long userId, Date start, Date end);

}
