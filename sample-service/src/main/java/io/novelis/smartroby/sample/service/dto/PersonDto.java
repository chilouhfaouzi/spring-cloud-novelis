package io.novelis.smartroby.sample.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter @Setter
@Builder
@ApiModel
public class PersonDto {

    @ApiModelProperty(notes = "The first name of the person", example = "Oussama")
    @NotNull
    @Size(min = 3, max = 20)
    private String firstName;

    @ApiModelProperty(notes = "The last name of the person", example = "AMARA")
    @NotNull
    @Size(min = 3, max = 20)
    private String lastName;
}
