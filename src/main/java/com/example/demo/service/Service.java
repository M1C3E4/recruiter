package com.example.demo.service;

import com.example.demo.entity.Students;
import com.example.demo.entity.Teachers;
import com.example.demo.exception.ErrorCode;
import com.example.demo.repository.StudyRepository;
import com.example.demo.repository.TeacherRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * @author Maciej Okliński
 * Klasa będąca usługodawcą
 */

@RestController
@RequestMapping("/api")
@Slf4j
@org.springframework.stereotype.Service
public class Service {


    private final StudyRepository studyRepository;
    private final TeacherRepository teacherRepository;

    public Service(StudyRepository studyRepository, TeacherRepository teacherRepository) {
        this.studyRepository = studyRepository;
        this.teacherRepository = teacherRepository;
    }

    /**
     * Endpoint restowy pobierający studenta o zadanym name.
     * @param name
     * @return - student o zadanym name.
     */

    @SneakyThrows
    @GetMapping("/findByNameStud/{name}")
    public Optional<Students> chargeByNameStud(@PathVariable String name){
        var valueExpected = studyRepository.findByName(name);
        if(valueExpected.isPresent()) {
            log.info("Pobieram studenta o imieniu =" + " " + name);
            return valueExpected;
        }else{
            log.error("W bazie danych nie znaleziono studentao name=" + name);
            throw new Exception(String.valueOf(ErrorCode.NO_SUCH_STUDENT_FOUND));
        }
    }

    /**
     * eEndpoint restowy pobierający wszystkich studentów posortowanych w tym przypadku rosnąco.
     * @return - lista studnetów posortowanych rosnąco.
     */
    @GetMapping("/findAllStudents")
    public List<Students> chargeAllStudents(){
        log.info("Pobieranie wszytskich studentów z bazy danych posortowanych rosnąco.");
        return studyRepository.findTop2ByOrderByNameAsc();
    }

    /**
     * Endpoint restowy pobierający nauczyciela o zadanym name.
     * @param name
     * @return - nauczyciel o zadanym name
     */
    @SneakyThrows
    @GetMapping("/findByNameTeach/{name}")
    public Optional<Teachers> chargeByNameTeach(@PathVariable String name){
        var valueExpected = teacherRepository.findByName(name);
        if(valueExpected.isPresent()) {
            log.info("Pobieram nauczyciela o name =" + " " + name);
            return valueExpected;
        }else {
            log.error("W bazie danych nie znaleziono nauczyciela o podanym name=" + name);
            throw new Exception(String.valueOf(ErrorCode.NO_SUCH_TEACHER_FOUND));
        }
    }

    /**
     * Endpoint restowy pobierający wszystkich nauczycieli posortowyanych w tym przypadku rosnąco.
     * @return - lista nauczycieli posortowanych rosnąco.
     */
    @GetMapping("/findAllTeach")
    public List<Teachers> findAllTeach(){
        log.info("Pobieranie wszytskich nauczycieli z bazy danych posortowanych rosnąco.");
           return teacherRepository.findTop1ByOrderByNameAsc();
    }

    /**
     * Zabieg zastosowany w celu zapisu danych do bazy danych z poziomu backendu
     * aby nie musieć pisac sqlowych poleceń w celu dodania danych do bazy
     */
    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
        Students students = new Students("Maciej", "Oklinski", 18, "mackoo.oklinski@gmail.com", "chemia", new HashSet<>());
        saveStud(students);
        Students students1 = new Students("Aamila", "Aintop", 18, "kamila.kintop@gmail.com", "matematyka", new HashSet<>());
        saveStud(students1);
        Students students2 = new Students("Bartek", "walec", 67, "bartek.walec@gmail.com", "fizyka", new HashSet<>());
        saveStud(students2);
        Set<Students> studentsSet = new HashSet<>();
        studentsSet.add(students);
        studentsSet.add(students1);
        studentsSet.add(students2);

        Teachers teachers = new Teachers("anna", "bartmanska", 45, "agnieszka.bartmanska@gmail.com", "chemia", studentsSet);
        saveTeach(teachers);
        Teachers teachers1 = new Teachers("dorota", "kowalska", 19, "dorota.kowalska@gmail.com", "matematykad", studentsSet);
        saveTeach(teachers1);
        Set<Teachers> teachersSet = new HashSet<>();
        teachersSet.add(teachers);
        teachersSet.add(teachers1);

    }

    private Students saveStud(Students students){
        return studyRepository.save(students);
    }

    private Teachers saveTeach(Teachers teachers) {
        return teacherRepository.save(teachers);
    }
}
