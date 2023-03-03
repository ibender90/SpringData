package ru.geek.market.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldsValidationErrorMessageCollector {
    private List<String> errorMessages;
}
