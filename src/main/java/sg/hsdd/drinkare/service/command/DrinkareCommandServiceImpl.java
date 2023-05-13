package sg.hsdd.drinkare.service.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.hsdd.drinkare.controller.dto.AlcoholSaveCommandDTO;
import sg.hsdd.drinkare.entity.Party;
import sg.hsdd.drinkare.entity.Statistic;
import sg.hsdd.drinkare.repository.PartyRepository;
import sg.hsdd.drinkare.repository.StatisticRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class DrinkareCommandServiceImpl implements DrinkareCommandService {

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private StatisticRepository statisticRepository;

    @Override
    public void save(AlcoholSaveCommandDTO alcoholSaveCommandDTO){

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date tempDate;
        try{
            tempDate = dateFormat.parse(alcoholSaveCommandDTO.getDate());
        }
        catch(ParseException ex){
            tempDate = new Date();
        }


        Party party = Party.builder()
                .userId(alcoholSaveCommandDTO.getUserId())
                .people(alcoholSaveCommandDTO.getPeople())
                .soju(alcoholSaveCommandDTO.getSoju())
                .beer(alcoholSaveCommandDTO.getBeer())
                .partyDate(tempDate)
                .build();

        partyRepository.save(party);


        Optional<Statistic> statisticOptional = statisticRepository.findByUserIdAndPartyMonth(alcoholSaveCommandDTO.getUserId(), Long.valueOf(tempDate.getMonth()+1));
        Statistic statistic;
        if(statisticOptional.isPresent()){
            statistic = statisticOptional.get();
            statistic.setPartyCount(statisticOptional.get().getPartyCount()+1);
            statistic.setSoju(statisticOptional.get().getSoju()+alcoholSaveCommandDTO.getSoju());
            statistic.setBeer(statisticOptional.get().getBeer()+alcoholSaveCommandDTO.getBeer());
        } else {
            statistic = Statistic.builder()
                    .userId(alcoholSaveCommandDTO.getUserId())
                    .partyMonth(Long.valueOf(tempDate.getMonth()+1))
                    .partyCount(Long.valueOf(1))
                    .soju(alcoholSaveCommandDTO.getSoju())
                    .beer(alcoholSaveCommandDTO.getBeer())
                    .build();
        }
        statisticRepository.save(statistic);
    }
}
