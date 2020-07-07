package it.mohanrc.socialmedia.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import it.mohanrc.socialmedia.dao.UserDaoService;
import it.mohanrc.socialmedia.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@RestController
public class DynamicJSONFilteringResource {

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping("employee/{id}")
    public MappingJacksonValue getUser(@PathVariable Integer id) {
        Employee employee = new Employee(id, "Mohan", LocalDate.now());

        return getMappingJacksonValue(employee, "id", "dob");
    }

    @GetMapping("employee")
    public MappingJacksonValue getUserList() {
        List<Employee> employees = Arrays.asList(
        new Employee(1, "Mohan", LocalDate.of(1989, Month.DECEMBER, 19)),
        new Employee(2, "Abinaya", LocalDate.of(1991, Month.MAY, 15)),
        new Employee(3, "Magilnila", LocalDate.of(2018, Month.DECEMBER, 4)));
        return getMappingJacksonValue(employees, "name", "dob");
    }

    private <T> MappingJacksonValue getMappingJacksonValue(T input, String... property) {
        SimpleBeanPropertyFilter simpleBeanPropertyFilter =
                SimpleBeanPropertyFilter.filterOutAllExcept(property);

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("EmployeeFilter", simpleBeanPropertyFilter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(input);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }
}
