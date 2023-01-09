## Desafio Técnico - QA Dock
Este é o documento com o levantamento e descrição de Bug encontrado durante a realização do desafio técnico de automação nas chamadas da API https://reqres.in.


### Erro no retorno do statusCode ao deletar usuário informando um ID que não existe ou não foi encontrado (deleteUserNotFound)
- Pré-requisitos:
1. Deverá ser utilizado um código (id) de usuário que não existe na listagem de usuários cadastrados

- Passos para reprodução:
1. Realizar uma chamada do método DELETE 
2. Informar na URL um código de usuário que não existe ou não foi encontrado (https://reqres.in/api/users/404)
3. Enviar a requisição
4. Validar o código de retorno (statusCode)

- Resultado esperado:
1. Deverá retornar o código (statusCode) 404 indicando que não existe o registro para o código (id) informado.

Resultado obtido:
1. O response está retornando sempre o código 204, esperado para quando o registro é deletado com sucesso. 


### Erro ao tentar registrar um novo usuário informando os campos username, email e password na requisição (registerSuccessfulUsernameEmailPassword)
- Pré-requisitos:
1. N/A

- Passos para reprodução:
1. Realizar uma chamada do método POST 
2. Informar na requisição a ser feita as informações de username, email e password
3. Enviar a requisição para a URL https://reqres.in/api/register
4. Validar o código de retorno (statusCode)

- Resultado esperado:
1. De acordo com as informações contidas em https://reqres.in/api-docs/#/default/post_register deverá ser possível enviar todas estas 3 informações na requisição (username, email, password)
2. Deverá retornar o código (statusCode) 200 indicando sucesso no registro
3. O response deverá exibir a mensagem com o token gerado para o registro realizado.

Resultado obtido:
1. O response está retornando o código 400 indicando que existe erro na requisição enviada
2. A mensagem de erro que consta no response é "Note: Only defined users succeed registration" (Apenas usuários definidos tiveram sucesso no registro)


### Erro no retorno do statusCode ao atualizar recurso informando um ID que não existe ou não foi encontrado (updateResourceNotFound)
- Pré-requisitos:
1. Deverá ser utilizado um código (id) de recurso que não existe na listagem de recursos cadastrados

- Passos para reprodução:
1. Realizar uma chamada do método PUT 
2. Informar na URL um código de recurso que não existe ou não foi encontrado (https://reqres.in/api/resource/9545)
3. Enviar a requisição
4. Validar o código de retorno (statusCode)

- Resultado esperado:
1. Deverá retornar o código (statusCode) 404 indicando que não existe o registro para o código (id) informado.

Resultado obtido:
1. O response está retornando sempre o código 204, esperado para quando o registro é deletado com sucesso. 