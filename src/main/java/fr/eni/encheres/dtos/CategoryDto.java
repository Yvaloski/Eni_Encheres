package fr.eni.encheres.dtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class CategoryDto {
    @NotBlank(message = "Label cannot be blank")
    @Length(min = 3, max = 20, message = "Label must be between 3-20 characters")
    private String label;

    public CategoryDto() {
    }

    public CategoryDto(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
