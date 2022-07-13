package springreactjunit.demo.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springreactjunit.demo.model.Student;
import springreactjunit.demo.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService{

//    StudentRepository studentRepository;
//
//    public StudentService(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }
//
//    public List<Student> getStudents() {
//        return studentRepository.findAll();
//    }
//
//    public void saveOrUpdate(Student student) {
//        studentRepository.save(student);
//    }
//
//    public void delete(int id){
//        studentRepository.deleteById(id);
//    }
//
//    public void update(Student student, int id){
//        student.setName("Patrick");
//        studentRepository.save(student);
//    }
}
