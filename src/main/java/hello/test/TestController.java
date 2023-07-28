package hello.test;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    public TestController(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    TestRepository testRepository;

    @GetMapping("/test")
    public String jpatest(){
        Test test=new Test();
        test.setContent("testing");
        testRepository.save(test);
        return "/blogs/list";
    }

}
