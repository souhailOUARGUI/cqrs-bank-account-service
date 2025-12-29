package org.example.cqrsbankaccountservice.query.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAccountByIdQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
}