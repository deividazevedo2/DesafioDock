## Desafio Técnico - QA Dock
Este é o documento com o levantamento e breve descrição dos casos de testes implementados para o desafio técnico de automação nas chamadas da API https://reqres.in.


### Validar buscar usuário com sucesso - stsatusCode 200 (singleUser)
- DESCRIÇÃO: Este caso de teste busca validar a pesquisa de um usuário informando um ID de um registro existente como parâmetro. Portanto o retorno esperado deve ser um código 200 indicando sucesso e um JSON com as informações de acordo com o usuário pesquisado.

### Validar buscar usuário não encontrado - statusCode 404 (singleUserNotFound)
- DESCRIÇÃO: Este caso de teste busca validar a pesquisa de um usuário informando o ID de um registro que não existe como parâmetro. Portanto, o retorno esperado deve ser um código 404 e um JSON vazio {}.

### Validar listar usuários da primeira página com sucesso - statusCode 200 (listUsersPageOne)
- DESCRIÇÃO: Este caso de teste busca validar a listagem de usuários exibidos na página 1, conforme parâmetro informado na URL. No retorno esperado constam as informações do número da página exibida, quantidade de registros por página e quantidade total de registros.

### Validar listar usuários da segunda página com sucesso - statusCode 200 (listUsersPageTwo)
- DESCRIÇÃO: Este caso de teste busca validar a listagem de usuários exibidos na página 2, conforme parâmetro informado na URL. No retorno esperado constam as informações do número da página exibida, quantidade de registros por página e quantidade total de registros.

### Validar listar usuários informando a quantidade a ser exibida por página - statusCode 200 (listUsersPageOneWithPerPageParameter)
- DESCRIÇÃO: Este caso de teste busca validar a listagem de usuários exibidos na página de acordo com a quantidade informada no parâmetro "per_page", inserida na URL de chamada da API. No retorno esperado constam as informações do número da página, quantidade de registros por página, quantidade total de registros e quantidade total de páginas de acordo com os registros cadastrados.

### Validar criar usuário com sucesso - statusCode 200 (createUser)
- DESCRIÇÃO: Este caso de teste busca validar a criação de um registro de novo usuário de acordo com as informações passadas como parâmetro para a chamada do método. O retorno esperado informa o código 200 de sucesso bem como as informações da data de criação do registro.

### Validar atualizar informações de um usuário com sucesso - statusCode 200 (updateUser)
- DESCRIÇÃO: Este caso de teste busca validar a atualização de um registro de usuário existente através da chamada do método UPDATE. O retorno esperado é de um código 200 indicando sucesso além das informações de data e hora da atualização realizada.

### Validar atualizar informações de um usuário que não existe ou não foi encontrado - statusCode 404 (updateUserNotFound)
- DESCRIÇÃO: Este caso de teste busca validar a chamada do método UPDATE para um usuário informando um ID que não existe. Portanto, o resultado esperado deve ser o código 404 e um JSON vazio {}.

### Validar atualizar informações de um usuário através da chamada do método Patch - statusCode 200 (updateUserPatch)
- DESCRIÇÃO: Este caso de teste busca validar a atualização de um registro de usuário existente através da chamada do método PATCH. O retorno esperado é de um código 200 indicando sucesso além das informações de data e hora da atualização realizada.

### Validar atualizar informações de um usuário que não existe ou não foi encontrato através da chamada do método Patch - statusCode 404 (updateUserPatchNotFound)
- DESCRIÇÃO: Este caso de teste busca validar a chamada do método UPDATE do tipo PATCH informando um código de usuário que não existe. Portanto, o retorno esperado deve ser de um código 404 e um JSON vazio {}.

### Validar excluir um registro de usuário com sucesso - statusCode 204 (deleteUser)
- DESCRIÇÃO: Este caso de teste busca validar a exclusão de um registro de usuário através do método DELETE informando o ID do usuário a ser removido. O retorno esperado é um código 204 indicando sucesso.

### Validar excluir um registro de usuário que não existe ou não foi encontrado - statusCode 404 (deleteUserNotFound)
- DESCRIÇÃO: Este caso de teste busca validar a chamada do método DELETE informando um ID de usuário que não existe. Portanto, o resultado esperado deve ser de um código de status 404 com um JSON vazio {}

### Validar listar recursos com sucesso - statusCode 200 (listResource)
- DESCRIÇÃO: Este caso de teste busca validar a listagem de recursos cadastrados via chamada do método GET. A validação realizada busca identificar todos os IDs retornados no response após a requisição ser realizada.

### Validar listar recursos informando a quantidade a ser exibida por página - statusCode 200 (listResourcesPageOneWithPerPageParameter)
- DESCRIÇÃO: Este caso de teste busca validar a listagem de recursos exibidos na página de acordo com a quantidade informada no parâmetro "per_page", inserida na URL de chamada da API. No retorno esperado constam as informações do número da página, quantidade de registros por página, quantidade total de registros e quantidade total de páginas de acordo com os registros cadastrados. A validação é feita no ID de cada um dos registros listados na reposta obtida.

