package com.grupolemon.ocarsionplus.service.impl;

import java.util.Arrays;

import javax.ws.rs.core.Response;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.grupolemon.ocarsionplus.service.SsoService;

@Service
public class SsoServiceKeycloackImpl implements SsoService {

	@Value("${keycloak.resource}")
	private String keycloakClient;

	@Value("${keycloak.auth-server-url}")
	private String keycloakUrl;

	@Value("${keycloak.realm}")
	private String keycloakRealm;

	@Value("${keycloak.credentials.secret}")
	private String keycloakSecret;

	@Value("${ocarsionplus.keycloak.admin.username}")
	private String keycloakAdmin;

	@Value("${ocarsionplus.keycloak.admin.password}")
	private String keycloakpassword;

	@Value("${ocarsionplus.keycloak.realm.role.name}")
	private String keycloakRealmRoleName;

	@Value("${ocarsionplus.keycloak.client.role.name}")
	private String keycloakClientRoleName;

	@Value("${ocarsionplus.keycloak.client.id}")
	private String keycloakClientId;

	private Keycloak getKeycloakInstance() {
		return Keycloak.getInstance(keycloakUrl, "master", keycloakAdmin, keycloakpassword, "admin-cli");
	}

	public void registrarUsuario(String nombre, String password, String nombreUsuario) {

		CredentialRepresentation credentials = new CredentialRepresentation();
		credentials.setType(CredentialRepresentation.PASSWORD);
		credentials.setValue(password);
		credentials.setTemporary(false);

		UserRepresentation userRepresentation = new UserRepresentation();
		userRepresentation.setUsername(nombreUsuario);
		userRepresentation.setCredentials(Arrays.asList(credentials));
		userRepresentation.setEnabled(true);
		Keycloak keycloak = getKeycloakInstance();
		Response result = keycloak.realm(keycloakRealm).users().create(userRepresentation);
		String userId = result.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

		RoleRepresentation clientRoleRepresentation = new RoleRepresentation();
		clientRoleRepresentation.setName(keycloakClient);
		clientRoleRepresentation.setClientRole(true);

		clientRoleRepresentation = keycloak.realm(keycloakRealm).clients().get(keycloakClientId).roles()
				.get(keycloakClientRoleName).toRepresentation();
		RoleRepresentation realmtRoleRepresentation = keycloak.realm(keycloakRealm).roles().get(keycloakRealmRoleName)
				.toRepresentation();

		UsersResource user = keycloak.realm(keycloakRealm).users();
		user.get(userId).roles().clientLevel(keycloakClientId).add(Arrays.asList(clientRoleRepresentation));
		user.get(userId).roles().realmLevel().add(Arrays.asList(realmtRoleRepresentation));

	}

}
