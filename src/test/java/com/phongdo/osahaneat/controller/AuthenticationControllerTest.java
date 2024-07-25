package com.phongdo.osahaneat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phongdo.osahaneat.dto.request.SignupRequest;
import com.phongdo.osahaneat.dto.response.PermissionResponse;
import com.phongdo.osahaneat.dto.response.RoleResponse;
import com.phongdo.osahaneat.dto.response.UserResponse;
import com.phongdo.osahaneat.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/test.properties")
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

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

        Mockito.when(userService.addUser(ArgumentMatchers.any())).thenReturn(response);
        // When, Then
        mockMvc.perform(MockMvcRequestBuilders
                .post("http://localhost:8080/auth/signup")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1000)
        );
    }

    @Test
    void createUser_usernameInvalidRequest_fail() throws Exception {
        // Given
        request.setUserName("dax");
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(request);

        // When, Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("http://localhost:8080/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1003))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Username must be at least 4 characters")
                );
    }
}
