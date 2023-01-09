package com.desafio.dock;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class DesafioQADockTests {

	private String url = "https://reqres.in/api";

	/**
	 * Este caso de teste busca validar a pesquisa de um usuário informando o ID de
	 * um registro que não existe como parâmetro. Portanto, o retorno esperado deve
	 * ser um código 404 e um JSON vazio {}.
	 */
	@Test
	public void singleUserNotFound() {
		given().when().get(url + "/users/999").then().statusCode(404).body(containsString("{}"));
	}

	/**
	 * Este caso de teste busca validar a pesquisa de um usuário informando um ID de
	 * um registro existente como parâmetro. Portanto o retorno esperado deve ser um
	 * código 200 indicando sucesso e um JSON com as informações de acordo com o
	 * usuário pesquisado.
	 */
	@Test
	public void singleUser() {
		given().when().get(url + "/users/2").then().statusCode(200).body(containsString("janet.weaver@reqres.in"),
				containsString("https://reqres.in/img/faces/2-image.jpg"));
	}

	/**
	 * Este caso de teste busca validar a listagem de usuários exibidos na página 1,
	 * conforme parâmetro informado na URL. No retorno esperado constam as
	 * informações do número da página exibida, quantidade de registros por página e
	 * quantidade total de registros.
	 */
	@Test
	public void listUsersPageOne() {
		given().queryParam("page", "1").when().get(url + "/users").then().statusCode(200).body(
				containsString("\"page\":1"), containsString("\"per_page\":6"), containsString("\"total\":12"),
				containsString("\"total_pages\":2"));
	}

	/**
	 * Este caso de teste busca validar a listagem de usuários exibidos na página 2,
	 * conforme parâmetro informado na URL. No retorno esperado constam as
	 * informações do número da página exibida, quantidade de registros por página e
	 * quantidade total de registros.
	 */
	@Test
	public void listUsersPageTwo() {
		given().queryParam("page", "2").when().get(url + "/users").then().statusCode(200).body(
				containsString("\"page\":2"), containsString("\"per_page\":6"), containsString("\"total\":12"),
				containsString("\"total_pages\":2"));
	}

	/**
	 * Este caso de teste busca validar a listagem de usuários exibidos na página de
	 * acordo com a quantidade informada no parâmetro "per_page", inserida na URL de
	 * chamada da API. No retorno esperado constam as informações do número da
	 * página, quantidade de registros por página, quantidade total de registros e
	 * quantidade total de páginas de acordo com os registros cadastrados.
	 */
	@SuppressWarnings({ "serial", "deprecation" })
	@Test
	public void listUsersPageOneWithPerPageParameter() {
		Map<String, String> pagePerPage = new HashMap<String, String>() {
			{
				put("page", "1");
				put("per_page", "12");
			}
		};
		given().queryParameters(pagePerPage).when().get(url + "/users").then().statusCode(200).body(
				containsString("\"page\":1"), containsString("\"per_page\":12"), containsString("\"total\":12"),
				containsString("\"total_pages\":1"));
	}

	/**
	 * Este caso de teste busca validar a criação de um registro de novo usuário de
	 * acordo com as informações passadas como parâmetro para a chamada do método. O
	 * retorno esperado informa o código 200 de sucesso bem como as informações da
	 * data de criação do registro.
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void createUser() {
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Deivid");
		requestParams.put("job", "QA");

		given().body(requestParams.toJSONString()).when().post(url + "/users").then().statusCode(201)
				.body(containsString("createdAt"));
	}

	/**
	 * Este caso de teste busca validar a atualização de um registro de usuário
	 * existente através da chamada do método UPDATE. O retorno esperado é de um
	 * código 200 indicando sucesso além das informações de data e hora da
	 * atualização realizada.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void updateUser() {
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "New Name");
		requestParams.put("job", "New Job");

		given().body(requestParams.toJSONString()).when().put(url + "/users/3").then().statusCode(200)
				.body(containsString("updatedAt"));
	}

	/**
	 * Este caso de teste busca validar a chamada do método UPDATE para um usuário
	 * informando um ID que não existe. Portanto, o resultado esperado deve ser o
	 * código 404 e um JSON vazio {}.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void updateUserNotFound() {
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Update User Not Found");
		requestParams.put("job", "Job Not Found");

		given().body(requestParams.toJSONString()).when().put(url + "/users/555").then().statusCode(404)
				.body(containsString("{}"));
	}

	/**
	 * Este caso de teste busca validar a atualização de um registro de usuário
	 * existente através da chamada do método PATCH. O retorno esperado é de um
	 * código 200 indicando sucesso além das informações de data e hora da
	 * atualização realizada.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void updateUserPatch() {
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Deivid Novo");
		requestParams.put("job", "QA na Dock");

		given().body(requestParams.toJSONString()).when().patch(url + "/users/12").then().statusCode(200)
				.body(containsString("updatedAt"));
	}

	/**
	 * Este caso de teste busca validar a chamada do método UPDATE do tipo PATCH
	 * informando um código de usuário que não existe. Portanto, o retorno esperado
	 * deve ser de um código 404 e um JSON vazio {}.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void updateUserPatchNotFound() {
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Deivid Novo");
		requestParams.put("job", "QA na Dock");

		given().body(requestParams.toJSONString()).when().patch(url + "/users/7777").then().statusCode(404)
				.body(containsString("{}"));
	}

	/**
	 * Este caso de teste busca validar a exclusão de um registro de usuário através
	 * do método DELETE informando o ID do usuário a ser removido. O retorno
	 * esperado é um código 204 indicando sucesso.
	 */
	@Test
	public void deleteUser() {
		given().when().delete(url + "/users/5").then().statusCode(204);
	}

	/**
	 * Este caso de teste busca validar a chamada do método DELETE informando um ID
	 * de usuário que não existe. Portanto, o resultado esperado deve ser de um
	 * código de status 404 com um JSON vazio {}.
	 */
	@Test
	public void deleteUserNotFound() {
		given().when().delete(url + "/users/404").then().statusCode(404).body(containsString("{}"));
	}

	/**
	 * Este caso de teste busca validar a listagem de recursos cadastrados via
	 * chamada do método GET. A validação realizada busca identificar todos os IDs
	 * retornados no response após a requisição ser realizada.
	 */
	@Test
	public void listResource() {
		int i = 1;
		while (i <= 6) {
			given().when().get(url + "/resource").then().statusCode(200).body("data.id[" + (i - 1) + "]", equalTo(i));
			i++;
		}
	}

	/**
	 * Este caso de teste busca validar a listagem de recursos exibidos na página de
	 * acordo com a quantidade informada no parâmetro "per_page", inserida na URL de
	 * chamada da API. No retorno esperado constam as informações do número da
	 * página, quantidade de registros por página, quantidade total de registros e
	 * quantidade total de páginas de acordo com os registros cadastrados. A
	 * validação é feita no ID de cada um dos registros listados na reposta obtida.
	 */
	@SuppressWarnings({ "serial", "deprecation" })
	@Test
	public void listResourcesPageOneWithPerPageParameter() {
		Map<String, String> pagePerPage = new HashMap<String, String>() {
			{
				put("page", "1");
				put("per_page", "12");
			}
		};
		int i = 1;
		while (i <= 12) {
			given().queryParameters(pagePerPage).when().get(url + "/resource").then().statusCode(200)
					.body("data.id[" + (i - 1) + "]", equalTo(i));
			i++;
		}
	}

	/**
	 * Este caso de teste busca validar a pesquisa de um recurso informando o ID
	 * deste por meio do parâmetro enviado na URL da requisição. O retorno esperado
	 * é um status 200 indicando sucesso bem como a validação de uma das informações
	 * do recurso pesquisado.
	 */
	@Test
	public void singleResource() {
		given().queryParam("id", "2").when().get(url + "/resource").then().statusCode(200)
				.body(containsString("fuchsia rose"));
	}

	/**
	 * Este caso de teste busca validar a pesquisa de um recurso informando um ID
	 * inexistente nos registros por meio do parâmetro enviado na URL da requisição.
	 * O retorno esperado é um status 404 NOT FOUND bem como um response JSON vazio
	 * {}.
	 */
	@Test
	public void singleResourceNotFound() {
		given().queryParam("id", "900").when().get(url + "/resource").then().statusCode(404).body(containsString("{}"));
	}

	/**
	 * Este caso de teste busca validar a chamada do método UPDATE para atualizar um
	 * recurso existente. As informações a serem atualizadas são passadas como
	 * parâmetro da requisição e o retorno esperado é um código 200 indicando
	 * sucesso e a informação de data e hora da atualização realizada.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void updateResource() {
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Red Color");
		requestParams.put("year", 2000);
		requestParams.put("color", "#C74375");
		requestParams.put("pantone_value", "25-2031");

		given().body(requestParams.toJSONString()).when().put(url + "/resource/6").then().statusCode(200)
				.body(containsString("updatedAt"));
	}

	/**
	 * Este caso de teste busca validar a chamada do método UPDATE para um registro
	 * informando um ID que não existe. Portanto, o retorno esperado deve ser um
	 * código 404 e um JSON vazio {}.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void updateResourceNotFound() {
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Resource Not Found");
		requestParams.put("year", 190);
		requestParams.put("color", "yellow");
		requestParams.put("pantone_value", "12-2002");

		given().body(requestParams.toJSONString()).when().put(url + "/resource/9545").then().statusCode(404)
				.body(containsString("{}"));
	}

	/**
	 * Este caso de teste busca validar o registro de um usuario informando na
	 * requisição o e-mail e senha através da chamada do método POST. O retorno
	 * esperado é um código 200 indicando sucesso bem como o retorno de um token
	 * para validação do registro realizado.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void registerSuccessfulEmailAndPassword() {

		JSONObject requestParams = new JSONObject();
		requestParams.put("email", "eve.holt@reqres.in");
		requestParams.put("password", "pistol");

		given().contentType(ContentType.JSON).body(requestParams).when().post(url + "/register").then().statusCode(200)
				.body(containsString("token"));
	}

	/**
	 * Este caso de teste busca validar o registro de um usuario informando na
	 * requisição o usuário, e-mail e senha através da chamada do método POST. De
	 * acordo com a documentação presente em https://reqres.in/api-docs/#/ o request
	 * enviado pode conter estas três informações (username, email, password). Dessa
	 * forma, neste cenário de testes, o retorno esperado é um código 200 indicando
	 * sucesso bem como o retorno de um token para validação do registro realizado.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void registerSuccessfulUsernameEmailPassword() {

		JSONObject requestParams = new JSONObject();
		requestParams.put("username", "eveholt");
		requestParams.put("email", "eveholt@req.in");
		requestParams.put("password", "pistol");

		given().contentType(ContentType.JSON).body(requestParams).when().post(url + "/register").then().statusCode(200)
				.body(containsString("token"));
	}

	/**
	 * Este caso de teste busca validar o envio via POST de uma requisição para
	 * registro onde só é informado o email faltando a senha. O retorno esperado é
	 * de um código 400 indicando erro bem com a mensagem correspondente.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void registerErrorMissingPassword() {

		JSONObject requestParams = new JSONObject();
		requestParams.put("email", "eve.holt@reqres.in");

		given().contentType(ContentType.JSON).body(requestParams).when().post(url + "/register").then().statusCode(400)
				.body(containsString("Missing password"));
	}

	/**
	 * Este caso de teste busca validar o envio via POST de uma requisição para
	 * registro onde só é informado a senha faltando o email. O retorno esperado é
	 * de um código 400 indicando erro bem com a mensagem correspondente.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void registerErrorMissingEmailOrUsername() {

		JSONObject requestParams = new JSONObject();
		requestParams.put("password", "pistol");

		given().contentType(ContentType.JSON).body(requestParams).when().post(url + "/register").then().statusCode(400)
				.body(containsString("Missing email or username"));
	}

	/**
	 * Este caso de teste busca validar o envio via POST de uma requisição para
	 * registro onde é informado o email e a senha com valor em branco/vazio. O
	 * retorno esperado é de um código 400 indicando erro bem com a mensagem
	 * correspondente.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void registerErrorPasswordEmpty() {
		JSONObject requestParams = new JSONObject();
		requestParams.put("email", "deivid@dock.com");
		requestParams.put("password", "");

		given().contentType(ContentType.JSON).body(requestParams).when().post(url + "/register").then().statusCode(400)
				.body(containsString("Missing password"));
	}

	/**
	 * Este caso de teste busca validar o envio via POST de uma requisição para
	 * registro onde é informado o email com valor em branco/vazio e a senha. O
	 * retorno esperado é de um código 400 indicando erro bem com a mensagem
	 * correspondente.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void registerErrorEmailEmpty() {
		JSONObject requestParams = new JSONObject();
		requestParams.put("email", "");
		requestParams.put("password", "senha123");

		given().contentType(ContentType.JSON).body(requestParams).when().post(url + "/register").then().statusCode(400)
				.body(containsString("Missing email or username"));
	}

	/**
	 * Este caso de teste busca validar a chamada de login para um usuario
	 * informando na requisição o e-mail e senha através da chamada do método POST.
	 * O retorno esperado é um código 200 indicando sucesso bem como o retorno de um
	 * token para validação do login realizado.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void loginSuccessful() {

		JSONObject requestParams = new JSONObject();
		requestParams.put("email", "eve.holt@reqres.in");
		requestParams.put("password", "cityslicka");

		given().contentType(ContentType.JSON).body(requestParams).when().post(url + "/login").then().statusCode(200)
				.body(containsString("token"));
	}

	/**
	 * Este caso de teste busca validar o envio via POST de uma requisição para
	 * realização de Login onde só é informado o email faltando a senha. O retorno
	 * esperado é de um código 400 indicando erro bem com a mensagem correspondente.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void loginMissingPassword() {

		JSONObject requestParams = new JSONObject();
		requestParams.put("email", "peter@klaven");

		given().contentType(ContentType.JSON).body(requestParams).when().post(url + "/login").then().statusCode(400)
				.body(containsString("Missing password"));
	}

	/**
	 * Este caso de teste busca validar o envio via POST de uma requisição para
	 * realização de Login onde só é informado a senha faltando o email. O retorno
	 * esperado é de um código 400 indicando erro bem com a mensagem correspondente.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void loginMissingEmailOrUsername() {

		JSONObject requestParams = new JSONObject();
		requestParams.put("password", "MyPassword");

		given().contentType(ContentType.JSON).body(requestParams).when().post(url + "/login").then().statusCode(400)
				.body(containsString("Missing email or username"));
	}

	/**
	 * Este caso de teste busca validar o envio via POST de uma requisição para
	 * realização de Login onde é informado o email de um usuário que não existe ou
	 * não foi encontrado. O retorno esperado deverá ser de um código 400 e a
	 * mensagem de erro indicando que o usuário não foi encontrado.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void loginUserNotFound() {

		JSONObject requestParams = new JSONObject();
		requestParams.put("email", "eveholt@reqres.in");
		requestParams.put("password", "notfound");

		given().contentType(ContentType.JSON).body(requestParams).when().post(url + "/login").then().statusCode(400)
				.body(containsString("user not found"));
	}

	/**
	 * Este caso de teste busca validar a chamada via post da realização de logout
	 * para encerrar a sessão de um determinado usuário logado. O retorno esperado é
	 * um código 200 indicando sucesso.
	 */
	@Test
	public void logout() {

		given().contentType(ContentType.JSON).when().post(url + "/logout").then().statusCode(200);
	}

}
