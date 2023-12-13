package com.mycompany.customerservice.config;
import static constants.Constants.ROLE_ADMIN;
import static constants.Constants.ROLE_CUSTOMER;
import static constants.Constants.ROLE_DELIVERY_PERSON;
import static constants.Constants.ROLE_RESTAURANT;
import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import org.eclipse.microprofile.auth.LoginConfig;

@SuppressWarnings({"EmptyClass", "SuppressionAnnotation"})
@LoginConfig(authMethod = "MP-JWT")
@DeclareRoles({ROLE_CUSTOMER,ROLE_ADMIN,ROLE_RESTAURANT,ROLE_DELIVERY_PERSON})
@ApplicationPath("rest")
public class BootStrap extends javax.ws.rs.core.Application {
}
