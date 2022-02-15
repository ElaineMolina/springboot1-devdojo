package br.com.molina.endpoint;

import br.com.molina.model.Student;
import br.com.molina.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//retificando commit
import static java.util.Arrays.asList;

@RestController
@RequestMapping("student")
public class StudentEndpoint {
    @Autowired
    private DateUtil dateUtil;
    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public List<Student> lisAll(){
        //System.out.println(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return asList(new Student("Deku"),new Student("Todoroki"));
    }

}
