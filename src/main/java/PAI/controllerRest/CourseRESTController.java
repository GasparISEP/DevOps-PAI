package PAI.controllerRest;

import PAI.assembler.course.ICourseAssembler;
import PAI.dto.course.CourseIDDTO;
import PAI.service.course.ICourseService;
import org.springframework.boot.web.servlet.server.AbstractServletWebServerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseRESTController {

    private final ICourseService courseService;
    private final ICourseAssembler courseAssembler;

    public CourseRESTController (ICourseService service, ICourseAssembler assembler, AbstractServletWebServerFactory abstractServletWebServerFactory) {
        courseAssembler = assembler;
        courseService = service;
    }

    @GetMapping("ids")
    public ResponseEntity<List<CourseIDDTO>> getAllCoursesIDDTO() {
        List<CourseIDDTO> courseIDDTOS = courseService.getAllCourseIDDTO();
        if (!courseIDDTOS.isEmpty()) {
            return new ResponseEntity<>(courseIDDTOS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
