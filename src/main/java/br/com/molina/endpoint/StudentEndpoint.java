package br.com.molina.endpoint;

import br.com.molina.error.ResourceNotFoundException;
import br.com.molina.model.Student;
import br.com.molina.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("students")
public class StudentEndpoint {
    private final StudentRepository studentDAO;
    @Autowired
    public StudentEndpoint(StudentRepository studentDao) {
        this.studentDAO = studentDao;
    }

    @GetMapping
    public ResponseEntity<?> listAll(){
        return new ResponseEntity<>(studentDAO.findAll(), HttpStatus.OK);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id){
        verifyIfStudentExists(id);
        return new ResponseEntity<>(studentDAO, HttpStatus.OK);
    }
    @GetMapping(path = "/findByName/{name}")
    public ResponseEntity<?> findStudentsByName(@PathVariable String name){
        return new ResponseEntity<>(studentDAO.findByNameIgnoreCaseContaining(name), HttpStatus.OK);

    }
    @PostMapping
     public ResponseEntity<?> save(@RequestBody Student student){
          return new ResponseEntity<>(studentDAO.save(student), HttpStatus.CREATED);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        verifyIfStudentExists(id);
        studentDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student){
        verifyIfStudentExists(student.getId());
        studentDAO.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    private void verifyIfStudentExists(Long id){
        if (studentDAO.findById(id) == null)
            throw new ResourceNotFoundException("Student not found for ID: " + id);

    }
}
