package com.phongdo.osahaneat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phongdo.osahaneat.dto.request.LoginRequest;
import com.phongdo.osahaneat.dto.request.SignupRequest;
import com.phongdo.osahaneat.dto.response.AuthenticationResponse;
import com.phongdo.osahaneat.dto.response.PermissionResponse;
import com.phongdo.osahaneat.dto.response.RoleResponse;
import com.phongdo.osahaneat.dto.response.UserResponse;
import com.phongdo.osahaneat.entity.Role;
import com.phongdo.osahaneat.repository.RoleRepository;
import com.phongdo.osahaneat.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@Slf4j
public class AuthenticationControllerIntegrationTest {
    @Container
    static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql:latest");

    @DynamicPropertySource
    static void configureDataSource(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url",() -> MY_SQL_CONTAINER.getJdbcUrl());
        registry.add("spring.datasource.username",() -> MY_SQL_CONTAINER.getUsername());
        registry.add("spring.datasource.password",() -> MY_SQL_CONTAINER.getPassword());
        registry.add("spring.datasource.driverClassName",() -> "com.mysql.cj.jdbc.Driver");
        registry.add("spring.jpa.hibernate.ddl-auto",() -> "update");
    }
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    RoleRepository roleRepository;
    private SignupRequest request;
    private UserResponse response;
    private Date createTime;
    private Set<String> roleNames;
    private Set<RoleResponse> roleResponseSet;
    private Set<PermissionResponse> permissionResponses;


    @BeforeEach
    void initData(){

        createTime = new Date();
        roleNames = new HashSet<>();
        roleResponseSet = new HashSet<>();
        permissionResponses = new HashSet<>();

        String roleName = "USER";
        roleNames.add(roleName);
        PermissionResponse permissionResponse = PermissionResponse.builder()
                .name("CREATE_DATA")
                .description("Create data permission")
                .build();
        permissionResponses.add(permissionResponse);
        RoleResponse roleResponse = RoleResponse.builder()
                .name("USER")
                .permissions(permissionResponses)
                .description("User role")
                .build();
        roleResponseSet.add(roleResponse);

        Role roleUser = Role.builder()
                .name(roleName)
                .description("User role")
                .build();
        roleRepository.save(roleUser);

        request = SignupRequest.builder()
                .userName("david")
                .fullname("David Ngo")
                .password("12345678")
                .roleName(roleNames)
                .build();

        response = UserResponse.builder()
                .id(18)
                .userName("david")
                .fullname("David Ngo")
                .createDate(createTime)
                .roles(roleResponseSet)
                .build();
    }

    @Test
    void createUser_validRequest_success() throws Exception {
        // Given
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(request);

        // When, Then
        var response = mockMvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/auth/signup")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1000)
        );
        log.info("Result: {}",response.andReturn().getResponse().getContentAsString());
    }
}
