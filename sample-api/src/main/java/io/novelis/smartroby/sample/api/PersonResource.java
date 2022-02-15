package io.novelis.smartroby.sample.api;

import io.novelis.smartroby.sample.service.PersonService;
import io.novelis.smartroby.sample.service.dto.PersonDto;
import io.novelis.smartroby.sample.service.dto.PersonListDto;
import io.novelis.smartroby.sample.service.dto.PersonDto;
import io.novelis.smartroby.sample.service.dto.PersonPartialDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@Api("Person management API")
public class PersonResource {

    @Autowired
    private PersonService personService;

    @ApiOperation(value = "get a person by its id")
    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable Long id) {
        return ResponseEntity.ok(personService.getPerson(id));
    }

    @ApiOperation(value = "create a new person")
    @PostMapping("/")
    public ResponseEntity<Long> createPerson(@RequestBody PersonDto person) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.createPerson(person));
    }

    @ApiOperation(value = "update an existing person")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePerson(@PathVariable Long id, @RequestBody PersonDto person) {
        personService.updatePerson(id, person);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "get a list of persons by criteria")
    @GetMapping("/")
    public ResponseEntity<PersonListDto> getPersonList(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        return ResponseEntity.ok(personService.getPersonList(firstName, lastName));
    }

    @ApiOperation(value = "update.person.firstname")
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateFirstName(@PathVariable Long id, @RequestBody PersonPartialDto person) {
        personService.updateFirstName(id, person);
        return ResponseEntity.ok().build();
    }
}