### Validar buscar recurso com sucesso - statusCode 200 (singleResource)
- DESCRIÇÃO: Este caso de teste busca validar a pesquisa de um recurso informando o ID deste por meio do parâmetro enviado na URL da requisição. O retorno esperado é um status 200 indicando sucesso bem como a validação de uma das informações do recurso pesquisado.

### Validar buscar recurso que não existe ou não foi encontrado - statusCode 404 (singleResourceNotFound)
- DESCRIÇÃO: Este caso de teste busca validar a pesquisa de um recurso informando um ID inexistente nos registros por meio do parâmetro enviado na URL da requisição. O retorno esperado é um status 404 NOT FOUND bem como um response JSON vazio {}.

### Validar atualizar informações de um recurso com sucesso - statusCode 200 (updateResource)
- DESCRIÇÃO: Este caso de teste busca validar a chamada do método UPDATE para atualizar um recurso existente. As informações a serem atualizadas são passadas como parâmetro da requisição e o retorno esperado é um código 200 indicando sucesso e a informação de data e hora da atualização realizada.

### Validar atualizar informações de um recurso que não existe ou não foi encontrado - statusCode 404 (updateResourceNotFound)
- DESCRIÇÃO: Este caso de teste busca validar a chamada do método UPDATE para um registro informando um ID que não existe. Portanto, o retorno esperado deve ser um código 404 e um JSON vazio {}.

### Validar registrar usuário com sucesso informando e-mail e senha - statusCode 200 (registerSuccessfulEmailAndPassword)
- DESCRIÇÃO: Este caso de teste busca validar o registro de um usuario informando na requisição o e-mail e senha através da chamada do método POST. O retorno esperado é um código 200 indicando sucesso bem como o retorno de um token para validação do registro realizado.

### Validar registrar usuário com sucesso informando nome, e-mail e senha - statusCode 200 (registerSuccessfulUsernameEmailPassword)
- DESCRIÇÃO: Este caso de teste busca validar o registro de um usuario informando na requisição o usuário, e-mail e senha através da chamada do método POST. De acordo com a documentação presente em https://reqres.in/api-docs/#/ o request enviado pode conter estas três informações (username, email, password). Dessa forma, neste cenário de testes, o retorno esperado é um código 200 indicando sucesso bem como o retorno de um token para validação do registro realizado.

### Validar registrar usuário informando apenas o campo e-mail - statusCode 400 (registerErrorMissingPassword)
- DESCRIÇÃO: Este caso de teste busca validar o envio via POST de uma requisição para registro onde só é informado o email faltando a senha. O retorno esperado é de um código 400 indicando erro bem com a mensagem correspondente.

### Validar registrar usuário informando apenas o campo senha - statusCode 400 (registerErrorMissingEmailOrUsername)
- DESCRIÇÃO: Este caso de teste busca validar o envio via POST de uma requisição para registro onde só é informado a senha faltando o email. O retorno esperado é de um código 400 indicando erro bem com a mensagem correspondente.

### Validar registrar usuário informando e-mail corretamente e a senha em branco - statusCode 400 (registerErrorPasswordEmpty)
- DESCRIÇÃO: Este caso de teste busca validar o envio via POST de uma requisição para registro onde é informado o email e a senha com valor em branco/vazio. O retorno esperado é de um código 400 indicando erro bem com a mensagem correspondente.

### Validar registrar usuário informando e-mail em branco e a senha corretamente - statusCode 400 (registerErrorEmailEmpty)
- DESCRIÇÃO: Este caso de teste busca validar o envio via POST de uma requisição para registro onde é informado o email com valor em branco/vazio e a senha. O retorno esperado é de um código 400 indicando erro bem com a mensagem correspondente.

### Validar realizar login com sucesso informando e-mail e senha corretamente - statusCode 200 (loginSuccessful)
- DESCRIÇÃO: Este caso de teste busca validar a chamada de login para um usuario informando na requisição o e-mail e senha através da chamada do método POST. O retorno esperado é um código 200 indicando sucesso bem como o retorno de um token para validação do login realizado.

### Validar realizar login informando apenas o campo e-mail - statusCode 400 (loginMissingPassword)
- DESCRIÇÃO: Este caso de teste busca validar o envio via POST de uma requisição para realização de Login onde só é informado o email faltando a senha. O retorno esperado é de um código 400 indicando erro bem com a mensagem correspondente.

### Validar realizar login informando apenas o campo senha - statusCode 400 (loginMissingEmailOrUsername)
- DESCRIÇÃO: Este caso de teste busca validar o envio via POST de uma requisição para realização de Login onde só é informado a senha faltando o email. O retorno esperado é de um código 400 indicando erro bem com a mensagem correspondente.

### Validar realizar login informando um e-mail de usuário que não existe ou não foi encontrado - statusCode 400 (loginUserNotFound)
- DESCRIÇÃO: Este caso de teste busca validar o envio via POST de uma requisição para realização de Login onde é informado o email de um usuário que não existe ou não foi encontrado. O retorno esperado deverá ser de um código 400 e a mensagem de erro indicando que o usuário não foi encontrado.

### Validar realizar logoff com sucesso - statusCode 200 (logout)
- DESCRIÇÃO: Este caso de teste busca validar a chamada via post da realização de logout para encerrar a sessão de um determinado usuário logado. O retorno esperado é um código 200 indicando sucesso.

