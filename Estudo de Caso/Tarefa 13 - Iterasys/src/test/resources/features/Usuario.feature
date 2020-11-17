#language: pt
#encoding: utf8
  Funcionalidade: Usuario
    Criar, manter e autenticar usuarios

    Contexto:
      Dado que estou em Microsoft To Do e deslogado
      Quando clico no icone para realizar o login
      Entao exibe a pagina de login

    Cenario: Login com sucesso
      Quando preencho o email e clico em proximo
      E seleciona a conta pessoal
      E digito a senha "correta" e clico em Entrar
      Entao o site realiza o login e exibe a pagina do To Do

    Cenario: Login Incorreto
      Quando preencho o email e clico em proximo
      E seleciona a conta pessoal
      E digito a senha "incorreta" e clico em Entrar
      Entao o site exibe a mensagem de erro: Sua conta ou senha está incorreta. Se você não se lembra de sua senha, redefina-a agora.

    Cenario: Esqueci a senha
      Quando preencho o email e clico em proximo
      E seleciona a conta pessoal
      E seleciono a opcao: Esqueceu a senha?
      Entao exibe a pagina para verificacao da identidade
      Quando seleciono o meu email e seleciono a opcao Obter codigo
      Entao exibe a pagina para inserir o codigo
      Quando insiro o codigo recebido no email e seleciono a opcao Proximo
      Entao exibe a pagina de redefinicao de senha
      Quando digito a nova senha nos campos Nova senha e Redigitar a senha e seleciono a opcao Proximo
      Entao exibe a pagina de confirmacao de alteracao da senha

    Cenario: Criar conta
      Quando seleciono a opcao Crie uma!
      Entao exibe a tela para definicao de conta
      Quando preencho o campo com o email e seleciono a opcao Proximo
      Entao exibe a tela para definicao de senha
      Quando preencho o campo com a nova senha e seleciono a opcao Proximo
      Entao exibe a tela para definir nome e sobrenome
      Quando preencho os campos Nome e Sobrenome e seleciono a opcao Proximo
      Entao exibe a tela para definir Pais/Regiao e Nascimento
      Quando preencho Pais/Regiao e Dia, Mes, Ano do nascimento e seleciono a opcao Proximo
      Entao exibe a tela para verificacao de codigo
      Quando insiro o codigo recebido no email e seleciono a opcao Proximo
      Entao exibe a tela de confirmacao da criacao da conta