package io.novelis.smartroby.sample.service;

import io.novelis.smartroby.sample.dao.person.PersonDao;
import io.novelis.smartroby.sample.domain.PersonEntity;
import io.novelis.smartroby.sample.service.dto.PersonDto;
import io.novelis.smartroby.sample.service.dto.PersonListDto;
import io.novelis.smartroby.sample.service.dto.PersonPartialDto;
import io.novelis.smartroby.sample.service.mapper.PersonMapper;
import io.novelis.smartroby.sample.service.validation.exception.common.DataNotFoundException;
import io.novelis.smartroby.sample.service.validation.exception.person.PersonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Validated
public class PersonService {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private PersonMapper personMapper;

    @Transactional(readOnly = true)
    public PersonDto getPerson(Long id) {
        PersonEntity person =  personDao.findById(id).orElseThrow(DataNotFoundException::new);
        return personMapper.toDto(person);
    }

    public Long createPerson(@Valid PersonDto person) {
        validate(person);
        PersonEntity personEntity = personMapper.toEntity(person);
        return personDao.create(personEntity).getId();
    }

    public void updatePerson(Long id, @Valid PersonDto person) {
        PersonEntity personEntity =  personDao.findById(id).orElseThrow(DataNotFoundException::new);
        personMapper.updateEntityFromDto(person, personEntity);
        personDao.update(personEntity);
    }

    public void deletePerson(Long id) {
        if(!personDao.existsById(id)) {
            throw new DataNotFoundException();
        }
        personDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public PersonListDto getPersonList(String firstName, String lastName) {
        List<PersonEntity> personList = personDao.findByCriteria(firstName, lastName);
        return new PersonListDto(personMapper.toDto(personList));
        //return new PersonListDto(personList.stream().map(personMapper::toDto).collect(Collectors.toList()));
    }

    public void updateFirstName(Long id, PersonPartialDto person) {
        PersonEntity personEntity =  personDao.lazyFindById(id).orElseThrow(DataNotFoundException::new);
        personMapper.updateEntityFromPartialDto(person, personEntity);
        personDao.update(personEntity);
    }

    private void validate(PersonDto person) {
        if(person.getFirstName().length() == 5) {
            throw new PersonException(PersonException.PersonErrorsEnum.PERSON_FIRSTNAME_LENGTH_INVALID);
        }
    }
}
