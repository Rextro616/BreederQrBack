package com.example.BreederQr.infra;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public abstract class Auditable {
    Date createdAt;
    int createdBy;
    Date updatedAt;
    int updatedBy;
    Boolean deleted;
    Date deletedAt;
    int deletedBy;

}
