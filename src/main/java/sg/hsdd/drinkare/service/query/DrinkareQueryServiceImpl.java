package sg.hsdd.drinkare.service.query;

import javax.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.hsdd.drinkare.controller.dto.StatisticGetDetailQueryDTO;
import sg.hsdd.drinkare.controller.dto.StatisticGetInfoQueryDTO;
import sg.hsdd.drinkare.controller.dto.StatisticGetMonthInfoQueryDTO;
import sg.hsdd.drinkare.entity.Party;
import sg.hsdd.drinkare.entity.Statistic;
import sg.hsdd.drinkare.entity.User;
import sg.hsdd.drinkare.repository.PartyRepository;
import sg.hsdd.drinkare.repository.StatisticRepository;
import sg.hsdd.drinkare.repository.UserRepository;
import sg.hsdd.drinkare.service.vo.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DrinkareQueryServiceImpl implements DrinkareQueryService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StatisticRepository statisticRepository;

    @Autowired
    PartyRepository partyRepository;

//    @Override
//    public StatisticInfoVO getInfo(StatisticGetInfoQueryDTO statisticGetInfoQueryDTO){
//
//        Long month = Long.valueOf(new Date().getMonth()+1);
//
//        Optional<User> userOptional = userRepository.findById(statisticGetInfoQueryDTO.getUserId());
//        Optional<Statistic> statisticOptional = statisticRepository.findByUserIdAndPartyMonth(statisticGetInfoQueryDTO.getUserId(), month);
//        StatisticInfoVO statisticInfoVO;
//
//        if(userOptional.isPresent()){
//            if(statisticOptional.isPresent()){
//                statisticInfoVO = StatisticInfoVO.builder()
//                        .age(userOptional.get().getAge())
//                        .name(userOptional.get().getName())
//                        .month(month)
//                        .soju(statisticOptional.get().getSoju())
//                        .beer(statisticOptional.get().getBeer())
//                        .count(statisticOptional.get().getPartyCount())
//                        .build();
//            } else {
//                statisticInfoVO = StatisticInfoVO.builder()
//                        .age(userOptional.get().getAge())
//                        .name(userOptional.get().getName())
//                        .month(month)
//                        .soju(Long.valueOf(0))
//                        .beer(Long.valueOf(0))
//                        .count(Long.valueOf(0))
//                        .build();
//            }
//        } else {
//            // error
//            statisticInfoVO = StatisticInfoVO.builder()
//                    .build();
//        }
//        return statisticInfoVO;
//    }

    @Override
    public StatisticInfoVO getInfo(StatisticGetInfoQueryDTO statisticGetInfoQueryDTO){

        Long month = Long.valueOf(new Date().getMonth()+1);
        Long year = Long.valueOf(new Date().getYear()+1900);

        Optional<User> userOptional = userRepository.findById(statisticGetInfoQueryDTO.getUserId());

        if(userOptional.isPresent()) {
            List<Statistic> statisticList = statisticRepository
                    .findByUserIdAndPartyYear(statisticGetInfoQueryDTO.getUserId(), year);


            List<Long> monthList = new ArrayList();
            for(int i=0;i<month;i++){
                monthList.add(new Long(0));
            }


            List<StatisticInfoDetailVO> statisticInfoDetailVOS = statisticList.stream().map(x -> {

                if(monthList.get(x.getPartyMonth().intValue()-1)==0){
                    monthList.set(x.getPartyMonth().intValue()-1, new Long(1));
                }

                return StatisticInfoDetailVO.builder()
                                .month(x.getPartyMonth())
                                .count(x.getPartyCount())
                                .soju(x.getSoju())
                                .beer(x.getBeer())
                                .build();
                    })
                    //.sorted(Comparator.comparing(StatisticInfoDetailVO::getMonth))
                    .collect(Collectors.toList());

            for(int i=0;i<month;i++){
                Long m = new Long(i+1);
                if(monthList.get(i)==0){
                    statisticInfoDetailVOS.add(
                            StatisticInfoDetailVO.builder()
                                    .month(m)
                                    .count(Long.valueOf(0))
                                    .soju(Long.valueOf(0))
                                    .beer(Long.valueOf(0))
                            .build());
                }
            }

            statisticInfoDetailVOS = statisticInfoDetailVOS.stream()
                    .sorted(Comparator.comparing(StatisticInfoDetailVO::getMonth))
                    .collect(Collectors.toList());

            StatisticInfoVO statisticInfoVO = StatisticInfoVO.builder()
                    .name(userOptional.get().getName())
                    .age(userOptional.get().getAge())
                    .list(statisticInfoDetailVOS)
                    .build();

            return statisticInfoVO;
        }
        else {
            return StatisticInfoVO.builder().build();
        }


    }


    @Override
    public StatisticDetailVO getDetail(StatisticGetDetailQueryDTO statisticGetDetailQueryDTO){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date tempDate;
        try{
            tempDate = dateFormat.parse(statisticGetDetailQueryDTO.getDate());
        }
        catch(ParseException ex){
            tempDate = new Date();
        }

        Optional<User> userOptional = userRepository.findById(statisticGetDetailQueryDTO.getUserId());
        if(userOptional.isPresent()){
            List<Party> parties = partyRepository.findAllByUserIdAndPartyDate(
                    statisticGetDetailQueryDTO.getUserId(),
                    tempDate
            );

            List<StatisticDetailListVO> statisticDetailListVOS = parties.stream().map(x->StatisticDetailListVO.builder()
                            .people(x.getPeople())
                            .soju(x.getSoju())
                            .beer(x.getBeer())
                            .build())
                    .collect(Collectors.toList());

            StatisticDetailVO statisticDetailVO = StatisticDetailVO.builder()
                    .name(userOptional.get().getName())
                    .list(statisticDetailListVOS)
                    .build();

            return statisticDetailVO;
        } else {
            return StatisticDetailVO.builder().build();
        }
    }

    @Override
    public StatisticMonthVO getMonth(StatisticGetMonthInfoQueryDTO statisticGetMonthInfoQueryDTO){

        Calendar calendar = Calendar.getInstance();
        calendar.set(statisticGetMonthInfoQueryDTO.getYear().intValue(), statisticGetMonthInfoQueryDTO.getMonth().intValue()-1, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date startDate = new Date(calendar.getTimeInMillis());

        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date endDate = new Date(calendar.getTimeInMillis());

        List<Party> parties = partyRepository.findAllByUserIdAndPartyDateBetween(statisticGetMonthInfoQueryDTO.getUserId(), startDate, endDate);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<String> partyDates = parties.stream().map(x->dateFormat.format(x.getPartyDate()))
                .distinct().collect(Collectors.toList());


        return StatisticMonthVO.builder()
                .partyDate(partyDates)
                .build();
    }

}