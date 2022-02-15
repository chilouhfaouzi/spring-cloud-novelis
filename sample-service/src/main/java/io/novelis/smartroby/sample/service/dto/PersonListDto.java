package io.novelis.smartroby.sample.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter @Setter
public class PersonListDto {

    private List<PersonDto> personList;

    private Integer resultsCount;

    public PersonListDto(List<PersonDto> personList) {
        this.personList = Collections.unmodifiableList(personList);
        resultsCount = personList.size();
    }
}
