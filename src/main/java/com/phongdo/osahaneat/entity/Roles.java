package com.phongdo.osahaneat.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity(name = "roles")
public class Roles {

    @Id
    String roleName;

    @Column(name = "create_date")
    Date createDate;

    @ManyToMany
    Set<Permission> permissions;

}
