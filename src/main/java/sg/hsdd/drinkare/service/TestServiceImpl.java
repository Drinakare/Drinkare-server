package sg.hsdd.drinkare.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.hsdd.drinkare.entity.Test;
import sg.hsdd.drinkare.repository.TestRepository;
import sg.hsdd.drinkare.service.vo.TestVO;

import java.util.Optional;

@Service
@Slf4j
public class TestServiceImpl implements TestService{

    @Autowired
    private TestRepository testRepository;

    @Override
    public TestVO testService(Long id){
        Optional<Test> test = testRepository.findById(id);
        if(test.isPresent()){
            return TestVO.builder()
                    .testName( test.get().getTestName())
                    .build();
        }
        else{
            return TestVO.builder()
                    .testName("not exist")
                    .build();
        }
    }
}
